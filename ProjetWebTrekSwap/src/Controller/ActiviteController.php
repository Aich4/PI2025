<?php

namespace App\Controller;

use App\Entity\Activite;
use App\Form\ActiviteType;
use App\Repository\ActiviteRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;
use App\Entity\Destination;
use App\Repository\DestinationRepository;

final class ActiviteController extends AbstractController
{
    private $entityManager;

    public function __construct(EntityManagerInterface $entityManager)
    {
        $this->entityManager = $entityManager;
    }
    #[Route('/activite', name: 'app_activite')]
    public function index(): Response
    {
        return $this->render('activite/index.html.twig', [
            'controller_name' => 'ActiviteController',
        ]);
    }

    #[Route('/activite/show', name: 'list_activite', methods: ['GET'])]
    public function list(ActiviteRepository $activiteRepository): Response
    {
        return $this->render('activite/list.html.twig', [
            'activites' => $activiteRepository->findAll(),
        ]);
    }

    #[Route('/activite/add', name: 'add_activite', methods: ['GET', 'POST'])]
    public function add(Request $request, EntityManagerInterface $manager, DestinationRepository $destinationRepository): Response
    {
        $activite = new Activite();
        $destinations = $destinationRepository->findAll();  // Fetch destinations

        $form = $this->createForm(ActiviteType::class, $activite, [
            'destinations' => $destinations,  // Pass destinations to the form
        ]);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $manager->persist($activite);
            $manager->flush();

            return $this->redirectToRoute('list_activite');
        }

        return $this->render('activite/add.html.twig', [
            'form' => $form->createView(),
        ]);
    }

    #[Route('/activite/edit/{id}', name: 'edit_activite', methods: ['GET', 'POST'])]
    public function edit(Activite $activite, Request $request): Response
    {
        // Create the form for editing the existing activity
        $form = $this->createForm(ActiviteType::class, $activite, [
            'destinations' => $this->entityManager->getRepository(Destination::class)->findAll(),
        ]);

        // Handle the form submission
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // Save the changes to the database
            $this->entityManager->flush();

            // Redirect after saving
            return $this->redirectToRoute('list_activite');
        }

        return $this->render('activite/edit.html.twig', [
            'form' => $form->createView(),
        ]);
    }


    #[Route('/activite/delete/{id}', name: 'delete_activite', methods: ['GET'])]
    public function delete(int $id, ActiviteRepository $activiteRepository, EntityManagerInterface $manager): Response
    {
        $activite = $activiteRepository->find($id);

        if (!$activite) {
            throw $this->createNotFoundException('Activity not found');
        }

        $manager->remove($activite);
        $manager->flush();

        return $this->redirectToRoute('list_activite'); // Redirect to the list after deletion
    }
}
