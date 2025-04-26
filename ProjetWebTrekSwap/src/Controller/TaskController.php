<?php

namespace App\Controller;

use App\Entity\Task;
use App\Form\TaskType;
use App\Repository\TaskRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/task')]
class TaskController extends AbstractController
{
    #[Route('/', name: 'app_task_index', methods: ['GET'])]
    public function index(TaskRepository $taskRepository): Response
    {
        return $this->render('task.html.twig', [
            'tasks' => $taskRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_task_new', methods: ['POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): JsonResponse
    {
        $data = json_decode($request->getContent(), true);
        
        $task = new Task();
        $task->setTitle($data['title']);
        $task->setStatus('pending');
        
        $entityManager->persist($task);
        $entityManager->flush();
        
        return $this->json([
            'id' => $task->getId(),
            'title' => $task->getTitle(),
            'status' => $task->getStatus()
        ]);
    }

    #[Route('/{id}/status', name: 'app_task_status', methods: ['PUT'])]
    public function updateStatus(Request $request, Task $task, EntityManagerInterface $entityManager): JsonResponse
    {
        $data = json_decode($request->getContent(), true);
        $task->setStatus($data['status']);
        
        $entityManager->flush();
        
        return $this->json(['status' => 'success']);
    }

    #[Route('/{id}', name: 'app_task_delete', methods: ['DELETE'])]
    public function delete(Task $task, EntityManagerInterface $entityManager): JsonResponse
    {
        $entityManager->remove($task);
        $entityManager->flush();
        
        return $this->json(['status' => 'success']);
    }
} 