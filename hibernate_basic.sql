CREATE DATABASE IF NOT EXISTS hibernate_basic CHARACTER SET utf8 COLLATE utf8_general_ci;
USE hibernate_basic;

DROP TABLE IF EXISTS sport_car;
CREATE TABLE sport_car
(
    id          INT AUTO_INCREMENT PRIMARY KEY ,
    model       VARCHAR(255) NOT NULL ,
    color       VARCHAR(255) NOT NULL ,
    is_deleted  BOOLEAN NOT NULL DEFAULT FALSE,
    created_at  TIMESTAMP NOT NULL,
    price       INT UNSIGNED NOT NULL ,
    max_speed   INT UNSIGNED NOT NULL
);