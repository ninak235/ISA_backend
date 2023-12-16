INSERT INTO Company (name, adress, start_working_time, end_working_time, description, grade, deleted)
VALUES
    ('Company1', 'Address1', '09:00:00', '17:30:00',  'Description1', 'A', false),
    ('Company2', 'Address2', '09:00:00', '17:30:00', 'Description2', 'B', false),
    ('Company3', 'Address3', '09:00:00', '17:30:00', 'Description3', 'C', false);

INSERT INTO users (city, country, number, email, first_name,user_name,is_enabled, last_name, password, deleted,last_password_reset_date)
VALUES
    ('Novi Sad', 'Srbija', '069875465','zdravko@gmail.com', 'Zdravko','Debil1', true, 'Zdravkic', '$2a$10$sngO7OZwcduKMsU1W3TpN.unpzrK4BvTtY7xMW2jRHCXcVzAfNTCa' , false,'2017-10-01 21:58:58.508-07'),
    ('Novi Sad', 'Srbija', '067459844','jana@gmail.com', 'Jana','Debil2', true, 'Janic', '12345678', false,'2017-10-01 21:58:58.508-07'),
    ('Novi Sad', 'Srbija', '068543654','petar@gmail.com', 'Petar','Debil3', true, 'Petrovic', '$2a$10$q19T.hIoeaOX2/Z3Nrx4m.z6rRBBNyBxGw0agLx4NOvlCY9FZU2.m', false,'2017-10-01 21:58:58.508-07');

INSERT INTO equipment (equipment_name, description, equipment_type, grade, price, deleted)
VALUES
    ('Equipment1', 'Description1', 'Therapeutic','A', 100, false),
    ('Equipment2', 'Description2','Surgical','C', 344, false),
    ('Equipment3', 'Description3','Surgical','D', 565,   false),
    ('Equipment4', 'Description4','Dental','B', 876, false);

INSERT INTO company_equipment  (company_id, equipment_id)
VALUES
    (1,1),
    (2,2),
    (2,3);

INSERT INTO customers (user_id, company_info, occupation, penalty_points)
VALUES
    (1,'Company1', 'Medical', 4),
    (3, 'Company2', 'Dental', 3);


INSERT INTO company_admins  (company_id, user_id)
VALUES
    (1,1),
    (2,2),
    (2,3);

INSERT INTO available_date (admin_confirmation_date, confirmed, duration, start_time, admin_id, taken)
VALUES
    ('2023-12-14T10:30:00', true, 1800, '2023-12-23T08:45:00', 1, true),
    ('2023-12-16T10:30:00', true, 1800, '2023-12-18T10:30:00', 1, false),
    ('2023-12-14T10:30:00', true, 1800, '2023-12-20T10:30:00', 2, false);
INSERT INTO role (name)
VALUES ('ROLE_CUSTOMER'),
       ('ROLE_COMPANYADMIN'),
       ('ROLE_ADMIN');

INSERT INTO user_role (user_id , role_id)
VALUES
    (1,1),
    (2,2),
    (3,3);


