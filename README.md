# task-ms
Описание задачи:
Простая система управления задачами (Task Management System) с использованием Java.
Система обеспечивает создание, редактирование, удаление и просмотр задач.
Каждая задача содержит заголовок, описание, статус и приоритет, автора задачи и исполнителя. 
Реализован только API.

Для реализации системы использованы: Java 17+, Spring, Spring Boot.
БД - PostgreSQL, Jooq ORM
Аутентификация и авторизация Spring Security.

## Создание контейнера с базой данных PostgreSQL в Docker

После установки приложения Docker в командной строке ввести команду для запуска контейнера с PostgreSQL (образ при необходимости загрузится автоматически) :

    docker run -d --name taskdb -p 5432:5432 -e POSTGRES_USER=admin -e POSTGRES_DB=test4 -e POSTGRES_PASSWORD=11111111 -e PGDATA=/var/lib/postgresql/data/pgdata postgres:16.0-alpine3.18

## database connection parameters `application-docker.yaml`: 
    spring:
        datasource:
        url:
        username:
        password:
        driver-class-name:

