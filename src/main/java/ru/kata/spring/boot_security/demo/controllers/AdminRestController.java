package ru.kata.spring.boot_security.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.exception.NoSuchUserException;
import ru.kata.spring.boot_security.demo.servicies.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api")
public class AdminRestController {
    private final UserServiceImpl userService;

    public AdminRestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> showAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<User> addUser(@RequestBody @Valid User user) {
        System.out.println(user);
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        System.out.println(user);
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id) {
        User user = userService.getUser(id);
        if (user == null) {
            throw new NoSuchUserException("There is no user with Id = " + id + " in database");
        }
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
