
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE employee_projects;
TRUNCATE TABLE employees;
TRUNCATE TABLE addresses;
TRUNCATE TABLE projects;
TRUNCATE TABLE companies;
SET FOREIGN_KEY_CHECKS = 1;

ALTER TABLE companies AUTO_INCREMENT = 1;
ALTER TABLE projects AUTO_INCREMENT = 1;
ALTER TABLE employees AUTO_INCREMENT = 1;
ALTER TABLE addresses AUTO_INCREMENT = 1;

INSERT INTO companies (id, name) VALUES
(1, 'TechCorp Solutions'),
(2, 'InnovateHub Inc'),
(3, 'DataSystems Ltd'),
(4, 'CloudVenture Technologies'),
(5, 'Digital Dynamics');

INSERT INTO addresses (id, street, city, state, pin_code) VALUES
(1, '123 Main Street', 'New York', 'NY', '10001'),
(2, '456 Oak Avenue', 'Los Angeles', 'CA', '90001'),
(3, '789 Pine Road', 'Chicago', 'IL', '60601'),
(4, '321 Elm Street', 'Houston', 'TX', '77001'),
(5, '654 Maple Drive', 'Phoenix', 'AZ', '85001'),
(6, '987 Cedar Lane', 'Philadelphia', 'PA', '19101'),
(7, '147 Birch Boulevard', 'San Antonio', 'TX', '78201'),
(8, '258 Spruce Court', 'San Diego', 'CA', '92101'),
(9, '369 Willow Way', 'Dallas', 'TX', '75201'),
(10, '741 Ash Street', 'San Jose', 'CA', '95101'),
(11, '852 Poplar Avenue', 'Austin', 'TX', '78701'),
(12, '963 Cypress Road', 'Jacksonville', 'FL', '32201'),
(13, '159 Magnolia Drive', 'San Francisco', 'CA', '94101'),
(14, '357 Dogwood Lane', 'Columbus', 'OH', '43201'),
(15, '468 Redwood Boulevard', 'Charlotte', 'NC', '28201'),
(16, '579 Sycamore Court', 'Seattle', 'WA', '98101'),
(17, '680 Hickory Way', 'Denver', 'CO', '80201'),
(18, '791 Walnut Street', 'Boston', 'MA', '02101'),
(19, '802 Chestnut Avenue', 'Nashville', 'TN', '37201'),
(20, '913 Fir Road', 'Detroit', 'MI', '48201'),
(21, '124 Hemlock Drive', 'Portland', 'OR', '97201'),
(22, '235 Juniper Lane', 'Oklahoma City', 'OK', '73101'),
(23, '346 Larch Boulevard', 'Las Vegas', 'NV', '89101'),
(24, '457 Alder Court', 'Memphis', 'TN', '38101'),
(25, '568 Beech Way', 'Louisville', 'KY', '40201');

INSERT INTO projects (id, name, company_id) VALUES
(1, 'E-Commerce Platform', 1),
(2, 'Mobile Banking App', 1),
(3, 'AI Analytics Dashboard', 2),
(4, 'Cloud Migration Project', 2),
(5, 'Data Warehouse System', 3),
(6, 'Customer Portal', 3),
(7, 'IoT Integration', 4),
(8, 'DevOps Automation', 4),
(9, 'Blockchain Solution', 5),
(10, 'Machine Learning API', 5),
(11, 'Microservices Architecture', 1),
(12, 'Security Enhancement', 2),
(13, 'Performance Optimization', 3),
(14, 'API Gateway', 4),
(15, 'Real-time Analytics', 5);

INSERT INTO employees (id, name, last_name, email, phone, address_id, company_id) VALUES
(1, 'John', 'Smith', 'john.smith@techcorp.com', '555-0101', 1, 1),
(2, 'Sarah', 'Johnson', 'sarah.johnson@techcorp.com', '555-0102', 2, 1),
(3, 'Michael', 'Williams', 'michael.williams@techcorp.com', '555-0103', 3, 1),
(4, 'Emily', 'Brown', 'emily.brown@techcorp.com', '555-0104', 4, 1),
(5, 'David', 'Jones', 'david.jones@techcorp.com', '555-0105', 5, 1),
(6, 'Jessica', 'Garcia', 'jessica.garcia@innovatehub.com', '555-0201', 6, 2),
(7, 'Christopher', 'Miller', 'christopher.miller@innovatehub.com', '555-0202', 7, 2),
(8, 'Amanda', 'Davis', 'amanda.davis@innovatehub.com', '555-0203', 8, 2),
(9, 'Matthew', 'Rodriguez', 'matthew.rodriguez@innovatehub.com', '555-0204', 9, 2),
(10, 'Ashley', 'Martinez', 'ashley.martinez@innovatehub.com', '555-0205', 10, 2),
(11, 'James', 'Hernandez', 'james.hernandez@datasystems.com', '555-0301', 11, 3),
(12, 'Melissa', 'Lopez', 'melissa.lopez@datasystems.com', '555-0302', 12, 3),
(13, 'Robert', 'Wilson', 'robert.wilson@datasystems.com', '555-0303', 13, 3),
(14, 'Michelle', 'Anderson', 'michelle.anderson@datasystems.com', '555-0304', 14, 3),
(15, 'Daniel', 'Thomas', 'daniel.thomas@datasystems.com', '555-0305', 15, 3),
(16, 'Laura', 'Taylor', 'laura.taylor@cloudventure.com', '555-0401', 16, 4),
(17, 'Kevin', 'Moore', 'kevin.moore@cloudventure.com', '555-0402', 17, 4),
(18, 'Nicole', 'Jackson', 'nicole.jackson@cloudventure.com', '555-0403', 18, 4),
(19, 'Brian', 'White', 'brian.white@cloudventure.com', '555-0404', 19, 4),
(20, 'Stephanie', 'Harris', 'stephanie.harris@cloudventure.com', '555-0405', 20, 4),
(21, 'Ryan', 'Martin', 'ryan.martin@digitaldynamics.com', '555-0501', 21, 5),
(22, 'Rachel', 'Thompson', 'rachel.thompson@digitaldynamics.com', '555-0502', 22, 5),
(23, 'Justin', 'Garcia', 'justin.garcia@digitaldynamics.com', '555-0503', 23, 5),
(24, 'Lauren', 'Martinez', 'lauren.martinez@digitaldynamics.com', '555-0504', 24, 5),
(25, 'Brandon', 'Robinson', 'brandon.robinson@digitaldynamics.com', '555-0505', 25, 5);

INSERT INTO employee_projects (employee_id, project_id) VALUES
(1, 1), (1, 11),
(2, 1), (2, 2),
(3, 2), (3, 11),
(4, 1), (4, 2), (4, 11),
(5, 11),
(6, 3), (6, 4),
(7, 3), (7, 12),
(8, 4), (8, 12),
(9, 3), (9, 4),
(10, 12),
(11, 5), (11, 6),
(12, 5), (12, 13),
(13, 6), (13, 13),
(14, 5), (14, 6), (14, 13),
(15, 13),
(16, 7), (16, 8),
(17, 7), (17, 14),
(18, 8), (18, 14),
(19, 7), (19, 8),
(20, 14),
(21, 9), (21, 10),
(22, 9), (22, 15),
(23, 10), (23, 15),
(24, 9), (24, 10), (24, 15),
(25, 15);