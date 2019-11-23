# Periodicals

### Project description:

**19. Система Периодические издания.**

**Администратор** осуществляет ведение каталога **периодических Изданий**.
**Читатель** может оформить **Подписку**, предварительно выбрав **периодические
Издания** из списка. Система подсчитывает сумму для оплаты и регистрирует **Платеж**.

### Общие требования:
- [x] На основе сущностей предметной области создать классы их описывающие.
- [x] Классы и методы должны иметь отражающую их функциональность названия и должны быть грамотно структурированы по пакетам.
- [x] Информацию о предметной области хранить в БД. В качестве СУБД рекомендуется MySQL.
- [x] Приложение должно поддерживать работу с кириллицей (быть многоязычной), в том числе и при хранении информации в БД.
- [x] Код должен быть документирован.
- [x] Приложение должно быть покрыто Юнит-тестами.
- [x] Cобытия в системе обрабатывать с помощью Log4j.
- [x] В приложении необходимо реализовать Pagination, Transaction в зависимости от Вашего проекта.
- [x] Приложение должно корректно реагировать на ошибки и исключения разного рода (Пользователь никогда не должен видеть stack-trace на стороне front-end).
- [x] В приложении должна быть реализована система Авторизации и Аутентификации.

### Servlets:
- [x] Для доступа к данным использовать API JDBC с использованием пула соединений, стандартного или разработанного самостоятельно. 
- [x] При разработке бизнес логики использовать сессии и фильтры.
- [x] Используя сервлеты и JSP, реализовать функциональности, предложенные в постановке конкретной задачи.
- [x] В страницах JSP применять библиотеку JSTL.

### Архитектура проекта (Servlets)

- [x] Архитектура приложения должна соответствовать шаблону ModelView-Controller. 
- [x] webapp. 
- [x] Сборка приложения происходит при помощи Maven / Gradle (Уметь собирать проект через консоль, не только нажимая зелёную кнопку Run). 
- [x] При реализации алгоритмов бизнес-логики использовать шаблоны: 
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

## Инструкция по запуску приложения на локальномом устройстве
1. В MySQL создать базу данных и сгенерировать начальные данные с помощью скриптов `mysql_script.sql` и `generate_data.sql` в `resources/script/*`.
2. Для досупа приложения к базе данных в файле `META_INF/context.xml` вставить свой логин, пароль напротив полей `username` и `password`;
3. Запустить Tomcat (на выбор):
    * с помощою скрипта `$CATALINA_HOME/bin/startup.sh (для *nix)` или `$CATALINA_HOME/bin/startup.bat` (для Windows)
    * (для виндовс) с помощью исполняемых файлов `Tomcat9.exe` или `Tomcat9.exe`
4. В корневой папке проекта выполнить `mvn tomcat7:deploy` (или `mvn tomcat7:redeploy`, если Tomcat уже имеет war-файл в исходной директории);
7. В браузере перейти по ссылке [`http://localhost:8080/app`](http://localhost:8080/app) - страница приветствия приложения.

> Как вариант, можно создать war-файл проекта с помощью команды `mvn clean install`, перетащить его в папку Tomcat'a - `$CATALINA_HOME/bin/`. Переименовать в `ROOT.war` с заменой существуещего. И перезапустить сервер.

> Для пропуска тестов во время сборки приложения можно использовать флаг `-DskipTests`

> Данные для входа в систему с ролью админа: 
> * email: **admin@gmail.com**
> * password: **admin**
## Результаты

### ER-модель базы данных

![modules.png](/docs/images/er-model-color.png)

### Интерфейс пользователя

![mainPage.PNG](/docs/images/mainPage.PNG)

![signUp.PNG](/docs/images/signUp.PNG)

![signIn.PNG](/docs/images/signIn.PNG)

![catalog.PNG](/docs/images/catalog.PNG)

![periodical.PNG](/docs/images/periodical.PNG)

![cart.PNG](/docs/images/cart.PNG)

![subscriptions.PNG](/docs/images/subscriptions.PNG)

![failed.PNG](/docs/images/failed.PNG)

![profile.PNG](/docs/images/profile.PNG)

![adminCatalog.PNG](/docs/images/adminCatalog.PNG)

![addPeriodical.PNG](/docs/images/addPeriodical.PNG)

![addIssue.PNG](/docs/images/addIssue.PNG)

![payments.PNG](/docs/images/payments.PNG)

![paymentOverview.PNG](/docs/images/paymentOverview.PNG)
