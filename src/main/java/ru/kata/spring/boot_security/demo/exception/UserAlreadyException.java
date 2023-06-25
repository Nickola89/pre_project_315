package ru.kata.spring.boot_security.demo.exception;

public class UserAlreadyException extends RuntimeException {
    public UserAlreadyException(String message) {
        super(message);
    }
}
