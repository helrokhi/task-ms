package ru.tasksystem.security;

import ru.tasksystem.web.model.AuthRequest;
import ru.tasksystem.web.model.JwtResponse;
import ru.tasksystem.web.model.SimpleResponse;

public interface SecurityService {
    JwtResponse authenticateUser(AuthRequest authRequest);

    SimpleResponse logout(String userName);

}
