USE `dbvehicleservice`;

DROP TABLE IF EXISTS Service_history_table;
DROP TABLE IF EXISTS Customer_table;
DROP TABLE IF EXISTS Vehicle_table;
DROP TABLE IF EXISTS Service_table;
DROP TABLE IF EXISTS Mechanic_table;
DROP TABLE IF EXISTS Stock_table;
DROP TABLE IF EXISTS Stock_usage_table;


CREATE TABLE Vehicle_table (
    vehicle_id 			INT  AUTO_INCREMENT NOT NULL,  
    brand 				VARCHAR(100) NOT NULL,            
    model 				VARCHAR(100) NOT NULL,                
    year_made 			INT NOT NULL,                          

	CONSTRAINT VEHICLE_PK PRIMARY KEY (vehicle_id)
);

INSERT INTO Vehicle_table(vehicle_id, brand, model, year_made)
VALUES
(1, "Toyota", "Camry", 2024),
(2, "Honda", "Civic", 2017),
(3, "Ford", "Mustang", 2021),
(4, "Nissan", "Sentra", 2021),
(5, "Jeep", "Wrangler", 2023);

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

CREATE TABLE Customer_table (
	customer_id		INT  AUTO_INCREMENT NOT NULL,
    vehicle_id		INT,
    service_id		INT,
    first_name		VARCHAR(30),
    last_name		VARCHAR(30),
    contact_details  VARCHAR(11),
    
    CONSTRAINT CUSTOMER_PK PRIMARY KEY (customer_id, vehicle_id) ,
    CONSTRAINT CUSTOMER_FK1 FOREIGN KEY (vehicle_id) REFERENCES Vehicle_table(vehicle_id),
    CONSTRAINT CUSTOMER_FK2 FOREIGN KEY (service_id) REFERENCES Service_table(service_id)
);

INSERT INTO Customer_table(customer_id, vehicle_id, service_id, first_name, last_name, contact_details)
VALUES
(1, 1, 1, "Luiz", "Moreno", "09134934312"),
(2, 2, 2, "Emily", "Carter", "09123849321"),
(3, 3, 3, "Raj", "Patel", "09122384556"),
(4, 4, 4, "Sophia", "Nguyen", "09128765433"),
(5, 5, 5, "Carlos", "Diaz", "09123344321");

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
(5, 'Maria', 'Blanco', '2021-12-16', '2025-12-16');

CREATE TABLE Service_history_table (
	service_id					INT NOT NULL,
    customer_id					INT NOT NULL,
    vehicle_id					INT NOT NULL,
    mechanic_id					INT,
    date_start					DATE,
    date_end					DATE,
    description_of_service		VARCHAR(150),
    total_cost					DECIMAL(12, 2),
    service_rating				INT(2),
    
    
	CONSTRAINT SERVICE_HISTORY_PK PRIMARY KEY(service_id, customer_id, vehicle_id),
    CONSTRAINT SERVICE_HISTORY_FK1 FOREIGN KEY(service_id) REFERENCES Service_table(service_id),
	CONSTRAINT SERVICE_HISTORY_FK2 FOREIGN KEY(customer_id, vehicle_id) REFERENCES Customer_table(customer_id, vehicle_id),
    CONSTRAINT SERVICE_HISTORY_FK3 FOREIGN KEY(vehicle_id) REFERENCES Vehicle_table(vehicle_id),
    CONSTRAINT SERVICE_HISTORY_FK4 FOREIGN KEY(mechanic_id) REFERENCES Mechanic_table(mechanic_id)
);

INSERT INTO Service_history_table(service_id, customer_id, vehicle_id, mechanic_id, date_start, date_end, 
description_of_service, total_cost, service_rating)
VALUES
(1, 1, 1, 1, '2024-11-13', NULL, 'Needs oil change', 1200.00, 10), 
(2, 2, 2, 2, '2024-11-14', NULL, 'Needs to replace breaks', 8001, 10),
(3, 3, 3, 3, '2024-11-15', NULL, 'Needs to replace battery', 6201.15, 10),
(4, 4, 4, 4, '2024-11-16', NULL, 'Needs to change tires', 18800.00, 10),
(5, 5, 5, 5, '2024-11-17', NULL, 'Needs to check engine', 11000.00, 10);


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
    service_id          INT NOT NULL,
    customer_id         INT NOT NULL,
    vehicle_id          INT NOT NULL,
    stock_id			INT,
    quantity			INT,
    
    CONSTRAINT STOCK_USAGE_PK PRIMARY KEY (stock_usage_id),
    CONSTRAINT STOCK_USAGE_FK1 FOREIGN KEY (stock_id) REFERENCES Stock_table(stock_id),
	CONSTRAINT STOCK_USAGE_FK2 FOREIGN KEY (service_id, customer_id, vehicle_id)
    REFERENCES Service_history_table (service_id, customer_id, vehicle_id)
);

INSERT INTO Stock_usage_table (stock_usage_id, service_id, customer_id, vehicle_id, stock_id, quantity)
VALUES
(1, 1, 1, 1, NULL, 0),
(2, 2, 2, 2, 2, 1),
(3, 3, 3, 3, 3, 1),
(4, 4, 4, 4, 1, 4),
(5, 5, 5, 5, 5, 1);

SELECT * 
FROM Vehicle_table;
SELECT * 
FROM Service_table;
SELECT * 
FROM Customer_table;
SELECT * 
FROM Mechanic_table;
SELECT * 
FROM Service_history_table;
SELECT *
FROM Stock_table;
SELECT * 
FROM Stock_usage_table;
