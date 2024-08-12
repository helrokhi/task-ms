package ru.tasksystem.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tasksystem.dto.PersonDto;
import ru.tasksystem.service.UserAuthService;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {
    private final UserAuthService userAuthService;

    @GetMapping("/me")
    public ResponseEntity<PersonDto> getAccountInfo(@RequestParam("email") String email) {
        Optional<PersonDto> accountInfo = userAuthService.getAccountInfo(email);
        return accountInfo
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getAccountById(@PathVariable Long id) {
        Optional<PersonDto> accountInfo = userAuthService.getAccountById(id);
        return accountInfo
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
