package com.inventorymanager.project.controller;

import com.inventorymanager.project.model.LoginResponse;
import com.inventorymanager.project.model.User;
import com.inventorymanager.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody User user) {
        Integer userId = userService.authenticateUser(user.getUsername(), user.getPassword());

        if (userId > 0) {
            return new LoginResponse("Login bem-sucedido!", userId);
        } else {
            return new LoginResponse("Falha na autenticação: Usuário ou senha inválidos.", null);
        }
    }
}
