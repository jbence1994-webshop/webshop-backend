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
    created_at DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_products_product_photos
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

CREATE TABLE IF NOT EXISTS users
(
    id         BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS profiles
(
    user_id          BIGINT       NOT NULL PRIMARY KEY,
    first_name       VARCHAR(255) NOT NULL,
    middle_name      VARCHAR(255),
    last_name        VARCHAR(255) NOT NULL,
    date_of_birth    DATE         NOT NULL,
    phone_number     VARCHAR(25) UNIQUE,
    avatar_file_name VARCHAR(41) UNIQUE    DEFAULT NULL,
    loyalty_points   INT UNSIGNED NOT NULL DEFAULT 0,
    created_at       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_profiles_users
        FOREIGN KEY (user_id) REFERENCES users (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS addresses
(
    profile_id   BIGINT       NOT NULL PRIMARY KEY,
    address_line VARCHAR(255) NOT NULL,
    municipality VARCHAR(255) NOT NULL,
    province     VARCHAR(255) NOT NULL,
    postal_code  VARCHAR(25)  NOT NULL,
    country      VARCHAR(255) NOT NULL,
    created_at   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_addresses_users
        FOREIGN KEY (profile_id) REFERENCES profiles (user_id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);
