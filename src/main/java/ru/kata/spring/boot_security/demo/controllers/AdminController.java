package ru.kata.spring.boot_security.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.servicies.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.servicies.UserServiceImpl;

import java.security.Principal;


@Slf4j
@Controller
public class AdminController {
    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }

    @GetMapping("/admin")
    public String getAllUsers(Model model, Principal principal) {
        model.addAttribute("allUsers", userService.getAllUsers());
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("titleTable", "Список всех пользователей:");
        return "showAllUsers";
    }

    @GetMapping("/user")
    public String getUserPage(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "currentUser";
    }
}
