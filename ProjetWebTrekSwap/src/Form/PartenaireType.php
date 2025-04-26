<?php

namespace App\Form;

use App\Entity\Categorie;
use App\Entity\Partenaire;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;

class PartenaireType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $categories = $options['categories']; // récupérées depuis le contrôleur

        $builder
            ->add('nom', TextType::class)
            ->add('email', EmailType::class)
            ->add('adresse', TextType::class)
            ->add('description', TextareaType::class)
            ->add('date_ajout', DateType::class, [
                'widget' => 'single_text',
            ])
            ->add('id_categorie', EntityType::class, [
                'class' => Categorie::class,
                'choices' => $categories, // 👈 on injecte les objets ici
                'choice_label' => 'nom',
                'placeholder' => 'Choisir une catégorie',
                'required' => true,
            ])

            ->add('montant', IntegerType::class, [
                'label' => 'Montant',
                'attr' => ['class' => 'form-control'],
                'required' => true,
            ]);

    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Partenaire::class,
            'categories' => [], // 👈 définir l’option personnalisée
        ]);
    }
}
