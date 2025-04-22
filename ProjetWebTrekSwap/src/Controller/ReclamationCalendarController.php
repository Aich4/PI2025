<?php

namespace App\Controller;

use App\Repository\ReclamationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Http\Attribute\IsGranted;

#[Route('/admin/reclamation')]
class ReclamationCalendarController extends AbstractController
{
    #[Route('/calendar', name: 'app_reclamation_calendar', methods: ['GET'])]
    #[IsGranted('ROLE_ADMIN')]
    public function index(ReclamationRepository $reclamationRepository): Response
    {
        return $this->render('reclamation/calendar.html.twig', [
            'reclamations' => $reclamationRepository->findAll(),
        ]);
    }

    #[Route('/calendar-events', name: 'app_reclamation_calendar_events', methods: ['GET'])]
    #[IsGranted('ROLE_ADMIN')]
    public function getCalendarEvents(ReclamationRepository $reclamationRepository): JsonResponse
    {
        $reclamations = $reclamationRepository->findAll();
        $events = [];

        foreach ($reclamations as $reclamation) {
            $events[] = [
                'id' => $reclamation->getIdRec(),
                'title' => substr($reclamation->getDescriptionRec(), 0, 30) . '...',
                'start' => $reclamation->getDateRec()->format('Y-m-d'),
                'type' => $reclamation->getTypeRec(),
                'backgroundColor' => $this->getEventColor($reclamation->getEtatRec()),
                'borderColor' => $this->getEventColor($reclamation->getEtatRec()),
                'url' => $this->generateUrl('app_reclamation_show', ['id_rec' => $reclamation->getIdRec()]),
                'extendedProps' => [
                    'etat' => $reclamation->getEtatRec(),
                    'description' => $reclamation->getDescriptionRec()
                ]
            ];
        }

        return new JsonResponse($events);
    }

    private function getEventColor(string $etat): string
    {
        return match ($etat) {
            'En cours' => '#ffc107',    // warning yellow
            'Résolue' => '#198754',     // success green
            'Rejetée' => '#dc3545',     // danger red
            default => '#6c757d'         // secondary gray
        };
    }
} 