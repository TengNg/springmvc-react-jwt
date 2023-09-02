CREATE DATABASE ecommercedb;

USE ecommercedb;

CREATE TABLE category (
  category_id INT AUTO_INCREMENT PRIMARY KEY,
  category_name VARCHAR(255)
);

-- Insert categories
INSERT INTO category (category_name) 
VALUES ('A'), ('B'), ('C'), ('D');

CREATE TABLE user (
    user_id VARCHAR(40) PRIMARY KEY DEFAULT (uuid()),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(20),
    address TEXT,
    image_url VARCHAR(255),
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    refresh_token VARCHAR(255),
    user_role VARCHAR(20),
    created_at datetime DEFAULT NULL
);

CREATE TABLE product (
  product_id VARCHAR(40) PRIMARY KEY DEFAULT (uuid()),
  user_id VARCHAR(40),
  name VARCHAR(255),
  description TEXT,
  image_url VARCHAR(255),
  price DECIMAL(10, 2),
  stock_quantity INT,
  category_id INT NOT NULL,
  created_at datetime DEFAULT NULL,
  FOREIGN KEY (user_id) REFERENCES user(user_id),
  FOREIGN KEY (category_id) REFERENCES category(category_id)
);

CREATE TABLE cart (
  cart_id VARCHAR(40) PRIMARY KEY DEFAULT (uuid()),
  user_id VARCHAR(40),
  payment_method_id INT,
  FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE cart_item (
  cart_item_id INT PRIMARY KEY,
  cart_id VARCHAR(40),
  product_id VARCHAR(40),
  quantity INT,
  FOREIGN KEY (cart_id) REFERENCES cart(cart_id),
  FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE product_review (
    review_id VARCHAR(40) PRIMARY KEY DEFAULT (uuid()),
    product_id VARCHAR(40),
    user_id VARCHAR(40),
    review_text TEXT,
    rating INT,
    review_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES product(product_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE shipper (
  shipper_id INT PRIMARY KEY,
  shipper_name VARCHAR(255)
);

-- Create shipping_processes table
CREATE TABLE shipping_process (
  process_id INT PRIMARY KEY AUTO_INCREMENT,
  cart_id VARCHAR(40),
  shipper_id INT,
  shipping_status VARCHAR(255),
  shipping_date DATE,
  FOREIGN KEY (cart_id) REFERENCES cart(cart_id),
  FOREIGN KEY (shipper_id) REFERENCES shipper(shipper_id)
);

-- Create payment_transactions table
CREATE TABLE payment_transaction (
  transaction_id INT PRIMARY KEY,
  user_id VARCHAR(40),
  cart_id VARCHAR(40),
  method_id INT,
  amount DECIMAL(10, 2),
  transaction_date DATETIME,
  FOREIGN KEY (user_id) REFERENCES user(user_id),
  FOREIGN KEY (cart_id) REFERENCES cart(cart_id)
);