package ru.tasksystem.service.tasks;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import ru.tasksystem.dto.TaskDto;
import org.springframework.data.domain.Pageable;

import java.time.OffsetDateTime;
import java.util.List;

public interface TaskService {
    Page<TaskDto> getAll(List<Long> ids,
                         List<Long> accountIds,
                         String author,
                         String text,
                         Boolean isDeleted,
                         OffsetDateTime dateFrom,
                         OffsetDateTime dateTo,
                         String sort,
                         Pageable pageable);

    ResponseEntity<TaskDto> getTaskById(Long id);

    TaskDto addNewTask(TaskDto taskDto);

    ResponseEntity<Long> editTask(TaskDto taskDto);

    ResponseEntity<Long> deleteTask(Long id);

    ResponseEntity<Long> updateStatusTask(Long id, String status);

    ResponseEntity<Long> updateExecutorTask(Long id, Long executorId);
}
