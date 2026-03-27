package com.sneha.taskmanager.service;

import com.sneha.taskmanager.entity.Task;
import com.sneha.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskScheduler {

    private final TaskRepository taskRepository;
    private final ReminderService reminderService;

    @Scheduled(fixedRate = 60000)
    public void checkDueTasks() {

        log.info("Running scheduled task check...");

        LocalDate today = LocalDate.now();

        List<Task> tasks = taskRepository.findAll();

        for (Task task : tasks) {
            if (task.getDueDate() != null &&
                    task.getDueDate().isEqual(today)) {

                reminderService.sendReminder(task);
            }
        }
    }
}