package com.example.devopsproject.services;

import com.example.devopsproject.Exceptions.InvalidCredentialsException;
import com.example.devopsproject.Exceptions.RegistrationFailedException;
import com.example.devopsproject.Exceptions.UserAlreadyExistException;
import com.example.devopsproject.dtos.LoginRequest;
import com.example.devopsproject.dtos.LoginResponse;
import com.example.devopsproject.dtos.RegisterRequest;
import com.example.devopsproject.dtos.RegisterResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {

    RegisterRequest registerRequest;
    RegisterRequest registerRequest2;
    LoginRequest loginRequest;
    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        registerRequest = RegisterRequest.builder()
                .email("kusejoshua@gmail.com")
                .password("Oluwatosin").build();
        registerRequest2 = RegisterRequest.builder()
                .email("kusejoshua@gmail.com")
                .password("Oluwatosin").build();

        loginRequest = new LoginRequest();
        loginRequest.setEmail("kusejoshua@gmail.com");
        loginRequest.setPassword("Oluwatosin");
    }

    @Test
    void register() throws UserAlreadyExistException, RegistrationFailedException {
        RegisterResponse response = userService.register(registerRequest);
        RegisterResponse response2 = userService.register(registerRequest2);
        assertEquals("Registration is Successfull", response2.getMessage());
    }

    @Test
    void login() throws InvalidCredentialsException, UserAlreadyExistException, RegistrationFailedException {
        RegisterResponse resgisterResponse = userService.register(registerRequest);
        LoginResponse response = userService.login(loginRequest);
        assertEquals("Login successfull", response.getMessage());
    }

    @Test
    void throwExceptionWhenLoginCredentialsIsWrong() throws InvalidCredentialsException, UserAlreadyExistException, RegistrationFailedException {
        RegisterResponse resgisterResponse = userService.register(registerRequest);
        loginRequest = new LoginRequest();
        loginRequest.setEmail("kusejoshua@gmail.com");
        loginRequest.setPassword("Oluwatosin1234");
        assertThrows(InvalidCredentialsException.class, ()-> userService.login(loginRequest));
    }
}