package com.example.devopsproject.services;

import com.example.devopsproject.Exceptions.InvalidCredentialsException;
import com.example.devopsproject.Exceptions.RegistrationFailedException;
import com.example.devopsproject.Exceptions.UserAlreadyExistException;
import com.example.devopsproject.data.models.User;
import com.example.devopsproject.data.repositories.UserRepository;
import com.example.devopsproject.dtos.LoginRequest;
import com.example.devopsproject.dtos.LoginResponse;
import com.example.devopsproject.dtos.RegisterRequest;
import com.example.devopsproject.dtos.RegisterResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) throws UserAlreadyExistException, RegistrationFailedException {
        Optional<User> foundUser = userRepository.findById(registerRequest.getEmail());
        if(foundUser.isPresent())throw new UserAlreadyExistException("User with Email already exist");
        User user =  modelMapper.map(registerRequest, User.class);
       User savedUser = userRepository.save(user);

       boolean isSavedUser = savedUser.getEmail() != null;
        if(!isSavedUser)throw new RegistrationFailedException("User Registration failed");
        RegisterResponse registerResponse = RegisterResponse.builder()
                .message("Registration is Successfull").build();

             return registerResponse;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws InvalidCredentialsException {
        LoginResponse loginResponse;
        Optional<User> foundUser = userRepository.findById(loginRequest.getEmail());
        System.out.println(foundUser.get().getPassword() );
        if(Objects.equals(foundUser.get().getPassword(), loginRequest.getPassword())){
            loginResponse = LoginResponse.builder().email(loginRequest.getEmail()).message("Login successfull").build();
            return loginResponse;
        }else{
        throw new InvalidCredentialsException("Invalid Credentials");}
        }
    }