package com.api.arithmeticcalculator.controllers;

import com.api.arithmeticcalculator.dtos.CalculateDto;
import com.api.arithmeticcalculator.services.ArithmeticCalculatorService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/")
@RestController
public class ArithmeticCalculatorController {

    private final ArithmeticCalculatorService arithmeticCalculatorService;

    public ArithmeticCalculatorController(ArithmeticCalculatorService arithmeticCalculatorService) {
        this.arithmeticCalculatorService = arithmeticCalculatorService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<Object> calculate(@PathParam("firstNumber") BigDecimal firstNumber,
                                            @PathParam("secondNumber") BigDecimal secondNumber,
                                            @PathParam("type") String type,
                                            @RequestHeader("userBalance") Integer userBalance,
                                            @RequestHeader("userId") String userId) {
        try {
            CalculateDto response = arithmeticCalculatorService.calculate(firstNumber, secondNumber, type, userBalance, userId);

            return  response != null
                    ? ResponseEntity.status(HttpStatus.OK).body(response)
                    : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insuficient Balance");

        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return null;
    }
}
