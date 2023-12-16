INSERT INTO Company (name, adress, start_working_time, end_working_time, description, grade, deleted)
VALUES
    ('Company1', 'Address1', '09:00:00', '17:30:00',  'Description1', 'A', false),
    ('Company2', 'Address2', '09:00:00', '17:30:00', 'Description2', 'B', false),
    ('Company3', 'Address3', '09:00:00', '17:30:00', 'Description3', 'C', false);

INSERT INTO users (city, country, number, email, first_name, is_enabled, last_name, password, user_type, deleted)
VALUES
    ('Novi Sad', 'Srbija', '069875465','zdravko@gmail.com', 'Zdravko', false, 'Zdravkic', '12345678', 'Customer', false),
    ('Novi Sad', 'Srbija', '067459844','jana@gmail.com', 'Jana', false, 'Janic', '87654321', 'CompanyAdmin', false),
    ('Novi Sad', 'Srbija', '068543654','petar@gmail.com', 'Petar', false, 'Petrovic', '56781234', 'Customer', false);

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
