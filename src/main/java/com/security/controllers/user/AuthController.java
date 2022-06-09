package com.security.controllers.user;

import com.security.controllers.user.models.LoginForm;
import com.security.service.token.TokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationManager authenticationManager;
    private final TokenGenerator tokenGenerator;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, TokenGenerator tokenGenerator, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.tokenGenerator = tokenGenerator;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/auth")
    public String getToken(@RequestBody LoginForm loginForm) {
        logger.info("auth begin");
        if (loginForm == null) {
            logger.info("no auth form is present");
            return "no auth form is present";
        }
        if (!loginForm.isFilled()) {
            logger.info("no login or password");
            return "no login or password";
        }
        logger.info("auth: " + loginForm);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
        return tokenGenerator.generateToken(userDetailsService.loadUserByUsername(loginForm.getUsername()));
    }
}
