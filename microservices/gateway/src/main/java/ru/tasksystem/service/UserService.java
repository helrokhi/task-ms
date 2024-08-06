package ru.tasksystem.service;

import ru.tasksystem.dto.UserAuthDto;

import java.util.Optional;

public interface UserService {
    Optional<UserAuthDto> findUserByEmail(String email);
}
