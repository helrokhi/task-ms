# task-ms
Описание задачи:
Простая система управления задачами (Task Management System) с использованием Java.
Система обеспечивает создание, редактирование, удаление и просмотр задач.
Каждая задача содержит заголовок, описание, статус и приоритет, автора задачи и исполнителя. 
Реализован только API.

Для реализации системы использованы: Java 17+, Spring, Spring Boot.
БД - PostgreSQL, Jooq ORM
Аутентификация и авторизация Spring Security.
Сборка Maven.
Документация по web-API Swagger
Тестирование Mockito

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

## Локальный запуск
    docker login -u helrokhitest4 -p helrokhitest4

    docker network create -d bridge test4-task-ms-net

    docker run -d --name taskdb --network test4-task-ms-net -p 5432:5432 -v data:/var/lib/postgresql/data -e POSTGRES_USER=admin -e POSTGRES_DB=test4 -e POSTGRES_PASSWORD=11111111 postgres:16.0-alpine3.18

    docker-compose -f .deploy/docker-compose-local.yaml pull

    docker-compose -p="test4-task-ms-net" -f .deploy/docker-compose-local.yaml up -d


## Build images:
    docker build -f .deploy/dockerfile-gateway -t helrokhitest4/task-ms:gateway .
    docker build -f .deploy/dockerfile-database -t helrokhitest4/task-ms:database .
    docker build -f .deploy/dockerfile-profile -t helrokhitest4/task-ms:profile .
    docker build -f .deploy/dockerfile-tasks -t helrokhitest4/task-ms:tasks .    

## Push images into Docker:
    docker push helrokhitest4/task-ms:gateway
    docker push helrokhitest4/task-ms:database
    docker push helrokhitest4/task-ms:profile
    docker push helrokhitest4/task-ms:tasks
