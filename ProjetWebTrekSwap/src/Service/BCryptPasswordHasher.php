<?php

namespace App\Service;

use Symfony\Component\PasswordHasher\PasswordHasherInterface;
use Symfony\Component\PasswordHasher\Hasher\PasswordHasherFactoryInterface;

class BCryptPasswordHasher implements PasswordHasherInterface
{
    private $hasherFactory;

    public function __construct(PasswordHasherFactoryInterface $hasherFactory)
    {
        $this->hasherFactory = $hasherFactory;
    }

    public function hash(string $plainPassword): string
    {
        // Générer le sel au format BCrypt
        $salt = base64_encode(random_bytes(16));
        $salt = str_replace('+', '.', $salt);
        $salt = substr($salt, 0, 22);
        
        // Construire le hash manuellement avec le format $2a$
        $hash = crypt($plainPassword, '$2a$12$' . $salt);
        
        // Vérifier que le hash commence bien par $2a$
        if (!str_starts_with($hash, '$2a$')) {
            throw new \RuntimeException('Failed to generate BCrypt hash with $2a$ prefix');
        }
        
        return $hash;
    }

    public function verify(string $hashedPassword, string $plainPassword): bool
    {
        return hash_equals($hashedPassword, crypt($plainPassword, $hashedPassword));
    }

    public function needsRehash(string $hashedPassword): bool
    {
        return !str_starts_with($hashedPassword, '$2a$12$');
    }
} 