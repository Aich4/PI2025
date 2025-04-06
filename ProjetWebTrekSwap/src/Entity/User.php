<?php

namespace App\Entity;

use App\Repository\UserRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Security\Core\User\PasswordAuthenticatedUserInterface;
use Symfony\Component\Security\Core\User\UserInterface;

#[ORM\Entity(repositoryClass: UserRepository::class)]
#[ORM\Table(name: '`user`')]
class User implements UserInterface, PasswordAuthenticatedUserInterface
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 255)]
    private ?string $nom = null;

    #[ORM\Column(length: 255)]
    private ?string $prenom = null;

    #[ORM\Column(length: 255)]
    private ?string $email = null;

    #[ORM\Column(length: 255, name: 'mot_de_passe')]
    private ?string $password = null;

    #[ORM\Column(length: 255, nullable: true)]
    private ?string $photo_profil = null;

    #[ORM\Column(length: 255)]
    private ?string $type_user = null;

    #[ORM\Column(length: 255, nullable: true)]
    private ?string $photo_carte_f1 = null;

    #[ORM\Column(length: 255, nullable: true)]
    private ?string $photo_carte_f2 = null;
    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): static
    {
        $this->nom = $nom;
        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): static
    {
        $this->prenom = $prenom;
        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): static
    {
        $this->email = $email;
        return $this;
    }

    public function getTypeUser(): ?string
    {
        return $this->type_user;
    }

    public function setTypeUser(string $type_user): static
    {
        $this->type_user = $type_user;
        return $this;
    }

    public function getPhotoProfile(): ?string
    {
        return $this->photo_profil;
    }

    public function setPhotoProfile(?string $photo_profil): static
    {
        $this->photo_profil = $photo_profil;
        return $this;
    }

    public function getPhotoCarteF1(): ?string
    {
        return $this->photo_carte_f1;
    }

    public function setPhotoCarteF1(?string $photo_carte_f1): static
    {
        $this->photo_carte_f1 = $photo_carte_f1;
        return $this;
    }

    public function getPhotoCarteF2(): ?string
    {
        return $this->photo_carte_f2;
    }

    public function setPhotoCarteF2(?string $photo_carte_f2): static
    {
        $this->photo_carte_f2 = $photo_carte_f2;
        return $this;
    }

    /**
     * A visual identifier that represents this user.
     *
     * @see UserInterface
     */
    public function getUserIdentifier(): string
    {
        return (string) $this->email;
    }

    /**
     * @see UserInterface
     * @return list<string>
     */
    public function getRoles(): array
    {
        // Convert type_user to proper Symfony role
        $role = match($this->type_user) {
            'Touriste' => 'ROLE_USER',
            'Admin' => 'ROLE_ADMIN',
            default => 'ROLE_USER'
        };
        
        return [$role];
    }

    /**
     * @see PasswordAuthenticatedUserInterface
     */
    public function getPassword(): string
    {
        return $this->password;
    }

    public function setPassword(string $password): static
    {
        $this->password = $password;
        return $this;
    }

    /**
     * @see UserInterface
     */
    public function eraseCredentials(): void
    {
        // If you store any temporary, sensitive data on the user, clear it here
    }
}
