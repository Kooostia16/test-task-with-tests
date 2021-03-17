package com.example.taskoftests.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class UsersAmount {
    private long count;

    public UsersAmount() {}

    public UsersAmount(long i) { count = i; }
}
