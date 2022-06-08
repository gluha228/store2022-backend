package com.security.controllers;

import com.security.controllers.models.LoginForm;
import com.security.db.entity.MyUser;
import com.security.service.JwtTokenService;
import com.security.service.MyUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class SecurityController {
    private final Logger logger = LoggerFactory.getLogger(SecurityController.class);
    private final AuthenticationManager authenticationManager;
    private final MyUserService myUserService;
    private final JwtTokenService jwtTokenService;
    @Autowired
    public SecurityController(AuthenticationManager authenticationManager, MyUserService myUserService, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.myUserService = myUserService;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/auth")
    public String getToken(@RequestBody LoginForm loginForm) {
        logger.info("auth begin");
        Objects.requireNonNull(loginForm, "no auth form is present");
        logger.info("auth user: " + loginForm);
        if (loginForm.isFilled()) {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
        }
        logger.info("auth end");
        return jwtTokenService.generateToken(myUserService.loadUserByUsername(loginForm.getUsername()));
    }

    @PostMapping("/registration")
    public String registration(@RequestBody LoginForm loginForm) {
        logger.info("registration: " + loginForm);
        MyUser userToAdd = new MyUser(loginForm.getUsername(),loginForm.getPassword());
        if (myUserService.addUser(userToAdd)) return "success"; else return "failed";
    }
}
