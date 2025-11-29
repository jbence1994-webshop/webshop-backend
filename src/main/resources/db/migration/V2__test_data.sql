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

INSERT INTO users (email, password, first_name, middle_name, last_name, date_of_birth, phone_number, role)
VALUES ('juhasz.bence.zsolt@gmail.com', '$2a$10$OnASmWOv6fF/voWlTQNfSOm20Fh4AaPgTDVTwPrMiF0FTjYEWzb6a', 'Bence',
        'Zsolt', 'Juhász', '1994-03-27', '+36501323566', 'ADMIN');

SET @userId := (SELECT id
                FROM users
                WHERE email = 'juhasz.bence.zsolt@gmail.com');

INSERT INTO addresses (user_id, address_line, municipality, province, postal_code, country)
VALUES (@userId, 'Balaton utca 2/B', 'Makó', 'Csongrád-Csanád', '6900', 'HUNGARY');

INSERT INTO coupons (code, type, value, description, expiration_date)
VALUES ('WELCOME10', 'PERCENT_OFF', 0.10, '10% off welcome coupon', '9999-12-31 23:59:59'),
       ('SPRING15', 'FIXED_AMOUNT', 15.00, '$15 cashback spring promotion', '2025-03-31 23:59:59');

INSERT INTO product_ratings (product_id, user_id, value)
VALUES (1, @userId, 5),
       (2, @userId, 3),
       (3, @userId, 4),
       (4, @userId, 5),
       (5, @userId, 2),
       (6, @userId, 5),
       (7, @userId, 3),
       (8, @userId, 4),
       (9, @userId, 2),
       (10, @userId, 5),
       (11, @userId, 5),
       (12, @userId, 3),
       (13, @userId, 5),
       (14, @userId, 4),
       (15, @userId, 5),
       (16, @userId, 2),
       (17, @userId, 5),
       (18, @userId, 4),
       (19, @userId, 3),
       (20, @userId, 5),
       (21, @userId, 5),
       (22, @userId, 4),
       (23, @userId, 3),
       (24, @userId, 5),
       (25, @userId, 4),
       (26, @userId, 3),
       (27, @userId, 5),
       (28, @userId, 2),
       (29, @userId, 4),
       (30, @userId, 5);

INSERT INTO product_reviews (product_id, user_id, text)
VALUES (1, @userId,
        'This wireless mouse feels very comfortable in hand and tracks smoothly on any surface. The battery lasts surprisingly long even with daily use.'),
       (2, @userId,
        'The gaming keyboard has a great tactile feel and beautiful RGB lighting. It’s built like a tank and feels professional for long gaming sessions.'),
       (3, @userId,
        'The LED monitor delivers sharp images and accurate colors right out of the box. Watching movies and editing photos on it is a pleasure.'),
       (4, @userId,
        'This USB-C cable is thick, durable, and transfers data quickly. It fits snugly and hasn’t frayed after months of use.'),
       (5, @userId,
        'The Bluetooth speaker produces clear sound with decent bass. It’s great for small gatherings, though volume could be a bit higher.'),
       (6, @userId,
        'The smartphone case looks elegant and feels premium. It protects the phone well without adding too much bulk.'),
       (7, @userId,
        'The power bank charges my phone fast and holds enough power for several recharges. It’s a reliable companion for travel.'),
       (8, @userId,
        'This laptop stand is sturdy and well-designed. It helps with posture and keeps my laptop cool during long work hours.'),
       (9, @userId,
        'The webcam does its job but image sharpness is average. Good enough for video calls, though lighting makes a big difference.'),
       (10, @userId,
        'The noise cancelling headphones are phenomenal with rich sound and deep bass. They block out background noise perfectly on flights.'),
       (11, @userId,
        'These organic apples are crisp, juicy, and full of flavor. You can really taste the difference in quality compared to supermarket ones.'),
       (12, @userId,
        'The whole grain bread is soft on the inside and crunchy on the crust. It tastes fresh and stays good for several days.'),
       (13, @userId,
        'This olive oil is top-notch—rich aroma and smooth flavor. It elevates every salad and pasta dish I make.'),
       (14, @userId,
        'The pasta cooks evenly and maintains a nice firm texture. It’s perfect for homemade sauces and quick dinners.'),
       (15, @userId,
        'The coffee beans have an incredible aroma that fills the kitchen. The flavor is bold but not bitter, truly excellent quality.'),
       (16, @userId,
        'This milk tastes incredibly fresh and natural. It’s rich without being heavy and works perfectly in coffee or cereal.'),
       (17, @userId,
        'The chef knife is razor-sharp and feels perfectly balanced. Cutting vegetables and meat has become effortless and even fun.'),
       (18, @userId,
        'The bamboo cutting board is thick, smooth, and easy to clean. It looks great in the kitchen and doesn’t dull my knives.'),
       (19, @userId,
        'The non-stick pan heats evenly and food slides right off. Cleaning it is super easy and nothing sticks at all.'),
       (20, @userId,
        'This mixing bowl set is a must-have for every kitchen. The different sizes nest perfectly and the steel is very sturdy.'),
       (21, @userId,
        'The office chair is comfortable even after hours of sitting. The adjustable support really helps with posture and back pain.'),
       (22, @userId,
        'The standing desk moves smoothly and feels very stable. Switching between sitting and standing has made my workday much better.'),
       (23, @userId,
        'The backpack has plenty of space and feels strong and durable. It’s perfect for daily commuting or short trips.'),
       (24, @userId,
        'These sunglasses look stylish and offer excellent sun protection. They’re lightweight enough to wear all day without discomfort.'),
       (25, @userId,
        'The water bottle is solid and leak-proof, keeping drinks cold for hours. The design is simple yet elegant.'),
       (26, @userId,
        'This notebook has thick, high-quality pages that prevent ink bleed. It’s perfect for journaling or meeting notes.'),
       (27, @userId,
        'The pen set writes smoothly and looks luxurious. It’s a perfect gift for professionals or anyone who loves stationery.'),
       (28, @userId,
        'The phone tripod is compact and very stable. Great for taking photos or recording videos without any shaking.'),
       (29, @userId,
        'The microphone captures clear and rich sound. It’s ideal for streaming, podcasts, or online meetings.'),
       (30, @userId,
        'The graphic tablet is responsive and precise, making drawing feel natural. The pen pressure sensitivity works flawlessly for digital art.');
