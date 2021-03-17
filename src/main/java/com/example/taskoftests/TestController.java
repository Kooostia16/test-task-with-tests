package com.example.taskoftests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class TestController {

    @Value("{testtask.text}")
    public String changeText;

    @Value("{testtask.url}")
    public String url;

    @Autowired
    public HttpRequests hr;

    @GetMapping("/count")
    public List<User> getUniqueUsers() {
        User[] users = hr.getUsersFrom(url);

        final int[] lastId = new int[1];
        return Arrays.stream(users).sorted(Comparator.comparingInt(user -> Integer.parseInt(user.getUserId()))).filter(s -> {
            if (lastId[0] != Integer.parseInt(s.getUserId())) {
                lastId[0] = Integer.parseInt(s.getUserId());
                return true;
            } else return false;
        }).collect(Collectors.toList());
    }

    @GetMapping("/change-user")
    public User setUser() {
        User[] users = hr.getUsersFrom(url);
        users[3].setBody(changeText);
        users[3].setTitle(changeText);
        TaskOfTestsApplication.user = users[3];

        return users[3];
    }
}
