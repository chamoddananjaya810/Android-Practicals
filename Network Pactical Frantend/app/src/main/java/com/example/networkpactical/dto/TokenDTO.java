package com.example.networkpactical.dto;

public class TokenDTO {
    private String accessToken;
    private String refeshToken; // ඔබේ API එකේ Spelling වලට අනුව සකසා ඇත

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefeshToken() {
        return refeshToken;
    }

    public void setRefeshToken(String refeshToken) {
        this.refeshToken = refeshToken;
    }
}