package com.security;

import com.security.service.token.TokenChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class RequestFilter extends OncePerRequestFilter {
    private final TokenChecker tokenChecker;
    private final UserDetailsService userDetailsService;
    private final Logger logger = LoggerFactory.getLogger(RequestFilter.class);

    @Autowired
    public RequestFilter(TokenChecker tokenChecker, UserDetailsService userDetailsService) {
        this.tokenChecker = tokenChecker;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null) {
            String token = Arrays.stream(header.split(" ")).toList().get(1);
            String username = tokenChecker.getUsernameFromToken(token);
            logger.info("user: " + username);
            if (username != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (userDetails != null && userDetails.isEnabled()) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    logger.info("load in context");
                } else logger.info("no such enabled user");
            } else logger.info("invalid token");
        } else logger.info("auth header lack");
        filterChain.doFilter(request, response);
    }
}
