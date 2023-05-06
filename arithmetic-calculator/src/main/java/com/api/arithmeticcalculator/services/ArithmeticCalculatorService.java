package com.api.arithmeticcalculator.services;

import com.api.arithmeticcalculator.dtos.CalculateDto;
import com.api.arithmeticcalculator.models.OperationModel;
import com.api.arithmeticcalculator.models.RecordModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.UUID;

@Service
public class ArithmeticCalculatorService {

    private final RandomApiService randomApiService;
    private final RecordService recordService;
    private final OperationService operationService;

    public ArithmeticCalculatorService (RandomApiService randomApiService,
                                        RecordService recordService,
                                        OperationService operationService) {
        this.randomApiService = randomApiService;
        this.recordService = recordService;
        this.operationService = operationService;
    }

    public CalculateDto calculate(String userId, Integer userBalance, BigDecimal firstNumber, BigDecimal secondNumber, String type) {
        BigDecimal result;

        switch (type) {
            case "addition" -> result = firstNumber.add(secondNumber);
            case "subtraction" -> result = firstNumber.subtract(secondNumber);
            case "multiplication" -> result = firstNumber.multiply(secondNumber);
            case "division" ->
                    result = firstNumber.divide(secondNumber, RoundingMode.HALF_DOWN).setScale(2, RoundingMode.HALF_DOWN);
            case "square_root" -> result = firstNumber.sqrt(MathContext.DECIMAL64);
            case "random_string" -> result = new BigDecimal(randomApiService.findRandomNumber());
            default -> {
                return null;
            }
        }

        OperationModel operationModel = operationService.getOperationByType(type);
        CalculateDto calculateDto = new CalculateDto();
        calculateDto.setResult(result);
        if (operationModel != null) {
            userBalance -= operationModel.getCost();

        }

        calculateDto.setUserBalance(userBalance);
        addToRecord(userId, userBalance, result, operationModel);

        return calculateDto;
    }

    private void addToRecord(String userId, Integer userBalance, BigDecimal result, OperationModel operationModel) {

        RecordModel recordModel = new RecordModel();

        recordModel.setOperationId(operationModel != null ? operationModel.getId() : null);
        recordModel.setUserId(UUID.fromString(userId));
        recordModel.setAmount(operationModel != null ? operationModel.getCost() : 0);
        recordModel.setUserBalance(userBalance);
        recordModel.setOperationResponse(result);

        recordService.addRecord(recordModel);
    }

}
