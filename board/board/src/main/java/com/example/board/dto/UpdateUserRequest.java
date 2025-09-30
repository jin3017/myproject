package com.example.board.dto;
import jakarta.validation.constraints.*;
public class UpdateUserRequest {
    @NotBlank(message = "필수입니다")
    private String name;
    @Email(message = "올바른 이메일 형식좀 쓰세요")
    private String email;
    private String password;
    public UpdateUserRequest(String name, String email){
        this.name = name;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
