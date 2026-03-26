package com.sneha.taskmanager.controller;

import com.sneha.taskmanager.dto.TaskRequestDTO;
import com.sneha.taskmanager.dto.TaskResponseDTO;
import com.sneha.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.sneha.taskmanager.entity.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @PostMapping("/{userId}")
    public ResponseEntity<TaskResponseDTO> createTask(
            @Valid @RequestBody TaskRequestDTO dto,
            @PathVariable Long userId) {

        logger.info("API call: Create task for user {}", userId);

        return ResponseEntity.ok(taskService.createTask(dto, userId));
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponseDTO>> getAllTasks(Pageable pageable) {
        return ResponseEntity.ok(taskService.getAllTasks(pageable));
    }

    @GetMapping("/status")
    public ResponseEntity<Page<TaskResponseDTO>> getTasksByStatus(
            @RequestParam Status status,
            Pageable pageable) {

        return ResponseEntity.ok(taskService.getTasksByStatus(status, pageable));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> updateTask(
            @PathVariable Long taskId,
            @Valid @RequestBody TaskRequestDTO dto) {

        return ResponseEntity.ok(taskService.updateTask(taskId, dto));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }
}