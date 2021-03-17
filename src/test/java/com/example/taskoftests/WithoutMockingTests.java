package com.example.taskoftests;


import com.example.taskoftests.dto.User;
import com.example.taskoftests.extractors.UserExtractor;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
        List<User> users = rt.execute(url, HttpMethod.GET,null, new UserExtractor());//.collect(Collectors.groupingBy(User::getUserId)).size();;
        for (int i = 0; i < users.size(); i++) {
            User user = (User)users.get(i);
            assertTrue("user is null",user != null);
            assertTrue("user id is null!",user.getId() != null);
            assertTrue("user userid is null!",user.getUserId() != null);
            assertTrue("user title is null!!",user.getTitle() != null);
            assertTrue("user body is null!!",user.getBody() != null);
        }

    }
}
