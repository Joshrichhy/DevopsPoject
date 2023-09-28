package com.example.devopsproject.Exceptions;

public class InvalidCredentialsException extends Throwable {
    public InvalidCredentialsException(String invalid_credentials) {
        super(invalid_credentials);
    }
}
