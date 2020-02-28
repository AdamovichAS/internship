
CREATE DATABASE IF NOT EXISTS jdbc_crud CHARACTER SET utf8 COLLATE utf8_general_ci;
USE jdbc_crud;

DROP TABLE IF EXISTS car;
CREATE TABLE car
(
    id      INT AUTO_INCREMENT PRIMARY KEY ,
    model   VARCHAR(255) not null ,
    color   VARCHAR(255) not null
);

SELECT * FROM car WHERE car.id =1;

INSERT INTO car(id, model, color)
VALUES (1,'bmv','blue'),(2,'mercedes','red'),(3,'lada','black');