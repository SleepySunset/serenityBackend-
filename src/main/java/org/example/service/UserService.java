package org.example.service;

import org.example.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    private static final Map<Long, User> users = new ConcurrentHashMap<>();
    private static final Map<String, User> usersByEmail = new ConcurrentHashMap<>();
    private static final AtomicLong idCounter = new AtomicLong(1L);

    static {
        // Inicializar con usuarios predefinidos
        User user1 = new User("admin@serenity.com", "admin123", "Administrador");
        user1.setId(1L);
        User user2 = new User("cliente@serenity.com", "cliente123", "Cliente Principal");
        user2.setId(2L);
        User user3 = new User("test@example.com", "test123", "Usuario de Prueba");
        user3.setId(3L);

        users.put(1L, user1);
        users.put(2L, user2);
        users.put(3L, user3);

        usersByEmail.put("admin@serenity.com", user1);
        usersByEmail.put("cliente@serenity.com", user2);
        usersByEmail.put("test@example.com", user3);

        idCounter.set(4L);
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(usersByEmail.get(email));
    }

    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public User saveUser(User user) {
        Long newId = idCounter.getAndIncrement();
        user.setId(newId);
        users.put(newId, user);
        usersByEmail.put(user.getEmail(), user);
        return user;
    }

    public boolean emailExists(String email) {
        return usersByEmail.containsKey(email);
    }

    public Optional<User> updateUser(Long id, User details) {
        return Optional.ofNullable(users.computeIfPresent(id, (k, v) -> {
            if (details.getName() != null) {
                v.setName(details.getName());
            }
            if (details.getPassword() != null) {
                v.setPassword(details.getPassword());
            }
            return v;
        }));
    }

    public void deleteUser(Long id) {
        User user = users.remove(id);
        if (user != null) {
            usersByEmail.remove(user.getEmail());
        }
    }
}

