package com.example.taskoftests;

import com.example.taskoftests.dto.User;
import com.example.taskoftests.services.RequestService;
import com.example.taskoftests.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
public class UserServiceTests {

    @MockBean
    private RequestService requestService;

    @Autowired
    private UserService userService;

    @Test
    public void testPutUsersFromNullList() {
        Mockito.when(requestService.getUsers()).thenReturn(null);

        Assertions.assertEquals(0, userService.getUsersAmount().getCount(),"List is not empty!");
    }

    @Test
    public void testGetUsersCountFromNullList() {
        Mockito.when(requestService.getUsers()).thenReturn(null);

        Assertions.assertEquals(0,userService.getNthUserAndChange(1).size(),"List is not empty!");
    }

    @Test
    public void testGetUsersCountFromHugeList() {
        Long hugeNumber = 10000L;
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < hugeNumber; i++) {
            userList.add(new User(String.valueOf(i),null,null,null));
        }
        Mockito.when(requestService.getUsers()).thenReturn(userList);

        Assertions.assertEquals(userService.getUsersAmount().getCount(),hugeNumber,"List doesn't have "+hugeNumber+" users");
    }
}
