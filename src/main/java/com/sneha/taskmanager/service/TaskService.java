package com.sneha.taskmanager.service;

import com.sneha.taskmanager.dto.TaskRequestDTO;
import com.sneha.taskmanager.dto.TaskResponseDTO;
import com.sneha.taskmanager.entity.Task;
import com.sneha.taskmanager.entity.User;
import com.sneha.taskmanager.exception.ResourceNotFoundException;
import com.sneha.taskmanager.repository.TaskRepository;
import com.sneha.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.sneha.taskmanager.entity.Status;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskResponseDTO createTask(TaskRequestDTO dto, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setDueDate(dto.getDueDate());
        task.setUser(user);

        Task saved = taskRepository.save(task);

        return new TaskResponseDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.getStatus()
        );
    }

    public Page<TaskResponseDTO> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable)
                .map(task -> new TaskResponseDTO(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getStatus()
                ));
    }

    public Page<TaskResponseDTO> getTasksByStatus(Status status, Pageable pageable) {
        return taskRepository.findByStatus(status, pageable)
                .map(task -> new TaskResponseDTO(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getStatus()
                ));
    }

    public TaskResponseDTO updateTask(Long taskId, TaskRequestDTO dto) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setDueDate(dto.getDueDate());

        Task updated = taskRepository.save(task);

        return new TaskResponseDTO(
                updated.getId(),
                updated.getTitle(),
                updated.getDescription(),
                updated.getStatus()
        );
    }

    public void deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new ResourceNotFoundException("Task not found");
        }
        taskRepository.deleteById(taskId);
    }
}