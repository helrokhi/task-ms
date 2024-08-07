package ru.tasksystem.repository.mapper;

import jooq.db.tables.records.TaskRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.tasksystem.dto.TaskDto;

@Mapper(componentModel = "spring")
public interface TaskDtoTaskRecordMapper {
    TaskDto taskRecordToTaskDto(TaskRecord taskRecord);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "time", expression = "java(java.time.OffsetDateTime.now())")
    @Mapping(target = "isDeleted", defaultValue = "false")
    @Mapping(target = "status", defaultValue = "NEW")
    @Mapping(target = "commentsCount", defaultValue = "0")
    TaskRecord taskDtoToTaskRecordForCreate(TaskDto taskDto);

    TaskRecord taskDtoToTaskRecordForEdit(TaskDto taskDto);
}
