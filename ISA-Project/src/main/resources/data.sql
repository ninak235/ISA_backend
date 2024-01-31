INSERT INTO location(address, city, country, latitude, longitude)
VALUES
    ('Vojvodjanska 22', 'Novi Sad', 'Srbija', 45.245018, 19.837681),
    ('Tolstojeva 10', 'Novi Sad', 'Srbija', 45.245431, 19.833935),
    ('Gunduliceva 22', 'Novi Sad', 'Srbija', 45.34343, 19.434333),
    ('Sumadijska 18', 'Novi Sad', 'Srbija', 45.2334, 19.323233),
    ('Sumadijska 29', 'Novi Sad', 'Srbija', 45.43345, 19.43553);


INSERT INTO Company (name, location_id, start_working_time, end_working_time, description, grade, deleted)
VALUES
    ('Company1', 3, '09:00:00', '17:30:00',  'Description1', 'A', false),
    ('Company2', 1, '09:00:00', '17:30:00', 'Description2', 'B', false),
    ('Company3', 5, '09:00:00', '17:30:00', 'Description3', 'C', false);

INSERT INTO users (city, country, number, email, first_name,user_name,is_enabled, last_name, password, deleted,last_password_reset_date)
VALUES
    ('Novi Sad', 'Srbija', '069875465','zarkokn@gmail.com', 'Zdravko','Customer', true, 'Zdravkic', '$2a$10$sngO7OZwcduKMsU1W3TpN.unpzrK4BvTtY7xMW2jRHCXcVzAfNTCa' , false,'2017-10-01 21:58:58.508-07'),
    -- CUSTOMER: 11111111
    ('Novi Sad', 'Srbija', '067459844','jana@gmail.com', 'Jana','AdminCompany1', true, 'Janic', '$2a$12$P9VeoGInlK53ACrgEuwl5O2iLay0ZLHkzj3q71bv/mXmw3uThJZ8i', false,'2017-10-01 21:58:58.508-07'),
    -- COMPANY ADMIN: 22222222
    ('Novi Sad', 'Srbija', '068543654','petar@gmail.com', 'Petar','AdminCompany2', true, 'Petrovic', '$2a$10$q19T.hIoeaOX2/Z3Nrx4m.z6rRBBNyBxGw0agLx4NOvlCY9FZU2.m', false,'2017-10-01 21:58:58.508-07'),
   -- COMPANY ADMIN: 12345678
    ('Novi Sad', 'Srbija', '068543655','visnja@gmail.com', 'Visnja','AdminCompany3', true, 'Peric', '$2a$12$S0xGqYF5BxDu92ma6Agvpe.eSKBnlqdoDMBm9Px4oerbChd1WRasG', false,'2017-10-01 21:58:58.508-07'),
   -- COMPANY ADMIN: 87654321
    ('Novi Sad', 'Srbija', '068543656','sara@gmail.com', 'Sara','AdminCompany4', true, 'Vasic', '$2a$12$tBX2tuZGHtZfOa9G8tplTOxamnVD2z5AcqgwmmVhKVpgp3id45sOW', false,'2017-10-01 21:58:58.508-07'),
   -- COMPANY ADMIN: 33333333
    ('Novi Sad', 'Srbija', '068543657','jovan@gmail.com', 'Jovan','Admin', true, 'Jovanovic', '$2a$12$dvmV8QbN1FS1qa9alu6HOeKBjfZ/Ls9ez7mzp7n60hE11AKB6BW3u', false,'2017-10-01 21:58:58.508-07');
    -- ADMIN: 12341234

INSERT INTO equipment (equipment_name, description, equipment_type, grade, price, deleted)
VALUES
    ('Equipment1', 'Description1', 'Therapeutic','A', 100, false),
    ('Equipment2', 'Description2','Surgical','C', 300, false),
    ('Equipment3', 'Description3','Surgical','D', 565,   false),
    ('Equipment4', 'Description4','Dental','B', 876, false);




INSERT INTO loyality_program (name_category, required_points, discount)
VALUES
    ('Gold', 100, 40),
    ('Silver', 65, 20),
    ('Regular', 45, 5);


INSERT INTO customers (user_id, company_info, occupation, penalty_points, last_penalty_points_date_reset, loyality_program_id)
VALUES
    (1,'Company1', 'Medical', 6, '2024-12-31T08:45:00', 1),
    (3, 'Company2', 'Dental', 6, '2023-12-30T08:45:00', 2);




INSERT INTO company_admins  (company_id, user_id)
VALUES
    (1,5),
    (2,2),
    (2,3),
    (3,4);

INSERT INTO complaints (content, replay, version, company_admin_id, customer_id)
VALUES
    ('veliki problem', '', 0, 2, 1),
    ('mali problem', '', 0, 2, 1),
    ('tezak problem', '', 0, 2, 1),
    ('nejasno', '', 0, 2, 1);

INSERT INTO available_date (duration, start_time, admin_id, taken, version)
VALUES
    (2, '2024-2-23T08:45:00', 5, true, 0),
    (3, '2024-2-24T10:30:00', 5, false, 0),
    (3, '2024-2-21T10:30:00', 2, false, 0),
    (4, '2024-2-25T08:45:00', 2, true, 0),
    (3, '2024-2-15T12:00:00', 3, false, 0),
    (1, '2024-2-27T10:30:00', 3, false, 0),
    (3, '2024-2-28T10:00:00', 4, false, 0);

INSERT INTO role (name)
VALUES ('ROLE_CUSTOMER'),
       ('ROLE_COMPANYADMIN'),
       ('ROLE_ADMIN');

INSERT INTO user_role (user_id , role_id)
VALUES
    (1,1),
    (2,2),
    (3,2),
    (4,2),
    (5,2),
    (6,3);

INSERT INTO company_equipment (quantity, version, company_id, equipment_id)
VALUES
    (10, 0, 1, 1),
    (20, 0, 1, 2),
    (50, 0, 2, 3),
    (30, 0, 2, 1);

INSERT INTO reservation (price, date_time, duration, grade, status, version, customer_id, company_admin_id)
VALUES
    (5500, '2023-12-17T10:00:00', 1, 5, 2, 0, 1, 2),
    (400, '2025-10-10T17:00:00', 2, 5, 0, 0, 1, 2),
    (4520, '2024-01-24T17:00:00', 3, 5, 1, 0, 1, 2),
    (5650, '2024-01-25T17:00:00', 3, 5, 1, 0, 1, 2),
    (2000, '2023-12-17T12:00:00', 5, 5, 1, 0, 1, 2),
    (2000,'2024-02-22T10:30:00', 1, 4, 1, 0, 3, 2);

INSERT INTO reservation_equipment (reservation_id, equipment_id, quantity)
VALUES
    (1, 1, 10),
    (1, 2, 15),
    (2, 1, 4),
    (3, 3, 8),
    (4, 3, 10),
    (5, 1, 20),
    (6, 1, 20);




