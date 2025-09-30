package com.example.board.service;
import com.example.board.dto.LoginRequest;
import com.example.board.model.User;
import com.example.board.dto.RegisterUserRequest;
import com.example.board.dto.UserResponse;
import com.example.board.mapper.UserMapper;
import com.example.board.repository.AuthRepository;
import com.example.board.repository.UserRepository;
import com.example.board.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    public final UserMapper userMapper;
    public final UserRepository userRepository;

    public AuthService(AuthRepository authRepository,UserMapper userMapper,UserRepository userRepository){
        this.authRepository = authRepository;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }
    // 회원가입
    public UserResponse register(RegisterUserRequest dto){
        User user = userMapper.toEntity(dto);
        User savedUser = authRepository.save(user);
       // User savedUser = authRepository.save(userMapper.RegisterEntityFromDto(dto));
        return userMapper.toResponse(savedUser);
    }
    // 로그인
    public UserResponse login(String email, String password){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("이메일이 없음!"));
        if(user.getPassword().equals(password)){
            return userMapper.toResponse(user);
        }
        throw new RuntimeException("비밀번호가 올바르지 않음");

    }
}
