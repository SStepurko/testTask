# Test java app
## Задача

- HTTP POST эндпоинт, который получает данные в json вида: {
name: "имя отправителя", password: "пароль" }  
  этот эндпоинт проверяет пароль по БД и создает jwt токен. 
в токен записывает данные: name: "имя отправителя" и отправляет токен в ответ, 
тоже json вида: { token: "тут сгенерированный токен" }

- Сервер слушает и отвечает в какой-нибудь эндпоинт, 
в него на вход поступают данные в формате json:
  - { name: "имя отправителя", message: "текст сообщение" }  
  В заголовках указан Bearer токен, полученный из эндпоинта выше
  (между Bearer и полученным токеном должно быть нижнее подчеркивание).
  Проверить токен, в случае успешной проверки токена, полученное сообщение сохранить в БД.

  - { name: "имя отправителя", message: "history 10" }  
  проверить токен, в случае успешной проверки токена отправить отправителю 10 последних сообщений из БД

## Конфигурация приложения

### База данных

Используется H2 база данных с хранинием данных в памяти.
Две таблицы с сущностями app_user и message.  
App_user содержить поля (user_id, name, password).
Message содержит поля (message_id, message_text, message_user_id).  
message_user_id foreign key для app_user.user_id.  
Таблицы и связь генерируются через SPRING JPA.

### Web server

Настроены два эндпоинта для POST запросов через SPRING WEB Services.  
Порт используется 7000. Основной адрес для контроллера localhost:7000/api  
Для аутентификации используется адрес /api/authenticate  
Для получения сообщений /api/message

### Описание структуры

- "api" пакет содержит всё для взаимодействия с пользователем на уровне web
  - AuthenticationData используется для маппинга данных из запроса на авторизацию
  - AuthenticationResponse генерация ответа и передача jwt токена
  - PostData для маппинга данных из запросов к эндпоинту для сообщений
  - UserController основной контроллер запросов на Spring Web Services
- "config" пакет для настройки безопасности и генерации первых данных 
  - JwtFilter проверка каждого запроса на токен
  - SecurityConfig переназначение методов Spring Security для реализации токена
  - UserConfig конфигурация для записи первых данных в таблицу
- "entity" пакет с сущностями БД
  - AppUser сущность пользователя
  - Message сущность сообщений пользователя
- "repository" пакет для работы с БД через Spring JPA
  - MessageRepository для работы с сущностью message  
    findFirst10ByAppUser возвращает последние 10 сообщений пользоватля
  - UserRepository для работы с сущностью AppUser  
    findAppUserByName поиск по имени
- "security" пакет для использования библиотеки по генерации jwt токена
  - JwtImpl имплементация утилиты для токена
- "service" пакет для бизнес логики приложения
  - UserService для работы с репозиториями message и appUser.  
  имплементация для сервиса авторизации UserDetailsService из Spring Security

## Возможные улучшения

Добавить Модель как сущность между Entity классами из JPA и API слоем.  
Добавить эндпоинт для регистрации новый пользователей


