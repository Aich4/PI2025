<?php

namespace App\Service;

use App\Entity\Abonnement;
use Doctrine\ORM\EntityManagerInterface;

class SubscriptionPredictionService
{
    private $em;

    public function __construct(EntityManagerInterface $em)
    {
        $this->em = $em;
    }

    // Predict if subscription will not be renewed
    public function predictNonRenewal(Abonnement $abonnement): string
    {
        $today = new \DateTimeImmutable();

        // Get the expiration date from the abonnement
        $expirationDate = $abonnement->getDateExpiration();

        // Example rules for predicting non-renewal

        // Check if the subscription has expired
        if ($expirationDate < $today) {
            return 'Non-renewal predicted due to expired subscription.';
        }

        // Check if the subscription is about to expire soon (e.g., within the next 30 days)
        if ($expirationDate < $today->modify('+3 days')) {
            return 'Non-renewal predicted due to approaching expiration date.';
        }

        // If none of the conditions apply, assume renewal is likely
        return 'Renewal likely.';
    }
}
