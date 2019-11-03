# Periodicals

### Project description:

**19. Система Периодические издания.**

**Администратор** осуществляет ведение каталога **периодических Изданий**.
**Читатель** может оформить **Подписку**, предварительно выбрав **периодические
Издания** из списка. Система подсчитывает сумму для оплаты и регистрирует **Платеж**.

### Общие требования:
- [ ] На основе сущностей предметной области создать классы их описывающие.
- [ ] Классы и методы должны иметь отражающую их функциональность названия и должны быть грамотно структурированы по пакетам.
- [ ] Информацию о предметной области хранить в БД. В качестве СУБД рекомендуется MySQL.
- [ ] Приложение должно поддерживать работу с кириллицей (быть многоязычной), в том числе и при хранении информации в БД.
- [ ] Код должен быть документирован.
- [ ] Приложение должно быть покрыто Юнит-тестами.
- [ ] Cобытия в системе обрабатывать с помощью Log4j.
- [ ] В приложении необходимо реализовать Pagination, Transaction в зависимости от Вашего проекта.
- [ ] Приложение должно корректно реагировать на ошибки и исключения разного рода (Пользователь никогда не должен видеть stack-trace на стороне front-end).
- [ ] В приложении должна быть реализована система Авторизации и Аутентификации.

### Servlets:
- [ ] Для доступа к данным использовать API JDBC с использованием пула соединений, стандартного или разработанного самостоятельно. 
- [ ] При разработке бизнес логики использовать сессии и фильтры.
- [ ] Используя сервлеты и JSP, реализовать функциональности, предложенные в постановке конкретной задачи.
- [ ] В страницах JSP применять библиотеку JSTL.

### Архитектура проекта (Servlets)

- [ ] Архитектура приложения должна соответствовать шаблону ModelView-Controller. 
- [ ] webapp. 
- [ ] Сборка приложения происходит при помощи Maven / Gradle (Уметь собирать проект через консоль, не только нажимая зелёную кнопку Run). 
- [ ] При реализации алгоритмов бизнес-логики использовать шаблоны: 
  - GoF (Factory Method, Command, Builder, Strategy, State, Observer etc.);
  - SOLID – понимать что это и применить в проекте;
  - GRASP – по желанию


## Инструкции по установке
1. [Скачать](https://github.com/KovalDS/Project4/archive/master.zip) или клонировать проект с репозитория используя команду:
> `git clone https://github.com/Valzavator/Servlet_Svynarchuk_Periodicals.git`
2. Установить на локальную машину:
- [Java SE 8 или новее](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
- [Apache Tomcat 9](https://tomcat.apache.org/download-90.cgi)
- [MySQL Server 8](https://dev.mysql.com/downloads/installer/)
- [Maven](https://maven.apache.org/download.cgi)

## Инструкция по запуску приложения [TO DO]
1. В MySQL создать базу данных и сгенерировать начальные данные с помощью скриптов `mysql_script.sql` и `generate_data.sql` в `resources/script/*`.
2. В файле `META_INF/context.xml` вставьте свой логин, пароль напротив полей `username` и `password`;
3. В главной папке проекта открыть командную строку;
4. Ввести в командной строке `jar -cfv periodicals.war *`;
5. В папке проекта появился файл `Periodicals.war`, перетаскиваем его в папку Tomcat'a - webapp;
6. Запускаем Tomcat (В Windows, нажимаем на файл Tomcat'a bin/startup.bat);
7. Вводим в браузере `http://localhost:8080/app` - оказываемся на странице приветствия приложения.
