package com.example.board.service;
import com.example.board.dto.UserResponse;
import com.example.board.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.example.board.model.User;
import com.example.board.dto.*;
import com.example.board.mapper.UserMapper;

import java.util.*;
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    // 전체 사용자 조회 내부 로직
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // 사용자 추가 내부 로직
    public void addUser(RegisterUserRequest userData){
        User user = userMapper.toEntity(userData);
        userRepository.save(user);
    }

    //특정 사용자 조회
    //Optional이란 Java8에서 새로 추가된 내용으로, NPE를 방지할 수 있도록 Null이 올 수 있는 값을 감싸는 Wrapper 클래스입니다
    public UserResponse getUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("해당 사용자를 찾을 수 없습니다. id="+id));
        return userMapper.toResponse(user);
    }

    // 사용자 삭제
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    // 사용자 정보 수정
//    public String updateUser(Long id, UserUpdateRequest updateUser){
//        User tempUser = userRepository.findById(id)
//                .orElseThrow(() -> new IllformedLocaleException("유저 없음!"));//예외 수정필요
//        if(updateUser.getName()!=null){
//            tempUser.setName(updateUser.getName());
//        }
//        if (updateUser.getEmail()!=null) {
//            tempUser.setEmail(updateUser.getEmail());
//        }
//        userRepository.save(tempUser);
//        return "사용자 정보가 수정되었습니다";
//    }
    public UserResponse updateUser(Long id, UpdateUserRequest dto){
        User existingUser = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User Not Found!"));

        //DTO -> Entity 업데이트
        userMapper.updateEntityFromDto(dto,existingUser);

        User savedUser =userRepository.save(existingUser);

        return userMapper.toResponse(savedUser);
    }
}
