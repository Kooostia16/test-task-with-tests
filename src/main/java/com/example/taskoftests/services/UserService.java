package com.example.taskoftests.services;

import com.example.taskoftests.dto.User;
import com.example.taskoftests.dto.UsersAmount;
import com.example.taskoftests.extractors.UserExtractor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    RestTemplate rt;

    @Autowired
    private void setRt() {
        rt = new RestTemplate();
    }

    public List<User> getUsersFrom(String url) {
        RequestCallback requestCallback = rt.acceptHeaderRequestCallback(null);
        List<User> users = rt.execute(url, HttpMethod.GET, requestCallback, new UserExtractor());

        return users;
    }

    public UsersAmount getUsersAmount(String url) {
        long usersN = getUsersFrom(url).stream().distinct().count();

        return new UsersAmount(usersN);
    }

    public User getNthUserAndChange(String url, String nn, String text) {
        List<User> l = getUsersFrom(url);
        User u;

        if (nn == null) return new User();

        int n = Integer.parseInt(nn);
        if (l.size()-1 < n) return new User();

        u = l.get(n);
        u.setBody(text);
        u.setTitle(text);

        return u;
    }
}
