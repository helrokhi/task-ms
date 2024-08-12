package ru.tasksystem.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tasksystem.client.ProfileClient;
import ru.tasksystem.dto.PersonDto;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {
    private final ProfileClient profileClient;

    @GetMapping("/me")
    public ResponseEntity<PersonDto> getMyProfile(
            @AuthenticationPrincipal UserDetails userDetails) {
        log.info("/api/v1/account/me");
        if (userDetails != null) {
            log.info(userDetails.getUsername());

            return profileClient.getMyProfile(userDetails.getUsername());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getProfileById(@PathVariable Long id) {
        log.info("get profile by id: {}", id);
        ResponseEntity<PersonDto> inputResponseEntity = profileClient.getProfileById(id);
        HttpStatusCode statusCode = inputResponseEntity.getStatusCode();

        if (statusCode.is4xxClientError()) {
            return ResponseEntity.badRequest().build();
        }
        return inputResponseEntity;
    }
}
