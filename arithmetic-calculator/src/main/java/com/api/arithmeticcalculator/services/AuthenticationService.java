package com.api.arithmeticcalculator.services;

import com.api.arithmeticcalculator.dtos.AuthenticationDto;
import com.api.arithmeticcalculator.models.UserModel;
import com.api.arithmeticcalculator.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel addUser(AuthenticationDto authenticationDto) {
        UserModel userModel = new UserModel();
        userModel.setUsername(authenticationDto.getUserName());
        userModel.setPassword(new BCryptPasswordEncoder().encode(authenticationDto.getPassword()));
        userModel.setStatus("Active");

        return userRepository.save(userModel);
    }

    public UserModel getUserByUserName(String userName) {
        return userRepository.getUserByUserName(userName)
                .orElseThrow(null);
    }
}
