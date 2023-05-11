package com.api.arithmeticcalculator.dtos;


import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.UUID;

public class RecordDto {
    private UUID id;
    @Column(name = "operation_id")
    private UUID operationId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "user_balance")
    private Integer userBalance;

    @Column(name = "operation_response")
    private BigDecimal operationResponse;

    @Column(name = "date")
    private String date;
    private String type;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOperationId() {
        return operationId;
    }

    public void setOperationId(UUID operationId) {
        this.operationId = operationId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(Integer userBalance) {
        this.userBalance = userBalance;
    }

    public BigDecimal getOperationResponse() {
        return operationResponse;
    }

    public void setOperationResponse(BigDecimal operationResponse) {
        this.operationResponse = operationResponse;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
