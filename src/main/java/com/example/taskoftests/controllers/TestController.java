package com.example.taskoftests.controllers;

import com.example.taskoftests.dto.User;
import com.example.taskoftests.dto.UsersAmount;
import com.example.taskoftests.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TestController {

    private final UserService userService;

    @GetMapping("/count")
    public UsersAmount retrieveUniqueUsers() {
        return userService.getUsersAmount();
    }

    @PutMapping("/change-user")
    public List<User> updateUser(@RequestParam(required = false, defaultValue = "4") Integer userToModify) {
        return userService.getNthUserAndChange(userToModify);
    }
}
