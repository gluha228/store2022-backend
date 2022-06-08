package com.security.service;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenService {
    private static final String secret = "top_secret_228";
    private static final Long tokenLifeTimeMinutes = 15L;
    private final Logger logger = LoggerFactory.getLogger(JwtTokenService.class);
    private final MyUserService myUserService;
    @Autowired
    public JwtTokenService(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    public String generateToken(UserDetails userDetails) {
        logger.info("create token for user: " + userDetails.getUsername());
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenLifeTimeMinutes*60*1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    private Claims getTokenBody(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }
        catch (SignatureException e) { logger.warn("wrong signature"); }
        catch (MalformedJwtException e) { logger.warn("invalid token"); }
        catch (ExpiredJwtException e) { logger.warn("expired token"); }
        return null;
    }

    public UserDetails getUsernameFromValidToken(String token) {
        logger.info("get user from token: " + token);
        Claims tokenBody = getTokenBody(token);
        if (tokenBody == null) return null;
        UserDetails userDetails = myUserService.loadUserByUsername(tokenBody.getSubject());
        if (tokenBody.getExpiration().getTime() < System.currentTimeMillis() && userDetails != null) return null;
        logger.info("token validated successfully");
        return userDetails;
    }
}
