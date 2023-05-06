package com.api.arithmeticcalculator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RandomApiService {

    @Value("${client.randomOrg.url}")
    private String randomOrgUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public RandomApiService (RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String findRandomNumber() {
        String randomNumber = "";
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    randomOrgUrl,
                    HttpMethod.GET,
                    null,
                    String.class
            );
            randomNumber = responseEntity.getBody();

        } catch (Exception e) {
            System.out.println(e);
        }

        assert randomNumber != null;
        return randomNumber.replace("\n", "");
    }

}
