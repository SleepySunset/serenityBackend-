package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");
        String name = request.get("name");

        Map<String, Object> response = new HashMap<>();

        if (email == null || email.isEmpty() || password == null || password.isEmpty() || name == null || name.isEmpty()) {
            response.put("success", false);
            response.put("message", "Email, password y name son requeridos");
            return ResponseEntity.badRequest().body(response);
        }

        if (userService.emailExists(email)) {
            response.put("success", false);
            response.put("message", "El email ya está registrado");
            return ResponseEntity.badRequest().body(response);
        }

        User newUser = new User(email, password, name);
        User savedUser = userService.saveUser(newUser);

        response.put("success", true);
        response.put("message", "Usuario registrado exitosamente");
        response.put("user", Map.of(
            "id", savedUser.getId(),
            "email", savedUser.getEmail(),
            "name", savedUser.getName()
        ));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        Map<String, Object> response = new HashMap<>();

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            response.put("success", false);
            response.put("message", "Email y password son requeridos");
            return ResponseEntity.badRequest().body(response);
        }

        var userOpt = userService.findByEmail(email);

        if (userOpt.isEmpty()) {
            response.put("success", false);
            response.put("message", "Usuario no encontrado");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        User user = userOpt.get();

        if (!user.getPassword().equals(password)) {
            response.put("success", false);
            response.put("message", "Contraseña incorrecta");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        response.put("success", true);
        response.put("message", "Login exitoso");
        response.put("user", Map.of(
            "id", user.getId(),
            "email", user.getEmail(),
            "name", user.getName()
        ));

        return ResponseEntity.ok(response);
    }
}

