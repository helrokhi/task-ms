package ru.tasksystem.service;

import jooq.db.tables.records.PersonRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tasksystem.dto.PersonDto;
import ru.tasksystem.dto.UserAuthDto;
import ru.tasksystem.repository.PersonRepository;
import ru.tasksystem.repository.UserAuthRepository;
import ru.tasksystem.repository.mapper.PersonDtoPersonRecordMapper;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserAuthServiceImpl implements UserAuthService {
    private final UserAuthRepository userAuthRepo;
    private final PersonRepository personRepo;
    private final PersonDtoPersonRecordMapper personMapper;

    @Override
    public Optional<UserAuthDto> getUserByEmail(String email) {
        return userAuthRepo.getUserByEmail(email);
    }

    @Override
    public Optional<PersonDto> getAccountInfo(String email) {
        return userAuthRepo.getAccountInfo(email);
    }

    @Override
    public Optional<PersonDto> getAccountById(Long id) {
        Optional<PersonRecord> resultRecord = personRepo.getPersonById(id);
        log.info("get person by id: {}", id);
        return resultRecord.map(personMapper::personRecordToPersonDto);
    }
}
