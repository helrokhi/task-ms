/*
 * This file is generated by jOOQ.
 */
package jooq.db;


import jooq.db.tables.Person;
import jooq.db.tables.Task;
import jooq.db.tables.TaskComment;
import jooq.db.tables.User;
import jooq.db.tables.records.PersonRecord;
import jooq.db.tables.records.TaskCommentRecord;
import jooq.db.tables.records.TaskRecord;
import jooq.db.tables.records.UserRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * tasksystem.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<PersonRecord> PERSON_ID_KEY = Internal.createUniqueKey(Person.PERSON, DSL.name("person_id_key"), new TableField[] { Person.PERSON.ID }, true);
    public static final UniqueKey<PersonRecord> PERSON_PKEY = Internal.createUniqueKey(Person.PERSON, DSL.name("person_pkey"), new TableField[] { Person.PERSON.USER_ID }, true);
    public static final UniqueKey<TaskRecord> TASK_PKEY = Internal.createUniqueKey(Task.TASK, DSL.name("task_pkey"), new TableField[] { Task.TASK.ID }, true);
    public static final UniqueKey<TaskCommentRecord> TASK_COMMENT_PK = Internal.createUniqueKey(TaskComment.TASK_COMMENT, DSL.name("task_comment_pk"), new TableField[] { TaskComment.TASK_COMMENT.ID }, true);
    public static final UniqueKey<UserRecord> USER_EMAIL_KEY = Internal.createUniqueKey(User.USER, DSL.name("user_email_key"), new TableField[] { User.USER.EMAIL }, true);
    public static final UniqueKey<UserRecord> USER_PKEY = Internal.createUniqueKey(User.USER, DSL.name("user_pkey"), new TableField[] { User.USER.ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<TaskRecord, UserRecord> TASK__TASK_AUTHOR_ID_USER_ID = Internal.createForeignKey(Task.TASK, DSL.name("task_author_id_user_id"), new TableField[] { Task.TASK.AUTHOR_ID }, Keys.USER_PKEY, new TableField[] { User.USER.ID }, true);
    public static final ForeignKey<TaskRecord, UserRecord> TASK__TASK_EXECUTOR_ID_USER_ID = Internal.createForeignKey(Task.TASK, DSL.name("task_executor_id_user_id"), new TableField[] { Task.TASK.EXECUTOR_ID }, Keys.USER_PKEY, new TableField[] { User.USER.ID }, true);
}