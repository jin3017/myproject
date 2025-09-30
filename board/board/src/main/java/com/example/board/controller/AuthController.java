package com.example.board.controller;

import com.example.board.model.*;
import com.example.board.dto.*;
import com.example.board.security.jwt.JwtTokenProvider;
import com.example.board.service.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtTokenProvider jwt;
    public AuthController(AuthService authService, JwtTokenProvider jwt){
        this.authService = authService;
        this.jwt = jwt;
    }

    // 사용자 등록 - POST "/register"
    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterUserRequest dto){
        return authService.register(dto);
    }
    // 사용자 로그인 - POST "/login" 셰션이용
//    @PostMapping("/login")
//    public UserResponse login(@Valid @RequestBody LoginRequest dto, HttpSession session){
//        UserResponse response = authService.login(dto.getEmail(), dto.getPassword());
//        session.setAttribute("LOGIN_USER", response.getId());
//        return response;
//    }

    // 사용자 로그인 - POST "/login" JWT이용 쿠키에 넣어서 보내기
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest dto, HttpServletResponse httpresponse){
//        UserResponse response = authService.login(dto.getEmail(), dto.getPassword());
//
//        String token = jwt.generateToken(response.getId(),response.getEmail());
//
//        Cookie cookie = new Cookie("ACCESS_TOKEN",token);
//        cookie.setHttpOnly(true);
//        cookie.setSecure(true);
//        cookie.setPath("/");
//        cookie.setMaxAge(60*60);
//        httpresponse.addCookie(cookie);
//
//        return ResponseEntity.ok("Login success");
//    }
    // 사용자 로그인 - POST "/login" JWT방식 AUTHORIZATION헤더에 토큰 넣기 결국 이것도 일회용
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest dto, HttpServletResponse httpresponse){
        UserResponse response = authService.login(dto.getEmail(), dto.getPassword());

        String token = jwt.generateToken(response.getId(),response.getEmail());

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION,"Bearer "+token)
                .body(response);//바디안에는 로그인된 유저의 ResponseDto가 들어감
    }


    // 사용자 로그아웃 - POST "/logout" 셰션방식
//    @PostMapping("/logout")
//    public void logout(HttpSession session){
//        session.invalidate();
//    }

    // 사용자 로그아웃 - POST "/logout" JWT방식 쿠키사용
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response){

        Cookie cookie = new Cookie("ACCESS_TOKEN",null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // 즉시 만료
        response.addCookie(cookie);

        return ResponseEntity.ok("Logged out");
    }


}
