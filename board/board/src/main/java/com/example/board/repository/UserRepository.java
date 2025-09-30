package com.example.board.repository;
import com.example.board.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.*;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);

    Optional<User> findIdByEmail(String email);
}