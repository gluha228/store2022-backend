package com.security.service.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class TokenGenerator {
    @Value("${secret}")
    private String secret;
    @Value("${tokenLifeMinutes}")
    private long tokenLifeMinutes;
    private final Logger logger = LoggerFactory.getLogger(TokenGenerator.class);
    public String generateToken(UserDetails userDetails) {
        logger.info("create token for user: " + userDetails.getUsername());
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenLifeMinutes*60*1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
