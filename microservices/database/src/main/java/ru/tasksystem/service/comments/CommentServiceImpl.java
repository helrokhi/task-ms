package ru.tasksystem.service.comments;

import jooq.db.tables.records.TaskCommentRecord;
import jooq.db.tables.records.TaskRecord;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.tasksystem.dto.CommentDto;
import ru.tasksystem.repository.CommentRepository;
import ru.tasksystem.repository.TaskRepository;
import ru.tasksystem.repository.mapper.CommentDtoCommentRecordMapper;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private TaskRepository taskRepository;
    private CommentDtoCommentRecordMapper mapper;

    @Override
    public ResponseEntity<Page<CommentDto>> getComments(
            Long taskId,
            boolean isDeleted,
            String sort,
            Pageable pageable) {
        Page<CommentDto> comments = commentRepository.getComments(taskId, isDeleted, sort, pageable);
        return ResponseEntity.ok().body(comments);
    }

    @Override
    public ResponseEntity<CommentDto> addNewComment(Long taskId, CommentDto commentDto) {
        TaskCommentRecord commentRecord = mapper.commentDtoToCommentRecord(commentDto);
        Long commentId = commentRepository.addNewComment(taskId, commentRecord);
        Optional<TaskCommentRecord> comment = commentRepository.getCommentById(commentId);
        if (comment.isPresent()) {
            Optional<TaskRecord> task = taskRepository.getTaskById(comment.get().getTaskId());
            task.ifPresent(taskRecord -> taskRepository.incrementCommentsCount(taskRecord));
        }

        return comment.map(taskCommentRecord -> ResponseEntity.ok()
                        .body(mapper.commentRecordToCommentDto(taskCommentRecord)))
                .orElse(null);
    }

    @Override
    public ResponseEntity<CommentDto> editComment(Long taskId, CommentDto commentDto) {
        if (commentRepository.getCommentById(commentDto.getId()).isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            TaskCommentRecord commentRecord = mapper.commentDtoToCommentRecordForEdit(commentDto);
            commentRepository.editComment(commentRecord);
            return ResponseEntity.ok().body(mapper.commentRecordToCommentDto(commentRecord));
        }
    }

    @Override
    public ResponseEntity<Long> deleteComment(Long taskId, Long commentId) {
        Optional<TaskRecord> task = taskRepository.getTaskById(taskId);
        Optional<TaskCommentRecord> comment = commentRepository.getCommentById(commentId);
        if (task.isPresent() && comment.isPresent()) {
            deleteCommentToTask(taskId, commentId, task.get());
        }
        return ResponseEntity.ok().body(commentId);
    }

    private void deleteCommentToTask(Long taskId,
                                     Long commentId,
                                     TaskRecord task) {

        taskRepository.decrementCommentsCount(task);
        commentRepository.deleteComment(taskId, commentId);
    }
}
