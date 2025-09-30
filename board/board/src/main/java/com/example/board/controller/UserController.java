package com.example.board.controller;
import com.example.board.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*; //어노테이션 용 import
import com.example.board.dto.*;
import com.example.board.model.User;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {
    //의존성 주입(DI) ioC가 객체 관리
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // 사용자 전체 조회 - GET /users
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    // 사용자 추가 - POST /users
    @PostMapping
    public void addUser(@Valid @RequestBody RegisterUserRequest userData){
        userService.addUser(userData);
    }

    // 특정 사용자 조회 / GET /users/:id
    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    // 사용자 삭제 DELETE /users/:id/delete
    @DeleteMapping("/{id}/delete")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    // 사용자 정보 수정
    @PatchMapping("/{id}/update")
    public void updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest updateUser){
        userService.updateUser(id, updateUser);
    }
}

