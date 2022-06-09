package com.security.controllers.user.models;

import lombok.Data;

@Data
public class LoginForm {
    private String username;
    private String password;
    public boolean isFilled() {
        return this.username != null && this.password != null;
    }
}
