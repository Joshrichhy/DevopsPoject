package com.example.devopsproject.Exceptions;

public class UserAlreadyExistException extends Throwable {
    public UserAlreadyExistException(String user_with_email_already_exist) {
        super(user_with_email_already_exist);
    }
}
