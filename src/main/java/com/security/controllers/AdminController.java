package com.security.controllers;

import com.security.db.entity.MyUser;
import com.security.db.repository.MyUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/admin")
@RestController
public class AdminController {
    private final MyUserRepository myUserRepository;
    private final Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    public AdminController(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @GetMapping("/users")
    public List<MyUser> getUsers() {
        return myUserRepository.findAll();
    }

    @PostMapping("/users/lock")
    public String lockUser(@RequestBody Long id) {
        MyUser myUser = myUserRepository.getById(id);
        myUser.setUnlocked(!myUser.getUnlocked());
        myUserRepository.save(myUser);
        logger.warn("user unlock change: " + myUser.getUsername() + ", " + !myUser.getUnlocked() + " -> " + myUser.getUnlocked());
        return "user unlock change: " + myUser.getUsername() + ", " + !myUser.getUnlocked() + " -> " + myUser.getUnlocked();
        //lock аккаунта блокирует аунтефикацию, но уже выданный токен будет работать
    }

    @PostMapping("/users/enable")
    public String enableUser(@RequestBody Long id) {
        MyUser myUser = myUserRepository.getById(id);
        myUser.setEnabled(!myUser.getEnabled());
        myUserRepository.save(myUser);
        logger.warn("user enable change: " + myUser.getUsername() + ", " + !myUser.getEnabled() + " -> " + myUser.getEnabled());
        return "user enable change: " + myUser.getUsername() + ", " + !myUser.getEnabled() + " -> " + myUser.getEnabled();
        //enable блокирует и токен
    }
}
