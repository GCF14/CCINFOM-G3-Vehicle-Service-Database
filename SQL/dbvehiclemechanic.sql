USE `dbvehiclemechanic`;

DROP TABLE IF EXISTS Mechanic_table;

CREATE TABLE Mechanic_table (
	Mechanic_ID			INTEGER(100) ZEROFILL NOT NULL,
    Mechanic_FirstName	VARCHAR(100),
    Mechanic_LastName	VARCHAR(100),
	Hire_Date			DATE,
    End_Date			DATE,
    Specialization		VARCHAR(100),
    
    CONSTRAINT MECHANIC_PK PRIMARY KEY (Mechanic_ID)
);

INSERT INTO Mechanic_table(Mechanic_ID, Mechanic_FirstName, Mechanic_LastName, Hire_Date, End_Date, Specialization)
VALUES
(1, 'Joan', 'Marquez', '2022-05-11', NULL, 'Electrical Systems'), 
(2, 'Victor', 'Benitez', '2022-03-18', NULL, 'Suspension and Steering'),
(3, 'Eva', 'Molina', '2021-07-07', NULL, 'Engine Performance'),
(4, 'Sara', 'Aguilar', '2023-02-05', NULL, 'Exhausts'),
(5, 'Maria', 'Blanco', '2021-12-16', NULL, 'HVAC'),
(6, 'Rosario', 'Gonzalez', '2023-09-10', NULL, 'Preventive Maintenance'),
(7, 'David', 'Carmona', '2021-12-20', NULL, 'Brake Systems'),
(8, 'Emilio', 'Navarro', '2020-06-09', NULL, 'Transmission'),
(9, 'Juan', 'Guerrero', '2023-09-13', NULL, 'Inspection and Safety'),
(10, 'Joseph', 'Diaz', '2022-07-03', '2024-07-23', 'Fuel Systems');