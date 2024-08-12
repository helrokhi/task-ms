package ru.tasksystem.repository;

import jooq.db.tables.records.PersonRecord;

import java.util.Optional;

public interface PersonRepository {
    Optional<PersonRecord> getPersonById(Long id);
}
