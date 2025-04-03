<?php

namespace App\Controller;

use App\Repository\UserRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/admin')]
class AdminController extends AbstractController
{
    #[Route('', name: 'app_admin')]
    public function index(UserRepository $userRepository): Response
    {
        // Only allow access to users with ROLE_ADMIN
        $this->denyAccessUnlessGranted('ROLE_ADMIN');

        // Get all users except admin
        $users = $userRepository->findAll();

        return $this->render('admin/index.html.twig', [
            'users' => $users
        ]);
    }
} 