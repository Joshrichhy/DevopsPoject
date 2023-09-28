package com.example.devopsproject.Exceptions;

public class RegistrationFailedException extends Throwable {
    public RegistrationFailedException(String user_registration_failed) {
        super(user_registration_failed);
    }
}
