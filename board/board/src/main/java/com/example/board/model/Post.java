package com.example.board.model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.*;
@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //기본 생성자 - ioC가 객체 관리할때 필요
    public Post() {}

    public Post(String title, String content){
        this.title = title;
        this.content = content;
    }
}
