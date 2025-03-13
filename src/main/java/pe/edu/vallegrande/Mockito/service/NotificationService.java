package pe.edu.vallegrande.Mockito.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public String sendNotification(String message) {
        return "🔔 Notificación enviada: " + message;
    }
}