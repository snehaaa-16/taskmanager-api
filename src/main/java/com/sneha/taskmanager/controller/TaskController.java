package com.sneha.taskmanager.controller;

import com.sneha.taskmanager.dto.TaskRequestDTO;
import com.sneha.taskmanager.dto.TaskResponseDTO;
import com.sneha.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/{userId}")
    public ResponseEntity<TaskResponseDTO> createTask(
            @RequestBody TaskRequestDTO dto,
            @PathVariable Long userId) {

        return ResponseEntity.ok(taskService.createTask(dto, userId));
    }
}