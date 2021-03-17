package com.example.taskoftests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;

public class WithMockingTests {

    static String url = "http://jsonplaceholder.typicode.com/posts";
    static HttpRequests hr = Mockito.mock(HttpRequests.class);

    @BeforeAll
    static public void mockingUsersWithMissingFields() {
        User[] users1 = new User[2];
        users1[0] = new User(null,null,null,null);
        users1[1] = new User("1","2",null,"test");

        Mockito.when(hr.getUsersFrom(url)).thenReturn(users1);
    }

    @Test
    public void testUsersSize() {
        User[] users = hr.getUsersFrom(url);
        assertTrue("users is empty!",users.length > 0);
    }

    @Test
    public void testUser() {
        User[] users = hr.getUsersFrom(url);
        for (int i = 0; i < users.length; i++) {
            User user = users[i];
            assertTrue("user is null",user != null);
            assertTrue("user id is null!",user.getId() != null);
            assertTrue("user userid is null!",user.getUserId() != null);
            assertTrue("user title is null!!",user.getTitle() != null);
            assertTrue("user body is null!!",user.getBody() != null);
        }

    }
}
