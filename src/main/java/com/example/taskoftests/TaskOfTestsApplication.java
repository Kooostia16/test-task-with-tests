package com.example.taskoftests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskOfTestsApplication {

    static public User user;

    public static void main(String[] args) {
        SpringApplication.run(TaskOfTestsApplication.class, args);
    }
}
