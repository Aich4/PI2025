<?php

namespace App\Controller;

use App\Form\ProfileFormType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\PasswordHasher\Hasher\UserPasswordHasherInterface;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\String\Slugger\SluggerInterface;

class ProfileController extends AbstractController
{
    #[Route('/profile', name: 'app_profile')]
    public function edit(
        Request $request,
        EntityManagerInterface $entityManager,
        UserPasswordHasherInterface $passwordHasher,
        SluggerInterface $slugger
    ): Response {
        $user = $this->getUser();
        if (!$user) {
            return $this->redirectToRoute('app_login');
        }

        $form = $this->createForm(ProfileFormType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // Handle password change if provided
            if ($newPassword = $form->get('plainPassword')->getData()) {
                $user->setPassword(
                    $passwordHasher->hashPassword($user, $newPassword)
                );
            }

            // Handle profile picture upload
            if ($photoFile = $form->get('photo_profil')->getData()) {
                $originalFilename = pathinfo($photoFile->getClientOriginalName(), PATHINFO_FILENAME);
                $safeFilename = $slugger->slug($originalFilename);
                $newFilename = $safeFilename.'-'.uniqid().'.'.$photoFile->guessExtension();

                try {
                    $photoFile->move(
                        $this->getParameter('profile_pictures_directory'),
                        $newFilename
                    );
                    $user->setPhotoProfile($newFilename);
                } catch (\Exception $e) {
                    // Handle file upload error
                }
            }

            $entityManager->flush();

            $this->addFlash('success', 'Profile updated successfully!');
            return $this->redirectToRoute('app_profile');
        }

        return $this->render('profile/edit.html.twig', [
            'profileForm' => $form->createView(),
        ]);
    }

    #[Route('/profile/delete', name: 'app_profile_delete', methods: ['POST'])]
    public function delete(Request $request, EntityManagerInterface $entityManager): Response
    {
        $user = $this->getUser();
        if (!$user) {
            return $this->redirectToRoute('app_login');
        }

        // Prevent admin account deletion
        if ($user->getTypeUser() === 'Admin') {
            $this->addFlash('error', 'Admin accounts cannot be deleted.');
            return $this->redirectToRoute('app_profile');
        }

        $submittedToken = $request->request->get('_token');
        if ($this->isCsrfTokenValid('delete-profile', $submittedToken)) {
            // Remove profile picture if exists
            if ($user->getPhotoProfile()) {
                $picturePath = $this->getParameter('profile_pictures_directory') . '/' . $user->getPhotoProfile();
                if (file_exists($picturePath)) {
                    unlink($picturePath);
                }
            }

            // Log out the user
            $this->container->get('security.token_storage')->setToken(null);
            $request->getSession()->invalidate();

            // Remove the user
            $entityManager->remove($user);
            $entityManager->flush();

            $this->addFlash('success', 'Your account has been successfully deleted.');
            return $this->redirectToRoute('app_login');
        }

        $this->addFlash('error', 'Invalid token.');
        return $this->redirectToRoute('app_profile');
    }
} 