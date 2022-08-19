--liquibase formatted sql

--changeset tpronicheva:create_tables
CREATE TABLE products (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(50) NOT NULL,
  description VARCHAR(200),
  price DOUBLE(10, 2) NOT NULL,
  amount INT NOT NULL,
  rating INT);

 CREATE TABLE products_images (
 product_id INT NOT NULL,
 image_url VARCHAR(100) NOT NULL,
 FOREIGN KEY (product_id) REFERENCES products (id));

CREATE TABLE categories (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(50) NOT NULL);

CREATE TABLE products_categories(
  product_id INT NOT NULL,
  category_id INT NOT NULL,
FOREIGN KEY (product_id) REFERENCES products (id),
FOREIGN KEY (category_id) REFERENCES categories (id) );

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  surname VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  phone VARCHAR(50) NOT NULL,
  login VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  address VARCHAR(200));

CREATE TABLE roles (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL);

CREATE TABLE users_roles(
  user_id INT NOT NULL,
  role_id INT NOT NULL,
FOREIGN KEY (user_id) REFERENCES users (id),
FOREIGN KEY (role_id) REFERENCES roles (id) );

 CREATE TABLE products_comments (
 product_id INT NOT NULL,
 user_id INT NOT NULL,
 rating INT NOT NULL,
 comment VARCHAR(200),
 FOREIGN KEY (product_id) REFERENCES products (id),
 FOREIGN KEY (user_id) REFERENCES users (id));

 CREATE TABLE purchase_history (
 user_id INT NOT NULL,
 product_id INT NOT NULL,
 amount INT NOT NULL,
 price DOUBLE(10, 2) NOT NULL,
 FOREIGN KEY (product_id) REFERENCES products (id),
 FOREIGN KEY (user_id) REFERENCES users (id));


