<?php

namespace App\Controller;

use App\Entity\Whishlist;
use App\Repository\DestinationRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;
use App\Service\GeminiService;
final class WhishlistController extends AbstractController
{
    #[Route('/wishlist', name: 'app_whishlist')]
    public function index(EntityManagerInterface $em): Response
    {
        $user = $this->getUser();

        if (!$user) {
            $this->addFlash('error', 'Vous devez être connecté pour voir votre liste de favoris.');
            return $this->redirectToRoute('app_login');
        }

        $whishlists = $em->getRepository(Whishlist::class)->findBy([
            'user' => $user,
        ]);

        return $this->render('whishlist/index.html.twig', [
            'whishlists' => $whishlists,
        ]);
    }

    #[Route('/wishlist/add/{id}', name: 'add_to_whishlist', methods: ['POST'])]
    public function addToWhishlist(int $id, EntityManagerInterface $em, DestinationRepository $destinationRepo): Response
    {
        $destination = $destinationRepo->find($id);
        $user = $this->getUser();

        if (!$user || !$destination) {
            return $this->redirectToRoute('listFrontDestination');
        }

        // 🔥 Check if the destination is already in the user's wishlist
        $exists = $em->getRepository(Whishlist::class)->findOneBy([
            'user' => $user,
            'destination' => $destination,
        ]);

        if ($exists) {
            $this->addFlash('info', 'Cette destination est déjà dans vos favoris.');
        } else {
            $whishlist = new Whishlist();
            $whishlist->setUser($user);
            $whishlist->setDestination($destination);
            $em->persist($whishlist);
            $em->flush();

            $this->addFlash('success', 'Destination ajoutée à vos favoris !');
        }

        return $this->redirectToRoute('listFrontDestination');
    }
    #[Route('/wishlist/remove/{id}', name: 'remove_from_whishlist', methods: ['POST'])]
    public function removeFromWhishlist(int $id, EntityManagerInterface $em): Response
    {
        $whishlist = $em->getRepository(Whishlist::class)->find($id);

        if (!$whishlist || $whishlist->getUser() !== $this->getUser()) {
            $this->addFlash('error', 'Action non autorisée.');
            return $this->redirectToRoute('app_whishlist');
        }

        $em->remove($whishlist);
        $em->flush();

        $this->addFlash('success', 'Destination supprimée de vos favoris.');

        return $this->redirectToRoute('app_whishlist');
    }
    #[Route('/generate-plan', name: 'generate_plan', methods: ['POST'])]
    public function generatePlan(EntityManagerInterface $em, GeminiService $geminiService): Response
    {
        $user = $this->getUser();

        if (!$user) {
            $this->addFlash('error', 'Vous devez être connecté pour générer un plan.');
            return $this->redirectToRoute('app_login');
        }

        // Fetch all wishlist entries for the user
        $whishlists = $em->getRepository(\App\Entity\Whishlist::class)->findBy([
            'user' => $user,
        ]);

        if (empty($whishlists)) {
            $this->addFlash('info', 'Votre liste de favoris est vide.');
            return $this->redirectToRoute('app_whishlist');
        }

        // Build a prompt from the list of destination names
        $destinationsList = array_map(function($w) {
            return $w->getDestination()->getNomDestination();
        }, $whishlists);

        $prompt = "Je veux visiter la Tunisie. Voici mes destinations : " . implode(', ', $destinationsList) . ". Peux-tu me proposer un plan de voyage détaillé de plusieurs jours, en expliquant quoi visiter dans chaque endroit ?";

        // Ask Gemini to generate the travel plan
        $plan = $geminiService->generateContent($prompt);

        // Render the plan in a new template
        return $this->render('whishlist/plan.html.twig', [
            'plan' => $plan,
        ]);
    }

}
