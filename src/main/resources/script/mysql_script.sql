CREATE SCHEMA IF NOT EXISTS periodicals DEFAULT CHARACTER SET utf8;

USE periodicals;

DROP TABLE IF EXISTS subscriptions;

DROP TABLE IF EXISTS payments;

DROP TABLE IF EXISTS subscription_plans;

DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS roles;

DROP TABLE IF EXISTS periodical_issues;

DROP TABLE IF EXISTS periodicals;

DROP TABLE IF EXISTS periodical_types;

DROP TABLE IF EXISTS frequencies;

DROP TABLE IF EXISTS publishers;

/*==============================================================*/
/* Table: roles                                                 */
/*==============================================================*/
CREATE TABLE roles
(
    role_id   INT          NOT NULL AUTO_INCREMENT,
    role_name VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (role_id)
);

/*==============================================================*/
/* Table: users                                                 */
/*==============================================================*/
CREATE TABLE users
(
    user_id       BIGINT                  NOT NULL AUTO_INCREMENT,
    role_id       INT                     NOT NULL,
    first_name    VARCHAR(255)            NOT NULL,
    last_name     VARCHAR(255)            NOT NULL,
    email         VARCHAR(255)            NOT NULL UNIQUE,
    password      VARCHAR(255)            NOT NULL,
    date_of_birth DATE                    NOT NULL,
    gender        ENUM ('male', 'female') NOT NULL,
    PRIMARY KEY (user_id),
    CONSTRAINT fk_user_role
        FOREIGN KEY (role_id) REFERENCES roles (role_id)
            ON UPDATE RESTRICT
            ON DELETE RESTRICT
);

/*==============================================================*/
/* Table: payments                                              */
/*==============================================================*/
CREATE TABLE payments
(
    payment_id   BIGINT                              NOT NULL AUTO_INCREMENT,
    user_id      BIGINT                              NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    total_price  DECIMAL(10, 2)                      NOT NULL,
    PRIMARY KEY (payment_id),
    CONSTRAINT fk_user_payment
        FOREIGN KEY (user_id) REFERENCES users (user_id)
            ON UPDATE RESTRICT
            ON DELETE RESTRICT,
    CONSTRAINT chk_total_price CHECK ( total_price >= 0 )
);

/*==============================================================*/
/* Table: subscription_plans                                    */
/*==============================================================*/
CREATE TABLE subscription_plans
(
    subscription_plan_id INT              NOT NULL AUTO_INCREMENT,
    plan_name            VARCHAR(255)     NOT NULL UNIQUE,
    months_amount        TINYINT UNSIGNED NOT NULL,
    rate                 DECIMAL(3, 2)    NOT NULL,
    plan_description     VARCHAR(1000),
    PRIMARY KEY (subscription_plan_id),
    CONSTRAINT chk_rate CHECK ( rate > 0 )
);

/*==============================================================*/
/* Table: frequencies                                             */
/*==============================================================*/
CREATE TABLE frequencies
(
    frequency_id   INT          NOT NULL AUTO_INCREMENT,
    frequency_name VARCHAR(255) NOT NULL UNIQUE,
    meaning        VARCHAR(255) NOT NULL,
    PRIMARY KEY (frequency_id)
);

/*==============================================================*/
/* Table: periodical_types                                      */
/*==============================================================*/
CREATE TABLE periodical_types
(
    periodical_type_id INT          NOT NULL AUTO_INCREMENT,
    type_name          VARCHAR(255) NOT NULL UNIQUE,
    type_description   VARCHAR(255),
    PRIMARY KEY (periodical_type_id)
);

/*==============================================================*/
/* Table: publishers                                            */
/*==============================================================*/
CREATE TABLE publishers
(
    publisher_id   BIGINT       NOT NULL AUTO_INCREMENT,
    publisher_name VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (publisher_id)
);

/*==============================================================*/
/* Table: periodicals                                           */
/*==============================================================*/
CREATE TABLE periodicals
(
    periodical_id          BIGINT                                        NOT NULL AUTO_INCREMENT,
    publisher_id           BIGINT                                        NOT NULL,
    frequency_id           INT                                           NOT NULL,
    periodical_type_id     INT                                           NOT NULL,
    periodical_name        VARCHAR(255)                                  NOT NULL,
    periodical_price       DECIMAL(10, 2)                                NOT NULL,
    periodical_description VARCHAR(1000)                                 NOT NULL,
    periodical_status      ENUM ('active', 'suspended') DEFAULT 'active' NOT NULL,
    PRIMARY KEY (periodical_id),
    CONSTRAINT fk_periodical_type
        FOREIGN KEY (periodical_type_id) REFERENCES periodical_types (periodical_type_id)
            ON UPDATE RESTRICT
            ON DELETE RESTRICT,
    CONSTRAINT fk_periodical_frequency
        FOREIGN KEY (frequency_id) REFERENCES frequencies (frequency_id)
            ON UPDATE RESTRICT
            ON DELETE RESTRICT,
    CONSTRAINT fk_periodical_publisher
        FOREIGN KEY (publisher_id) REFERENCES publishers (publisher_id)
            ON UPDATE RESTRICT
            ON DELETE RESTRICT,
    CONSTRAINT chk_price CHECK ( periodical_price > 0 )
);

/*==============================================================*/
/* Table: periodical_issues                                     */
/*==============================================================*/
CREATE TABLE periodical_issues
(
    periodical_issues_id BIGINT       NOT NULL AUTO_INCREMENT,
    periodical_id        BIGINT       NOT NULL,
    issues_name          VARCHAR(255) NOT NULL,
    issue_no             VARCHAR(10)  NOT NULL,
    publication_date     DATE         NOT NULL,
    issues_description   VARCHAR(1000),
    PRIMARY KEY (periodical_issues_id),
    UNIQUE (periodical_id, issue_no),
    CONSTRAINT fk_periodical_issue
        FOREIGN KEY (periodical_id) REFERENCES periodicals (periodical_id)
            ON UPDATE RESTRICT
            ON DELETE RESTRICT
);

/*==============================================================*/
/* Table: subscriptions                                         */
/*==============================================================*/
CREATE TABLE subscriptions
(
    subscription_id      BIGINT NOT NULL AUTO_INCREMENT,
    payment_id           BIGINT NOT NULL,
    user_id              BIGINT NOT NULL,
    periodical_id        BIGINT NOT NULL,
    subscription_plan_id INT    NOT NULL,
    start_date           DATE   NOT NULL,
    end_date             DATE   NOT NULL,
    PRIMARY KEY (subscription_id),
    CONSTRAINT fk_payment
        FOREIGN KEY (payment_id) REFERENCES payments (payment_id)
            ON UPDATE RESTRICT
            ON DELETE RESTRICT,
    CONSTRAINT fk_subscription_plan
        FOREIGN KEY (subscription_plan_id) REFERENCES subscription_plans (subscription_plan_id)
            ON UPDATE RESTRICT
            ON DELETE RESTRICT,
    CONSTRAINT fk_subscription_periodical
        FOREIGN KEY (periodical_id) REFERENCES periodicals (periodical_id)
            ON UPDATE RESTRICT
            ON DELETE RESTRICT,
    CONSTRAINT fk_user_subscription
        FOREIGN KEY (user_id) REFERENCES users (user_id)
            ON UPDATE RESTRICT
            ON DELETE RESTRICT,
    CONSTRAINT chk_start_end_time CHECK ( end_date > start_date )
);

/*==============================================================*/
/* Added default values                                         */
/*==============================================================*/

INSERT INTO roles(role_id, role_name)
VALUES (1, 'admin'),
       (2, 'user');

INSERT INTO subscription_plans(plan_name, months_amount, rate)
VALUES ('1 month', 1, 1),
       ('3 month', 3, 0.9),
       ('6 month', 6, 0.8),
       ('12 month', 12, 0.7);

INSERT INTO periodical_types(type_name, type_description)
VALUES ('Comics', ''),
       ('Magazine', ''),
       ('Newspaper', ''),
       ('Manga', ''),
       ('Others', '');

INSERT INTO frequencies(frequency_name, meaning)
VALUES ('Daily', 'Once per business day'),
       ('Semi-weekly', 'Twice per week'),
       ('Weekly', 'Every week'),
       ('Biweekly, Fortnightly', 'Every two weeks'),
       ('Semi-monthly', 'Twice per month'),
       ('Monthly', 'Every month');

INSERT INTO publishers(publisher_name)
VALUES ('Pearson'),
       ('ThomsonReuters'),
       ('Penguin Random House'),
       ('RELX Group'),
       ('Hachette Livre'),
       ('Grupo Planeta'),
       ('Wiley'),
       ('OLMA Media Group'),
       ('Klett'),
       ('Wolters Kluwer');
