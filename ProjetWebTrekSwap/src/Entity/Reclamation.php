<?php

namespace App\Entity;

use App\Repository\ReclamationRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: ReclamationRepository::class)]
class Reclamation
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id_rec = null;

    #[ORM\Column(length: 255)]
    private ?string $description_rec = null;

    #[ORM\Column]
    private ?\DateTimeImmutable $date_rec = null;

    #[ORM\Column(length: 255)]
    private ?string $type_rec = null;

    #[ORM\Column(length: 255)]
    private ?string $etat_rec = null;

    public function getIdrec(): ?int
    {
        return $this->id_rec;
    }

    public function getDescriptionRec(): ?string
    {
        return $this->description_rec;
    }

    public function setDescriptionRec(string $description_rec): static
    {
        $this->description_rec = $description_rec;

        return $this;
    }

    public function getDateRec(): ?\DateTimeImmutable
    {
        return $this->date_rec;
    }

    public function setDateRec(\DateTimeImmutable $date_rec): static
    {
        $this->date_rec = $date_rec;

        return $this;
    }

    public function getTypeRec(): ?string
    {
        return $this->type_rec;
    }

    public function setTypeRec(string $type_rec): static
    {
        $this->type_rec = $type_rec;

        return $this;
    }

    public function getEtatRec(): ?string
    {
        return $this->etat_rec;
    }

    public function setEtatRec(string $etat_rec): static
    {
        $this->etat_rec = $etat_rec;

        return $this;
    }
}
