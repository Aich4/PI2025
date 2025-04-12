<?php

namespace App\Form;

use App\Entity\Categorie;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Validator\Constraints\File;

use Symfony\Component\Validator\Constraints\NotBlank;
use Symfony\Component\Validator\Constraints\Type;

class CategorieType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nom', TextType::class, [
                'label' => 'Nom de la catégorie',
                'attr' => ['class' => 'form-control'], // Pas d'autres attributs HTML
            ])
            ->add('description', TextType::class, [
                'label' => 'Description',
                'attr' => ['class' => 'form-control'], // Pas d'autres attributs HTML
            ])
            // Ajout d'une logique pour conserver l'état du fichier
            ->add('logo', FileType::class, [
                'label' => 'Logo de la catégorie',
                'mapped' => true, // Supprimer 'mapped' => false
                'required' => false,
                'attr' => ['accept' => 'image/*'],
            ])

            // Ajout de la contrainte de validation pour le champ nbrPartenaire
            ->add('nbrPartenaire', IntegerType::class, [
                'label' => 'Nombre de partenaires',
                'attr' => ['class' => 'form-control'],
                'constraints' => [
                    new NotBlank([
                        'message' => 'Le nombre de partenaires est obligatoire.'
                    ]),
                    new Type([
                        'type' => 'integer',
                        'message' => 'Veuillez entrer un nombre valide.'
                    ])
                ]
            ]);
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Categorie::class, // Assurez-vous que le formulaire est lié à l'entité Categorie
        ]);
    }
}