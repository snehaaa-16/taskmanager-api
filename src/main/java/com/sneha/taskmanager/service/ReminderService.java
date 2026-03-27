package com.sneha.taskmanager.service;

import com.sneha.taskmanager.entity.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReminderService {

    @Async
    public void sendReminder(Task task) {
        log.info("Reminder: Task '{}' is due soon!", task.getTitle());
    }
}