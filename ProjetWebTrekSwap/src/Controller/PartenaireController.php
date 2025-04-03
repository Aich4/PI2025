<?php

namespace App\Controller;

use Doctrine\Persistence\ManagerRegistry;
use App\Entity\Partenaire;
use App\Form\PartenaireType;
use App\Repository\PartenaireRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\File\Exception\FileException;

class PartenaireController extends AbstractController
{
    #[Route('/partenaire', name: 'app_partenaire')]
    public function index(): Response
    {
        return $this->render('partenaire/index.html.twig', [
            'controller_name' => 'PartenaireController',
        ]);
    }

    #[Route('/Partenaires', name: 'list_partenaire', methods: ['GET'])]
    public function listPartenaires(PartenaireRepository $partenaireRepository): Response
    {
        $partenaires = $partenaireRepository->findAll();

        return $this->render('partenaire/index.html.twig', [
            'partenaires' => $partenaires,
        ]);
    }

    #[Route('/partenaire/add', name: 'add_partenaire', methods: ['GET', 'POST'])]
    public function addPartenaire(Request $request, EntityManagerInterface $entityManager): Response
    {
        $partenaire = new Partenaire();
        $form = $this->createForm(PartenaireType::class, $partenaire);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // Définir la date d'ajout
            $partenaire->setDateAjout(new \DateTime());

            $entityManager->persist($partenaire);
            $entityManager->flush();

            $this->addFlash('success', 'Partenaire ajouté avec succès !');
            return $this->redirectToRoute('list_partenaire');
        }

        return $this->render('partenaire/addPartenaire.html.twig', [
            'form' => $form->createView(),
        ]);
    }

    #[Route('/partenaire/edit/{id}', name: 'edit_partenaire', methods: ['GET', 'POST'])]
    public function edit(int $id, Request $request, EntityManagerInterface $entityManager): Response
    {
        // Récupérer le partenaire à éditer par son ID
        $partenaire = $entityManager->getRepository(Partenaire::class)->find($id);

        if (!$partenaire) {
            throw $this->createNotFoundException('Partenaire non trouvé');
        }

        // Créer le formulaire de modification
        $form = $this->createForm(PartenaireType::class, $partenaire);
        $form->handleRequest($request);

        // Si le formulaire est soumis et valide
        if ($form->isSubmitted() && $form->isValid()) {
            // Sauvegarder les modifications dans la base de données
            $entityManager->flush();

            // Afficher un message de succès et rediriger vers la liste des partenaires
            $this->addFlash('success', 'Partenaire mis à jour avec succès!');
            return $this->redirectToRoute('list_partenaire');
        }

        return $this->render('partenaire/editPartenaire.html.twig', [
            'partenaire' => $partenaire,
            'form' => $form->createView(),
        ]);
    }

    #[Route('/partenaire/delete/{id}', name: 'delete_partenaire', methods: ['GET'])]
    public function delete(int $id, PartenaireRepository $partenaireRepository, ManagerRegistry $managerRegistry): Response
    {
        // Récupérer l'EntityManager
        $em = $managerRegistry->getManager();

        // Trouver le partenaire par son ID
        $partenaire = $partenaireRepository->find($id);

        // Si le partenaire n'existe pas, afficher une erreur
        if (!$partenaire) {
            throw $this->createNotFoundException('Partenaire non trouvé');
        }

        // Supprimer le partenaire
        $em->remove($partenaire);
        $em->flush();

        // Rediriger vers la liste des partenaires
        return $this->redirectToRoute('list_partenaire');
    }
}
