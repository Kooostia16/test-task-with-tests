package com.example.taskoftests.services;

import com.example.taskoftests.dto.User;
import com.example.taskoftests.dto.UsersAmount;
import com.example.taskoftests.exceptions.OnNegativeValueException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${testtask.text}")
    public String changeText;

    private final RequestService requestService;

    public UsersAmount getUsersAmount() {
        List<User> users = requestService.getUsers();

        if (users == null) {
            users = new ArrayList<>();
        }

        long usersCount = users.stream()
                .map(User::getUserId)
                .filter(Objects::nonNull)
                .distinct()
                .count();

        return new UsersAmount(usersCount);
    }

    public List<User> getNthUserAndChange(Integer userToModify) {
        List<User> users = requestService.getUsers();

        if (users == null) {
            return new ArrayList<>();
        }

        if (userToModify < 0) {
            throw new OnNegativeValueException();
        }

        if (users.size() < userToModify) {
            return users;
        }

        User u = users.get(userToModify - 1);
        u.setBodyAndTitleTo(changeText);

        return users;
    }
}
