package ru.tasksystem.repository.mapper;

import javax.annotation.processing.Generated;
import jooq.db.tables.records.TaskCommentRecord;
import org.springframework.stereotype.Component;
import ru.tasksystem.dto.CommentDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-13T14:36:54+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class CommentDtoCommentRecordMapperImpl implements CommentDtoCommentRecordMapper {

    @Override
    public TaskCommentRecord commentDtoToCommentRecord(CommentDto commentDto) {
        if ( commentDto == null ) {
            return null;
        }

        TaskCommentRecord taskCommentRecord = new TaskCommentRecord();

        taskCommentRecord.setTaskId( commentDto.getTaskId() );

        taskCommentRecord.setTime( java.time.OffsetDateTime.now() );
        taskCommentRecord.setIsDeleted( false );

        return taskCommentRecord;
    }

    @Override
    public CommentDto commentRecordToCommentDto(TaskCommentRecord commentRecord) {
        if ( commentRecord == null ) {
            return null;
        }

        CommentDto.CommentDtoBuilder commentDto = CommentDto.builder();

        commentDto.id( commentRecord.getId() );
        commentDto.time( commentRecord.getTime() );
        commentDto.taskId( commentRecord.getTaskId() );
        if ( commentRecord.getIsDeleted() != null ) {
            commentDto.isDeleted( commentRecord.getIsDeleted() );
        }

        return commentDto.build();
    }

    @Override
    public TaskCommentRecord commentDtoToCommentRecordForEdit(CommentDto commentDto) {
        if ( commentDto == null ) {
            return null;
        }

        TaskCommentRecord taskCommentRecord = new TaskCommentRecord();

        taskCommentRecord.setId( commentDto.getId() );
        taskCommentRecord.setTime( commentDto.getTime() );
        taskCommentRecord.setTaskId( commentDto.getTaskId() );

        return taskCommentRecord;
    }
}
