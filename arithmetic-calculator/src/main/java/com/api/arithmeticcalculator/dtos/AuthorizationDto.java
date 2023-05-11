package com.api.arithmeticcalculator.dtos;

public class AuthorizationDto {

    private String userId;
    private Integer userBalance;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(Integer userBalance) {
        this.userBalance = userBalance;
    }
}
