<?php

namespace App\Controller;
use App\Entity\Pack;
use App\Entity\Abonnement;
use App\Form\AbonnementType;
use App\Repository\AbonnementRepository;
use App\Repository\PackRepository;
use App\Repository\CategorieRepository;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;
use Doctrine\ORM\EntityManagerInterface;

final class AbonnementController extends AbstractController
{
    #[Route('/pricing', name: 'app_Abonnement')]
    public function indexA(): Response
    {
        return $this->redirectToRoute('pricing');
    }
    #[Route('/frontA', name: 'pricing')]
    public function pricing(AbonnementRepository $abonnementRepository, PackRepository $packRepository, CategorieRepository $categorieRepository): Response
    {
        $abonnements = $abonnementRepository->findAll();
        $categories = $categorieRepository->findAll();
        return $this->render('base.html.twig', [
            'abonnements' => $abonnements,
            'packRepository' => $packRepository,
            'categories' => $categories,
        ]);
    }

    #[Route('/backA', name: 'app_Abonnements')]
    public function indexBA(AbonnementRepository $abonnementRepository): Response
    {
        return $this->render('base-back.html.twig', [
            'Abonnements' => $abonnementRepository->findAll(),
        ]);
    }

    #[Route('/Abonshow', name: 'list_Abonnement', methods: ['GET'])]
    public function listAbonnements(AbonnementRepository $abonnementRepository): Response
    {
        $abonnements = $abonnementRepository->findAll();

        return $this->render('abonnements/showAbon.html.twig', [
            'abonnements' => $abonnements,
        ]);
    }
    #[Route('/AbonshowF', name: 'list_AbonnementF', methods: ['GET'])]
    public function listAbonnementF(AbonnementRepository $abonnementRepository): Response
    {
        $abonnements = $abonnementRepository->findAll();

        return $this->render('abonnements/showfrontA.html.twig', [
            'abonnements' => $abonnements,
        ]);
    }

    #[Route('/Abonadd', name: 'add_Abonnement', methods: ['GET', 'POST'])]
    public function add(Request $request, EntityManagerInterface $managerRegistry): Response
    {
        $abonnement = new Abonnement();
    
    // Fetch only the integer IDs of all packs
    $packIds = $managerRegistry->getRepository(Pack::class)->getAllPackIds();
    
    // Pass the integer pack IDs to the form
    $form = $this->createForm(AbonnementType::class, $abonnement, [
        'pack_ids' => $packIds
    ]);

    $form->handleRequest($request);

    if ($form->isSubmitted() && $form->isValid()) {
        $managerRegistry->persist($abonnement);
        $managerRegistry->flush();

        return $this->redirectToRoute('list_Abonnement');
    }

    return $this->render('abonnements/addAbon.html.twig', [
        'abonnement' => $abonnement,
        'form' => $form->createView(),
    ]);
    }

    #[Route('/Abonedit/{id_abonnement}', name: 'edit_Abonnement', methods: ['GET', 'POST'])]
public function edit(int $id_abonnement, Request $request, EntityManagerInterface $managerRegistry): Response
{
    $abonnement = $managerRegistry->getRepository(Abonnement::class)->find($id_abonnement);
    
    if (!$abonnement) {
        throw $this->createNotFoundException('Abonnement not found');
    }

    $packIds = $managerRegistry->getRepository(Pack::class)->getAllPackIds();
    
    $form = $this->createForm(AbonnementType::class, $abonnement, [
        'pack_ids' => $packIds
    ]);

    $form->handleRequest($request);

    if ($form->isSubmitted() && $form->isValid()) {
        $managerRegistry->flush();

        return $this->redirectToRoute('list_Abonnement'); 
    }

    return $this->render('abonnements/editAbon.html.twig', [
        'abonnement' => $abonnement,
        'form' => $form->createView(),
    ]);
}


    #[Route('/Abondelete/{id_abonnement}', name: 'delete_Abonnement', methods: ['GET'])]
    public function delete(int $id_abonnement, AbonnementRepository $abonnementRepository, ManagerRegistry $managerRegistry): Response
    {
        $em = $managerRegistry->getManager();
        $abonnement = $abonnementRepository->find($id_abonnement);

        if (!$abonnement) {
            throw $this->createNotFoundException('Abonnement non trouvé');
        }

        $em->remove($abonnement);
        $em->flush();

        return $this->redirectToRoute('list_Abonnement');
    }
}
