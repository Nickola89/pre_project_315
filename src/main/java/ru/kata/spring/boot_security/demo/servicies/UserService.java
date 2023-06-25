package ru.kata.spring.boot_security.demo.servicies;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void saveUser(User user);

    User getUser(long id);

    void deleteUser(long id);

    void updateUser(User user);

    User findByUsername(String username);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
