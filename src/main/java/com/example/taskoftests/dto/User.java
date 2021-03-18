package com.example.taskoftests.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userId;
    private String id;
    private String body;
    private String title;

    public void setBodyAndTitleTo(String text) {
        this.title = text;
        this.body = text;
    }
}
