/*
 * This file is generated by jOOQ.
 */
package jooq.db.tables.records;


import java.time.OffsetDateTime;

import jooq.db.tables.Person;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Пользователь
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PersonRecord extends UpdatableRecordImpl<PersonRecord> implements Record5<Long, Long, String, String, OffsetDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>tasksystem.person.id</code>. ID пользователя
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>tasksystem.person.id</code>. ID пользователя
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>tasksystem.person.user_id</code>. ID пользователя
     */
    public void setUserId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>tasksystem.person.user_id</code>. ID пользователя
     */
    public Long getUserId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>tasksystem.person.first_name</code>. Имя
     */
    public void setFirstName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>tasksystem.person.first_name</code>. Имя
     */
    public String getFirstName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>tasksystem.person.last_name</code>. Фамилия
     */
    public void setLastName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>tasksystem.person.last_name</code>. Фамилия
     */
    public String getLastName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>tasksystem.person.reg_date</code>. Дата и время
     * регистрации
     */
    public void setRegDate(OffsetDateTime value) {
        set(4, value);
    }

    /**
     * Getter for <code>tasksystem.person.reg_date</code>. Дата и время
     * регистрации
     */
    public OffsetDateTime getRegDate() {
        return (OffsetDateTime) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, Long, String, String, OffsetDateTime> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Long, Long, String, String, OffsetDateTime> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Person.PERSON.ID;
    }

    @Override
    public Field<Long> field2() {
        return Person.PERSON.USER_ID;
    }

    @Override
    public Field<String> field3() {
        return Person.PERSON.FIRST_NAME;
    }

    @Override
    public Field<String> field4() {
        return Person.PERSON.LAST_NAME;
    }

    @Override
    public Field<OffsetDateTime> field5() {
        return Person.PERSON.REG_DATE;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public Long component2() {
        return getUserId();
    }

    @Override
    public String component3() {
        return getFirstName();
    }

    @Override
    public String component4() {
        return getLastName();
    }

    @Override
    public OffsetDateTime component5() {
        return getRegDate();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public Long value2() {
        return getUserId();
    }

    @Override
    public String value3() {
        return getFirstName();
    }

    @Override
    public String value4() {
        return getLastName();
    }

    @Override
    public OffsetDateTime value5() {
        return getRegDate();
    }

    @Override
    public PersonRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public PersonRecord value2(Long value) {
        setUserId(value);
        return this;
    }

    @Override
    public PersonRecord value3(String value) {
        setFirstName(value);
        return this;
    }

    @Override
    public PersonRecord value4(String value) {
        setLastName(value);
        return this;
    }

    @Override
    public PersonRecord value5(OffsetDateTime value) {
        setRegDate(value);
        return this;
    }

    @Override
    public PersonRecord values(Long value1, Long value2, String value3, String value4, OffsetDateTime value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PersonRecord
     */
    public PersonRecord() {
        super(Person.PERSON);
    }

    /**
     * Create a detached, initialised PersonRecord
     */
    public PersonRecord(Long id, Long userId, String firstName, String lastName, OffsetDateTime regDate) {
        super(Person.PERSON);

        setId(id);
        setUserId(userId);
        setFirstName(firstName);
        setLastName(lastName);
        setRegDate(regDate);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised PersonRecord
     */
    public PersonRecord(jooq.db.tables.pojos.Person value) {
        super(Person.PERSON);

        if (value != null) {
            setId(value.getId());
            setUserId(value.getUserId());
            setFirstName(value.getFirstName());
            setLastName(value.getLastName());
            setRegDate(value.getRegDate());
            resetChangedOnNotNull();
        }
    }
}
