<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\RegistrationFormType;
use App\Repository\CategorieRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\PasswordHasher\Hasher\UserPasswordHasherInterface;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;
use Symfony\Component\String\Slugger\SluggerInterface;
use Symfony\Component\Validator\Validator\ValidatorInterface;

class SecurityController extends AbstractController
{
    #[Route('/login', name: 'app_login')]
    public function login(AuthenticationUtils $authenticationUtils, CategorieRepository $categorieRepository): Response
    {
        $categories = $categorieRepository->findAll();
        if ($this->getUser()) {
            if ($this->isGranted('ROLE_ADMIN')) {
                $this->addFlash('info', 'Vous êtes déjà connecté en tant qu\'administrateur.');
                return $this->redirectToRoute('app_Abonnements');
            }
            $this->addFlash('info', 'Vous êtes déjà connecté.');
            return $this->redirectToRoute('app_Abonnement');
        }

        $error = $authenticationUtils->getLastAuthenticationError();
        $lastUsername = $authenticationUtils->getLastUsername();

        // Personnalisation des messages d'erreur
        $errorMessage = null;
        if ($error) {
            if ($error->getMessageKey() === 'Invalid credentials.') {
                $errorMessage = 'Identifiants invalides. Veuillez vérifier votre email et mot de passe.';
            } else {
                $errorMessage = 'Une erreur est survenue lors de la connexion.';
            }
        }

        return $this->render('security/login.html.twig', [
            'last_username' => $lastUsername,
            'error' => $errorMessage,
            'categories' => $categories,
        ]);
    }

    #[Route('/register', name: 'app_register')]
    public function register(
        Request $request, 
        UserPasswordHasherInterface $userPasswordHasher, 
        EntityManagerInterface $entityManager,
        SluggerInterface $slugger,
        CategorieRepository $categorieRepository,
        ValidatorInterface $validator
    ): Response
    {
        if ($this->getUser()) {
            $this->addFlash('info', 'Vous êtes déjà inscrit et connecté.');
            return $this->redirectToRoute('app_Abonnements');
        }

        $user = new User();
        $form = $this->createForm(RegistrationFormType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted()) {
            // Validation personnalisée
            $errors = $validator->validate($user);
            if (count($errors) > 0) {
                foreach ($errors as $error) {
                    $this->addFlash('error', $error->getMessage());
                }
            } elseif ($form->isValid()) {
                $photoFile = $form->get('photo_profil')->getData();
                if ($photoFile) {
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
                        $this->addFlash('error', 'Une erreur est survenue lors du téléchargement de la photo de profil. Veuillez réessayer.');
                        return $this->redirectToRoute('app_register');
                    }
                }

                $user->setTypeUser('Touriste');
                
                try {
                    $user->setPassword(
                        $userPasswordHasher->hashPassword(
                            $user,
                            $form->get('plainPassword')->getData()
                        )
                    );

                    $entityManager->persist($user);
                    $entityManager->flush();

                    $this->addFlash('success', 'Votre compte a été créé avec succès ! Vous pouvez maintenant vous connecter.');
                    return $this->redirectToRoute('app_login');
                } catch (\Exception $e) {
                    $this->addFlash('error', 'Une erreur est survenue lors de la création du compte. Veuillez réessayer.');
                }
            }
        }

        return $this->render('security/register.html.twig', [
            'registrationForm' => $form->createView(),
            'categories' => $categorieRepository->findAll(),
        ]);
    }

    #[Route('/logout', name: 'app_logout')]
    public function logout(): void
    {
        // Cette méthode sera interceptée par la configuration de sécurité
    }
} 