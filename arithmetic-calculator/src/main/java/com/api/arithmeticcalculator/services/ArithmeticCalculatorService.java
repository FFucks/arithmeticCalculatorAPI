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

    public CalculateDto calculate(BigDecimal firstNumber, BigDecimal secondNumber, String type,
                                  Integer userBalance, String userId) throws Exception {
        BigDecimal result;
        CalculateDto calculateDto = new CalculateDto();

        OperationModel operationModel = operationService.getOperationByType(type);
        if (operationModel != null) {
            if (operationModel.getCost() > userBalance) {
                return null;
            }

            userBalance -= operationModel.getCost();
            calculateDto.setUserBalance(userBalance);
        }


        switch (type) {
            case "addition" -> result = firstNumber.add(secondNumber);
            case "subtraction" -> result = firstNumber.subtract(secondNumber);
            case "multiplication" -> result = firstNumber.multiply(secondNumber);
            case "division" ->
                    result = firstNumber.divide(secondNumber, RoundingMode.HALF_DOWN).setScale(2, RoundingMode.HALF_DOWN);
            case "square_root" -> result = firstNumber.sqrt(MathContext.DECIMAL64);
            case "random_string" -> result = new BigDecimal(randomApiService.findRandomNumber());
            default -> {
                throw new Exception("Operation type does not exist");
            }
        }

        calculateDto.setResult(result);
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
