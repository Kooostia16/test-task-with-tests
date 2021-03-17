package com.example.taskoftests;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class User {
    public User(String userId, String id, String body, String title) {
        this.userId = userId;
        this.id = id;
        this.body = body;
        this.title = title;
    }

    public User() {
    }

    private String userId;
    private String id;
    private String body;
    private String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", id='" + id + '\'' +
                ", body='" + body + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
