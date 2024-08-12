/*
 * This file is generated by jOOQ.
 */
package jooq.db.tables;


import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import jooq.db.Keys;
import jooq.db.Tasksystem;
import jooq.db.tables.records.PersonRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function5;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row5;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * Пользователь
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Person extends TableImpl<PersonRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>tasksystem.person</code>
     */
    public static final Person PERSON = new Person();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PersonRecord> getRecordType() {
        return PersonRecord.class;
    }

    /**
     * The column <code>tasksystem.person.id</code>. ID пользователя
     */
    public final TableField<PersonRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "ID пользователя");

    /**
     * The column <code>tasksystem.person.user_id</code>. ID пользователя
     */
    public final TableField<PersonRecord, Long> USER_ID = createField(DSL.name("user_id"), SQLDataType.BIGINT.nullable(false), this, "ID пользователя");

    /**
     * The column <code>tasksystem.person.first_name</code>. Имя
     */
    public final TableField<PersonRecord, String> FIRST_NAME = createField(DSL.name("first_name"), SQLDataType.VARCHAR(50), this, "Имя");

    /**
     * The column <code>tasksystem.person.last_name</code>. Фамилия
     */
    public final TableField<PersonRecord, String> LAST_NAME = createField(DSL.name("last_name"), SQLDataType.VARCHAR(50), this, "Фамилия");

    /**
     * The column <code>tasksystem.person.reg_date</code>. Дата и время
     * регистрации
     */
    public final TableField<PersonRecord, OffsetDateTime> REG_DATE = createField(DSL.name("reg_date"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "Дата и время регистрации");

    private Person(Name alias, Table<PersonRecord> aliased) {
        this(alias, aliased, null);
    }

    private Person(Name alias, Table<PersonRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("Пользователь"), TableOptions.table());
    }

    /**
     * Create an aliased <code>tasksystem.person</code> table reference
     */
    public Person(String alias) {
        this(DSL.name(alias), PERSON);
    }

    /**
     * Create an aliased <code>tasksystem.person</code> table reference
     */
    public Person(Name alias) {
        this(alias, PERSON);
    }

    /**
     * Create a <code>tasksystem.person</code> table reference
     */
    public Person() {
        this(DSL.name("person"), null);
    }

    public <O extends Record> Person(Table<O> child, ForeignKey<O, PersonRecord> key) {
        super(child, key, PERSON);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Tasksystem.TASKSYSTEM;
    }

    @Override
    public Identity<PersonRecord, Long> getIdentity() {
        return (Identity<PersonRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<PersonRecord> getPrimaryKey() {
        return Keys.PERSON_PKEY;
    }

    @Override
    public List<UniqueKey<PersonRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.PERSON_ID_KEY);
    }

    @Override
    public Person as(String alias) {
        return new Person(DSL.name(alias), this);
    }

    @Override
    public Person as(Name alias) {
        return new Person(alias, this);
    }

    @Override
    public Person as(Table<?> alias) {
        return new Person(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Person rename(String name) {
        return new Person(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Person rename(Name name) {
        return new Person(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Person rename(Table<?> name) {
        return new Person(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, Long, String, String, OffsetDateTime> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function5<? super Long, ? super Long, ? super String, ? super String, ? super OffsetDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function5<? super Long, ? super Long, ? super String, ? super String, ? super OffsetDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}