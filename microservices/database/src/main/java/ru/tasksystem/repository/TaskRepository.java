package ru.tasksystem.repository;

import jooq.db.tables.records.TaskRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.tasksystem.dto.TaskDto;
import ru.tasksystem.dto.enums.StatusType;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Page<TaskDto> getAll(
            List<Long> ids,
            List<Long> accountIds,
            String author,
            String text,
            Boolean isDeleted,
            OffsetDateTime dateFrom,
            OffsetDateTime dateTo,
            String sort,
            Pageable pageable);

    Optional<TaskRecord> getTaskById(Long id);

    Long addNewTask(TaskRecord taskRecord);

    void editTask(TaskRecord taskRecord);

    void deleteTaskById(Long id);

    void updateStatusTask(TaskRecord taskRecord, StatusType statusType);

    void updateExecutorTask(TaskRecord taskRecord, Long executorId);

    void incrementCommentsCount(TaskRecord taskRecord);

    void decrementCommentsCount(TaskRecord taskRecord);
}
