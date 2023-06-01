package com.nadia.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nadia.model.User;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * @author Yevhenii Filatov
 * @since 5/30/23
 */

@Service
public class UserService {
    private static final String FILENAME = "src/main/resources/users.txt";
    private static final ObjectMapper MARSHALLER = new ObjectMapper();

    @SneakyThrows
    public void addUserToFile(User user) {
        if (user != null) {
            Files.writeString(Path.of(FILENAME), toJson(user) + "\n", StandardOpenOption.APPEND);
        }
    }

    @SneakyThrows
    public Integer getUsersCount() {
        return Files.readAllLines(Path.of(FILENAME)).size();
    }

    @SneakyThrows
    public List<User> getAll() {
        return Files.readAllLines(Path.of(FILENAME))
           .stream()
           .map(s -> fromJson(s, User.class))
           .toList();
    }

    @SneakyThrows
    private <T> String toJson(T object) {
        return MARSHALLER.writeValueAsString(object);
    }

    @SneakyThrows
    private <T> T fromJson(String value, Class<T> type) {
        return MARSHALLER.readValue(value, type);
    }
}
