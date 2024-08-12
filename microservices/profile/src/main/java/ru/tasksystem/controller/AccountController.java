package ru.tasksystem.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tasksystem.client.AccountClient;
import ru.tasksystem.dto.PersonDto;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountClient accountClient;

    @GetMapping("/me")
    public ResponseEntity<PersonDto> getMyProfile(@RequestParam("email") String email) {
        return accountClient.getAccountInfo(email);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getProfileById(@PathVariable Long id) {
        return accountClient.getProfileById(id);
    }
}
