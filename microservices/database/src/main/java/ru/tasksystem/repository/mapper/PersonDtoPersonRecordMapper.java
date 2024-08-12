package ru.tasksystem.repository.mapper;

import jooq.db.tables.records.PersonRecord;
import org.mapstruct.Mapper;
import ru.tasksystem.dto.PersonDto;

@Mapper(componentModel = "spring")
public interface PersonDtoPersonRecordMapper {
    PersonDto personRecordToPersonDto(PersonRecord personRecord);
}
