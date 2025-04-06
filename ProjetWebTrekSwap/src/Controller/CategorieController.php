<?php

namespace App\Controller;
use Doctrine\Persistence\ManagerRegistry;

use App\Entity\Categorie;
use App\Form\CategorieType;
use App\Repository\CategorieRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use App\Repository\PartenaireRepository;



final class CategorieController extends AbstractController
{
    #[Route('/categorie', name: 'app_categorie')]
    public function index(): Response
    {
        return $this->render('categorie/index.html.twig', [
            'controller_name' => 'CategorieController',
        ]);
    }

    #[Route('/Catshow', name: 'list_category', methods: ['GET'])]
    public function listCategories(CategorieRepository $categorieRepository): Response
    {
        $categories = $categorieRepository->findAll();

        return $this->render('categorie/index.html.twig', [
            'categories' => $categories,
        ]);
    }
    #[Route('/CatshowFront', name: 'list_categoryFront', methods: ['GET'])]
    public function listCategoriesFront(CategorieRepository $categorieRepository): Response
    {
        $categories = $categorieRepository->findAll();

        return $this->render('categorie/ShowFront.html.twig', [
            'categories' => $categories,
        ]);
    }

    #[Route('/categorie/add', name: 'add_category', methods: ['GET', 'POST'])]
    public function addCategory(Request $request, EntityManagerInterface $entityManager): Response
    {
        $category = new Categorie();
        $form = $this->createForm(CategorieType::class, $category);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // Gestion de l'upload du logo
            $logoFile = $form->get('logo')->getData();

            if ($logoFile) {
                $newFilename = uniqid().'.'.$logoFile->guessExtension();
                try {
                    $logoFile->move($this->getParameter('logos_directory'), $newFilename);
                    $category->setLogo($newFilename); // Stocker le nom du fichier dans la BDD
                } catch (FileException $e) {
                    $this->addFlash('error', 'Erreur lors de l\'upload du fichier.');
                }
            }

            $entityManager->persist($category);
            $entityManager->flush();

            $this->addFlash('success', 'Catégorie ajoutée avec succès !');
            return $this->redirectToRoute('list_category');
        }

        return $this->render('categorie/addCat.html.twig', [
            'category' => $category,
            'form' => $form->createView(),
        ]);
    }

    #[Route('/Categorie/edit/{id}', name: 'edit_category', methods: ['GET', 'POST'])]
    public function edit(int $id, Request $request, EntityManagerInterface $entityManager): Response
    {
        // Récupérer la catégorie à éditer par son ID
        $category = $entityManager->getRepository(Categorie::class)->find($id);

        if (!$category) {
            throw $this->createNotFoundException('Catégorie non trouvée');
        }

        // Créer le formulaire de modification
        $form = $this->createForm(CategorieType::class, $category);
        $form->handleRequest($request);

        // Si le formulaire est soumis et valide
        if ($form->isSubmitted() && $form->isValid()) {
            // Sauvegarder les modifications dans la base de données
            $entityManager->flush();

            // Afficher un message de succès et rediriger vers la liste des catégories
            $this->addFlash('success', 'Catégorie mise à jour avec succès!');
            return $this->redirectToRoute('list_category');
        }

        // Si le formulaire n'est pas valide, afficher les erreurs
        $this->addFlash('error', 'Veuillez corriger les erreurs avant de soumettre.');

        return $this->render('categorie/editCat.html.twig', [
            'category' => $category,
            'form' => $form->createView(),
        ]);
    }



    #[Route('/Categorie/delete/{id}', name: 'delete_category', methods: ['GET'])]
    public function delete(int $id, CategorieRepository $categorieRepository, ManagerRegistry $managerRegistry): Response
    {
        // Récupérer l'EntityManager
        $em = $managerRegistry->getManager();

        // Trouver la catégorie par son ID
        $category = $categorieRepository->find($id);

        // Si la catégorie n'existe pas, afficher une erreur
        if (!$category) {
            throw $this->createNotFoundException('Catégorie non trouvée');
        }

        // Supprimer la catégorie
        $em->remove($category);
        $em->flush();

        // Rediriger vers la liste des catégories
        return $this->redirectToRoute('list_category');
    }


    #[Route('/categorie/{id}', name: 'categorie_partenaires')]
    public function partenairesParCategorie(int $id, PartenaireRepository $partenaireRepository, CategorieRepository $categorieRepository): Response
    {
        $categorie = $categorieRepository->find($id);

        // Vérifier si la catégorie existe
        if (!$categorie) {
            throw $this->createNotFoundException('La catégorie n\'existe pas.');
        }

        // Récupérer tous les partenaires liés à cette catégorie
        $partenaires = $partenaireRepository->findBy(['id_categorie' => $id]);

        return $this->render('categorie/partenaires.html.twig', [
            'categorie' => $categorie,
            'partenaires' => $partenaires,
        ]);
    }

}
