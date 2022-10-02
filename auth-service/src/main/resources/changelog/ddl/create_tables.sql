--liquibase formatted sql

--changeset tpronicheva:create_tables

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  surname VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  phone VARCHAR(50) NOT NULL,
  login VARCHAR(50) NOT NULL,
  password VARCHAR(200) NOT NULL,
  address VARCHAR(200));

CREATE TABLE roles (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL);

CREATE TABLE users_roles(
  user_id INT NOT NULL,
  role_id INT NOT NULL,
FOREIGN KEY (user_id) REFERENCES users (id),
FOREIGN KEY (role_id) REFERENCES roles (id) );
