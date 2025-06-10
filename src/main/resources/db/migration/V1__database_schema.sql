CREATE DATABASE IF NOT EXISTS webshop
    DEFAULT CHARACTER SET utf8
    COLLATE utf8_hungarian_ci;

USE webshop;

CREATE TABLE IF NOT EXISTS products
(
    id          BIGINT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255)   NOT NULL,
    price       DECIMAL(10, 2) NOT NULL,
    unit        VARCHAR(25)    NOT NULL,
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

CREATE TABLE IF NOT EXISTS carts
(
    id         BINARY(16) DEFAULT (UUID_TO_BIN(UUID())) NOT NULL PRIMARY KEY,
    created_at DATE       DEFAULT (CURDATE())           NOT NULL
);

CREATE TABLE IF NOT EXISTS cart_items
(
    id         BIGINT     NOT NULL PRIMARY KEY AUTO_INCREMENT,
    cart_id    BINARY(16) NOT NULL,
    product_id BIGINT     NOT NULL,
    quantity   INT        NOT NULL DEFAULT 1,
    CONSTRAINT unique_cart_id_product_id UNIQUE (cart_id, product_id),
    CONSTRAINT fk_cart_items_carts
        FOREIGN KEY (cart_id) REFERENCES carts (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT fk_cart_items_products
        FOREIGN KEY (product_id) REFERENCES products (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS coupons
(
    code       VARCHAR(25)    NOT NULL PRIMARY KEY,
    amount     DECIMAL(10, 2) NOT NULL,
    expiration DATETIME       NOT NULL
);
