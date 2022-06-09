package com.security.controllers.user;

import com.security.controllers.user.models.LoginForm;
import com.security.db.entity.MyUser;
import com.security.db.repository.MyUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RegistrationController {
    private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    private final MyUserRepository myUserRepository;
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    public RegistrationController(MyUserRepository myUserRepository, UserDetailsService userDetailsService) {
        this.myUserRepository = myUserRepository;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/registration")
    public String registration(@RequestBody LoginForm loginForm) {
        if (loginForm != null && !loginForm.isFilled()) return "empty fields";
        logger.info("registration: " + loginForm);
        if (userDetailsService.loadUserByUsername(loginForm.getUsername()) == null) {
            logger.info("user already exists");
            return "user already exists";
        }
        myUserRepository.save(new MyUser(loginForm.getUsername(), bCryptPasswordEncoder.encode(loginForm.getPassword())));
        return "success";
    }
}
