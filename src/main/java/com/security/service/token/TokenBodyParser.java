package com.security.service.token;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenBodyParser {
    @Value("${secret}")
    private String secret;
    private final Logger logger = LoggerFactory.getLogger(TokenChecker.class);
    public Claims getTokenBody(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (SignatureException e) {
            logger.warn("wrong signature");
        } catch (MalformedJwtException e) {
            logger.warn("invalid token");
        } catch (ExpiredJwtException e) {
            logger.warn("expired token");
        }
        return null;
    }
}
