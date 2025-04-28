<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Contracts\HttpClient\HttpClientInterface;

class TestEmailController extends AbstractController
{
    private $client;
    private $apiKey = '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'; // Mets ici ta vraie clé API HTTP Brevo

    public function __construct(HttpClientInterface $client)
    {
        $this->client = $client;
    }

    #[Route('/send-mail-api', name: 'send_mail_api')]
    public function sendMailApi(): Response
    {
        $response = $this->client->request('POST', 'https://api.brevo.com/v3/smtp/email', [
            'headers' => [
                'accept' => 'application/json',
                'api-key' => $this->apiKey,
                'content-type' => 'application/json',
            ],
            'json' => [
                'sender' => ['email' => 'douaabj4@gmail.com'],  // Expéditeur
                'to' => [['email' => 'abdghalia04@gmail.com']],  // Destinataire
                'subject' => 'Bravo 🎉',
                'htmlContent' => '<p>Félicitations pour ta mission réussie ! 🎯</p>',
            ],
        ]);

        $statusCode = $response->getStatusCode();
        $content = $response->getContent(false);

        return $this->json([
            'status' => $statusCode,
            'response' => json_decode($content, true)
        ]);
    }
}
