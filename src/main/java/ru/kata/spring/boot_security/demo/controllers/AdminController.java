package ru.kata.spring.boot_security.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.servicies.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.servicies.UserServiceImpl;

import java.security.Principal;
import java.util.Collection;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String printUsers(ModelMap model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<User> listOfUsers = userService.getAllUsers();
        model.addAttribute("listOfUsers", listOfUsers);
        return "showAllUsers";
    }

    @GetMapping("/addNewUser")
    public String showCreateUserForm(ModelMap model) {
        User user = new User();
        Collection<Role> roles = roleService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "newUser";
    }

    @PostMapping("/")
    public String addUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("{id}/edit")
    public String showEditUserForm(Model model, @PathVariable long id) {
        model.addAttribute("user", userService.getUser(id));
        return "updateUser";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute User user, @PathVariable("id") long id) {
        userService.updateUser(user, id);
        return "redirect:/admin/";
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }
}
