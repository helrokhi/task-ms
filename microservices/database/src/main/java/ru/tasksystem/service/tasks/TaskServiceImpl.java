package ru.tasksystem.service.tasks;

import jooq.db.tables.records.TaskRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.tasksystem.dto.TaskDto;
import ru.tasksystem.dto.enums.StatusType;
import ru.tasksystem.repository.TaskRepository;
import ru.tasksystem.repository.mapper.TaskDtoTaskRecordMapper;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskDtoTaskRecordMapper mapper;

    @Override
    public Page<TaskDto> getAll(
            List<Long> ids,
            List<Long> accountIds,
            String author,
            String text,
            Boolean isDeleted,
            OffsetDateTime dateFrom,
            OffsetDateTime dateTo,
            String sort,
            Pageable pageable) {
        return taskRepository.getAll(ids, accountIds,
                author, text, isDeleted,
                dateFrom, dateTo, sort, pageable);
    }

    @Override
    public ResponseEntity<TaskDto> getTaskById(Long id) {
        Optional<TaskRecord> optionalRecord = taskRepository.getTaskById(id);
        Optional<TaskDto> taskDto = optionalRecord.map(mapper::taskRecordToTaskDto);
        if (taskDto.isPresent()) {
            TaskDto task = taskDto.get();
            return ResponseEntity.ok().body(task);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public TaskDto addNewTask(TaskDto taskDto) {
        TaskRecord taskRecord = mapper.taskDtoToTaskRecordForCreate(taskDto);
        Long postId = taskRepository.addNewTask(taskRecord);
        if (postId != 0L) {
            return taskDto;
        }
        return null;
    }

    @Override
    public ResponseEntity<Long> editTask(TaskDto taskDto) {
        if (taskRepository.getTaskById(taskDto.getId()).isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            TaskRecord taskRecord = mapper.taskDtoToTaskRecordForEdit(taskDto);
            taskRepository.editTask(taskRecord);
            return ResponseEntity.ok().body(taskRecord.getId());
        }
    }

    @Override
    public ResponseEntity<Long> deleteTask(Long id) {
        if (taskRepository.getTaskById(id).isPresent()) {
            taskRepository.deleteTaskById(id);
            return ResponseEntity.ok().body(id);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Long> updateStatusTask(Long id, String status) {
        Optional<TaskRecord> taskRecord = taskRepository.getTaskById(id);
        if (taskRecord.isPresent()) {
            StatusType statusType = StatusType.valueOf(status);
            taskRepository.updateStatusTask(taskRecord.get(), statusType);
            return ResponseEntity.ok().body(taskRecord.get().getId());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Long> updateExecutorTask(Long id, Long executorId) {
        Optional<TaskRecord> taskRecord = taskRepository.getTaskById(id);
        if (taskRecord.isPresent()) {
            taskRepository.updateExecutorTask(taskRecord.get(), executorId);
            return ResponseEntity.ok().body(taskRecord.get().getId());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
