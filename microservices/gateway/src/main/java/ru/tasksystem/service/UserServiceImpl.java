package ru.tasksystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tasksystem.dto.UserAuthDto;
import ru.tasksystem.client.*;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final DatabaseClient databaseClient;

    @Override
    public Optional<UserAuthDto> findUserByEmail(String email) {
        return Optional.ofNullable(databaseClient.getUserByEmail(email));
    }
}
