package com.healthcare.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Scheduled(fixedRate = 60000) // Run every minute
    public void sendNotifications() {
        // Logic to send notifications
    }
}