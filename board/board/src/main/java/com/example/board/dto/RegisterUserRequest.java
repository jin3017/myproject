package com.example.board.dto;

import jakarta.validation.constraints.*;
import lombok.*;
@Getter
@Setter
public class RegisterUserRequest {
    @NotBlank(message = "필수입니다")
   /* @Size(min = 3, max = 20, message = "3~20글자로 입력하십시오")*/
    private String name;
    @NotBlank(message = "필수입니다")
    @Email
    private String email;
    @NotBlank(message = "비밀번호를 설정하세요")
   /* @Size(min = 8,max = 20, message = "최소 8글자 최대 20글자를 입력할 수 있습니다")*/
    private String password;

    public RegisterUserRequest(){}

    public RegisterUserRequest(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
