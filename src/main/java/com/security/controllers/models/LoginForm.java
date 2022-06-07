package com.security.controllers.models;

import lombok.Data;

@Data
public class LoginForm {
    private String username;
    private String password;
    public boolean isFilled() {
        return this.username != null && this.password != null;
    }
}
