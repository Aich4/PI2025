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
use App\Repository\CategorieRepository; // Correct importation du repository
use App\Entity\Categorie; // Assure-toi d'importer Categorie depuis App\Entity, pas App\Controller

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
    public function listPartenaires(
        PartenaireRepository $partenaireRepository,
        CategorieRepository $categorieRepository
    ): Response {
        $partenaires = $partenaireRepository->findAll();
        $categories = $categorieRepository->findAll();

        // On crée une map ID => Nom
        $categorieMap = [];
        foreach ($categories as $categorie) {
            $categorieMap[$categorie->getId()] = $categorie->getNom();
        }

        return $this->render('partenaire/index.html.twig', [
            'partenaires' => $partenaires,
            'categorieMap' => $categorieMap,
        ]);
    }

    #[Route('/partenaire/add', name: 'add_partenaire', methods: ['GET', 'POST'])]
    public function addPartenaire(Request $request, EntityManagerInterface $entityManager): Response
    {
        $partenaire = new Partenaire();
        $partenaire->setDateAjout(new \DateTime());

        // Récupérer les catégories depuis la base
        $conn = $entityManager->getConnection();
        $sql = 'SELECT id, nom FROM categorie';
        $stmt = $conn->prepare($sql);
        $resultSet = $stmt->executeQuery();
        $rows = $resultSet->fetchAllAssociative();

        // Transformer pour l’option 'choices'
        $categories = [];
        foreach ($rows as $row) {
            $categories[$row['nom']] = $row['id']; // "nom" affiché, "id" stocké
        }

        $form = $this->createForm(PartenaireType::class, $partenaire, [
            'categories' => $categories, // on passe les catégories au formulaire
        ]);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
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
        $partenaire = $entityManager->getRepository(Partenaire::class)->find($id);

        if (!$partenaire) {
            throw $this->createNotFoundException('Partenaire non trouvé');
        }

        // Get all categories from DB
        $categoriesEntities = $entityManager->getRepository(Categorie::class)->findAll();

        // Build array for the form (key => label, value => id)
        $categories = [];
        foreach ($categoriesEntities as $categorie) {
            $categories[$categorie->getNom()] = $categorie->getId();
        }

        // Create form and pass categories
        $form = $this->createForm(PartenaireType::class, $partenaire, [
            'categories' => $categories,
        ]);
        $form->handleRequest($request);

        if ($form->isSubmitted()) {
            if ($form->isValid()) {
                $entityManager->flush();
                $this->addFlash('success', 'Partenaire mis à jour avec succès!');
                return $this->redirectToRoute('list_partenaire');
            } else {
                $this->addFlash('error', 'Veuillez corriger les erreurs de saisie.');
            }
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
