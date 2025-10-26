INSERT INTO categories (name)
VALUES ('Electronics'),
       ('Home & Living'),
       ('Fashion'),
       ('Beauty & Personal Care'),
       ('Sports & Outdoors'),
       ('Toys, Kids & Baby'),
       ('Grocery & Food'),
       ('Office & School Supplies'),
       ('Automotive'),
       ('Health & Wellness'),
       ('Pet Supplies'),
       ('Seasonal & Special');

SET @electronicsCategoryId := (SELECT id
                               FROM categories
                               WHERE name = 'Electronics');
SET @homeLivingCategoryId := (SELECT id
                              FROM categories
                              WHERE name = 'Home & Living');
SET @fashionCategoryId := (SELECT id
                           FROM categories
                           WHERE name = 'Fashion');
SET @beautyCareCategoryId := (SELECT id
                              FROM categories
                              WHERE name = 'Beauty & Personal Care');
SET @sportsOutdoorsCategoryId := (SELECT id
                                  FROM categories
                                  WHERE name = 'Sports & Outdoors');
SET @toysKidsBabyCategoryId := (SELECT id
                                FROM categories
                                WHERE name = 'Toys, Kids & Baby');
SET @groceryAndFoodCategoryId := (SELECT id
                                  FROM categories
                                  WHERE name = 'Grocery & Food');
SET @officeSchoolCategoryId := (SELECT id
                                FROM categories
                                WHERE name = 'Office & School Supplies');
SET @automotiveCategoryId := (SELECT id
                              FROM categories
                              WHERE name = 'Automotive');
SET @healthWellnessCategoryId := (SELECT id
                                  FROM categories
                                  WHERE name = 'Health & Wellness');
SET @petSuppliesCategoryId := (SELECT id
                               FROM categories
                               WHERE name = 'Pet Supplies');
SET @seasonalSpecialCategoryId := (SELECT id
                                   FROM categories
                                   WHERE name = 'Seasonal & Special');

INSERT INTO products (name, price, unit, description, category_id)
VALUES ('Wireless Mouse', 49.99, 'piece', 'High quality wireless mouse perfect for everyday use.',
        @electronicsCategoryId),
       ('Gaming Keyboard', 89.99, 'unit', 'High quality gaming keyboard perfect for everyday use.',
        @electronicsCategoryId),
       ('LED Monitor', 129.99, 'pack', 'High quality led monitor perfect for everyday use.', @electronicsCategoryId),
       ('USB-C Cable', 9.99, 'box', 'High quality usb-c cable perfect for everyday use.', @electronicsCategoryId),
       ('Bluetooth Speaker', 59.99, 'set', 'High quality bluetooth speaker perfect for everyday use.',
        @electronicsCategoryId),
       ('Smartphone Case', 19.99, 'piece', 'High quality smartphone case perfect for everyday use.',
        @electronicsCategoryId),
       ('Power Bank', 39.99, 'unit', 'High quality power bank perfect for everyday use.', @electronicsCategoryId),
       ('Laptop Stand', 29.99, 'pack', 'High quality laptop stand perfect for everyday use.', @electronicsCategoryId),
       ('Webcam', 69.99, 'box', 'High quality webcam perfect for everyday use.', @electronicsCategoryId),
       ('Noise Cancelling Headphones', 149.99, 'set', 'High-quality noise-cancelling headphones.',
        @electronicsCategoryId),
       ('Organic Apples', 3.49, 'kg', 'Fresh organic apples, juicy and crisp.', @groceryAndFoodCategoryId),
       ('Whole Grain Bread', 2.99, 'loaf', 'Healthy whole grain bread, freshly baked.', @groceryAndFoodCategoryId),
       ('Olive Oil', 10.99, 'bottle', 'Extra virgin olive oil, cold pressed.', @groceryAndFoodCategoryId),
       ('Pasta', 1.49, 'pack', 'Durum wheat pasta for perfect al dente.', @groceryAndFoodCategoryId),
       ('Coffee Beans', 8.99, 'bag', 'Premium coffee beans with rich aroma.', @groceryAndFoodCategoryId),
       ('Milk', 1.09, 'liter', 'Fresh whole milk, farm-produced.', @groceryAndFoodCategoryId),
       ('Chef Knife', 24.99, 'piece', 'High carbon steel chef knife for precision cutting.', @homeLivingCategoryId),
       ('Cutting Board', 12.99, 'piece', 'Bamboo cutting board, durable and eco-friendly.', @homeLivingCategoryId),
       ('Non-stick Pan', 29.99, 'piece', 'Non-stick frying pan for easy cooking and cleaning.', @homeLivingCategoryId),
       ('Mixing Bowl Set', 19.99, 'set', 'Stainless steel mixing bowls set, various sizes.', @homeLivingCategoryId),
       ('Office Chair', 159.99, 'piece', 'High quality office chair perfect for everyday use.', @homeLivingCategoryId),
       ('Standing Desk', 299.99, 'unit', 'High quality standing desk perfect for everyday use.', @homeLivingCategoryId),
       ('Backpack', 49.99, 'pack', 'High quality backpack perfect for everyday use.', @fashionCategoryId),
       ('Sunglasses', 29.99, 'box', 'High quality sunglasses perfect for everyday use.', @fashionCategoryId),
       ('Water Bottle', 14.99, 'set', 'High quality water bottle perfect for everyday use.', @sportsOutdoorsCategoryId),
       ('Notebook', 4.99, 'piece', 'High quality notebook perfect for everyday use.', @officeSchoolCategoryId),
       ('Pen Set', 9.99, 'unit', 'High quality pen set perfect for everyday use.', @officeSchoolCategoryId),
       ('Phone Tripod', 19.99, 'pack', 'High quality phone tripod perfect for everyday use.', @electronicsCategoryId),
       ('Microphone', 79.99, 'box', 'High quality microphone perfect for everyday use.', @electronicsCategoryId),
       ('Graphic Tablet', 149.99, 'set', 'High quality graphic tablet perfect for everyday use.',
        @electronicsCategoryId);

INSERT INTO users (email, password, role)
VALUES ('juhasz.bence.zsolt@gmail.com', '$2a$10$OnASmWOv6fF/voWlTQNfSOm20Fh4AaPgTDVTwPrMiF0FTjYEWzb6a', 'ADMIN');

SET @userId := (SELECT id
                FROM users
                WHERE email = 'juhasz.bence.zsolt@gmail.com');

INSERT INTO profiles (user_id, first_name, middle_name, last_name, date_of_birth, phone_number)
VALUES (@userId, 'Bence', 'Zsolt', 'Juhász', '1994-03-27', '+36501323566');

SET @profileId := (SELECT user_id
                   FROM profiles
                   WHERE user_id = @userId);

INSERT INTO addresses (profile_id, address_line, municipality, province, postal_code, country)
VALUES (@profileId, 'Balaton utca 2/B', 'Makó', 'Csongrád-Csanád', '6900', 'HUNGARY');

INSERT INTO coupons (code, type, value, description, expiration_date)
VALUES ('WELCOME10', 'PERCENT_OFF', 0.10, '10% off welcome coupon', '9999-12-31 23:59:59'),
       ('SPRING15', 'FIXED_AMOUNT', 15.00, '$15 cashback spring promotion', '2025-03-31 23:59:59');

SET @couponCode := (SELECT code
                    FROM coupons
                    WHERE code = 'WELCOME10');

INSERT INTO user_coupons (user_id, coupon_code)
VALUES (@userId, @couponCode);
