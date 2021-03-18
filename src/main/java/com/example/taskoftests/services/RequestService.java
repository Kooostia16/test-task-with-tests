package com.example.taskoftests.services;

import com.example.taskoftests.dto.User;
import com.sun.istack.internal.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RequestService {

    @Value("${testtask.url}")
    public String url;

    private final RestTemplate restTemplate;

    private static ParameterizedTypeReference<List<User>> typeRef = new ParameterizedTypeReference<List<User>>() {};

    public @Nullable List<User> getUsers() {
        ResponseEntity<List<User>> respEnt = restTemplate.exchange(url, HttpMethod.GET, null, typeRef);

        if (HttpStatus.OK == respEnt.getStatusCode()) {
            return respEnt.getBody();
        }

        throw new IllegalStateException("Can't retrieve list of users");
    }
}
