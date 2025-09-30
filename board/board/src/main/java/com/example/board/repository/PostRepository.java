package com.example.board.repository;
import com.example.board.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.*;

public interface PostRepository extends JpaRepository<Post, Long> {
    // 내가 만든 함수야 jpa 니가 이거 만들어줘 시그니처보고
    List<Post> findByUserId(Long userId);

    boolean existsByIdAndUserId(Long id, Long userId);
}
