package com.api.arithmeticcalculator.dtos;

import java.math.BigDecimal;

public class CalculateDto {

    private BigDecimal result;
    private Integer userBalance;

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public Integer getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(Integer userBalance) {
        this.userBalance = userBalance;
    }
}
