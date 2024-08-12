package ru.tasksystem.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tasksystem.dto.CommentDto;
import ru.tasksystem.dto.TaskDto;

import java.time.OffsetDateTime;
import java.util.List;

@FeignClient(name = "tasksClient", dismiss404 = true, url = "${profile.url}" + "/api/v1/tasks")
public interface TaskClient {

    @GetMapping
    ResponseEntity<Page<TaskDto>> getAll(
            @RequestParam(required = false) List<Long> ids,
            @RequestParam(required = false) List<Long> accountIds,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String text,
            @RequestParam(required = false, defaultValue = "false") Boolean isDeleted,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dateFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dateTo,
            @RequestParam(required = false, defaultValue = "time,desc") String sort,
            Pageable pageable);

    @GetMapping("/{id}")
    ResponseEntity<TaskDto> getTaskById(@PathVariable Long id);


    @PostMapping
    TaskDto addNewTask(@RequestBody TaskDto taskDto);

    @PutMapping
    ResponseEntity<Long> editTask(@RequestBody TaskDto taskDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Long> deleteTask(@PathVariable Long id);

    @PatchMapping
    ResponseEntity<Long> updateStatusTask(
            @RequestBody TaskDto taskDto,
            @RequestParam String status);


    @PatchMapping
    ResponseEntity<Long> updateExecutorTask(
            @RequestBody TaskDto taskDto,
            @RequestParam Long executor);


    @GetMapping("/{taskId}/comment")
    ResponseEntity<Page<CommentDto>> getComments(
            @PathVariable Long taskId,
            @RequestParam(required = false, defaultValue = "false") Boolean isDeleted,
            @RequestParam(required = false, defaultValue = "time,desc") String sort,
            Pageable pageable);


    @PostMapping("/{taskId}/comment")
    ResponseEntity<CommentDto> addNewComment(
            @PathVariable Long taskId,
            @RequestBody CommentDto commentDto);


    @PutMapping("/{taskId}/comment")
    ResponseEntity<CommentDto> editComment(
            @PathVariable Long taskId,
            @RequestBody CommentDto commentDto);


    @DeleteMapping("/{taskId}/comment/{commentId}")
    ResponseEntity<Long> deleteComment(
            @PathVariable Long taskId,
            @PathVariable Long commentId);
}
