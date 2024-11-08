USE 'dbvehicle';

DROP TABLE IF EXISTS Vehicle_table;

CREATE TABLE IF NOT EXIST Vehicle_table;

CREATE TABLE Vehicle_table (
    VehicleID INT PRIMARY KEY AUTO_INCREMENT,  -- Primary Key for Vehicle ID
    Brand VARCHAR(100) NOT NULL,            -- Brand of the vehicle (e.g., Toyota)
    Model VARCHAR(100) NOT NULL,                -- Model of the vehicle (e.g., Camry)
    Year INT NOT NULL,                          -- Year of manufacture
    Customer_ID INT,                             -- Foreign Key to link to a customer
    Status VARCHAR(50) NOT NULL,            -- Status of the vehicle (e.g., Active, Inactive, Sold)

	CONSTRAINT VEHICLE_PK PRIMARY KEY (Vehicle_ID),
	CONSTRAINT CUSTOMER_FK FOREIGN KEY (Customer_ID) REFERENCES Customer_table(CUSTOMER_ID)
);

INSERT INTO Vehicle_table(Vehicle_ID, Brand, Model, Year, Customer_ID, Status)
VALUES
(0000000001, "Toyota", "Camry", 2024, 0000000001, Active),
(0000000002, "Honda", "Civic", 2017, 0000000001, Inactive),
(0000000003, "Ford", "Mustang", 2021, 0000000001, Active),
(0000000004, "Nissan", "Sentra", 2021, 0000000001, Inactive),
(0000000005, "Jeep", "Wrangler", 2023, 0000000001, Active),
(0000000006, "Hyundai", "Tucson", 2023, 0000000001, Inactive),
(0000000007, "Kia", "Sportage", 2023, 0000000001, Active),
(0000000008, "Honda", "Accord", 2022, 0000000001, Active),
(0000000009, "Chevrolet", "Silverado", 2023, 0000000001, Active),
(0000000010, "Toyota", "Corolla", 2020, 0000000001, Active);