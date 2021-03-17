package com.example.taskoftests.controllers;

import com.example.taskoftests.dto.User;
import com.example.taskoftests.dto.UsersAmount;
import com.example.taskoftests.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TestController {

    @Value("${testtask.text}")
    public String changeText;

    @Value("${testtask.url}")
    public String url;

    @Autowired
    public UserService us;

    @GetMapping("/count")
    public UsersAmount getUniqueUsers() {
        return us.getUsersAmount(url);
    }

    @GetMapping("/change-user")
    public User setUser(@RequestParam(name = "n",required = false,defaultValue = "4") String n) {
        return us.getNthUserAndChange(url, n, changeText);
    }
}
