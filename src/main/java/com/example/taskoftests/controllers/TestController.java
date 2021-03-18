package com.example.taskoftests.controllers;

import com.example.taskoftests.dto.User;
import com.example.taskoftests.dto.UsersAmount;
import com.example.taskoftests.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    public UserService us;

    @GetMapping("/count")
    public UsersAmount retrieveUniqueUsers() {
        return us.getUsersAmount();
    }

    @PutMapping("/change-user")
    public List<User> updateUser(@RequestParam(name = "n",required = false,defaultValue = "4") String n) {
        return us.getNthUserAndChange(n);
    }
}
