package pe.edu.vallegrande.Mockito.service;

import org.springframework.stereotype.Service;
import pe.edu.vallegrande.Mockito.model.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private final NotificationService notificationService;

    // Inyectamos el servicio de notificaciones
    public TaskService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task createTask(String title, String description, LocalDateTime dueDate) {
        Task task = new Task(String.valueOf(tasks.size() + 1), title, description, dueDate);
        tasks.add(task);

        // ✅ Ahora se llama al servicio de notificaciones
        notificationService.sendNotification("Tarea creada: " + title);

        return task;
    }
}
