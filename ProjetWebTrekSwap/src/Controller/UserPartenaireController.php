<?php
namespace App\Controller;

use App\Entity\UserPartenaire;
use App\Form\UserPartenaireType;
use App\Repository\UserPartenaireRepository;
use App\Repository\PartenaireRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class UserPartenaireController extends AbstractController
{
    #[Route('/partenaire/{id}/avis', name: 'partenaire_avis', methods: ['POST'])]
    public function avis(
        int $id,
        Request $request,
        EntityManagerInterface $entityManager
    ): JsonResponse {
        $data = json_decode($request->getContent(), true);

        if (!$data || !isset($data['commentaire']) || empty(trim($data['commentaire']))) {
            return new JsonResponse(['success' => false, 'message' => 'Commentaire vide'], 400);
        }

        $user = $this->getUser();
        if (!$user) {
            return new JsonResponse(['success' => false, 'message' => 'Utilisateur non connecté'], 401);
        }

        $userPartenaire = new UserPartenaire();
        $userPartenaire->setUser($user);
        $userPartenaire->setPartenaireId($id);
        $userPartenaire->setCommentaire($data['commentaire']);

        $entityManager->persist($userPartenaire);
        $entityManager->flush();

        return new JsonResponse(['success' => true]);
    }

    #[Route('/partenaire/{id}/voir-avis', name: 'voir_avis', methods: ['GET'])]
    public function voirAvis(int $id, UserPartenaireRepository $userPartenaireRepository): Response
    {
        $avis = $userPartenaireRepository->findBy(['partenaireId' => $id]);

        return $this->render('user_partenaire/_list_avis.html.twig', [
            'avis' => $avis,
        ]);
    }
}