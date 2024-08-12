package ru.tasksystem.repository;

import jooq.db.Tables;
import jooq.db.tables.records.TaskCommentRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.OrderField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.tasksystem.dto.CommentDto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.jooq.impl.DSL.asterisk;

@RequiredArgsConstructor
@Repository
@Slf4j
public class CommentRepositoryImpl implements CommentRepository {

    private final DSLContext dsl;

    @Override
    public Page<CommentDto> getComments(Long taskId, boolean isDeleted, String sort, Pageable pageable) {
        List<CommentDto> comments = dsl.select(asterisk()).from(Tables.TASK_COMMENT)
                .where(getCommentsCondition(taskId, isDeleted))
                .orderBy((OrderField<?>) sorted(sort))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchInto(CommentDto.class);
        return new PageImpl<>(comments, pageable, getCommentsCount(taskId, isDeleted));
    }

    @Override
    public Long addNewComment(Long taskId, TaskCommentRecord commentRecord) {
        return dsl.insertInto(Tables.TASK_COMMENT)
                .set(commentRecord)
                .set(Tables.TASK_COMMENT.TASK_ID, taskId)
                .returning()
                .fetchOptional()
                .map(TaskCommentRecord::getId)
                .orElse(0L);
    }

    @Override
    public Optional<TaskCommentRecord> getCommentById(Long id) {
        return dsl.selectFrom(Tables.TASK_COMMENT)
                .where(Tables.TASK_COMMENT.ID.eq(id))
                .and(Tables.TASK_COMMENT.IS_DELETED.eq(false))
                .fetchOptional();
    }

    @Override
    public void editComment(TaskCommentRecord commentRecord) {
        dsl.update(Tables.TASK_COMMENT)
                .set(Tables.TASK_COMMENT.COMMENT_TEXT, commentRecord.getCommentText())
                .set(Tables.TASK_COMMENT.TIME, OffsetDateTime.now())
                .where(Tables.TASK_COMMENT.ID.eq(commentRecord.getId()))
                .execute();
    }

    @Override
    public void deleteComment(Long taskId, Long commentId) {
        dsl.update(Tables.TASK_COMMENT)
                .set(Tables.TASK_COMMENT.IS_DELETED, true)
                .where(Tables.TASK_COMMENT.ID.eq(commentId))
                .and(Tables.TASK_COMMENT.TASK_ID.eq(taskId))
                .execute();
    }

    private long getCommentsCount(Long taskId, Boolean isDeleted) {
        return dsl.fetchCount(dsl.select().from(Tables.TASK_COMMENT)
                .where(getCommentsCondition(taskId, isDeleted)));
    }

    private Condition getCommentsCondition(Long taskId, Boolean isDeleted) {
        Condition condition = Tables.TASK_COMMENT.TASK_ID.eq(taskId);
        if (isDeleted != null) {
            condition = condition.and(Tables.TASK_COMMENT.IS_DELETED.eq(isDeleted));
        }
        return condition;
    }

    public Object sorted(String sort) {
        Object sortField = null;
        String[] params = sort.split(",+");
        if (Objects.equals(params[0], "time")) {
            if (Objects.equals(params[1], "desc")) {
                sortField = Tables.TASK_COMMENT.TIME.desc();
            } else {
                sortField = Tables.TASK_COMMENT.TIME.asc();
            }
        }
        return sortField;
    }
}
