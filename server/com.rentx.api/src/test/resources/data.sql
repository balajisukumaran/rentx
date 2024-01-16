INSERT INTO area (area_name, city, state, country)
VALUES ('SpringDale', 'Baltimore', 'MD', 'USA');

INSERT INTO `user` (userID, phone, first_name, last_name, pref_language, joining_date, privacy, status, area_ID, login,
                    token, email, isexists)
VALUES (2, '5554203888', 'asdas', 'asfasd', 'english', '2023-09-27 00:00:00.000000', 'enabled', 'verified', 1, 'test',
        '$2a$10$u7F1u8nftJ1xv7nwulpKNOtqX5dpt/Vg.RkVRwgPUuTzRISkfAHCa', 'dasdas@sada.com', 1);

INSERT INTO category (category_id, name)
VALUES (1, 'Kitchen Appliances');

INSERT INTO category (category_id, name)
VALUES (14, 'Test Category');

INSERT INTO product (productID, Price, advertisement_id,
                     advt_title,
                     areaID,
                     area_name,
                     category_id,
                     category_name,
                     city,
                     country,
                     email,
                     expiry_date,
                     first_name,
                     joining_date,
                     last_name,
                     phone,
                     post_date,
                     pref_language,
                     privacy,
                     product_description,
                     product_name,
                     sellType,
                     userID,
                     categoryID)
VALUES (1,
        100,
        1,
        'Ad Title 1',
        1,
        'SpringDale',
        1,
        'test category',
        'Baltimore',
        'USA',
        'asdasd@dfas.com',
        '2023-09-27 00:00:00.000000',
        'asdas',
        '2023-09-27 00:00:00.000000',
        'adssad',
        '21212112',
        '2023-09-27 00:00:00.000000',
        'english',
        'p',
        'Google Pixel',
        'Google Pixel',
        'sell',
        2,
        1);

INSERT INTO advertisement (advertisement_id,
                           advt_title,
                           expiry_date,
                           is_active,
                           post_date,
                           verification_status,
                           product_id,
                           user_id)
VALUES (1,
        'asdasd',
        '2023-09-27 00:00:00.000000',
        'y',
        '2023-09-27 00:00:00.000000',
        'verification',
        1,
        2);

INSERT INTO purchase
(purchaseID,
 date_of_sale,
 is_emi,
 paid,
 productID,
 advertisementID,
 buyer_id,
 seller_id)
VALUES (1,
        '2023-09-27 00:00:00.000000',
        1,
        30,
        1,
        1,
        2,
        2);

INSERT INTO review (reviewID, description, rating, productID, userID)
VALUES (1, 'desc', 5, 1, 2);

INSERT INTO wishlist
(wishlistID,
 productID,
 userID)
VALUES (1,
        1,
        2);