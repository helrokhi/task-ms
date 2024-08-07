/*
 * This file is generated by jOOQ.
 */
package jooq.db.tables.records;


import jooq.db.tables.User;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Данные пользователя для аутентификации
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserRecord extends UpdatableRecordImpl<UserRecord> implements Record4<Long, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>tasksystem.user.id</code>. ID пользователя
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>tasksystem.user.id</code>. ID пользователя
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>tasksystem.user.email</code>. Email
     */
    public void setEmail(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>tasksystem.user.email</code>. Email
     */
    public String getEmail() {
        return (String) get(1);
    }

    /**
     * Setter for <code>tasksystem.user.password</code>. Пароль
     */
    public void setPassword(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>tasksystem.user.password</code>. Пароль
     */
    public String getPassword() {
        return (String) get(2);
    }

    /**
     * Setter for <code>tasksystem.user.role</code>. Роль
     */
    public void setRole(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>tasksystem.user.role</code>. Роль
     */
    public String getRole() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Long, String, String, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return User.USER.ID;
    }

    @Override
    public Field<String> field2() {
        return User.USER.EMAIL;
    }

    @Override
    public Field<String> field3() {
        return User.USER.PASSWORD;
    }

    @Override
    public Field<String> field4() {
        return User.USER.ROLE;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getEmail();
    }

    @Override
    public String component3() {
        return getPassword();
    }

    @Override
    public String component4() {
        return getRole();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getEmail();
    }

    @Override
    public String value3() {
        return getPassword();
    }

    @Override
    public String value4() {
        return getRole();
    }

    @Override
    public UserRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public UserRecord value2(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public UserRecord value3(String value) {
        setPassword(value);
        return this;
    }

    @Override
    public UserRecord value4(String value) {
        setRole(value);
        return this;
    }

    @Override
    public UserRecord values(Long value1, String value2, String value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserRecord
     */
    public UserRecord() {
        super(User.USER);
    }

    /**
     * Create a detached, initialised UserRecord
     */
    public UserRecord(Long id, String email, String password, String role) {
        super(User.USER);

        setId(id);
        setEmail(email);
        setPassword(password);
        setRole(role);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised UserRecord
     */
    public UserRecord(jooq.db.tables.pojos.User value) {
        super(User.USER);

        if (value != null) {
            setId(value.getId());
            setEmail(value.getEmail());
            setPassword(value.getPassword());
            setRole(value.getRole());
            resetChangedOnNotNull();
        }
    }
}
