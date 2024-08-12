package ru.tasksystem.repository.mapper;

import javax.annotation.processing.Generated;
import jooq.db.tables.records.PersonRecord;
import org.springframework.stereotype.Component;
import ru.tasksystem.dto.PersonDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-12T16:23:00+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class PersonDtoPersonRecordMapperImpl implements PersonDtoPersonRecordMapper {

    @Override
    public PersonDto personRecordToPersonDto(PersonRecord personRecord) {
        if ( personRecord == null ) {
            return null;
        }

        PersonDto.PersonDtoBuilder personDto = PersonDto.builder();

        personDto.id( personRecord.getId() );
        personDto.userId( personRecord.getUserId() );
        personDto.firstName( personRecord.getFirstName() );
        personDto.lastName( personRecord.getLastName() );
        personDto.regDate( personRecord.getRegDate() );

        return personDto.build();
    }
}
