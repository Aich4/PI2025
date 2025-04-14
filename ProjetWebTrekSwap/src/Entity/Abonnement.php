<?php

namespace App\Entity;

use App\Repository\AbonnementRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: AbonnementRepository::class)]
class Abonnement
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(type: 'integer')]
    private ?int $id_abonnement = null;

    #[ORM\Column(nullable: true)]
    private ?int $id_utilisateur = 0;

    #[ORM\Column]
    private ?int $id_Pack = null;

    #[ORM\Column(type: Types::DATE_MUTABLE)]
    #[Assert\GreaterThanOrEqual(value: "today", message: "La date de souscription ne peut pas être dans le passé.")]
    private ?\DateTimeInterface $date_Souscription = null;

    #[ORM\Column(type: Types::DATE_MUTABLE)]
    #[Assert\GreaterThan(propertyPath: "date_Souscription", message: "La date d'expiration ne peut pas être avant la date de souscription.")]
    private ?\DateTimeInterface $date_Expiration = null;

    #[ORM\Column(type: "string", length: 20)]
    private ?string $statut = null;

    public function getIdAbonnement(): ?int
    {
        return $this->id_abonnement;
    }

    public function setIdAbonnement(int $id_abonnement): static
    {
        $this->id_abonnement = $id_abonnement;

        return $this;
    }

    public function getIdUtilisateur(): ?int
    {
        return $this->id_utilisateur;
    }

    public function setIdUtilisateur(?int $id_utilisateur): static
    {
        $this->id_utilisateur = $id_utilisateur;

        return $this;
    }

    public function getIdPack(): ?int
    {
        return $this->id_Pack;
    }

    public function setIdPack(?int $id_Pack): static
    {
        $this->id_Pack = $id_Pack;

        return $this;
    }

    public function getDateSouscription(): ?\DateTimeInterface
    {
        return $this->date_Souscription;
    }

    public function setDateSouscription(?\DateTimeInterface $date_Souscription): static
    {
        $this->date_Souscription = $date_Souscription;

        return $this;
    }

    public function getDateExpiration(): ?\DateTimeInterface
    {
        return $this->date_Expiration;
    }

    public function setDateExpiration(?\DateTimeInterface $date_Expiration): static
    {
        $this->date_Expiration = $date_Expiration;

        return $this;
    }

    public function getStatut(): ?string
    {
        return $this->statut;
    }

    public function setStatut(?string $statut): static
    {
        $this->statut = $statut;

        return $this;
    }
}
