<?php

use Symfony\Component\Console\Command\Command;
use Symfony\Component\Console\Input\InputInterface;
use Symfony\Component\Console\Output\OutputInterface;
use Twilio\Rest\Client;

class TestTwilioCommand extends Command
{
    protected static $defaultName = 'app:test-twilio';
    
    public function __construct(
        private string $twilioSid,
        private string $twilioToken,
        private string $twilioFrom
    ) {
        parent::__construct();
    }

    protected function execute(InputInterface $input, OutputInterface $output): int
    {
        try {
            $twilio = new Client($this->twilioSid, $this->twilioToken);
            
            $message = $twilio->messages->create(
                '+21658664146', // Your test number
                [
                    'from' => $this->twilioFrom,
                    'body' => 'Test message from Symfony'
                ]
            );
            
            $output->writeln('Message SID: '.$message->sid);
            return Command::SUCCESS;
            
        } catch (\Exception $e) {
            $output->writeln('<error>Error: '.$e->getMessage().'</error>');
            return Command::FAILURE;
        }
    }
}