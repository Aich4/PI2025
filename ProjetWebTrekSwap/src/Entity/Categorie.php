<?php

namespace App\Entity;

use App\Repository\CategorieRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: CategorieRepository::class)]
class Categorie
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 255)]
    #[Assert\NotBlank(message: "Le nom ne doit pas être vide.")]
    #[Assert\Regex(
        pattern: "/^(?!\d+$).*$/", // Le nom ne peut pas être uniquement composé de chiffres
        message: "Le nom ne peut pas être composé uniquement de chiffres."
    )]
    private ?string $nom = null;

    #[ORM\Column(length: 255)]
    #[Assert\NotBlank(message: "La description ne doit pas être vide.")]
    #[Assert\Regex(
        pattern: "/^(?!\d+$).*$/", // La description ne peut pas être uniquement composée de chiffres
        message: "La description ne peut pas être uniquement composée de chiffres."
    )]
    #[Assert\Length(
        min: 4,
        max: 500,
        minMessage: "La description doit comporter au moins {{ limit }} caractères.",
        maxMessage: "La description ne peut pas dépasser {{ limit }} caractères."
    )]
    private ?string $description = null;

    #[ORM\Column(type: "string", length: 255, nullable: true)]
    #[Assert\NotBlank(message: "Le logo ne doit pas être vide.")]
    #[Assert\File(
        mimeTypes: ["image/jpeg", "image/png", "image/webp"],
        mimeTypesMessage: "Veuillez télécharger un fichier image valide (JPEG, PNG, WebP)."
    )]
    private ?string $logo = null;

    #[ORM\Column]
    #[Assert\NotBlank(message: "Le nombre de partenaires ne doit pas être vide.")]
    #[Assert\GreaterThanOrEqual(
        value: 0,
        message: "Le nombre de partenaires doit être un nombre positif ou zéro."
    )]
    private ?int $nbr_partenaire = null;
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

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): static
    {
        $this->description = $description;
        return $this;
    }

    public function getLogo(): ?string
    {
        return $this->logo;
    }

    public function setLogo(?string $logo): static
    {
        $this->logo = $logo;
        return $this;
    }

    public function getNbrPartenaire(): ?int
    {
        return $this->nbr_partenaire;
    }

    public function setNbrPartenaire(int $nbr_partenaire): static
    {
        $this->nbr_partenaire = $nbr_partenaire;
        return $this;
    }
}
