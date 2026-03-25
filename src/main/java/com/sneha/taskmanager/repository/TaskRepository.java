package com.sneha.taskmanager.repository;

import com.sneha.taskmanager.entity.Task;
import com.sneha.taskmanager.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByStatus(Status status, Pageable pageable);
}