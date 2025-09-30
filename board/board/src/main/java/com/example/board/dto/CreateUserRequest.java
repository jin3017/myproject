package com.example.board.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.apache.catalina.authenticator.SavedRequest;

public class CreateUserRequest {
    @NotBlank(message = "필수입니다")
    @Size(min = 3, max = 20, message = "3~20글자로 입력하십시오")
    private String name;
    @NotBlank(message = "필수입니다")
    @Email
    private String email;
    @NotBlank(message = "비밀번호를 설정하세요")
    @Size(min = 8,max = 20, message = "최소 8글자 최대 20글자를 입력할 수 있습니다")
    private String password;

    public CreateUserRequest(){}

    public CreateUserRequest(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
