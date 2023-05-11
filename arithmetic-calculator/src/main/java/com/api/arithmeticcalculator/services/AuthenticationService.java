package com.api.arithmeticcalculator.services;

import com.api.arithmeticcalculator.dtos.AuthenticationDto;
import com.api.arithmeticcalculator.dtos.AuthorizationDto;
import com.api.arithmeticcalculator.dtos.TokenDto;
import com.api.arithmeticcalculator.models.UserModel;
import com.api.arithmeticcalculator.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class AuthenticationService {

    private static final Integer USER_BALANCE = 200;
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel addUser(AuthenticationDto authenticationDto) {
        UserModel userModel = new UserModel();
        userModel.setUsername(authenticationDto.getUserName());
        userModel.setPassword(encryptPassword(authenticationDto.getPassword()));
        userModel.setStatus("Active");

        return userRepository.save(userModel);
    }

    public UserModel getUserByUserName(String userName) {
        return userRepository.getUserByUserName(userName)
                .orElse(null);
    }

    public AuthorizationDto postLogin(String userName) {
        UserModel userModel = this.getUserByUserName(userName);

        AuthorizationDto authorizationDto = new AuthorizationDto();
        authorizationDto.setUserId(userModel.getId().toString());
        authorizationDto.setUserBalance(USER_BALANCE);

        return authorizationDto;
    }

    public TokenDto getToken(AuthenticationDto authenticationDto) {
        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken("Basic " + generateBasicAuthToken(authenticationDto.getUserName(), authenticationDto.getPassword()));

        return tokenDto;
    }

    private String generateBasicAuthToken(String username, String password) {
        String credentials = username + ":" + password;
        byte[] credentialsBytes = credentials.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(credentialsBytes);
    }

    public String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
