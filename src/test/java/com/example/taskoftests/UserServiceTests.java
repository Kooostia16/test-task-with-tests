package com.example.taskoftests;

import com.example.taskoftests.services.RequestService;
import com.example.taskoftests.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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

        Assertions.assertThrows(NullPointerException.class, () -> userService.getNthUserAndChange(2), "List is null!");
    }

    @Test
    public void testGetUsersCountFromNullList() {
        Mockito.when(requestService.getUsers()).thenReturn(null);

        Assertions.assertThrows(NullPointerException.class, () -> userService.getUsersAmount(), "List is null!");
    }
}
