package ru.tasksystem.repository;

import ru.tasksystem.dto.PersonDto;
import ru.tasksystem.dto.UserAuthDto;

import java.util.Optional;

public interface UserAuthRepository {
    Optional<UserAuthDto> getUserByEmail(String email);

    Optional<PersonDto> getAccountInfo(String email);
}
