<?php

namespace App\Entity;

use App\Repository\ReponseRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: ReponseRepository::class)]
class Reponse
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    
    #[ORM\Column]
    private ?int $id_rep = null;

    #[ORM\Column]
    private ?int $id_rec = null;

    public function getIdRec(): ?int
    {
        return $this->id_rec;
    }
    public function setIdRec(int $id_rec): static
    {
        $this->id_rec = $id_rec;

        return $this;
    }


    #[ORM\Column]
    private ?\DateTimeImmutable $date_rep = null;

    #[ORM\Column(length: 255)]
    private ?string $contenu_rep = null;

    public function getId_rep(): ?int
    {
        return $this->id_rep;
    }
    

    public function getDateRep(): ?\DateTimeImmutable
    {
        return $this->date_rep;
    }

    public function setDateRep(\DateTimeImmutable $date_rep): static
    {
        $this->date_rep = $date_rep;

        return $this;
    }

    public function getContenuRep(): ?string
    {
        return $this->contenu_rep;
    }

    public function setContenuRep(string $contenu_rep): static
    {
        $this->contenu_rep = $contenu_rep;

        return $this;
    }

    
}
