# task-ms

## Создание контейнера с базой данных PostgreSQL в Docker

После установки приложения Docker в командной строке ввести команду для запуска контейнера с PostgreSQL (образ при необходимости загрузится автоматически) :

    docker run -d --name taskdb -p 5432:5432 -e POSTGRES_USER=admin -e POSTGRES_DB=test4 -e POSTGRES_PASSWORD=11111111 -e PGDATA=/var/lib/postgresql/data/pgdata postgres:16.0-alpine3.18
