package com.example.taskoftests.extractors;

import com.example.taskoftests.dto.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseExtractor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserExtractor implements ResponseExtractor<List<User>> {
    @Override
    public List<User> extractData(ClientHttpResponse clientHttpResponse) throws IOException {
        List<User> users = new ArrayList<>();
        ObjectMapper om = new ObjectMapper();
        BufferedReader is = new BufferedReader(new InputStreamReader(clientHttpResponse.getBody()));
        String res = is.lines().collect(Collectors.joining());
        JsonNode jn = om.readTree(res);

        for (int i = 0; i < jn.size(); i++) {
            JsonNode jn2 = om.readTree(jn.get(i).toString());
            User u = new User();
            try {
                u.setId(Optional.of(jn2.get("id")).get().toString());
                u.setUserId(Optional.of(jn2.get("userId")).get().toString());
                u.setTitle(Optional.of(jn2.get("title")).get().toString());
                u.setBody(Optional.of(jn2.get("body")).get().toString());
                users.add(u);
            } catch (Throwable e) {
            }

        }

        return users;
    }
}
