package ru.tasksystem.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tasksystem.dto.PersonDto;

@FeignClient(name = "profileClient", dismiss404 = true, url = "${profile.url}" + "/api/v1/account")
public interface ProfileClient {

    @GetMapping("/me")
    ResponseEntity<PersonDto> getMyProfile(@RequestParam("email") String email);

    @GetMapping("/{id}")
    ResponseEntity<PersonDto> getProfileById(@PathVariable Long id);
}
