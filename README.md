# AS222S6_Mockito

## 📌 Descripción
Este proyecto demuestra el uso de **Mockito** para realizar pruebas unitarias en una aplicación Spring Boot. Se utiliza un servicio de tareas (`TaskService`) que interactúa con un servicio de notificaciones (`NotificationService`), y se simulan sus comportamientos con mocks.

## 🛠 Tecnologías Utilizadas
- **Java 17+**
- **Spring Boot**
- **Mockito**
- **JUnit 5**
- **Maven**

## 📂 Estructura del Proyecto
```
pe.edu.vallegrande.Mockito
│── model
│   ├── Task.java
│── service
│   ├── TaskService.java
│   ├── NotificationService.java
│── controller
│   ├── TaskController.java
│── test
│   ├── TaskServiceTest.java
```

---

## 🏗 Modelo (`Task`)
```java
package pe.edu.vallegrande.Mockito.model;

import java.time.LocalDateTime;

public class Task {
    private String id;
    private String title;
    private String description;
    private LocalDateTime dueDate;

    public Task(String id, String title, String description, LocalDateTime dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    // Getters y Setters
}
```

---

## ⚙️ Servicio (`TaskService`)
```java
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

    public TaskService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task createTask(String title, String description, LocalDateTime dueDate) {
        Task task = new Task(String.valueOf(tasks.size() + 1), title, description, dueDate);
        tasks.add(task);
        notificationService.sendNotification("Tarea creada: " + title);
        return task;
    }
}
```

---

## 📩 Servicio de Notificaciones (`NotificationService`)
```java
package pe.edu.vallegrande.Mockito.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void sendNotification(String message) {
        System.out.println("Notificación enviada: " + message);
    }
}
```

---

## 🌍 Controlador (`TaskController`)
```java
package pe.edu.vallegrande.Mockito.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @GetMapping("/test")
    public String testEndpoint() {
        return "El controlador funciona correctamente.";
    }
}
```

---

## 🧪 Pruebas con Mockito (`TaskServiceTest`)
```java
package pe.edu.vallegrande.Mockito.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.edu.vallegrande.Mockito.model.Task;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {
    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTask() {
        Task task = taskService.createTask("Prueba", "Descripción de prueba", LocalDateTime.now());
        assertNotNull(task);
        verify(notificationService, times(1)).sendNotification("Tarea creada: Prueba");
    }
}
```

---

# 🔥 Recomendaciones y Buenas Prácticas
✔ **Usar Mocks en los Servicios Dependientes:** Evita llamadas reales en pruebas unitarias.  
✔ **Verificar Interacciones:** Usa `verify()` para asegurarte de que los métodos mockeados fueron llamados.  
✔ **Usar `@BeforeEach` para Inicializar Mockito:** Garantiza que los mocks estén listos antes de cada prueba.  
✔ **Evitar Pruebas con Base de Datos:** Las pruebas unitarias deben ejecutarse rápido y sin dependencias externas.  

---

# 🚫 Errores Comunes y Qué Evitar
❌ **No inicializar Mockito:** Si no usas `MockitoAnnotations.openMocks(this)`, los mocks no funcionarán.  
❌ **No usar `@Mock` y `@InjectMocks` correctamente:** `@Mock` crea el mock, pero `@InjectMocks` inyecta la dependencia.  
❌ **Hacer pruebas de integración en pruebas unitarias:** Las pruebas unitarias deben enfocarse en lógica de negocio.  
❌ **No verificar interacciones:** Si usas mocks pero no verificas su uso con `verify()`, la prueba pierde sentido.  

---

## 🚀 Ejecución del Proyecto
1. Clonar el repositorio
```sh
git clone https://github.com/tu-repo/AS222S6_Mockito.git
cd mockito-tests
```
2. Ejecutar las pruebas
```sh
mvn test
```
3. Iniciar la aplicación
```sh
mvn spring-boot:run
```

# 🏆 Autor  

📌 **Desarrollado por:** _Valery Chumpitaz_ 🚀  
📌 **Contribuye:** Si este proyecto te fue útil, ¡no dudes en dejar una ⭐ y compartirlo! 😎✨  
📌 **Contacto:** valery.chumpitaz@vallegrande.edu.pe

💡 _“El código bien probado es el camino a un software robusto.”_  
