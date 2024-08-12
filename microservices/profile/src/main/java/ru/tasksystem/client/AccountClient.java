package ru.tasksystem.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tasksystem.dto.PersonDto;

@FeignClient(name = "accountDatabaseClient", dismiss404 = true, url = "${database.url}" + "/account")
public interface AccountClient {

    @GetMapping("/me")
    ResponseEntity<PersonDto> getAccountInfo(@RequestParam("email") String email);

    @GetMapping("/{id}")
    ResponseEntity<PersonDto> getProfileById(@PathVariable Long id);
}
