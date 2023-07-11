package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.servicies.UserServiceImpl;

import java.security.Principal;

@RestController
public class UserRestController {
    private final UserServiceImpl userService;

    @Autowired
    public UserRestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/api/currentUser")
    public ResponseEntity<User> showUser(Principal principal) {
        System.out.println(principal);
        User user = userService.findByUsername(principal.getName());
        System.out.println(principal.getName());
        System.out.println(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
