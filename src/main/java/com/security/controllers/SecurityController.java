package com.security.controllers;

import com.security.controllers.models.LoginForm;
import com.security.db.entity.MyUser;
import com.security.service.JwtTokenService;
import com.security.service.MyUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
public class SecurityController {
    Logger logger = LoggerFactory.getLogger(SecurityController.class);
    private AuthenticationManager authenticationManager;
    private MyUserService myUserService;
    private JwtTokenService jwtTokenService;
    @Autowired
    public SecurityController(AuthenticationManager authenticationManager, MyUserService myUserService, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.myUserService = myUserService;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> getToken(@RequestBody LoginForm loginForm) throws Exception {
        logger.info("auth begin");
        Objects.requireNonNull(loginForm, "no auth form is present");
        logger.info("auth user: " + loginForm);
        if (loginForm.isFilled()) {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
        }
        logger.info("auth end");
        return ResponseEntity.ok(jwtTokenService.generateToken(myUserService.loadUserByUsername(loginForm.getUsername())));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody LoginForm loginForm) {
        logger.info("registration: " + loginForm);
        MyUser userToAdd = new MyUser(loginForm.getUsername(),loginForm.getPassword());
        if (myUserService.addUser(userToAdd)) return ResponseEntity.ok(userToAdd); else return null;
    }
}
