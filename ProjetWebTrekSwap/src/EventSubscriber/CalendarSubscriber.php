<?php

namespace App\EventSubscriber;

use App\Entity\Reclamation;
use App\Repository\ReclamationRepository;
use CalendarBundle\CalendarEvents;
use CalendarBundle\Entity\Event;
use CalendarBundle\Event\CalendarEvent;
use Symfony\Component\EventDispatcher\EventSubscriberInterface;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;

class CalendarSubscriber implements EventSubscriberInterface
{
    private $reclamationRepository;
    private $router;

    public function __construct(
        ReclamationRepository $reclamationRepository,
        UrlGeneratorInterface $router
    ) {
        $this->reclamationRepository = $reclamationRepository;
        $this->router = $router;
    }

    public static function getSubscribedEvents()
    {
        return [
            CalendarEvents::SET_DATA => 'onCalendarSetData',
        ];
    }

    public function onCalendarSetData(CalendarEvent $event)
    {
        $start = $event->getStart();
        $end = $event->getEnd();
        $filters = $event->getFilters();

        // Récupérer toutes les réclamations
        $reclamations = $this->reclamationRepository->findAll();

        foreach ($reclamations as $reclamation) {
            // Créer un événement pour chaque réclamation
            $calendarEvent = new Event(
                $reclamation->getDescriptionRec(),
                $reclamation->getDateRec(),
            );

            // Personnaliser l'événement avec des données supplémentaires
            $calendarEvent->setOptions([
                'backgroundColor' => $this->getColorForState($reclamation->getEtatRec()),
                'borderColor' => $this->getColorForState($reclamation->getEtatRec()),
                'textColor' => 'white',
            ]);

            // Ajouter des données personnalisées pour l'affichage
            $calendarEvent->addOption(
                'extendedProps', [
                    'description' => $reclamation->getDescriptionRec(),
                    'type' => $reclamation->getTypeRec(),
                    'etat' => $reclamation->getEtatRec(),
                ]
            );

            // Ajouter une URL pour le clic sur l'événement
            $calendarEvent->setUrl(
                $this->router->generate('app_reclamation_show', [
                    'id_rec' => $reclamation->getIdRec(),
                ])
            );

            // Ajouter l'événement au calendrier
            $event->addEvent($calendarEvent);
        }
    }

    /**
     * Obtenir une couleur en fonction de l'état de la réclamation
     */
    private function getColorForState(string $state): string
    {
        switch ($state) {
            case 'En cours':
                return '#f6c23e'; // jaune
            case 'Résolue':
                return '#1cc88a'; // vert
            case 'Rejetée':
                return '#e74a3b'; // rouge
            default:
                return '#4e73df'; // bleu
        }
   }
}