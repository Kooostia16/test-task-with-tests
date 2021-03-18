package com.example.taskoftests.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userId;
    private String id;
    private String body;
    private String title;
}
