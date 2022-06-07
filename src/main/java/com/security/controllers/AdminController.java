package com.security.controllers;

import com.security.db.entity.MyUser;
import com.security.db.repository.MyUserRepository;
import com.security.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
@RestController
public class AdminController {
    MyUserRepository myUserRepository;

    @Autowired
    public AdminController(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @GetMapping("/users")
    public List<MyUser> getUsers() {
        return myUserRepository.findAll();
    }


}
