package ru.tasksystem.repository.mapper;

import jooq.db.tables.records.TaskCommentRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.tasksystem.dto.CommentDto;

@Mapper(componentModel = "spring")
public interface CommentDtoCommentRecordMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "time", expression = "java(java.time.OffsetDateTime.now())")
    @Mapping(target = "isDeleted", expression = "java(false)")
    TaskCommentRecord commentDtoToCommentRecord(CommentDto commentDto);

    CommentDto commentRecordToCommentDto(TaskCommentRecord commentRecord);

    TaskCommentRecord commentDtoToCommentRecordForEdit(CommentDto commentDto);
}
