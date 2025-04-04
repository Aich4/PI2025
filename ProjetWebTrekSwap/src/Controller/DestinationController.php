<?php

namespace App\Controller;

use App\Entity\Destination;
use App\Form\DestinationType;
use App\Repository\DestinationRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;
use Symfony\Component\Validator\Validator\ValidatorInterface;

final class DestinationController extends AbstractController
{
    #[Route('/destination', name: 'app_destination')]
    public function index(): Response
    {
        return $this->render('destination/index.html.twig', [
            'controller_name' => 'DestinationController',
        ]);
    }

    #[Route('/DestinationBackShow', name: 'list_destination', methods: ['GET'])]
    public function list_Abonnement(DestinationRepository $destinationRepository): Response
    {
        return $this->render('destination/index.html.twig', [
            'destinations' => $destinationRepository->findAll(),
        ]);
    }

    #[Route('/addDestination', name: 'addDestination', methods: ['GET', 'POST'])]
    public function add(Request $request, EntityManagerInterface $manager, ValidatorInterface $validator): Response
    {
        $destination = new Destination();
        $form = $this->createForm(DestinationType::class, $destination);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // Handle file upload
            $imageFile = $form->get('imageDestination')->getData();

            if ($imageFile) {
                // Generate a unique filename
                $newFilename = uniqid().'.'.$imageFile->guessExtension();

                // Move the file to the uploads directory
                $imageFile->move(
                    $this->getParameter('uploads_directory'), // Defined in services.yaml
                    $newFilename
                );

                // Store the file path in the entity
                $destination->setImageDestination('/uploads/'.$newFilename);
            }

            // Validate the entity
            $errors = $validator->validate($destination);

            if (count($errors) > 0) {
                // If there are validation errors, display them to the user
                return $this->render('destination/addDest.html.twig', [
                    'form' => $form->createView(),
                    'errors' => $errors
                ]);
            }

            // If no errors, persist and flush the entity
            $manager->persist($destination);
            $manager->flush();

            return $this->redirectToRoute('addDestination');
        }

        return $this->render('destination/addDest.html.twig', [
            'form' => $form->createView(),
        ]);
    }

    #[Route('/editDestination/{id}', name: 'editDest', methods: ['GET', 'POST'])]
    public function edit(int $id, Request $request, EntityManagerInterface $manager, ValidatorInterface $validator): Response
    {
        $destination = $manager->getRepository(Destination::class)->find($id);

        if (!$destination) {
            throw $this->createNotFoundException('Destination not found');
        }

        $form = $this->createForm(DestinationType::class, $destination);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // Handle file upload if the image is updated
            $imageFile = $form->get('imageDestination')->getData();

            if ($imageFile) {
                // Generate a unique filename
                $newFilename = uniqid().'.'.$imageFile->guessExtension();

                // Move the file to the uploads directory
                $imageFile->move(
                    $this->getParameter('uploads_directory'),
                    $newFilename
                );

                // Store the file path in the entity
                $destination->setImageDestination('/uploads/'.$newFilename);
            }

            // Validate the entity
            $errors = $validator->validate($destination);

            if (count($errors) > 0) {
                // If there are validation errors, display them to the user
                return $this->render('destination/editDest.html.twig', [
                    'form' => $form->createView(),
                    'destination' => $destination,
                    'errors' => $errors
                ]);
            }

            // If no errors, flush the entity
            $manager->flush();

            return $this->redirectToRoute('list_destination'); // Redirect to the list after edit
        }

        return $this->render('destination/editDest.html.twig', [
            'form' => $form->createView(),
            'destination' => $destination
        ]);
    }


    #[Route('/deleteDestination/{id}', name: 'deleteDest', methods: ['GET'])]
    public function delete(int $id, DestinationRepository $destinationRepository, EntityManagerInterface $manager): Response
    {
        $destination = $destinationRepository->find($id);

        if (!$destination) {
            throw $this->createNotFoundException('Destination not found');
        }

        $manager->remove($destination);
        $manager->flush();

        return $this->redirectToRoute('list_destination'); // Redirect to the list after deletion
    }
}
