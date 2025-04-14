<?php

namespace App\Form;

use App\Entity\Abonnement;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Validator\Constraints\NotBlank;

class AbonnementType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $packIds = $options['pack_ids']; // Get pack IDs from controller

        $builder
            ->add('id_pack', ChoiceType::class, [
                'choices' => array_combine($packIds, $packIds), 
                'placeholder' => 'Sélectionnez un Pack',
                'attr' => ['class' => 'form-control']
            ])
            ->add('date_Souscription', DateType::class, [
                'widget' => 'single_text',
                'required' => true,
                'label' => 'Date de souscription',
                'constraints' => [
                    new NotBlank([
                        'message' => 'Obligatoire',
                    ]),
                ],
            ])
            ->add('date_Expiration', DateType::class, [
                'widget' => 'single_text',
                'required' => true,
                'label' => 'Date d\'expiration',
                'constraints' => [
                    new NotBlank([
                        'message' => 'Obligatoire',
                    ]),
                ],
            ])
            ->add('statut', ChoiceType::class, [
                'choices' => [
                    'Actif' => 'actif',
                    'Inactif' => 'inactif',
                    'Suspendu' => 'suspendu'
                ],
                'placeholder' => 'Sélectionnez un Statut',
                'attr' => ['class' => 'form-control']
            ]);
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Abonnement::class,
            'attr' => ['novalidate' => 'novalidate'],
            'pack_ids' => [] // Expect an array of integer pack IDs
        ]);
    }
}
