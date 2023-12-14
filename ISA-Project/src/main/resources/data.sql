INSERT INTO Company (name, adress, description, grade, deleted)
VALUES
    ('Company1', 'Address1', 'Description1', 'A', false),
    ('Company2', 'Address2', 'Description2', 'B', false),
    ('Company3', 'Address3', 'Description3', 'C', false);

INSERT INTO users (city, country, number, email, first_name,user_name,is_enabled, last_name, password, user_type, deleted)
VALUES
    ('Novi Sad', 'Srbija', '069875465','zdravko@gmail.com', 'Zdravko','Debil1', false, 'Zdravkic', '12345678', 'Customer', false),
    ('Novi Sad', 'Srbija', '067459844','jana@gmail.com', 'Jana','Debil2', false, 'Janic', '87654321', 'CompanyAdmin', false),
    ('Novi Sad', 'Srbija', '068543654','petar@gmail.com', 'Petar','Debil3', false, 'Petrovic', '56781234', 'Customer', false);

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
