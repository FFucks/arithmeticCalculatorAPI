package com.api.arithmeticcalculator.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AuthenticationDto {

    @JsonProperty("username")
    @NotBlank
    @Email
    private String userName;

    @JsonProperty("password")
    @NotBlank
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
