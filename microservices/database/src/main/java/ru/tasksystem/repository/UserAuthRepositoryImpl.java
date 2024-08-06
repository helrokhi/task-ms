package ru.tasksystem.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import jooq.db.Tables;
import org.springframework.stereotype.Repository;
import ru.tasksystem.dto.UserAuthDto;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
@Slf4j
public class UserAuthRepositoryImpl implements UserAuthRepository {
    private final DSLContext dsl;

    @Override
    public Optional<UserAuthDto> getUserByEmail(String email) {
        List<UserAuthDto> userList =
                dsl
                        .selectFrom(Tables.USER)
                        .where(Tables.USER.EMAIL.eq(email))
                        .fetchInto(UserAuthDto.class);

        UserAuthDto user = (!userList.isEmpty()) ? userList.get(0) : null;

        return Optional.ofNullable(user);
    }
}
