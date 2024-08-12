package ru.tasksystem.service;

import java.util.Optional;

import ru.tasksystem.dto.PersonDto;
import ru.tasksystem.dto.UserAuthDto;

public interface UserAuthService {
    Optional<UserAuthDto> getUserByEmail(String email);

    Optional<PersonDto> getAccountInfo(String email);

    Optional<PersonDto> getAccountById(Long id);
}
