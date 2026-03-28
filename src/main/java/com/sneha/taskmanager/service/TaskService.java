package com.sneha.taskmanager.service;

import com.sneha.taskmanager.dto.TaskRequestDTO;
import com.sneha.taskmanager.dto.TaskResponseDTO;
import com.sneha.taskmanager.entity.Task;
import com.sneha.taskmanager.entity.User;
import com.sneha.taskmanager.exception.ResourceNotFoundException;
import com.sneha.taskmanager.repository.TaskRepository;
import com.sneha.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.sneha.taskmanager.entity.Status;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @CacheEvict(value = {"tasks", "tasksByStatus"}, allEntries = true)
    public TaskResponseDTO createTask(TaskRequestDTO dto, Long userId) {

        logger.info("Creating task for user: {}", userId);

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

    @Cacheable("tasks")
    public Page<TaskResponseDTO> getAllTasks(Pageable pageable) {

        logger.info("Fetching tasks from DB");

        return taskRepository.findAll(pageable)
                .map(task -> new TaskResponseDTO(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getStatus()
                ));
    }

    @Cacheable("tasksByStatus")
    public Page<TaskResponseDTO> getTasksByStatus(Status status, Pageable pageable) {

        logger.info("Fetching tasks by status from DB");

        return taskRepository.findByStatus(status, pageable)
                .map(task -> new TaskResponseDTO(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getStatus()
                ));
    }

    @CacheEvict(value = {"tasks", "tasksByStatus"}, allEntries = true)
    public TaskResponseDTO updateTask(Long taskId, TaskRequestDTO dto) {

        logger.info("Updating task: {}", taskId);

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

    @CacheEvict(value = {"tasks", "tasksByStatus"}, allEntries = true)
    public void deleteTask(Long taskId) {

        logger.info("Deleting task: {}", taskId);

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        String currentUser = getCurrentUserEmail();

        if (!task.getUser().getEmail().equals(currentUser)) {
            logger.error("Unauthorized delete attempt by user: {}", currentUser);
            throw new RuntimeException("Access denied");
        }

        taskRepository.delete(task);
    }

    private String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}