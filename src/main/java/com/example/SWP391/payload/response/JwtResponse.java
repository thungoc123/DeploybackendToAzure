package com.example.SWP391.Payload.Response;

public class JwtResponse {

    private final String accessToken;

    public JwtResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}

