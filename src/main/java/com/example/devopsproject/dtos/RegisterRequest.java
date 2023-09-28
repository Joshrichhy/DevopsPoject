package com.example.devopsproject.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class RegisterRequest {
    private String email;
    private String password;
}
