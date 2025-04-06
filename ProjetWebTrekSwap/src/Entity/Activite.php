<?php

namespace App\Entity;

use App\Repository\ActiviteRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: ActiviteRepository::class)]
class Activite
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(nullable: true)]
    #[Assert\NotNull(message: "L'ID de la destination ne peut pas être nul.")]
    private ?int $id_destination = 0;

    #[ORM\Column(length: 255)]
    #[Assert\NotBlank(message: "Le nom de l'activité ne peut pas être vide.")]
    #[Assert\Length(max: 255, maxMessage: "Le nom de l'activité ne peut pas dépasser {{ limit }} caractères.")]
    private ?string $nom_activite = null;

    #[ORM\Column(type: Types::DATE_MUTABLE)]
    #[Assert\NotNull(message: "La date de l'activité ne peut pas être nulle.")]
    #[Assert\Range(
        min: "today",
        minMessage: "La date de l'activité doit être après aujourd'hui.",
        invalidMessage: "La date doit être au format valide."
    )]
    private ?\DateTimeInterface $date = null;
    #[ORM\Column(length: 255)]
    #[Assert\NotBlank(message: "L'heure ne peut pas être vide.")]
    #[Assert\Length(max: 255, maxMessage: "L'heure ne peut pas dépasser {{ limit }} caractères.")]
    private ?string $heure = null;

    #[ORM\Column(length: 255)]
    #[Assert\NotBlank(message: "Le statut de l'activité ne peut pas être vide.")]
    #[Assert\Choice(choices: ['active', 'inactive','completed'], message: "Le statut doit être 'active' ou 'inactive' ou 'Completed' .")]
    private ?string $statut = null;
    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdDestination(): ?int
    {
        return $this->id_destination;
    }

    public function setIdDestination(?int $id_destination): void
    {
        $this->id_destination = $id_destination;
    }

    public function getNomActivite(): ?string
    {
        return $this->nom_activite;
    }

    public function setNomActivite(string $nom_activite): static
    {
        $this->nom_activite = $nom_activite;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): static
    {
        $this->date = $date;

        return $this;
    }

    public function getHeure(): ?string
    {
        return $this->heure;
    }

    public function setHeure(string $heure): static
    {
        $this->heure = $heure;

        return $this;
    }

    public function getStatut(): ?string
    {
        return $this->statut;
    }

    public function setStatut(string $statut): static
    {
        $this->statut = $statut;

        return $this;
    }
}
