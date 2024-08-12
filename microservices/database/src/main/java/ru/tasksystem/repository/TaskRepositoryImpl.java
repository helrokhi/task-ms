package ru.tasksystem.repository;

import jooq.db.Tables;
import jooq.db.tables.records.TaskRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.OrderField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.tasksystem.dto.TaskDto;
import ru.tasksystem.dto.enums.StatusType;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.jooq.impl.DSL.*;

@RequiredArgsConstructor
@Repository
@Slf4j
public class TaskRepositoryImpl implements TaskRepository {

    private final DSLContext dsl;

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
        List<TaskDto> tasks = dsl.select(asterisk()).from(Tables.TASK)
                .where(getAllPostCondition(ids, accountIds, author,
                        text, isDeleted, dateFrom, dateTo))
                .orderBy((OrderField<?>) sorted(sort))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchInto(TaskDto.class);

        long tasksCount = getPostsCount(ids, accountIds, author, text, isDeleted, dateFrom, dateTo);

        return new PageImpl<>(tasks, pageable, tasksCount);
    }

    @Override
    public Optional<TaskRecord> getTaskById(Long id) {
        return dsl.selectFrom(Tables.TASK)
                .where(Tables.TASK.ID.eq(id))
                .and(Tables.TASK.IS_DELETED.eq(false))
                .and(Tables.TASK.TIME.lessOrEqual(OffsetDateTime.now()))
                .fetchOptional();
    }

    @Override
    public Long addNewTask(TaskRecord taskRecord) {
        return dsl.insertInto(Tables.TASK)
                .set(taskRecord)
                .returning()
                .fetchOptional()
                .map(TaskRecord::getId)
                .orElse(0L);
    }

    @Override
    public void editTask(TaskRecord taskRecord) {
        dsl.update(Tables.TASK)
                .set(Tables.TASK.TASK_TEXT, taskRecord.getTaskText())
                .set(Tables.TASK.TITLE, taskRecord.getTitle())
                .where(Tables.TASK.ID.eq(taskRecord.getId()))
                .execute();
    }

    @Override
    public void deleteTaskById(Long id) {
        dsl.update(Tables.TASK)
                .set(Tables.TASK.IS_DELETED, true)
                .where(Tables.TASK.ID.eq(id))
                .execute();
    }

    @Override
    public void updateStatusTask(TaskRecord taskRecord, StatusType statusType) {
        dsl.update(Tables.TASK)
                .set(Tables.TASK.STATUS, statusType.name())
                .where(Tables.TASK.ID.eq(taskRecord.getId()))
                .execute();
    }

    @Override
    public void updateExecutorTask(TaskRecord taskRecord, Long executorId) {
        dsl.update(Tables.TASK)
                .set(Tables.TASK.EXECUTOR_ID, executorId)
                .where(Tables.TASK.ID.eq(taskRecord.getId()))
                .execute();
    }

    @Override
    public void incrementCommentsCount(TaskRecord taskRecord) {
        dsl.update(Tables.TASK)
                .set(Tables.TASK.COMMENTS_COUNT, Tables.TASK.COMMENTS_COUNT.add(1))
                .where(Tables.TASK.ID.eq(taskRecord.getId()))
                .execute();
    }

    @Override
    public void decrementCommentsCount(TaskRecord taskRecord) {
        dsl.update(Tables.TASK)
                .set(Tables.TASK.COMMENTS_COUNT, taskRecord.getCommentsCount() - 1)
                .where(Tables.TASK.ID.eq(taskRecord.getId()))
                .execute();
    }

    private Condition getAllPostCondition(List<Long> ids,
                                          List<Long> accountIds,
                                          String author,
                                          String text,
                                          Boolean isDeleted,
                                          OffsetDateTime dateFrom,
                                          OffsetDateTime dateTo) {

        Condition condition = Tables.TASK.IS_DELETED.eq(isDeleted);

        if (ids != null) {
            condition = condition.and(Tables.TASK.ID.in(ids));
        }

        if (accountIds != null) {
            condition = condition.and(Tables.TASK.AUTHOR_ID.in(accountIds));
        }

        if (author != null) {
            condition = condition.and(Tables.TASK.AUTHOR_ID.in(authorsIdsByFullName(author)));
        }

        if (text != null) {
            condition = condition.and(Tables.TASK.TASK_TEXT.like("%" + text + "%"));
        }

        if (dateFrom != null) {
            condition = condition.and(Tables.TASK.TIME.greaterOrEqual(dateFrom));
        }

        if (dateTo != null) {
            condition = condition.and(Tables.TASK.TIME.lessOrEqual(dateTo));
        }

        return condition;
    }

    private Object sorted(String sort) {
        Object sortField = null;
        String[] params = sort.split(",+");
        if (Objects.equals(params[0], "time")) {
            if (Objects.equals(params[1], "desc")) {
                sortField = Tables.TASK.TIME.desc();
            } else {
                sortField = Tables.TASK.TIME.asc();
            }
        }
        return sortField;
    }

    private int getPostsCount(List<Long> ids, List<Long> accountsId,
                              String author,
                              String text, Boolean isDeleted,
                              OffsetDateTime dateFrom, OffsetDateTime dateTo) {
        return Optional.ofNullable(dsl.selectCount().from(Tables.TASK)
                .where(getAllPostCondition(ids, accountsId,
                        author, text,
                        isDeleted, dateFrom,
                        dateTo))
                .fetchOne(count())).orElse(0);
    }

    private List<Long> authorsIdsByFullName(String author) {
        return dsl.select(Tables.PERSON.ID).from(Tables.PERSON)
                .where(concat(Tables.PERSON.FIRST_NAME, Tables.PERSON.LAST_NAME).like("%" + author + "%"))
                .fetchInto(Long.class);
    }
}
