<?php

namespace App\Service;

use Symfony\Contracts\HttpClient\HttpClientInterface;

class GeminiPart
{
    private $client;
    private $apiKey = 'AIzaSyCQ4y0D1vtWSq3nhZqtSS-9J5JMtAS00j0'; // ✅ your real API key

    public function __construct(HttpClientInterface $client)
    {
        $this->client = $client;
    }

    public function generatePartners(string $place, int $number): array
    {
        $prompt = "Generate exactly $number fictional companies located in $place. 
Types must include restaurants, cafés, hotels, travel agencies, sports clubs, or cultural centers.
For each partner, give a JSON object with:
- name
- address
- email
- type

Return ONLY a pure JSON array with NO extra explanation, NO comments.";

        // ✅ Correct URL using v1
        $url = 'https://generativelanguage.googleapis.com/v1/models/gemini-1.5-pro:generateContent?key=' . $this->apiKey;

        $response = $this->client->request('POST', $url, [
            'headers' => [
                'Content-Type' => 'application/json',
            ],
            'json' => [
                'contents' => [
                    [
                        'role' => 'user',
                        'parts' => [
                            ['text' => $prompt]
                        ]
                    ]
                ]
            ]
        ]);

        $content = $response->getContent(false);
        $data = json_decode($content, true);

        // If Gemini returns error
        if (isset($data['error'])) {
            throw new \Exception('Gemini API Error: ' . $data['error']['message']);
        }

        // If Gemini returns no candidates
        if (!isset($data['candidates'][0]['content']['parts'][0]['text'])) {
            return [];
        }

        $text = $data['candidates'][0]['content']['parts'][0]['text'];

        $partners = json_decode($text, true);

        if (!is_array($partners)) {
            return [];
        }

        return $partners;
    }
}
