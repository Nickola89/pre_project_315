package ru.kata.spring.boot_security.demo.controllers;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.exception.UserAlreadyException;
import ru.kata.spring.boot_security.demo.servicies.UserServiceImpl;

import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userService;

    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "showAllUsers";
    }

    @GetMapping("/newUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(UserAlreadyException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.PAYMENT_REQUIRED);
    }

    @SneakyThrows
    @PostMapping("/showAllUsers")
    public String create(@ModelAttribute @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "newUser";
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String displayUser(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "displayUser";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable int id) {
        model.addAttribute("user", userService.getUser(id));
        return "updateUser";
    }

    @PatchMapping("{id}")
    public String update(@ModelAttribute @Valid User user, BindingResult bindingResult,
                         @PathVariable int id) {
        if (bindingResult.hasErrors())
            return "updateUser";
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
