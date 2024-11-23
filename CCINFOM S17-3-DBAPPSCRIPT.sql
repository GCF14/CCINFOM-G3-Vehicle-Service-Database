USE `dbvehicleservice`;

DROP TABLE IF EXISTS Service_history_table;
DROP TABLE IF EXISTS Customer_table;
DROP TABLE IF EXISTS Vehicle_table;
DROP TABLE IF EXISTS Service_table;
DROP TABLE IF EXISTS Mechanic_table;
DROP TABLE IF EXISTS Stock_table;
DROP TABLE IF EXISTS Stock_usage_table;
DROP VIEW IF EXISTS service_history_view;


CREATE TABLE Customer_table (
	customer_id		INT  AUTO_INCREMENT NOT NULL,
    first_name		VARCHAR(30),
    last_name		VARCHAR(30),
    contact_details  VARCHAR(11),
    
    CONSTRAINT CUSTOMER_PK PRIMARY KEY (customer_id) 
);

INSERT INTO Customer_table(customer_id, first_name, last_name, contact_details)
VALUES
(1, "Luiz", "Moreno", "09134934312"),
(2, "Emily", "Carter", "09123849321"),
(3, "Raj", "Patel", "09122384556"),
(4, "Sophia", "Nguyen", "09128765433"),
(5, "Carlos", "Diaz", "09123344321");

CREATE TABLE Vehicle_table (
    vehicle_id 			INT  AUTO_INCREMENT NOT NULL,  
    customer_id  		INT NOT NULL,
    brand 				VARCHAR(100) NOT NULL,            
    model 				VARCHAR(100) NOT NULL,                
    year_made 			INT NOT NULL,                          

	CONSTRAINT VEHICLE_PK PRIMARY KEY (vehicle_id),
    CONSTRAINT VEHICLE_FK FOREIGN KEY (customer_id) REFERENCES Customer_table(customer_id)
);

INSERT INTO Vehicle_table(vehicle_id, customer_id, brand, model, year_made)
VALUES
(1, 1, "Toyota", "Camry", 2024),
(2, 2, "Honda", "Civic", 2017),
(3, 3, "Ford", "Mustang", 2021),
(4, 4, "Nissan", "Sentra", 2021),
(5, 5, "Jeep", "Wrangler", 2023);

CREATE TABLE Service_table (
	service_id			INT AUTO_INCREMENT NOT NULL,
    service_type     	VARCHAR(150),
    base_cost 			DECIMAL(12, 2),
    
    CONSTRAINT SERVICE_PK PRIMARY KEY (service_id)
);

INSERT INTO Service_table(service_id, service_type, base_cost)
VALUES
(1, 'Oil Change', 1200.00),
(2, 'Brake Replacement', 4500.50),
(3, 'Battery Replacement', 3000.75),
(4, 'Tire Change', 800.00),
(5, 'Engine Diagnostic', 2500.00);


CREATE TABLE Mechanic_table (
	mechanic_id			INT AUTO_INCREMENT NOT NULL,
    mechanic_first_name	VARCHAR(100),
    mechanic_last_name	VARCHAR(100),
	hire_date			DATE,
    end_date			DATE,
    
    CONSTRAINT MECHANIC_PK PRIMARY KEY (Mechanic_ID)
);

INSERT INTO Mechanic_table(mechanic_id, mechanic_first_name, mechanic_last_name, hire_date, end_date)
VALUES
(1, 'Joan', 'Marquez', '2022-05-11', '2025-05-11'), 
(2, 'Victor', 'Benitez', '2022-03-18', '2025-03-18'),
(3, 'Eva', 'Molina', '2021-07-07', '2025-07-07'),
(4, 'Sara', 'Aguilar', '2023-02-05', '2025-02-05'),
(5, 'Maria', 'Blanco', '2021-12-16', '2025-12-16'),
(6, 'Boruto', 'Uzumaki', '2021-12-16', '2025-12-16');


CREATE TABLE Service_history_table (
	service_history_id 			INT AUTO_INCREMENT NOT NULL,
	service_id					INT NOT NULL,
    customer_id					INT NOT NULL,
    vehicle_id					INT NOT NULL,
    mechanic_id					INT,
    date_start					DATE,
    date_end					DATE,
    total_cost					DECIMAL(12, 2),
    service_rating				INT,
    
    
	CONSTRAINT SERVICE_HISTORY_PK PRIMARY KEY(service_history_id),
    CONSTRAINT SERVICE_HISTORY_FK1 FOREIGN KEY(service_id) REFERENCES Service_table(service_id),
	CONSTRAINT SERVICE_HISTORY_FK2 FOREIGN KEY(customer_id) REFERENCES Customer_table(customer_id),
    CONSTRAINT SERVICE_HISTORY_FK3 FOREIGN KEY(vehicle_id) REFERENCES Vehicle_table(vehicle_id),
    CONSTRAINT SERVICE_HISTORY_FK4 FOREIGN KEY(mechanic_id) REFERENCES Mechanic_table(mechanic_id)
);

INSERT INTO Service_history_table(service_history_id, service_id, customer_id, vehicle_id, mechanic_id, date_start, date_end, total_cost, service_rating)
VALUES
(1, 1, 1, 1, 1, '2024-11-13', NULL, 1200.00, 10), 
(2, 2, 2, 2, 2, '2024-11-14', NULL, 8001, 10),
(3, 3, 3, 3, 3, '2024-11-15', NULL, 6201.15, 10),
(4, 4, 4, 4, 4, '2024-11-16', NULL, 18800.00, 10),
(5, 5, 5, 5, 5, '2024-11-17', NULL, 11000.00, 10);


CREATE TABLE Stock_table (
	stock_id				INT AUTO_INCREMENT NOT NULL,
    name					VARCHAR(50),
    price					DECIMAL(12, 2),
    manufacturing_date		DATE,
    warranty				DATE,
    
    CONSTRAINT STOCK_PK PRIMARY KEY (stock_id)
);
INSERT INTO Stock_table(stock_id, name, price, manufacturing_date, warranty)
VALUES
(1, 'Tire', 4500.00, '2023-03-15', '2026-03-15'),
(2, 'Brake Pad Set', 3500.50, '2022-11-20', '2025-11-20'),
(3, 'Battery', 3200.75, '2023-06-01', '2026-06-01'),
(4, 'Engine Oil', 1200.00, '2024-02-10', '2025-02-10'),
(5, 'Alternator', 8500.00, '2022-08-25', '2025-08-25');


CREATE TABLE Stock_usage_table (
	stock_usage_id		INT AUTO_INCREMENT NOT NULL,
    service_history_id	INT NOT NULL,
    stock_id			INT,
    quantity			INT,
    
    CONSTRAINT STOCK_USAGE_PK PRIMARY KEY (stock_usage_id),
    CONSTRAINT STOCK_USAGE_FK1 FOREIGN KEY (stock_id) REFERENCES Stock_table(stock_id),
	CONSTRAINT STOCK_USAGE_FK2 FOREIGN KEY (service_history_id)
    REFERENCES Service_history_table (service_history_id)
);

INSERT INTO Stock_usage_table (stock_usage_id, service_history_id, stock_id, quantity)
VALUES
(1, 1, 1, 1),
(2, 2, 2, 1),
(3, 3, 3, 1),
(4, 4, 1, 4),
(5, 5, 5, 1);


SELECT * 
FROM Vehicle_table;
SELECT * 
FROM Service_table;
SELECT * 
FROM Customer_table;
SELECT * 
FROM Mechanic_table;
SELECT * 
FROM Service_history_table ORDER BY total_cost DESC;
SELECT *
FROM Stock_table;
SELECT * 
FROM Stock_usage_table;

SELECT COUNT(*) FROM Stock_usage_table WHERE service_id = 1;

DELETE FROM Service_history_table WHERE mechanic_id = 6;

SELECT COUNT(*) 
FROM Customer_table c JOIN Vehicle_table v ON v.customer_id = c.customer_id
WHERE c.first_name = 'Gerard Christian' AND c.last_name = 'Felipe' AND v.brand = 'Toyota' AND v.model = 'Landcruiser';

CREATE VIEW service_history_view AS
SELECT s.service_type, CONCAT(c.first_name, " ", c.last_name) AS customer_name, v.brand, v.model, CONCAT(m.mechanic_first_name, " ", 
m.mechanic_last_name) AS mechanic_name, sh.date_start, sh.date_end, sh.total_cost, sh.service_rating
FROM Service_history_table sh JOIN Vehicle_table v ON sh.vehicle_id = v.vehicle_id 
JOIN Customer_table c ON sh.customer_id = c.customer_id
JOIN Service_table s ON sh.service_id = s.service_id
JOIN Mechanic_table m ON sh.mechanic_id = m.mechanic_id;

SELECT * FROM service_history_view ORDER BY total_cost DESC;

CREATE VIEW customer_and_vehicle AS
SELECT c.last_name, c.first_name, c.contact_details, v.vehicle_id, v.brand, v.model, v.year_made
FROM Customer_table c JOIN Vehicle_table v ON c.customer_id = v.customer_id
ORDER BY c.last_name;

DROP VIEW IF EXISTS customer_and_vehicle;
SELECT * FROM customer_and_vehicle;

CREATE VIEW mechanics AS
SELECT mechanic_last_name, mechanic_first_name, hire_date, end_date
FROM Mechanic_table
ORDER BY mechanic_last_name;

DROP VIEW IF EXISTS mechanics;

SELECT * FROM mechanics;


CREATE VIEW stocks AS
SELECT name, price, manufacturing_date, warranty
FROM Stock_table;

DROP VIEW IF EXISTS stocks;

SELECT * FROM stocks;

CREATE VIEW services AS
SELECT service_type, base_cost
FROM Service_table;

DROP VIEW IF EXISTS services;

SELECT * FROM services;

CREATE VIEW services_with_upgrades AS
SELECT s.service_type, CONCAT(c.first_name, " ", c.last_name) AS customer_name, v.brand, v.model, CONCAT(m.mechanic_first_name, " ", 
m.mechanic_last_name) AS mechanic_name, sh.date_start, sh.date_end, st.name, su.quantity AS quantity_bought, sh.total_cost, sh.service_rating
FROM Service_history_table sh JOIN Vehicle_table v ON sh.vehicle_id = v.vehicle_id 
JOIN Customer_table c ON sh.customer_id = c.customer_id
JOIN Service_table s ON sh.service_id = s.service_id
JOIN Mechanic_table m ON sh.mechanic_id = m.mechanic_id
JOIN Stock_usage_table su ON sh.service_history_id = su.service_history_id
JOIN Stock_table st ON su.stock_id = st.stock_id;

DROP VIEW IF EXISTS services_with_upgrades;

SELECT * FROM services_with_upgrades;

CREATE VIEW customers AS
SELECT last_name, first_name, contact_details
FROM Customer_table
ORDER BY last_name;

DROP VIEW IF EXISTS customers;

SELECT * FROM customers;

CREATE VIEW working_mechanics AS
SELECT m.mechanic_last_name, m.mechanic_first_name, v.brand, v.model, s.service_type, sh.date_start, sh.date_end
FROM Service_history_table sh JOIN Mechanic_table m ON sh.mechanic_id = m.mechanic_id
JOIN Service_table s ON sh.service_id = s.service_id
JOIN Vehicle_table v ON sh.vehicle_id = v.vehicle_id
WHERE sh.date_end IS NULL;

DROP VIEW IF EXISTS working_mechanics;

SELECT * FROM working_mechanics;

CREATE VIEW stock_use AS
SELECT su.stock_usage_id, sh.service_history_id, s.service_id, s.service_type, st.name, su.quantity 
FROM Stock_usage_table su JOIN Service_history_table sh ON su.service_history_id = sh.service_history_id
JOIN Service_table s ON sh.service_id = s.service_id
JOIN Stock_table st ON su.stock_id = st.stock_id;

DROP VIEW IF EXISTS stock_use;

SELECT * FROM stock_use;

CREATE VIEW service_history_delete AS
SELECT sh.service_history_id, s.service_type, CONCAT(c.first_name, " ", c.last_name) AS customer_name, v.brand, v.model, CONCAT(m.mechanic_first_name, " ", 
m.mechanic_last_name) AS mechanic_name, sh.date_start, sh.date_end, sh.total_cost, sh.service_rating
FROM Service_history_table sh JOIN Vehicle_table v ON sh.vehicle_id = v.vehicle_id 
JOIN Customer_table c ON sh.customer_id = c.customer_id
JOIN Service_table s ON sh.service_id = s.service_id
JOIN Mechanic_table m ON sh.mechanic_id = m.mechanic_id;

SELECT * FROM service_history_delete;


