package ru.tasksystem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tasksystem.client.TaskClient;
import ru.tasksystem.dto.CommentDto;
import ru.tasksystem.dto.TaskDto;

import java.time.OffsetDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private TaskClient taskClient;

    @GetMapping
    public ResponseEntity<Page<TaskDto>> getAll(
            @RequestParam(required = false) List<Long> ids,
            @RequestParam(required = false) List<Long> accountIds,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String text,
            @RequestParam(required = false, defaultValue = "false") Boolean isDeleted,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dateFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dateTo,
            @RequestParam(required = false, defaultValue = "time,desc") String sort,
            Pageable pageable) {
        return ResponseEntity.ok().body(taskClient.getAll(
                ids, accountIds,
                author, text, isDeleted,
                dateFrom, dateTo, sort, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        return taskClient.getTaskById(id);
    }

    @PostMapping
    public TaskDto addNewTask(@RequestBody TaskDto taskDto) {
        return taskClient.addNewTask(taskDto);
    }

    @PutMapping
    public ResponseEntity<Long> editTask(@RequestBody TaskDto taskDto) {
        return taskClient.editTask(taskDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteTask(@PathVariable Long id) {
        return taskClient.deleteTask(id);
    }

    @PutMapping("/{id}/updateStatus")
    public ResponseEntity<Long> updateStatusTask(
            @PathVariable Long id,
            @RequestParam String status) {
        return taskClient.updateStatusTask(id, status);
    }

    @PutMapping("/{id}/updateExecutor")
    public ResponseEntity<Long> updateExecutorTask(
            @PathVariable Long id,
            @RequestParam Long executor) {
        return taskClient.updateExecutorTask(id, executor);
    }

    @GetMapping("/{taskId}/comment")
    public ResponseEntity<Page<CommentDto>> getComments(
            @PathVariable Long taskId,
            @RequestParam(required = false, defaultValue = "false") Boolean isDeleted,
            @RequestParam(required = false, defaultValue = "time,desc") String sort,
            Pageable pageable) {
        return taskClient.getComments(taskId, isDeleted, sort, pageable);
    }

    @PostMapping("/{taskId}/comment")
    public ResponseEntity<CommentDto> addNewComment(
            @PathVariable Long taskId,
            @RequestBody CommentDto commentDto) {
        return taskClient.addNewComment(taskId, commentDto);
    }

    @PutMapping("/{taskId}/comment")
    public ResponseEntity<CommentDto> editComment(
            @PathVariable Long taskId,
            @RequestBody CommentDto commentDto) {
        return taskClient.editComment(taskId, commentDto);
    }

    @DeleteMapping("/{taskId}/comment/{commentId}")
    public ResponseEntity<Long> deleteComment(
            @PathVariable Long taskId,
            @PathVariable Long commentId) {
        return taskClient.deleteComment(taskId, commentId);
    }
}
