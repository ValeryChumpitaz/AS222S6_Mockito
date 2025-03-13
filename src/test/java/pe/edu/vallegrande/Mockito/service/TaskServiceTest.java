package pe.edu.vallegrande.Mockito.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.vallegrande.Mockito.model.Task;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private NotificationService notificationService; // Mock del servicio de notificación

    @InjectMocks
    private TaskService taskService; // Se inyecta el mock en TaskService

    @Test
    void testCreateTask() {
        // Simulamos la respuesta del servicio de notificaciones
        when(notificationService.sendNotification(anyString())).thenReturn("Notificación simulada");

        // Llamamos al servicio de tareas
        Task task = taskService.createTask("Nueva Tarea", "Descripción", LocalDateTime.now().plusDays(3));

        // Verificamos que la tarea se creó correctamente
        assertNotNull(task);
        assertEquals("Nueva Tarea", task.getTitle());

        // ✅ Ahora verificamos que el mock realmente se usó
        verify(notificationService, times(1)).sendNotification(anyString());
    }
}
