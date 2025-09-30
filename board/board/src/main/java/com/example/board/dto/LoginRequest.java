package com.example.board.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
@Setter
@Getter

public class LoginRequest {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 8)
    private String password;

    public LoginRequest(){}

    public LoginRequest(String email, String password){
        this.email = email;
        this.password = password;
    }
}
