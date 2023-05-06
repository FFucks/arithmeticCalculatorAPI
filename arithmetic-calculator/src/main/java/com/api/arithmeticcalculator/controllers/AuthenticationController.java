package com.api.arithmeticcalculator.controllers;

import com.api.arithmeticcalculator.DefaultError;
import com.api.arithmeticcalculator.dtos.AuthenticationDto;
import com.api.arithmeticcalculator.dtos.CalculateDto;
import com.api.arithmeticcalculator.models.UserModel;
import com.api.arithmeticcalculator.services.AuthenticationService;
import jakarta.validation.Valid;
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
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authentication")
    public ResponseEntity<Object> authenticate(@RequestBody @Valid AuthenticationDto authenticationDto) {
        UserModel userModel = authenticationService.getUserByUserName(authenticationDto.getUserName());
        if (null != userModel) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DefaultError(HttpStatus.BAD_REQUEST.value(),
                    "User already used"));
        }
        userModel = authenticationService.addUser(authenticationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

}
