INSERT INTO Company (name, adress, description, grade, deleted)
VALUES
    ('Company1', 'Address1', 'Description1', 'A', false),
    ('Company2', 'Address2', 'Description2', 'B', false),
    ('Company3', 'Address3', 'Description3', 'C', false),
    ('Company4', 'Address4', 'Description4', 'A', false),
    ('Company5', 'Address5', 'Description5', 'A', false),
    ('Company6', 'Address6', 'Description6', 'A', false);

INSERT INTO Equipment (equipment_name, description, status, company_id, deleted)
VALUES
    ('Equipment1', 'Description1', 'Available', 1, false),
    ('Equipment2', 'Description2', 'Available', 1, false),
    ('Equipment3', 'Description3', 'Available', 2, false),
    ('Equipment4', 'Description4', 'Available', 2, false);
