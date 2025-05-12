<?php

namespace App\Command;

use App\Entity\User;
use App\Service\BCryptPasswordHasher;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\Console\Command\Command;
use Symfony\Component\Console\Input\InputInterface;
use Symfony\Component\Console\Output\OutputInterface;
use Symfony\Component\Console\Style\SymfonyStyle;

class MigratePasswordsCommand extends Command
{
    protected static $defaultName = 'app:migrate-passwords';
    private $entityManager;
    private $bcryptHasher;

    public function __construct(EntityManagerInterface $entityManager, BCryptPasswordHasher $bcryptHasher)
    {
        parent::__construct();
        $this->entityManager = $entityManager;
        $this->bcryptHasher = $bcryptHasher;
    }

    protected function configure()
    {
        $this->setDescription('Migre les mots de passe existants vers BCrypt');
    }

    protected function execute(InputInterface $input, OutputInterface $output): int
    {
        $io = new SymfonyStyle($input, $output);
        $io->title('Migration des mots de passe vers BCrypt');

        $users = $this->entityManager->getRepository(User::class)->findAll();
        $count = 0;

        foreach ($users as $user) {
            $currentPassword = $user->getPassword();
            
            // Vérifier si le mot de passe n'est pas déjà en BCrypt avec le bon format
            if (!str_starts_with($currentPassword, '$2a$12$')) {
                // Demander le mot de passe en clair à l'utilisateur
                $io->section('Migration pour l\'utilisateur: ' . $user->getEmail());
                $plainPassword = $io->askHidden('Entrez le mot de passe en clair pour cet utilisateur');
                
                if ($plainPassword) {
                    // Hacher avec BCrypt
                    $hashedPassword = $this->bcryptHasher->hash($plainPassword);
                    $user->setPassword($hashedPassword);
                    $count++;
                }
            }
        }

        if ($count > 0) {
            $this->entityManager->flush();
            $io->success(sprintf('%d mots de passe ont été migrés vers BCrypt', $count));
        } else {
            $io->info('Aucun mot de passe à migrer');
        }

        return Command::SUCCESS;
    }
} 