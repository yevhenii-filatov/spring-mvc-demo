package com.nadia;

import com.nadia.model.User;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * @author Yevhenii Filatov
 * @since 5/30/23
 */

@Service
public class UserService {
    private static final String FILENAME = "src/main/resources/users.txt";

    @SneakyThrows
    public void addUserToFile(User user) {
        if (user == null) {
            return;
        }
        Files.writeString(Path.of(FILENAME), user + "\n", StandardOpenOption.APPEND);
    }

    @SneakyThrows
    public Integer getUsersCount() {
        return Files.readAllLines(Path.of(FILENAME)).size();
    }
}
