package ru.tasksystem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tasksystem.dto.TaskDto;
import ru.tasksystem.dto.CommentDto;
import ru.tasksystem.dto.enums.StatusType;
import ru.tasksystem.service.comments.CommentService;
import ru.tasksystem.service.tasks.TaskService;

import java.time.OffsetDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;
    private CommentService commentService;

    @GetMapping
    public Page<TaskDto> getAll(
            @RequestParam(required = false) List<Long> ids,
            @RequestParam(required = false) List<Long> accountIds,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String text,
            @RequestParam(required = false, defaultValue = "false") Boolean isDeleted,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dateFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dateTo,
            @RequestParam(required = false, defaultValue = "time,desc") String sort,
            Pageable pageable) {
        return taskService.getAll(
                ids, accountIds,
                author, text, isDeleted,
                dateFrom, dateTo, sort, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public TaskDto addNewTask(@RequestBody TaskDto taskDto) {
        return taskService.addNewTask(taskDto);
    }

    @PutMapping
    public ResponseEntity<Long> editTask(@RequestBody TaskDto taskDto) {
        return taskService.editTask(taskDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }

    @PatchMapping
    public ResponseEntity<Long> updateStatusTask(
            @RequestBody TaskDto taskDto,
            @RequestParam String status) {
        return taskService.updateStatusTask(taskDto, status);
    }

    @PatchMapping
    public ResponseEntity<Long> updateExecutorTask(
            @RequestBody TaskDto taskDto,
            @RequestParam Long executor) {
        return taskService.updateExecutorTask(taskDto, executor);
    }

    @GetMapping("/{taskId}/comment")
    public ResponseEntity<Page<CommentDto>> getComments(
            @PathVariable Long taskId,
            @RequestParam(required = false, defaultValue = "false") Boolean isDeleted,
            @RequestParam(required = false, defaultValue = "time,desc") String sort,
            Pageable pageable) {
        return commentService.getComments(taskId, isDeleted, sort, pageable);
    }

    @PostMapping("/{taskId}/comment")
    public ResponseEntity<CommentDto> addNewComment(
            @PathVariable Long taskId,
            @RequestBody CommentDto commentDto) {
        return commentService.addNewComment(taskId, commentDto);
    }

    @PutMapping("/{taskId}/comment")
    public ResponseEntity<CommentDto> editComment(
            @PathVariable Long taskId,
            @RequestBody CommentDto commentDto) {
        return commentService.editComment(taskId, commentDto);
    }

    @DeleteMapping("/{taskId}/comment/{commentId}")
    public ResponseEntity<Long> deleteComment(
            @PathVariable Long taskId,
            @PathVariable Long commentId) {
        return commentService.deleteComment(taskId, commentId);
    }
}
