package ru.tasksystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tasksystem.dto.enums.PriorityType;
import ru.tasksystem.dto.enums.StatusType;

import java.time.OffsetDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {
    private Long id;
    private OffsetDateTime time;
    private Long authorId;
    private Long executorId;
    private String title;
    private String text;
    private StatusType status;
    private PriorityType priority;
    private int commentsCount;
}
