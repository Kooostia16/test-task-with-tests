package com.example.taskoftests;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpRequests {

    public User[] getUsersFrom(String url) {
        RestTemplate rt = new RestTemplate();
        User[] users = rt.getForObject(url, User[].class);

        return users;
    }

}
