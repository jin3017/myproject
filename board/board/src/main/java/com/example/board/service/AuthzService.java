package com.example.board.service;

import com.example.board.repository.PostRepository;
import com.example.board.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("authz")
public class AuthzService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 두 레포지토리 의존성 주입
    public AuthzService(PostRepository postRepository, UserRepository userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public boolean isOwnerOfPost(Long postId){
        // 컨텍스트로부터 사용자 정보 가져오기
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || !auth.isAuthenticated()) return false;

        //사용자 정보 추출
        String email = auth.getName(); // principal 문자열(email)
        Long currentUserId = userRepository.findIdByEmail(email)
                .orElse(null)
                .getId();
        if(currentUserId == null) return false;

        return postRepository.existsByIdAndUserId(postId, currentUserId);
    }
}
