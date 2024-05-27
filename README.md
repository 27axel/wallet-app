# Wallet-app

## Описание
Wallet Service - это RESTful приложение для управления кошельками. 
Оно позволяет выполнять операции пополнения (DEPOSIT) и снятия (WITHDRAW) средств с кошелька, а также получать баланс кошелька. 
Приложение написано на языке Java с использованием фреймворка Spring и базы данных PostgreSQL. 
Все компоненты приложения запускаются в контейнерах Docker, а для управления инфраструктурой используется Docker Compose.

## Функциональность
### API
#### POST /api/v1/wallet
Позволяет выполнить операцию пополнения или снятия средств с кошелька.

#### Запрос:
```
{
  "walletId": "UUID",
  "operationType": "DEPOSIT or WITHDRAW",
  "amount": 1000
}
```
#### Ответы:

- 200 OK: Операция выполнена успешно.
- 400 Bad Request: Некорректный запрос (например, неверный формат JSON).
- 404 Not Found: Кошелек не найден.
- 409 Conflict: Недостаточно средств для выполнения операции.
- 500 Internal Server Error: Внутренняя ошибка сервера.

#### GET /api/v1/wallets/{WALLET_UUID}
Возвращает баланс кошелька.

#### Ответы:

- 200 OK: Успешный запрос. Возвращает JSON с балансом.
- 404 Not Found: Кошелек не найден.
- 500 Internal Server Error: Внутренняя ошибка сервера.

## Как запустить приложение
1) Скачать проект
2) Открыть консоль и перейти в папку проекта
3) Используй эти команды для настройки и запуска приложения:
- Переменные окружения:
  - Windows:
    ```
    set DB_NAME=[db_name]
    set DB_USER=[db_username]
    set DB_PASSWORD=[db_password]
    set DDL_AUTO=[hibernate_ddl_auto]
    ```
  - Linux:
    ```
    export DB_NAME=[db_name]
    export DB_USER=[db_username]
    export DB_PASSWORD=[db_password]
    export DDL_AUTO=[hibernate_ddl_auto]
    ```
- Сборка проекта Maven:
```
mvn clean package
```
- Создание Docker image и запуск docker-compose:
```
docker build -t wallet-app .
```
```
docker-compose up
```

## Доступ к запущенному приложению
http://localhost:8080/api/v1/wallet/{walletId}

### Тестовые UUID кошельков
> e7b8a59e-4f2e-4fda-82d4-4d1c2b9d7a7b  
> c0a8012b-4c0a-4d3b-90d4-6e4d9b2d7c1f  
> f47ac10b-58cc-4372-a567-0e02b2c3d479  
