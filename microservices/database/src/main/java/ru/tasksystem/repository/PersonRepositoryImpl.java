package ru.tasksystem.repository;

import jooq.db.Tables;
import jooq.db.tables.records.PersonRecord;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Slf4j
public class PersonRepositoryImpl implements PersonRepository{

    private final DSLContext dsl;
    @Override
    public Optional<PersonRecord> getPersonById(Long id) {
        return dsl.selectFrom(Tables.PERSON)
                .where(Tables.PERSON.ID.eq(id))
                .fetchOptional();
    }
}
