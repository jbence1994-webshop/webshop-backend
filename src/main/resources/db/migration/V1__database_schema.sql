CREATE DATABASE IF NOT EXISTS webshop
    DEFAULT CHARACTER SET utf8
    COLLATE utf8_hungarian_ci;

USE webshop;

CREATE TABLE IF NOT EXISTS products
(
    id          BIGINT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255)   NOT NULL,
    price       DECIMAL(10, 2) NOT NULL,
    unit        VARCHAR(255)   NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS product_photos
(
    id         BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    product_id BIGINT      NOT NULL,
    file_name  VARCHAR(41) NOT NULL UNIQUE,
    CONSTRAINT fk_product_photos_products
        FOREIGN KEY (product_id) REFERENCES products (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);
