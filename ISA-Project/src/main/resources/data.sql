INSERT INTO Company (id, name, adress, description, grade, deleted)
VALUES
    (1, 'Company1', 'Address1', 'Description1', 'A', false),
    (2, 'Company2', 'Address2', 'Description2', 'B', false),
    (3, 'Company3', 'Address3', 'Description3', 'C', false);

INSERT INTO Equipment (id, equipment_name, description, status, company_id, deleted)
VALUES
    (1, 'Equipment1', 'Description1', 'Available', 1, false),
    (2, 'Equipment2', 'Description2', 'Available', 1, false),
    (3, 'Equipment3', 'Description3', 'Available', 2, false),
    (4, 'Equipment4', 'Description4', 'Available', 2, false);
