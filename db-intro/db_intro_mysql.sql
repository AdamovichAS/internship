
CREATE DATABASE IF NOT EXISTS electronic_store CHARACTER SET utf8 COLLATE utf8_general_ci;
USE electronic_store;


DROP TABLE IF EXISTS category;
CREATE TABLE category
(
  id        BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  name      VARCHAR(255)
);

DROP TABLE IF EXISTS product;
CREATE TABLE product
(
    id          BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    category_id BIGINT NOT NULL REFERENCES category(id),
    model       VARCHAR(255) NOT NULL UNIQUE ,
    price       INT UNSIGNED NOT NULL
);

DROP TABLE IF EXISTS address;
CREATE TABLE address
(
    id                      BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    country                 VARCHAR(255) NOT NULL ,
    city                    VARCHAR(255) NOT NULL ,
    street_house_appartment VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS customer;
CREATE TABLE customer
(
    id          BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    login       VARCHAR(255) NOT NULL UNIQUE ,
    password    VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS customer_info;
CREATE TABLE customer_info
(
    customer_id     BIGINT NOT NULL REFERENCES customer(id),
    first_name      VARCHAR(255) NOT NULL ,
    last_name       VARCHAR(255) NOT NULL ,
    e_mail          VARCHAR(255) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS order_info;
CREATE TABLE order_info
(
    id          BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT NOT NULL REFERENCES customer(id),
    address_id  BIGINT NOT NULL REFERENCES address(id)
);

DROP TABLE IF EXISTS bucket;
CREATE TABLE bucket
(
    order_info_id   BIGINT NOT NULL REFERENCES order_info(id),
    product_id      BIGINT NOT NULL REFERENCES product(id)
);


INSERT INTO category(id,name)
VALUES (1,'Компьютеры'),
       (2,'Телевизоры'),
       (3,'Пылесосы');

INSERT INTO product(id, category_id, model, price)
VALUES (1,1,'HP 450G',1300),
       (2,2,'SAMSUNG HD',1300),
       (3,1,'Apple MacBook Pro',3000),
       (4,3,'Karcher WD',1500);

INSERT INTO address(id, country, city, street_house_appartment)
VALUES (1,'Беларусь','Минск','Горецкого 81-3'),
       (2,'Беларусь','Гомель','Карла Маркса 86-123');

INSERT INTO customer(id, login, password)
VALUES (1,'AAsJr','123'),
       (2,'user','1234'),
       (3,'user1','1234');

INSERT INTO customer_info(customer_id, first_name, last_name, e_mail)
VALUES(1,'Anton','Adamovich','anton-adamovich@mail.ru'),
       (2,'Igor','Mikulo','igor-mikulo@mail.ru'),
       (3,'Dima','Pupkin','dima-pupkin@mail.ru');

INSERT INTO order_info(id, customer_id, address_id)
VALUES (1,1,1),
       (2,2,2);

INSERT INTO bucket(order_info_id, product_id)
VALUES (1,1),
       (1,2),
       (1,3),
       (2,2);