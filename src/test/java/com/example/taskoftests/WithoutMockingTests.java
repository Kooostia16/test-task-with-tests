package com.example.taskoftests;


import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import static org.junit.Assert.assertTrue;

public class WithoutMockingTests {

    String url = "http://jsonplaceholder.typicode.com/posts";

    @Test
    public void testUsersSize() {
        RestTemplate rt = new RestTemplate();
        User[] users = rt.getForObject(url, User[].class);
        assertTrue("users is empty!",users.length > 0);
    }

    @Test
    public void testUser() {
        RestTemplate rt = new RestTemplate();
        User[] users = rt.getForObject(url, User[].class);
        for (User user: users) {
            assertTrue("user is null",user != null);
            assertTrue("user id is null!",user.getId() != null);
            assertTrue("user userid is null!",user.getUserId() != null);
            assertTrue("user title is null!!",user.getTitle() != null);
            assertTrue("user body is null!!",user.getBody() != null);
        }

    }
}
