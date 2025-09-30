package com.example.board.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import com.example.board.service.PostService;
import com.example.board.model.Post;
import java.util.*;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    //전체 게시물 조회 / GET /posts
    // 특정 사용자의 게시글 조회 - GET /posts?userId={id}
    @GetMapping
    public List<Post> getAllPosts(@RequestParam(required = false) Long userId){
        if(userId == null){
            //전체 게시물 조회
            return postService.getAllPosts();
        }
        else {
            return postService.getPostByUserId(userId);
        }

    }
    // 게시물 단건 조회 - GET /posts/:id
    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id){
        return postService.getPost(id);
    }
    // 게시물 추가 - POST /posts?userId={id}
    @PostMapping
    public Post addPost(@RequestParam Long userId, @RequestBody Post postData){
        return postService.addPost(userId, postData);
    }
    // 게시물 수정 - PATCH /posts/:id
    @PreAuthorize("hasRole('ADMIN') or @authz.isOwnerOfPost(#postId)")
    @PatchMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post postData){
        return postService.updatePost(id, postData);
    }
    // 게시물 삭제 - DELETE /posts/:id
    @PreAuthorize("hasRole('ADMIN') or @authz.isOwnerOfPost(#postId)")
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id){
        postService.deletePost(id);
    }

}
