package com.security.db.repository;

import com.security.db.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    MyUser getFirstByUsername(String username);
    List<MyUser> findAll();
}