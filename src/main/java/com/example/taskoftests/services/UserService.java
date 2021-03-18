package com.example.taskoftests.services;

import com.example.taskoftests.dto.User;
import com.example.taskoftests.dto.UsersAmount;
import com.example.taskoftests.exceptions.OnNegativeValueException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${testtask.text}")
    public String changeText;

    @Value("${testtask.url}")
    public String url;

    private final RestTemplate rt;

    static ParameterizedTypeReference<List<User>> typeRef = new ParameterizedTypeReference<List<User>>() {};
    static List<User> ulist;

    public List<User> getUsersFrom() {

        try {
            ResponseEntity<List<User>> respEnt = rt.exchange(url, HttpMethod.GET, null, typeRef);
            return respEnt.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    public UsersAmount getUsersAmount() {

        ulist = getUsersFrom();

        Map<String, User> hm = new HashMap<>();
        ulist.stream().map(s -> hm.put(s.getUserId(),s)).count();
        long usersN = hm.size();

        return new UsersAmount(usersN);
    }

    public List<User> getNthUserAndChange(String nn) {

        ulist = getUsersFrom();

        int n = Integer.parseInt(nn)-1;

        if (n < 0) throw new OnNegativeValueException();
        if (ulist.size()-1 < n) return ulist;

        User u = ulist.get(n);
        u.setBody(changeText);
        u.setTitle(changeText);

        return ulist;
    }
}
