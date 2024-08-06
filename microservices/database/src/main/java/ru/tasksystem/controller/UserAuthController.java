package ru.tasksystem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.tasksystem.dto.UserAuthDto;
import ru.tasksystem.service.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/auth")
public class UserAuthController {
    private final UserAuthService userAuthService;

    @GetMapping("/user")
    public ResponseEntity<UserAuthDto> getUser(@RequestParam("email") String email) {
        Optional<UserAuthDto> user = userAuthService.getUserByEmail(email);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
