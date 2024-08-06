package ru.tasksystem.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tasksystem.dto.UserAuthDto;
import ru.tasksystem.repository.*;

import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService{
    private final UserAuthRepository userAuthRepo;
    @Override
    public Optional<UserAuthDto> getUserByEmail(String email) {
        return userAuthRepo.getUserByEmail(email);
    }
}
