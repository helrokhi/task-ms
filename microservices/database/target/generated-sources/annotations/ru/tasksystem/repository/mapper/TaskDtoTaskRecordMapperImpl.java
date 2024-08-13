package ru.tasksystem.repository.mapper;

import javax.annotation.processing.Generated;
import jooq.db.tables.records.TaskRecord;
import org.springframework.stereotype.Component;
import ru.tasksystem.dto.TaskDto;
import ru.tasksystem.dto.enums.PriorityType;
import ru.tasksystem.dto.enums.StatusType;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-13T14:36:54+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class TaskDtoTaskRecordMapperImpl implements TaskDtoTaskRecordMapper {

    @Override
    public TaskDto taskRecordToTaskDto(TaskRecord taskRecord) {
        if ( taskRecord == null ) {
            return null;
        }

        TaskDto.TaskDtoBuilder taskDto = TaskDto.builder();

        taskDto.id( taskRecord.getId() );
        taskDto.time( taskRecord.getTime() );
        taskDto.authorId( taskRecord.getAuthorId() );
        taskDto.executorId( taskRecord.getExecutorId() );
        taskDto.title( taskRecord.getTitle() );
        if ( taskRecord.getStatus() != null ) {
            taskDto.status( Enum.valueOf( StatusType.class, taskRecord.getStatus() ) );
        }
        if ( taskRecord.getPriority() != null ) {
            taskDto.priority( Enum.valueOf( PriorityType.class, taskRecord.getPriority() ) );
        }
        if ( taskRecord.getCommentsCount() != null ) {
            taskDto.commentsCount( taskRecord.getCommentsCount() );
        }
        if ( taskRecord.getIsDeleted() != null ) {
            taskDto.isDeleted( taskRecord.getIsDeleted() );
        }

        return taskDto.build();
    }

    @Override
    public TaskRecord taskDtoToTaskRecordForCreate(TaskDto taskDto) {
        if ( taskDto == null ) {
            return null;
        }

        TaskRecord taskRecord = new TaskRecord();

        if ( taskDto.getStatus() != null ) {
            taskRecord.setStatus( taskDto.getStatus().name() );
        }
        else {
            taskRecord.setStatus( "NEW" );
        }
        taskRecord.setCommentsCount( taskDto.getCommentsCount() );
        taskRecord.setAuthorId( taskDto.getAuthorId() );
        taskRecord.setExecutorId( taskDto.getExecutorId() );
        taskRecord.setTitle( taskDto.getTitle() );
        if ( taskDto.getPriority() != null ) {
            taskRecord.setPriority( taskDto.getPriority().name() );
        }

        taskRecord.setTime( java.time.OffsetDateTime.now() );
        taskRecord.setIsDeleted( false );

        return taskRecord;
    }

    @Override
    public TaskRecord taskDtoToTaskRecordForEdit(TaskDto taskDto) {
        if ( taskDto == null ) {
            return null;
        }

        TaskRecord taskRecord = new TaskRecord();

        taskRecord.setId( taskDto.getId() );
        taskRecord.setTime( taskDto.getTime() );
        taskRecord.setAuthorId( taskDto.getAuthorId() );
        taskRecord.setExecutorId( taskDto.getExecutorId() );
        taskRecord.setTitle( taskDto.getTitle() );
        if ( taskDto.getStatus() != null ) {
            taskRecord.setStatus( taskDto.getStatus().name() );
        }
        if ( taskDto.getPriority() != null ) {
            taskRecord.setPriority( taskDto.getPriority().name() );
        }
        taskRecord.setCommentsCount( taskDto.getCommentsCount() );

        return taskRecord;
    }
}
