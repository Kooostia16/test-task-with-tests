package com.example.taskoftests;

import com.example.taskoftests.dto.User;
import com.example.taskoftests.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class WithMockingTests {

    static String url = "http://jsonplaceholder.typicode.com/posts";
    static UserService hr = Mockito.mock(UserService.class);

    @BeforeAll
    static public void mockingUsersWithMissingFields() {
        List<User> users1 = new ArrayList<>();
        users1.add(new User(null,null,null,null));
        users1.add(new User("1","2",null,"test"));

        Mockito.when(hr.getUsersFrom(url)).thenReturn(users1);
    }

    @Test
    public void testUsersSize() {
        List<User> users = hr.getUsersFrom(url);
        assertTrue("users is empty!",users.size() > 0);
    }

    @Test
    public void testUser() {
        List<User> users = hr.getUsersFrom(url);
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            assertTrue("user is null",user != null);
            assertTrue("user id is null!",user.getId() != null);
            assertTrue("user userid is null!",user.getUserId() != null);
            assertTrue("user title is null!!",user.getTitle() != null);
            assertTrue("user body is null!!",user.getBody() != null);
        }

    }
}
