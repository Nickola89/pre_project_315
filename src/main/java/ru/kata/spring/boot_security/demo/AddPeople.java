package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.servicies.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.servicies.UserServiceImpl;

import java.util.HashSet;
import java.util.Set;

@Component
public class AddPeople implements CommandLineRunner {
    private final RoleServiceImpl roleServiceImpl;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public AddPeople(RoleServiceImpl roleServiceImpl, UserServiceImpl userServiceImpl) {
        this.roleServiceImpl = roleServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public void run(String... args) {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        Set<Role> adminRoles = new HashSet<>();
        Set<Role> userRoles = new HashSet<>();
        roleServiceImpl.addRole(roleAdmin);
        roleServiceImpl.addRole(roleUser);
        adminRoles.add(roleAdmin);
        adminRoles.add(roleUser);
        userRoles.add(roleUser);

        User userAdmin = new User(1, "admin", "admin", "user1@mail.ru", adminRoles);
        User userUser = new User(2, "user", "user", "user2@mail.ru", userRoles);
        userServiceImpl.saveUser(userAdmin);
        userServiceImpl.saveUser(userUser);

    }
}
