package ru.tasksystem.service.comments;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import ru.tasksystem.dto.CommentDto;

public interface CommentService {
    ResponseEntity<Page<CommentDto>> getComments(Long taskId, boolean isDeleted, String sort, Pageable pageable);

    ResponseEntity<CommentDto> addNewComment(Long taskId, CommentDto commentDto);

    ResponseEntity<CommentDto> editComment(Long taskId, CommentDto commentDto);

    ResponseEntity<Long> deleteComment(Long taskId, Long commentId);
}
