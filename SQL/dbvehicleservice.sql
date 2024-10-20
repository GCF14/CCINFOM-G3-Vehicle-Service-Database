USE `dbvehicleservice`;

DROP TABLE IF EXISTS Service_table;

CREATE TABLE Service_table (
	Service_ID			INTEGER(100) ZEROFILL NOT NULL,
    Vehicle_ID			INTEGER(100) NOT NULL,
    Mechanic_ID			INTEGER(100) NOT NULL,
    Service_Date		DATETIME,
    Service_Description	VARCHAR(150),
    Service_Cost		FLOAT(8),	
    Service_Rating		INTEGER(2)	 ZEROFILL,
    
    CONSTRAINT SERVICE_PK PRIMARY KEY (Service_ID),
    CONSTRAINT SERVICE_FK1 FOREIGN KEY(Vehicle_ID) REFERENCES Vehicle_table(Vehicle_ID),
    CONSTRAINT SERVICE_FK2 FOREIGN KEY(Mechanic_ID) REFERENCES Mechanic_table(Mechanic_ID)
);

INSERT INTO Service_table(Service_ID, Vehicle_ID, Mechanic_ID, Service_Date, Service_Description, Service_Cost, Service_Rating)
VALUES
(1, 0000000001, 0000000001, '2024-10-20 10:30:00', 'Oil change and filter replacement', 4342.76, 10),
(2, 0000000002, 0000000003, '2024-10-21 12:45:00', 'Brake pad replacement', 8628.00, 8),
(3, 0000000001, 0000000002, '2024-10-22 09:15:00', 'Tire rotation and balancing', 2876.00, 7),
(4, 0000000003, 0000000004, '2024-10-23 14:00:00', 'Transmission fluid change', 11504.00, 9),
(5, 0000000004, 0000000001, '2024-10-24 08:30:00', 'Battery replacement', 6902.40, 8),
(6, 0000000005, 0000000003, '2024-10-25 16:00:00', 'Engine diagnostic and tune-up', 10396.74, 6),
(7, 0000000006, 0000000005, '2024-10-26 11:15:00', 'Air conditioning service', 5205.56, 7),
(8, 0000000007, 0000000002, '2024-10-27 10:45:00', 'Wheel alignment', 4314.00, 5),
(9, 0000000004, 0000000004, '2024-10-28 13:30:00', 'Exhaust system repair', 12654.40, 7),
(10, 0000000003, 0000000005, '2024-10-29 09:00:00', 'Full vehicle inspection', 5752.00, 5);