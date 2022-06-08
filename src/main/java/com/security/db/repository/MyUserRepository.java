package com.security.db.repository;

import com.security.db.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    MyUser getFirstByUsername(String username);
}