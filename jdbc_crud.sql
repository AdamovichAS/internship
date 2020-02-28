
CREATE DATABASE IF NOT EXISTS jdbc_crud CHARACTER SET utf8 COLLATE utf8_general_ci;
USE jdbc_crud;

DROP TABLE IF EXISTS car;
CREATE TABLE car
(
    id          INT AUTO_INCREMENT PRIMARY KEY ,
    model       VARCHAR(255) NOT NULL ,
    color       VARCHAR(255) NOT NULL ,
    is_deleted  BOOLEAN NOT NULL DEFAULT FALSE,
    created_at  TIMESTAMP NOT NULL
);

INSERT INTO car(id, model, color, is_deleted, created_at)
VALUES (1,'bmv','blue',false,'2020-01-01'),(2,'mercedes','red',false,'2020-02-02'),(3,'lada','black',true,'2020-03-03');