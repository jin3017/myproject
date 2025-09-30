package com.example.board.service;
import com.example.board.repository.PostRepository;
import com.example.board.repository.UserRepository;
import org.apache.coyote.http11.upgrade.UpgradeServletOutputStream;
import org.springframework.stereotype.*;
import com.example.board.model.Post;
import com.example.board.model.User;
import com.example.board.repository.UserRepository;
import java.util.List;
import java.util.Optional;


@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postResporitory,UserRepository userRepository){
        this.postRepository = postResporitory;
        this.userRepository = userRepository;
    }
    // 전체 게시물 조회
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }
    // 특정사용자의 게시물 조회
    public List<Post> getPostByUserId(Long userId){
        return postRepository.findByUserId(userId);
    }
    // 게시물 단건 조회
    public Post getPost(Long id){
        return postRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("게시물 없음!"));
    }
    // 게시물 추가
    public Post addPost(Long userId, Post postData){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("사용자 없음!"));
        user.addPost(postData);
        return postRepository.save(postData);
    }
    // 게시물 수정
    public Post updatePost(Long id, Post postdata){
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("게시물 없음!"));

        if(postdata.getTitle()!=null){
            post.setTitle(postdata.getTitle());
        }
        if(postdata.getContent()!=null){
            post.setContent(postdata.getContent());
        }

        return postRepository.save(post);

    }
    // 게시물 삭제
    public void deletePost(Long id){
        postRepository.deleteById(id);
    }
}
