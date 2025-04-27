<?php

namespace App\Controller;

use App\Entity\Reclamation;
use App\Form\ReclamationType;
use App\Repository\CategorieRepository;
use App\Repository\ReclamationRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;
use Symfony\Component\Security\Http\Attribute\IsGranted;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Notifier\Message\SmsMessage;
use Symfony\Component\Notifier\TexterInterface;
use libphonenumber\PhoneNumberUtil;
use libphonenumber\PhoneNumberFormat;
use libphonenumber\NumberParseException;

#[Route('/reclamation')]
final class ReclamationController extends AbstractController {
    #[Route('/', name: 'app_reclamation_index', methods: ['GET'])]
    public function index(ReclamationRepository $reclamationRepository): Response
    {
        return $this->render('reclamation/index.html.twig', [
            'reclamations' => $reclamationRepository->findAll(),
        ]);
    }

    // Front office routes
#[Route('/new', name: 'app_reclamation_new', methods: ['GET', 'POST'])]
public function new(
    Request $request,
    EntityManagerInterface $entityManager,
    CategorieRepository $categorieRepository,
    ReclamationRepository $reclamationRepository,
    TexterInterface $texter
): Response {
    $reclamation = new Reclamation();
    $form = $this->createForm(ReclamationType::class, $reclamation);
    $form->handleRequest($request);
    $categories = $categorieRepository->findAll();

    // Initialize debug info with all possible keys
    $debugInfo = [
        'sms_status' => 'not_attempted',
        'phone_number' =>'+17756557047', // or get from form/user
        'message_content' => null,
        'error' => null,
        'message_id' => null,
        'timestamp' => date('Y-m-d H:i:s')
    ];

    if ($form->isSubmitted() && $form->isValid()) {
        $entityManager->persist($reclamation);
        $entityManager->flush();

        try {
            // Prepare SMS
            $debugInfo['message_content'] = "Votre réclamation (#{$reclamation->getIdRec()}) a été enregistrée.";
            $sms = new SmsMessage(
                $debugInfo['phone_number'],
                $debugInfo['message_content']
            );

            // Send SMS
            $sentMessage = $texter->send($sms);
            
            if ($sentMessage) {
                $debugInfo['sms_status'] = 'success';
                $debugInfo['message_id'] = $sentMessage->getMessageId();
                $this->addFlash('success', 'Réclamation enregistrée. SMS envoyé!');
            } else {
                $debugInfo['sms_status'] = 'failed';
                $debugInfo['error'] = 'No response from SMS provider';
                $this->addFlash('warning', 'Réclamation enregistrée, mais pas de confirmation SMS.');
            }
        } catch (\Exception $e) {
            $debugInfo['sms_status'] = 'error';
            $debugInfo['error'] = $e->getMessage();
            $this->addFlash('warning', 'Réclamation enregistrée, mais erreur d\'envoi SMS.');
        }

        // Store debug info in session
        $request->getSession()->set('sms_debug_info', $debugInfo);
        return $this->redirectToRoute('app_reclamation_new');
    }

    // Safely get debug info from session
    $sessionDebugInfo = $request->getSession()->get('sms_debug_info', []);
    $debugInfo = array_merge([
        'sms_status' => 'not_attempted',
        'phone_number' => null,
        'message_content' => null,
        'error' => null,
        'message_id' => null,
        'timestamp' => null
    ], $sessionDebugInfo);
    
    $request->getSession()->remove('sms_debug_info');

    return $this->render('reclamation/new.html.twig', [
        'reclamation' => $reclamation,
        'form' => $form,
        'reclamations' => $reclamationRepository->findAll(),
        'categories' => $categories,
        'debug_info' => $debugInfo
    ]);
}

    private function renderWithDebug(array $debugOutput, $reclamation, $form, $reclamationRepository, $categories): Response
    {
        return $this->render('reclamation/new.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form,
            'reclamations' => $reclamationRepository->findAll(),
            'categories' => $categories,
            'debug_output' => $debugOutput
        ]);
    }

    // Back office routes
    #[Route('/back', name: 'reclamationBack', methods: ['GET'])]
    #[IsGranted('ROLE_ADMIN')]
    public function showback(Request $request, ReclamationRepository $reclamationRepository): Response
    {
        // Récupérer les paramètres de tri et de filtrage
        $sort = $request->query->get('sort', 'dateRec');
        $direction = $request->query->get('direction', 'DESC');
        $typeFilter = $request->query->get('type');
        $etatFilter = $request->query->get('etat');
        $searchQuery = $request->query->get('search');

        // Créer le QueryBuilder
        $queryBuilder = $reclamationRepository->createQueryBuilder('r');

        // Appliquer les filtres
        if ($typeFilter) {
            $queryBuilder->andWhere('r.typeRec = :type')
                        ->setParameter('type', $typeFilter);
        }
        if ($etatFilter) {
            $queryBuilder->andWhere('r.etatRec = :etat')
                        ->setParameter('etat', $etatFilter);
        }
        if ($searchQuery) {
            $queryBuilder->andWhere('(r.descriptionRec LIKE :search OR r.typeRec LIKE :search OR r.etatRec LIKE :search)')
                        ->setParameter('search', '%' . $searchQuery . '%');
        }

        // Appliquer le tri
        $queryBuilder->orderBy('r.' . $sort, $direction);

        // Exécuter la requête
        $reclamations = $queryBuilder->getQuery()->getResult();

        $events = [];
        foreach ($reclamations as $reclamation) {
            $events[] = [
                'id' => $reclamation->getIdRec(),
                'title' => substr($reclamation->getDescriptionRec(), 0, 30) . '...',
                'start' => $reclamation->getDateRec()->format('Y-m-d'),
                'type' => $reclamation->getTypeRec(),
                'backgroundColor' => $this->getEventColor($reclamation->getEtatRec()),
                'borderColor' => $this->getEventColor($reclamation->getEtatRec()),
            ];
        }

        // Récupérer les valeurs uniques pour les filtres
        $types = $reclamationRepository->createQueryBuilder('r')
            ->select('DISTINCT r.typeRec')
            ->getQuery()
            ->getResult();
        
        $etats = $reclamationRepository->createQueryBuilder('r')
            ->select('DISTINCT r.etatRec')
            ->getQuery()
            ->getResult();

        // Calculer les statistiques
        $stats = [
            'total' => count($reclamations),
            'enCours' => 0,
            'resolues' => 0,
            'rejetees' => 0,
            'parType' => [],
            'parMois' => []
        ];

        $moisActuel = new \DateTime();
        $moisActuel->modify('first day of this month');
        $stats['moisActuel'] = 0;

        foreach ($reclamations as $reclamation) {
            // Compter par état
            switch ($reclamation->getEtatRec()) {
                case 'En cours':
                    $stats['enCours']++;
                    break;
                case 'Résolue':
                    $stats['resolues']++;
                    break;
                case 'Rejetée':
                    $stats['rejetees']++;
                    break;
            }

            // Compter par type
            $type = $reclamation->getTypeRec();
            if (!isset($stats['parType'][$type])) {
                $stats['parType'][$type] = 0;
            }
            $stats['parType'][$type]++;

            // Compter par mois
            $moisReclamation = $reclamation->getDateRec()->format('Y-m');
            if (!isset($stats['parMois'][$moisReclamation])) {
                $stats['parMois'][$moisReclamation] = 0;
            }
            $stats['parMois'][$moisReclamation]++;

            // Compter pour le mois actuel
            if ($reclamation->getDateRec() >= $moisActuel) {
                $stats['moisActuel']++;
            }
        }

        // Trier les statistiques par mois
        krsort($stats['parMois']);
        $stats['parMois'] = array_slice($stats['parMois'], 0, 6, true);

        return $this->render('reclamation/back.html.twig', [
            'reclamations' => $reclamations,
            'reclamations_events' => $events,
            'types' => array_column($types, 'typeRec'),
            'etats' => array_column($etats, 'etatRec'),
            'currentSort' => $sort,
            'currentDirection' => $direction,
            'currentType' => $typeFilter,
            'currentEtat' => $etatFilter,
            'searchQuery' => $searchQuery,
            'stats' => $stats
        ]);
    }

    #[Route('/back/{id_rec}', name: 'app_reclamation_show', methods: ['GET'])]
    #[IsGranted('ROLE_ADMIN')]
    public function show(ReclamationRepository $reclamationRepository, string $id_rec): Response
    {
        $reclamation = $reclamationRepository->findOneBy(['idRec' => (int)$id_rec]);
        
        if (!$reclamation) {
            throw $this->createNotFoundException('La réclamation n\'existe pas');
        }

        return $this->render('reclamation/show.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }

    #[Route('/back/{id_rec}/edit', name: 'app_reclamation_edit', methods: ['GET', 'POST'])]
    #[IsGranted('ROLE_ADMIN')]
    public function edit(Request $request, ReclamationRepository $reclamationRepository, int $id_rec, EntityManagerInterface $entityManager): Response
    {
        $reclamation = $reclamationRepository->findOneBy(['idRec' => $id_rec]);
        
        if (!$reclamation) {
            throw $this->createNotFoundException('La réclamation n\'existe pas');
        }

        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            $this->addFlash('success', 'La réclamation a été modifiée avec succès.');
            return $this->redirectToRoute('reclamationBack', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reclamation/edit.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form,
        ]);
    }

    #[Route('/back/{id_rec}', name: 'app_reclamation_delete', methods: ['POST'])]
    #[IsGranted('ROLE_ADMIN')]
    public function delete(Request $request, ReclamationRepository $reclamationRepository, int $id_rec, EntityManagerInterface $entityManager): Response
    {
        $reclamation = $reclamationRepository->findOneBy(['idRec' => $id_rec]);
        
        if (!$reclamation) {
            throw $this->createNotFoundException('La réclamation n\'existe pas');
        }

        if ($this->isCsrfTokenValid('delete'.$reclamation->getIdRec(), $request->getPayload()->getString('_token'))) {
            $entityManager->remove($reclamation);
            $entityManager->flush();
            $this->addFlash('success', 'La réclamation a été supprimée avec succès.');
        }

        return $this->redirectToRoute('reclamationBack', [], Response::HTTP_SEE_OTHER);
    }

    #[Route('/back/calendar', name: 'app_reclamation_calendar', methods: ['GET'])]
    #[IsGranted('ROLE_ADMIN')]
    public function calendar(ReclamationRepository $reclamationRepository): Response
    {
        $reclamations = $reclamationRepository->findAll();
        $events = [];

        foreach ($reclamations as $reclamation) {
            $events[] = [
                'title' => $reclamation->getTypeRec(),
                'start' => $reclamation->getDateRec()->format('Y-m-d H:i:s'),
                'description' => $reclamation->getDescriptionRec(),
                'type' => $reclamation->getTypeRec(),
                'etat' => $reclamation->getEtatRec(),
                'backgroundColor' => $this->getEventColor($reclamation->getEtatRec()),
                'borderColor' => $this->getEventColor($reclamation->getEtatRec()),
                'textColor' => '#ffffff'
            ];
        }

        return $this->render('reclamation/calendar_view.html.twig', [
            'reclamations' => $events
        ]);
    }

    private function getEventColor(string $etat): string
    {
        return match($etat) {
            'En cours' => '#ffc107',
            'Résolue' => '#28a745',
            'Rejetée' => '#dc3545',
            default => '#6c757d'
        };
    }

    #[Route('/reclamations/by-date', name: 'reclamation_by_date', methods: ['GET'])]
    public function reclamationsByDate(Request $request, ReclamationRepository $repo): JsonResponse
    {
        $dateString = $request->query->get('date'); // format: YYYY-MM-DD

        if (!$dateString) {
            return new JsonResponse(['error' => 'Missing date'], 400);
        }

        $date = \DateTime::createFromFormat('Y-m-d', $dateString);

        if (!$date) {
            return new JsonResponse(['error' => 'Invalid date'], 400);
        }

        $reclamations = $repo->findByDate($date);

        $data = array_map(fn($rec) => [
            'id' => $rec->getIdRec(),
            'description' => $rec->getDescriptionRec(),
            'type' => $rec->getTypeRec(),
            'date' => $rec->getDateRec()->format('Y-m-d H:i:s'),
        ], $reclamations);

        return new JsonResponse($data);
    }
    #[Route('/search', name: 'reclamation_search')]
    public function search(Request $request, NormalizerInterface $normalizer, ReclamationRepository $reclamationRepository): JsonResponse
    {
        $searchValue = $request->get('searchValue');
        
        $reclamations = $reclamationRepository->findReclamationByDescriptionRec($searchValue);

        $jsonContent = $normalizer->normalize($reclamations, 'json', ['groups' => 'reclamations']);
        
        return new JsonResponse($jsonContent);
    }

}
