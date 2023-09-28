package com.example.devopsproject.services;

import com.example.devopsproject.Exceptions.InvalidCredentialsException;
import com.example.devopsproject.Exceptions.RegistrationFailedException;
import com.example.devopsproject.Exceptions.UserAlreadyExistException;
import com.example.devopsproject.dtos.LoginRequest;
import com.example.devopsproject.dtos.LoginResponse;
import com.example.devopsproject.dtos.RegisterRequest;
import com.example.devopsproject.dtos.RegisterResponse;

public interface UserService {
    RegisterResponse register(RegisterRequest registerRequest) throws UserAlreadyExistException, RegistrationFailedException;
    LoginResponse login(LoginRequest loginRequest) throws InvalidCredentialsException;
}
