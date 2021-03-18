package com.example.taskoftests.services;

import com.example.taskoftests.dto.User;
import com.example.taskoftests.dto.UsersAmount;

import com.example.taskoftests.exceptions.OnNegativeValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.util.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    @Value("${testtask.text}")
    public String changeText;

    @Value("${testtask.url}")
    public String url;

    private RestTemplate rt;

    @Autowired
    public void getRestTemplate(RestTemplate rt) {
        this.rt = rt;
    }

    public List<User> getUsersFrom() {
        ParameterizedTypeReference<List<User>> typeRef = new ParameterizedTypeReference<List<User>>() {};
        ResponseEntity<List<User>> respEnt = rt.exchange(url, HttpMethod.GET, null, typeRef);

        return respEnt.getBody();
    }

    public UsersAmount getUsersAmount() {
        List<User> ulist = getUsersFrom();
        if (ulist == null) ulist = new ArrayList<>();

        HashSet<String> hs = new HashSet<>();
        long usersN = ulist.stream().filter(s -> {
            if (!hs.contains(s.getUserId())) {
                hs.add(s.getUserId());
                return true;
            }
            return false;
        }).count();

        return new UsersAmount(usersN);
    }

    public List<User> getNthUserAndChange(String nn) {
        List<User> ulist = getUsersFrom();
        if (ulist == null) ulist = new ArrayList<>();

        int n = Integer.parseInt(nn);
        if (n < 0) throw new OnNegativeValueException();
        if (ulist.size()-1 < n) return ulist;

        User u = ulist.get(n);
        u.setBody(changeText);
        u.setTitle(changeText);

        return ulist;
    }
}
