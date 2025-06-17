INSERT INTO products (name, price, unit, description)
VALUES ('Wireless Mouse', 49.99, 'piece', 'High quality wireless mouse perfect for everyday use.'),
       ('Gaming Keyboard', 89.99, 'unit', 'High quality gaming keyboard perfect for everyday use.'),
       ('LED Monitor', 129.99, 'pack', 'High quality led monitor perfect for everyday use.'),
       ('USB-C Cable', 9.99, 'box', 'High quality usb-c cable perfect for everyday use.'),
       ('Bluetooth Speaker', 59.99, 'set', 'High quality bluetooth speaker perfect for everyday use.'),
       ('Smartphone Case', 19.99, 'piece', 'High quality smartphone case perfect for everyday use.'),
       ('Power Bank', 39.99, 'unit', 'High quality power bank perfect for everyday use.'),
       ('Laptop Stand', 29.99, 'pack', 'High quality laptop stand perfect for everyday use.'),
       ('Webcam', 69.99, 'box', 'High quality webcam perfect for everyday use.'),
       ('Noise Cancelling Headphones', 149.99, 'set', 'High-quality noise-cancelling headphones.'),
       ('Organic Apples', 3.49, 'kg', 'Fresh organic apples, juicy and crisp.'),
       ('Whole Grain Bread', 2.99, 'loaf', 'Healthy whole grain bread, freshly baked.'),
       ('Olive Oil', 10.99, 'bottle', 'Extra virgin olive oil, cold pressed.'),
       ('Pasta', 1.49, 'pack', 'Durum wheat pasta for perfect al dente.'),
       ('Coffee Beans', 8.99, 'bag', 'Premium coffee beans with rich aroma.'),
       ('Milk', 1.09, 'liter', 'Fresh whole milk, farm-produced.'),
       ('Chef Knife', 24.99, 'piece', 'High carbon steel chef knife for precision cutting.'),
       ('Cutting Board', 12.99, 'piece', 'Bamboo cutting board, durable and eco-friendly.'),
       ('Non-stick Pan', 29.99, 'piece', 'Non-stick frying pan for easy cooking and cleaning.'),
       ('Mixing Bowl Set', 19.99, 'set', 'Stainless steel mixing bowls set, various sizes.'),
       ('Office Chair', 159.99, 'piece', 'High quality office chair perfect for everyday use.'),
       ('Standing Desk', 299.99, 'unit', 'High quality standing desk perfect for everyday use.'),
       ('Backpack', 49.99, 'pack', 'High quality backpack perfect for everyday use.'),
       ('Sunglasses', 29.99, 'box', 'High quality sunglasses perfect for everyday use.'),
       ('Water Bottle', 14.99, 'set', 'High quality water bottle perfect for everyday use.'),
       ('Notebook', 4.99, 'piece', 'High quality notebook perfect for everyday use.'),
       ('Pen Set', 9.99, 'unit', 'High quality pen set perfect for everyday use.'),
       ('Phone Tripod', 19.99, 'pack', 'High quality phone tripod perfect for everyday use.'),
       ('Microphone', 79.99, 'box', 'High quality microphone perfect for everyday use.'),
       ('Graphic Tablet', 149.99, 'set', 'High quality graphic tablet perfect for everyday use.');

INSERT INTO users (email, password)
VALUES ('juhasz.bence.zsolt@gmail.com', '$2a$10$OnASmWOv6fF/voWlTQNfSOm20Fh4AaPgTDVTwPrMiF0FTjYEWzb6a');

SET @userId := (SELECT id
                FROM users
                WHERE email = 'juhasz.bence.zsolt@gmail.com');

INSERT INTO profiles (user_id, first_name, middle_name, last_name, date_of_birth, phone_number)
VALUES (@userId, 'Bence', 'Zsolt', 'Juhász', '1994-03-27', '+36501323566');

SET @profileId := (SELECT user_id
                   FROM profiles
                   WHERE user_id = @userId);

INSERT INTO addresses (profile_id, address_line, municipality, province, postal_code, country)
VALUES (@profileId, 'Balaton utca 2/B.', 'Makó', 'Csongrád-Csanád', '6900', 'HUNGARY');

INSERT INTO coupons (code, type, value, description, expiration_date)
VALUES ('WELCOME10', 'PERCENT_OFF', 10.00, '10% off welcome coupon', '9999-12-31 23:59:59'),
       ('SPRING15', 'PERCENT_OFF', 15.00, '15% off spring promotion', '2025-03-31 23:59:59'),
       ('SAVE20', 'PERCENT_OFF', 20.00, '20% off site-wide sale', '2025-11-30 23:59:59'),
       ('NEWUSER5', 'FIXED_AMOUNT', 5.00, '$5 off first purchase', '9999-12-31 23:59:59'),
       ('CASHBACK5', 'FIXED_AMOUNT', 5.00, '$5 cashback on any order', '2025-09-30 23:59:59'),
       ('EXTRA5', 'FIXED_AMOUNT', 5.00, '$5 off sale items', '2025-02-28 23:59:59'),
       ('FREESHIP', 'FREE_SHIPPING', 0.00, 'Free shipping on all orders', '2025-12-31 23:59:59'),
       ('BUY1GET1', 'BUY_ONE_GET_ONE', 0.00, 'Buy one get one free', '2025-12-31 23:59:59');

SET @couponCodeNotExpired := (SELECT code
                              FROM coupons
                              WHERE code = 'WELCOME10');

SET @couponCodeExpired := (SELECT code
                           FROM coupons
                           WHERE code = 'SPRING15');

INSERT INTO user_coupons (user_id, coupon_code)
VALUES (@userId, @couponCodeNotExpired),
       (@userId, @couponCodeExpired);
