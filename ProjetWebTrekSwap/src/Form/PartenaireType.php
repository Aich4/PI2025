<?php

namespace App\Form;

use App\Entity\Partenaire;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

class PartenaireType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $categories = $options['categories']; // tableau passé depuis le contrôleur

        $builder
            ->add('nom', TextType::class)
            ->add('email', EmailType::class)
            ->add('adresse', TextType::class)
            ->add('description', TextareaType::class)
            ->add('date_ajout', DateType::class, [
                'widget' => 'single_text',
            ])
            ->add('id_categorie', ChoiceType::class, [
                'label' => 'Catégorie',
                'choices' => $categories, // injecté depuis le contrôleur
                'placeholder' => 'Choisissez une catégorie',
                'choice_label' => function ($value, $key) {
                    return $key;
                },
                'choice_value' => function ($value) {
                    return $value;
                },
            ]);
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Partenaire::class,
            'categories' => [], // option par défaut vide
        ]);
    }
}
