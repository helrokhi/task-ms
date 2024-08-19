package ru.tasksystem.controller;

import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.tasksystem.dto.CommentDto;
import ru.tasksystem.dto.TaskDto;
import ru.tasksystem.dto.enums.PriorityType;
import ru.tasksystem.dto.enums.StatusType;
import ru.tasksystem.service.comments.CommentService;
import ru.tasksystem.service.tasks.TaskService;

import java.time.OffsetDateTime;
import java.util.List;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @MockBean
    private TaskService taskService;

    @MockBean
    private CommentService commentService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAll() throws Exception {

        List<TaskDto> tasks = getTaskDtos();

        String sort = "time,desc";
        Pageable pageable = PageRequest.of(0, 20);

        Page<TaskDto> tasksDtoPage = new PageImpl<>(tasks, pageable, tasks.size());

        Mockito.when(taskService.getAll(null, null, null, null,
                        false, null, null,
                        sort, pageable))
                .thenReturn(tasksDtoPage);

        String tasksJson = objectMapper.writeValueAsString(tasksDtoPage);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/tasks")
                                .param("isDeleted", "false")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(tasksJson));

        Mockito.verify(taskService, Mockito.times(1))
                .getAll(null, null, null, null,
                        false, null, null,
                        sort, pageable);

    }

    private static List<TaskDto> getTaskDtos() {
        OffsetDateTime time = OffsetDateTime.parse("2024-08-13T16:00:05.829402900Z");

        return List.of(
                new TaskDto(1L, time, 2L, 3L, "title", "text",
                        StatusType.NEW, PriorityType.LOW, 0, false),
                new TaskDto(2L, time, 2L, 4L, "title", "text",
                        StatusType.PROCESS, PriorityType.MEDIUM, 0, false),
                new TaskDto(3L, time, 2L, 5L, "title", "text",
                        StatusType.COMPLETE, PriorityType.HIGH, 0, false)
        );
    }

    @Test
    void getTaskById() throws Exception {
        OffsetDateTime time = OffsetDateTime.parse("2024-08-13T16:00:05.829402900Z");
        TaskDto taskDto = new TaskDto(1L, time,
                2L, 3L, "title", "text",
                StatusType.NEW, PriorityType.LOW, 0, false);

        Mockito.when(taskService.getTaskById(1L))
                .thenReturn(ResponseEntity.ok(taskDto));

        String taskJson = objectMapper.writeValueAsString(taskDto);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/tasks/{id}", 1L)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(taskJson));


        Mockito.verify(taskService, Mockito.times(1))
                .getTaskById(1L);
    }

    @Test
    void addNewTask() throws Exception {
        OffsetDateTime time = OffsetDateTime.parse("2024-08-13T16:00:05.829402900Z");
        TaskDto taskDto = new TaskDto(1L, time,
                2L, 3L, "title", "text",
                StatusType.NEW, PriorityType.LOW, 0, false);

        Mockito.when(taskService.addNewTask(taskDto))
                .thenReturn(taskDto);

        String taskJson = objectMapper.writeValueAsString(taskDto);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/tasks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(taskJson)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(taskJson));


        Mockito.verify(taskService, Mockito.times(1))
                .addNewTask(taskDto);

    }

    @Test
    void editTask() throws Exception {
        OffsetDateTime time = OffsetDateTime.parse("2024-08-13T16:00:05.829402900Z");
        TaskDto taskDto = new TaskDto(1L, time,
                2L, 3L, "title", "text",
                StatusType.NEW, PriorityType.LOW, 0, false);

        taskDto.setTitle("newTitle");

        Mockito.when(taskService.editTask(taskDto))
                .thenReturn(ResponseEntity.ok().body(1L));

        String taskJson = objectMapper.writeValueAsString(taskDto);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put("/tasks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(taskJson)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"));


        Mockito.verify(taskService, Mockito.times(1))
                .editTask(taskDto);
    }

    @Test
    void deleteTask() throws Exception {

        OffsetDateTime time = OffsetDateTime.parse("2024-08-13T16:00:05.829402900Z");
        TaskDto taskDto = new TaskDto(1L, time,
                2L, 3L, "title", "text",
                StatusType.NEW, PriorityType.LOW, 0, false);

        taskDto.setDeleted(true);

        Mockito.when(taskService.deleteTask(1L))
                .thenReturn(ResponseEntity.ok().body(1L));

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete("/tasks/{id}", 1L)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"));


        Mockito.verify(taskService, Mockito.times(1))
                .deleteTask(1L);
    }

    @Test
    void updateStatusTask() throws Exception {

        OffsetDateTime time = OffsetDateTime.parse("2024-08-13T16:00:05.829402900Z");
        TaskDto taskDto = new TaskDto(1L, time,
                2L, 3L, "title", "text",
                StatusType.NEW, PriorityType.LOW, 0, false);

        taskDto.setStatus(StatusType.PROCESS);

        String status = StatusType.PROCESS.name();

        Mockito.when(taskService.updateStatusTask(1L, status))
                .thenReturn(ResponseEntity.ok().body(1L));

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put("/tasks/{id}/updateStatus", 1L)
                                .param("status", status)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"));


        Mockito.verify(taskService, Mockito.times(1))
                .updateStatusTask(1L, status);
    }

    @Test
    void updateExecutorTask() throws Exception {
        OffsetDateTime time = OffsetDateTime.parse("2024-08-13T16:00:05.829402900Z");
        TaskDto taskDto = new TaskDto(1L, time,
                2L, 3L, "title", "text",
                StatusType.NEW, PriorityType.LOW, 0, false);

        taskDto.setExecutorId(8L);

        Mockito.when(taskService.updateExecutorTask(1L, 8L))
                .thenReturn(ResponseEntity.ok().body(1L));

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put("/tasks/{id}/updateExecutor", 1L)
                                .param("executor", "8")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"));


        Mockito.verify(taskService, Mockito.times(1))
                .updateExecutorTask(1L, 8L);
    }

    @Test
    void getComments() throws Exception{
        OffsetDateTime time = OffsetDateTime.parse("2024-08-13T16:00:05.829402900Z");
        Long taskId = 2L;
        List<CommentDto> comments = List.of(
                new CommentDto(1L, time, taskId, "text", false),
                new CommentDto(2L, time, taskId, "text", false),
                new CommentDto(3L, time, taskId, "text", false)
        );

        String sort = "time,desc";
        Pageable pageable = PageRequest.of(0, 20);

        Page<CommentDto> commentDtoPage = new PageImpl<>(comments, pageable, comments.size());

        Mockito.when(commentService.getComments(taskId, false, sort, pageable))
                .thenReturn(ResponseEntity.ok(commentDtoPage));

        String commentsJson = objectMapper.writeValueAsString(commentDtoPage);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/tasks/{taskId}/comment", taskId)
                                .param("isDeleted" , "false")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(commentsJson));

        Mockito.verify(commentService, Mockito.times(1))
                .getComments(taskId, false, sort, pageable);

    }

    @Test
    void addNewComment() throws Exception{
        OffsetDateTime time = OffsetDateTime.parse("2024-08-13T16:00:05.829402900Z");
        Long taskId = 2L;
        CommentDto commentDto = new CommentDto(1L, time,
                taskId, "text", false);

        Mockito.when(commentService.addNewComment(taskId, commentDto))
                .thenReturn(ResponseEntity.ok(commentDto));

        String commentJson = objectMapper.writeValueAsString(commentDto);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/tasks/{taskId}/comment", taskId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(commentJson)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(commentJson));


        Mockito.verify(commentService, Mockito.times(1))
                .addNewComment(taskId, commentDto);
    }

    @Test
    void editComment() throws Exception{
        OffsetDateTime time = OffsetDateTime.parse("2024-08-13T16:00:05.829402900Z");
        Long taskId = 2L;
        CommentDto commentDto = new CommentDto(1L, time,
                taskId, "text", false);

        commentDto.setText("newText");

        Mockito.when(commentService.editComment(taskId, commentDto))
                .thenReturn(ResponseEntity.ok().body(commentDto));

        String commentJson = objectMapper.writeValueAsString(commentDto);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put("/tasks/{taskId}/comment", taskId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(commentJson)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(commentJson));


        Mockito.verify(commentService, Mockito.times(1))
                .editComment(taskId, commentDto);
    }

    @Test
    void deleteComment() throws Exception {

        OffsetDateTime time = OffsetDateTime.parse("2024-08-13T16:00:05.829402900Z");
        Long taskId = 2L;
        CommentDto commentDto = new CommentDto(1L, time,
                taskId, "text", false);

        commentDto.setDeleted(true);

        Mockito.when(commentService.deleteComment(taskId,1L))
                .thenReturn(ResponseEntity.ok().body(1L));

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete("/tasks/{taskId}/comment/{commentId}", taskId, 1L)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"));


        Mockito.verify(commentService, Mockito.times(1))
                .deleteComment(taskId, 1L);
    }
}