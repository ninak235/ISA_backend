package com.ISA.ISAProject.Dto;

public class JwtAuthenticationRequestDto {

    private String userName;
    private String password;

    public JwtAuthenticationRequestDto() {
        super();
    }

    public JwtAuthenticationRequestDto(String userName, String password) {
        this.setUsername(userName);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}