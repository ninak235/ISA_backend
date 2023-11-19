INSERT INTO Company (name, adress, description, grade, deleted)
VALUES
    ('Company1', 'Address1', 'Description1', 'A', false),
    ('Company2', 'Address2', 'Description2', 'B', false),
    ('Company3', 'Address3', 'Description3', 'C', false);

INSERT INTO Equipment (equipment_name, description, equipment_type, grade, price, company_id, deleted)
VALUES
    ('Equipment1', 'Description1', 'Therapeutic','A', 100, 1, false),
    ('Equipment2', 'Description2','Surgical','C', 344, 1, false),
    ('Equipment3', 'Description3','Surgical','D', 565, 2,  false),
    ('Equipment4', 'Description4','Dental','B', 876, 2, false);

