package com.library.system.DTO;

import com.library.system.Entities.userRole;

public class LoginResponseDTO {

    private String authToken;
    private userRole role;
    private String message;
    private Long userId;


    public LoginResponseDTO(String authToken, userRole role, String message, Long userId) {
        this.authToken = authToken;
        this.role = role;
        this.message = message;
        this.userId = userId;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public userRole getRole() {
        return role;
    }

    public void setRole(userRole role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
