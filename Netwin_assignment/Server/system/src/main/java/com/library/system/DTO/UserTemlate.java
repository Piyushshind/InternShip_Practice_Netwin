package com.library.system.DTO;

import com.library.system.Entities.userRole;

public class UserTemlate {
    private String username;
    private String password;
    private userRole role;
    private String userEmail;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String UserEmail) {
        this.userEmail = UserEmail;
    }

    public userRole getRole() {
        return role;
    }

    public void setRole(userRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
