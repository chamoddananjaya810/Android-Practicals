package com.example.networkpactical.dto;

public class LoginRequestDTO {

    private String email;
    private  String passwordl;

    public String getEmail() {
        return email;
    }

    public LoginRequestDTO(String email, String passwordl) {
        this.email = email;
        this.passwordl = passwordl;
    }

    public LoginRequestDTO() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordl() {
        return passwordl;
    }

    public void setPasswordl(String passwordl) {
        this.passwordl = passwordl;
    }
}
