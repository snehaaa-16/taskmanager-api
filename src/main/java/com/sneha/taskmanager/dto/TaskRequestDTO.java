package com.sneha.taskmanager.dto;

import com.sneha.taskmanager.entity.Status;
import lombok.Data;
import java.time.LocalDate;

@Data
public class TaskRequestDTO {
    private String title;
    private String description;
    private Status status;
    private LocalDate dueDate;
}