package com.security.service.token;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenChecker {
    private final Logger logger = LoggerFactory.getLogger(TokenChecker.class);
    private final TokenBodyParser tokenBodyParser;

    @Autowired
    public TokenChecker(TokenBodyParser tokenBodyParser) {
        this.tokenBodyParser = tokenBodyParser;
    }

    public String getUsernameFromToken(String token) {
        logger.info("get user from token: " + token);
        Claims tokenBody = tokenBodyParser.getTokenBody(token);
        if (tokenBody == null) return null;
        logger.info("token validated successfully");
        return tokenBody.getSubject();
    }
}
