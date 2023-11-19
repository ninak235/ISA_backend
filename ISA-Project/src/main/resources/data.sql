INSERT INTO Company (name, adress, description, grade, deleted)
VALUES
    ('Company1', 'Address1', 'Description1', 'A', false),
    ('Company2', 'Address2', 'Description2', 'B', false),
    ('Company3', 'Address3', 'Description3', 'C', false),
    ('Company4', 'Address4', 'Description4', 'A', false),
    ('Company5', 'Address5', 'Description5', 'A', false),
    ('Company6', 'Address6', 'Description6', 'A', false);

INSERT INTO Equipment (equipment_name, description, status, deleted)
VALUES
    ('Equipment1', 'Description1', 'Available', false),
    ('Equipment2', 'Description2', 'Available', false),
    ('Equipment3', 'Description3', 'Available', false),
    ('Equipment4', 'Description4', 'Available', false);

INSERT INTO company_equipment  (company_id, equipment_id)
VALUES
    (1,1),
    (2,2),
    (2,3);


/*
INSERT INTO Users (id, city, country, number, deleted, email, first_name, is_enabled, last_name, password, user_type)
VALUES
    (1, 'Novi Sad', 'Srbija', '064534565', false, 'ana@gmail.com', true, 'Ana', 'Boskovic', 'Anoka123', 0);

INSERT INTO Customers (user_id, company_info, occupation, penalty_points)
VALUES
    (1, 'super', 'student', 0)*/