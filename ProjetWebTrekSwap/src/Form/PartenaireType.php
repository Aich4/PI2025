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
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Validator\Constraints\GreaterThanOrEqual;

class PartenaireType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nom', TextType::class, [
                'constraints' => [
                    new Assert\NotBlank([
                        'message' => 'Le nom ne doit pas être vide.'
                    ]),
                ],
            ])
            ->add('email', EmailType::class, [
                'constraints' => [
                    new Assert\NotBlank([
                        'message' => 'L\'email ne doit pas être vide.'
                    ]),
                    new Assert\Email([
                        'message' => 'L\'email "{{ value }}" n\'est pas un email valide.',
                    ]),
                ],
            ])
            ->add('adresse', TextType::class, [
                'constraints' => [
                    new Assert\NotBlank([
                        'message' => 'L\'adresse ne doit pas être vide.'
                    ]),
                    new Assert\Regex([
                        'pattern' => '/^(?=.*[a-zA-Z])[a-zA-Z0-9\s,.-]+$/',
                        'message' => 'L\'adresse doit contenir au moins une lettre et peut inclure des lettres, des chiffres et des caractères spéciaux valides.',
                    ]),
                    new Assert\Regex([
                        'pattern' => '/^(?!\d+$).*$/',
                        'message' => 'L\'adresse ne peut pas être composée uniquement de chiffres.',
                    ]),
                ],
            ])
            ->add('description', TextareaType::class, [
                'constraints' => [
                    new Assert\NotBlank([
                        'message' => 'La description ne doit pas être vide.'
                    ]),
                    new Assert\Regex([
                        'pattern' => '/^(?=.*[a-zA-Z])[a-zA-Z0-9\s,.-]+$/',
                        'message' => 'La description doit contenir au moins une lettre et peut inclure des lettres, des chiffres et des caractères spéciaux valides.',
                    ]),
                    new Assert\Regex([
                        'pattern' => '/^(?!\d+$).*$/',
                        'message' => 'La description ne peut pas être composée uniquement de chiffres.',
                    ]),
                ],
            ])
            ->add('date_ajout', DateType::class, [
                'widget' => 'single_text',
                'constraints' => [
                    new Assert\NotBlank([
                        'message' => 'La date d\'ajout ne doit pas être vide.'
                    ]),
                    new GreaterThanOrEqual([
                        'value' => 'today',
                        'message' => 'La date d\'ajout ne peut pas être une date passée.',
                    ]),
                ],
            ]);
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Partenaire::class,
        ]);
    }
}
