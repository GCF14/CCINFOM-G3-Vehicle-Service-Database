create table Customer_Record_Management(
	Customer_ID int,
    Vehicle_ID int,
    First_Name varchar(30),
    Last_Name varchar (30),
    Contact_Details long
);

insert into Customer_Record_Management
values
(1, 101, "Luiz", "Moreno", "09134934312"),
(2, 102, "Emily", "Carter", "09123849321"),
(3, 103, "Raj", "Patel", "09122384556"),
(4, 104, "Sophia", "Nguyen", "09128765433"),
(5, 105, "Carlos", "Diaz", "09123344321"),
(6, 106, "Liam", "Wong", "09129877654"),
(7, 107, "Olivia", "Brown", "09127894566"),
(8, 108, "Emma", "Wilson", "09124567889"),
(9, 109, "Noah", "Martinez", "09126789012"),
(10, 110, "Mia", "Kim", "09123456987"),
(11, 111, "James", "Clark", "09122349871"),
(12, 112, "Ava", "Lopez", "09121234567"),
(13, 113, "Ethan", "Adams", "09124567892"),
(14, 114, "Isabella", "Hall", "09127654321"),
(15, 115, "Michael", "Young", "09129876543"),
(16, 116, "Amelia", "Hernandez", "09123458967"),
(17, 117, "Benjamin", "King", "09121267890"),
(18, 118, "Charlotte", "Scott", "09126784561"),
(19, 119, "Lucas", "Green", "09127893456"),
(20, 120, "Harper", "Baker", "09124567833");

select * from customer_record_management;