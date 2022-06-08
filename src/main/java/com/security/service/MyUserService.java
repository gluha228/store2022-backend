package com.security.service;

import com.security.db.entity.MyUser;
import com.security.db.entity.Role;
import com.security.db.repository.MyUserRepository;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service
public class MyUserService implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(MyUserService.class);
    private final MyUserRepository myUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    public MyUserService(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
        this.addUser(new MyUser(1L, "admin", "admin", new HashSet<>(Collections.singletonList(new Role("ROLE_ADMIN"))), true, true));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("load user from db: " + username);
        return myUserRepository.getFirstByUsername(username);
    }

    public boolean addUser(MyUser myUser) {
        logger.info("add user: " + myUser.getUsername());
        if (loadUserByUsername(myUser.getUsername()) != null) return false;
        logger.info("success");
        myUser.setPassword(bCryptPasswordEncoder.encode(myUser.getPassword()));
        myUserRepository.save(myUser);
        return true;
    }

}
