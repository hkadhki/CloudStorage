# Дипломная работа “Облачное хранилище”

## Описание проекта

Разработано приложение - REST-сервис. Сервис предоставляет REST интерфейс для возможности загрузки файлов и вывода списка уже загруженных файлов пользователя.
Заранее подготовленное веб-приложение (FRONT) подключаетсяя к разработанному сервису без доработок. Сервис реализовывает следующие функции:
1. Вывод списка файлов
2. Добавление файла
3. Удаление файла
4. Переименование файла
5. Авторизация

После ввода логина и пароля производится проверка клиента на наличие в базе данных и его прав. Пароль в базе данных хранится в зашифрованном виде. В случае успешной авторизации генерируется JWT token, который позволяет пользователю работать с файлами в течение срока действия токена. Стоит отметить, что после успешной авторизации все запросы с FRONT отправляются с header "auth-token", а в качестве значения содержится токен. При осуществлении пользователем logout токен аннулируется и происходит переходит на страницу входа.


## Описание и запуск FRONT

1. Установить nodejs (версия не ниже 19.7.0) на компьютер следуя инструкции: https://nodejs.org/ru/download/
2. Скачать [FRONT](https://github.com/netology-code/jd-homeworks/tree/master/diploma/netology-diplom-frontend) (JavaScript)
3. Перейти в папку FRONT приложения и все команды для запуска выполнять из нее.
4. Следуя описанию README.md FRONT проекта запустить nodejs приложение (npm install, npm run serve)
5. Можно задать url для вызова своего backend сервиса:
    1. В файле `.env` FRONT (находится в корне проекта) приложения нужно изменить url до backend, например: `VUE_APP_BASE_URL=http://localhost:8080`
    2. Пересобрать и запустить FRONT снова: `npm run serve`
    3. Измененный `url` сохранится для следующих запусков.

## Запуск сервера и базы данных:

* По-умолчанию FRONT запускается на порте 8080 и доступен по url в браузере http://localhost:8080
* В приложении используется база данных PostgreSQL. Запускается на порте 5432. Для запуска приложения необходимо выполнить команду docker compose up.

## Стартовые пользователи:

**USERNAME:** User1 **PASSWORD:** User

**USERNAME:** User2 **PASSWORD:** User 