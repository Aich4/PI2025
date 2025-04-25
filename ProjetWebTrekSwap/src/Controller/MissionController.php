<?php

namespace App\Controller;

use App\Entity\Mission;
use App\Form\MissionType;
use App\Repository\CategorieRepository;
use App\Repository\MissionRepository;
use Doctrine\ORM\EntityManagerInterface;
use Doctrine\Persistence\ManagerRegistry;
use Knp\Component\Pager\PaginatorInterface;
use ReCaptcha\ReCaptcha;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;
use App\Service\OcrService;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;;
use EWZ\RecaptchaBundle\Service\Recaptcha as EwzRecaptcha;





#[Route('/mission')]
final class MissionController extends AbstractController
{
  /*  #[Route(name: 'app_mission_index', methods: ['GET'])]
    public function index(MissionRepository $missionRepository): Response
    {
        return $this->render('mission/index.html.twig', [
            'missions' => $missionRepository->findAll(),
        ]);
    }*/

    #[Route('/new', name: 'app_mission_new', methods: ['GET', 'POST'])]
    public function new(
        Request $request,
        EntityManagerInterface $entityManager,
        ManagerRegistry $doctrine
    ): Response {
        $mission = new Mission();
        $form = $this->createForm(MissionType::class, $mission);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($mission);
            $entityManager->flush();

            // 🔢 Récupérer le nombre total de missions après insertion
            $totalMissions = $doctrine->getRepository(Mission::class)
                ->createQueryBuilder('m')
                ->select('COUNT(m.id)')
                ->getQuery()
                ->getSingleScalarResult();

            $missionsPerPage = 10; // même nombre que dans listeMissions()
            $lastPage = ceil($totalMissions / $missionsPerPage);

            // ✅ Rediriger vers la dernière page
            return $this->redirectToRoute('app_mission_index', ['page' => $lastPage], Response::HTTP_SEE_OTHER);
        }

        return $this->render('mission/new.html.twig', [
            'mission' => $mission,
            'form' => $form,
        ]);
    }


    #[Route('/details/{id}', name: 'app_mission_show', methods: ['GET'])]
    public function show(Mission $mission): Response
    {
        // Symfony va automatiquement récupérer la mission par son ID
        return $this->render('mission/show.html.twig', [
            'mission' => $mission
        ]);
    }


    #[Route('/{id}/edit', name: 'app_mission_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Mission $mission, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(MissionType::class, $mission);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();
            return $this->redirectToRoute('app_mission_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('mission/edit.html.twig', [
            'mission' => $mission,
            'form' => $form,
        ]);
    }


    #[Route('/mission/delete/{id}', name: 'app_mission_delete', methods: ['GET'])]
    public function delete(int $id, MissionRepository $missionRepository, ManagerRegistry $managerRegistry): Response
    {
        $em = $managerRegistry->getManager();

        $mission = $missionRepository->find($id);

        if (!$mission) {
            throw $this->createNotFoundException('Mission non trouvée');
        }

        $em->remove($mission);
        $em->flush();

        return $this->redirectToRoute('app_mission_index');
    }

    #[Route('/missions-front', name: 'app_mission_front', methods: ['GET'])]
    public function frontShow(MissionRepository $repo, CategorieRepository $categorieRepository): Response
    {
        $categories = $categorieRepository->findAll();
        return $this->render('mission/missionFront.html.twig', [
            'missions' => $repo->findAll(),
            'categories' => $categories,
        ]);
    }

    #[Route('/mission/{id}', name: 'app_mission_details', methods: ['GET'])]
    public function details(?Mission $mission): Response
    {
        if (!$mission) {
            throw $this->createNotFoundException('La mission demandée est introuvable.');
        }

        return $this->render('mission/show.html.twig', [
            'mission' => $mission
        ]);
    }



    #[Route('/mission/valider/{id}', name: 'app_mission_valider', methods: ['POST'])]
    public function validerMission(
        int $id,
        Request $request,
        MissionRepository $missionRepository,
        EntityManagerInterface $entityManager,
        OcrService $ocrService,
        EwzRecaptcha  $recaptchaService  // Injection du service Recaptcha
    ): Response {
        // 1. Récupération de la mission
        $mission = $missionRepository->find($id);
        if (!$mission) {
            throw $this->createNotFoundException('Mission introuvable');
        }

        // 2. Validation du reCAPTCHA
        $recaptchaResponse = $request->get('g-recaptcha-response');
        $recaptchaValidation = $recaptchaService->isValid($recaptchaResponse, $request->getClientIp());

        if (!$recaptchaValidation) {
            // Si la validation du reCAPTCHA échoue
            $this->addFlash('error', 'La vérification reCAPTCHA a échoué. Veuillez réessayer.');
            return $this->redirectToRoute('app_mission_front');
        }

        // 3. Vérification du fichier image
        $file = $request->files->get('preuve');
        if (!$file || !$file->isValid()) {
            $this->addFlash('error', 'Veuillez soumettre une image valide.');
            return $this->redirectToRoute('app_mission_front');
        }

        // 4. Exécution de l’OCR
        $extractedText = $ocrService->extractText($file->getPathname());

        if (!$extractedText || stripos($extractedText, 'validation mission') === false) {
            $this->addFlash('error', 'Le texte requis "validation mission" n’a pas été trouvé dans l’image.');
            return $this->redirectToRoute('app_mission_front');
        }

        // 5. Mise à jour de la mission
        $mission->setStatut('valide');
        $entityManager->flush();

        // 6. Message succès
        $this->addFlash('success', 'Validation mission réussie !');
        return $this->redirectToRoute('app_mission_front');
    }


    #[Route(name: 'app_mission_index')]
    public function listeMissions(Request $request, PaginatorInterface $paginator, ManagerRegistry $doctrine): Response
    {
        // Créer la requête pour récupérer les missions avec un tri par id croissant
        $missionsQuery = $doctrine->getRepository(Mission::class)
            ->createQueryBuilder('m')
            // Tri par id croissant
            ->orderBy('m.id', 'ASC')  // Trier par id de manière croissante
            ->getQuery();

        // Pagination des résultats (10 missions par page)
        $pagination = $paginator->paginate(
            $missionsQuery, // La requête
            $request->query->getInt('page', 1), // Le numéro de la page à afficher (par défaut 1)
            10 // Nombre de missions à afficher par page
        );

        // Retourne la vue avec les missions paginées
        return $this->render('mission/index.html.twig', [
            'pagination' => $pagination, // On passe les résultats paginés à la vue
        ]);
    }
}






