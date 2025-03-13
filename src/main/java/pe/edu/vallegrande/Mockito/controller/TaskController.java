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
