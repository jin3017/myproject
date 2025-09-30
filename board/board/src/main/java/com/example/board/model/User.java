package com.example.board.model;

import jakarta.persistence.*;
import java.util.*;
import com.example.board.model.Post;
import lombok.*;
@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    // 생성자(사용자 정의)
    public User(long id, String name, String email, String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    //default 생성자 - 필수
    public User() {}


    // 헬퍼 메서드
    public void addPost(Post post){
        this.posts.add(post);
        post.setUser(this);
    }
}
