package ru.tasksystem.web.controller;

import feign.FeignException;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.tasksystem.security.SecurityService;
import ru.tasksystem.service.UserService;
import ru.tasksystem.web.model.AuthRequest;
import ru.tasksystem.web.model.JwtResponse;
import ru.tasksystem.web.model.SimpleResponse;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final SecurityService securityService;

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;
    private AtomicInteger usersCount;

    public AuthController(SecurityService securityService,
                          PasswordEncoder passwordEncoder,
                          UserService userService,
                          MeterRegistry meterRegistry) {
        this.securityService = securityService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        meterRegistry.gauge("users_count", usersCount);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> signIn(@RequestBody AuthRequest authRequest) {
        log.debug("/api/v1/auth/login");
        log.debug(authRequest.getEmail());

        JwtResponse responseBody = securityService.authenticateUser(authRequest);

        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/logout")
    public ResponseEntity<SimpleResponse> logout(@AuthenticationPrincipal UserDetails userDetails) {
        log.debug("/api/v1/auth/logout");
        log.debug(userDetails.getUsername());

        SimpleResponse responseBody = securityService.logout(userDetails.getUsername());

        return ResponseEntity.ok(responseBody);
    }

    @ExceptionHandler(FeignException.class)
    private ResponseEntity<Void> handler(FeignException ex) {
        log.warn("Error in the gateway {}", ex.getMessage());
        return ResponseEntity.status(ex.status()).build();
    }
}
