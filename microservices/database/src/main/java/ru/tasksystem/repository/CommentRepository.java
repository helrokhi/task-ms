package ru.tasksystem.repository;

import jooq.db.tables.records.TaskCommentRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.tasksystem.dto.CommentDto;

import java.util.Optional;

public interface CommentRepository {
    Page<CommentDto> getComments(
            Long taskId,
            boolean isDeleted,
            String sort,
            Pageable pageable);

    Long addNewComment (Long taskId, TaskCommentRecord commentRecord);

    Optional<TaskCommentRecord> getCommentById(Long commentId);

    void editComment(TaskCommentRecord commentRecord);

    void deleteComment(Long postId, Long commentId);
}
