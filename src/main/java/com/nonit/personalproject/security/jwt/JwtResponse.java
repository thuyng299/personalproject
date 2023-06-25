package com.nonit.personalproject.security.jwt;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class JwtResponse implements Serializable {
    private String token;
    private String type = "Bearer";
    private String username;
    private String employeeName;
    private List<String> roles;

    public JwtResponse(String token, String username, String employeeName, List<String> roles) {
        this.token = token;
        this.username = username;
        this.employeeName = employeeName;
        this.roles = roles;
    }
}
