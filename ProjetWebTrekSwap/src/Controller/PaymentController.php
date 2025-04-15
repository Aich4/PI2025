<?php

namespace App\Controller;

use App\Repository\PackRepository;
use App\Repository\AbonnementRepository;
use Stripe\Stripe;
use Stripe\Checkout\Session;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;

class PaymentController extends AbstractController
{
    #[Route('/payment', name: 'payment')]
    public function index(): Response
    {
        return $this->render('payment/index.html.twig', [
            'controller_name' => 'PaymentController',
        ]);
    }

    #[Route('/checkout/{id}', name: 'checkout')]
    public function checkout($id, string $stripeSK, AbonnementRepository $AbonRepository, PackRepository $packRepository): Response
    {
        // Set Stripe API key
        Stripe::setApiKey($stripeSK);

        // Fetch the abonnement (subscription) by ID
        $Abon = $AbonRepository->find($id);
        if (!$Abon) {
            throw $this->createNotFoundException('Abonnement not found.');
        }

        // Retrieve the pack price using the repository method
        $unitAmount = $packRepository->getPriceById($Abon->getIdPack());

        // Ensure the price is converted to cents (Stripe uses the smallest currency unit)
        $unitAmount = $unitAmount * 100; // Assuming price is in DT, convert to cents

        // Create a Stripe checkout session
        $session = Session::create([
            'payment_method_types' => ['card'],
            'line_items'           => [[
                'price_data' => [
                    'currency'     => 'usd',  
                    'product_data' => [
                        'name' => 'Payment subscription',
                    ],
                    'unit_amount'  => $unitAmount, 
                ],
                'quantity'   => 1,
            ]],
            'mode'                 => 'payment',
            'success_url'          => $this->generateUrl('success_url', [], UrlGeneratorInterface::ABSOLUTE_URL),
            'cancel_url'           => $this->generateUrl('cancel_url', [], UrlGeneratorInterface::ABSOLUTE_URL),
        ]);

        // Redirect user to Stripe checkout page
        return $this->redirect($session->url, 303);
    }

    #[Route('/success-url', name: 'success_url')]
    public function successUrl(): Response
    {
        return $this->render('payment/success.html.twig', []);
    }

    #[Route('/cancel-url', name: 'cancel_url')]
    public function cancelUrl(AbonnementRepository $abonnementRepository, PackRepository $packRepository): Response
    {
        $abonnements = $abonnementRepository->findAll();
    
        return $this->render('payment/cancel.html.twig', [
            'abonnements' => $abonnements,
            'packRepository' => $packRepository,
        ]);
    }
}
