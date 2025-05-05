-- Creating databse and using it --

create database  IF NOT EXISTS Car_rental_System;
use Car_rental_System;

-- dropping tables in case of exsiting --

drop table IF EXISTS Vehicle;
drop table IF EXISTS Customer;
drop table IF EXISTS Lease;
drop table IF EXISTS Payment;

-- creating required tables for the database Car_rental_System --

create table Vehicle (
    vehicleID int PRIMARY KEY AUTO_INCREMENT,
    make varchar(20),
    model varchar(25),
    year int,
    dailyRate decimal(10,2),
    status enum('available', 'notAvailable'),
    passengerCapacity int,
    engineCapacity int );
    
create table Customer (
    customerID int PRIMARY KEY AUTO_INCREMENT,
    firstName varchar(30),
    lastName varchar(30),
    email varchar(50),
    phoneNumber varchar(10) );
    
create table Lease (
    leaseID int PRIMARY KEY AUTO_INCREMENT,
    vehicleID int,
    customerID int,
    startDate date,
    endDate date,
    type enum('Daily', 'Monthly'),
    FOREIGN KEY (vehicleID) references Vehicle(vehicleID),
    FOREIGN KEY (customerID) references Customer(customerID) );
    
create table Payment (
    paymentID int PRIMARY KEY AUTO_INCREMENT,
    leaseID int,
    paymentDate date,
    amount decimal(10,2),
    FOREIGN KEY (leaseID) references Lease(leaseID) );
    
-- Checking and verifying the structure of tables in Car_Rental_System DB --

desc Vehicle;
desc Customer;
desc Lease;
desc Payment;
    
-- Insereting the data or values to the four tables in Car_Rental_System DB --alter

insert into Vehicle (make, model, year, dailyRate, status, passengerCapacity, engineCapacity) values
('Toyota', 'Camry', 2022, 50.00, 'available', 4, 1450),
('Honda', 'Civic', 2023, 45.00, 'available', 7, 1500),
('Ford', 'Focus', 2022, 48.00, 'notavailable', 4, 1400),
('Nissan', 'Altima', 2023, 52.00, 'available', 7, 1200),
('Chevrolt', 'Malibu', 2022, 47.00, 'available', 4, 1800),
('Hyundai', 'Sonata', 2023, 49.00, 'notavailable', 7, 1400),
('BMW', '3 Series', 2023, 60.00, 'available', 7, 2499),
('Mercedes', 'C-Class', 2022, 58.00, 'available', 8, 2599),
('Audi', 'A4', 2022, 55.00, 'notavailable', 4, 2500),
('Lexus', 'ES', 2023, 54.00, 'available', 4, 2500);

alter table Customer modify column  phoneNumber varchar(15);

insert into Customer (firstName, lastName, email, phoneNumber) values
('John', 'Doe', 'johndoe@example.com', '555-555-5555'),
('Jane', 'Smith', 'janesmith@example.com', '555-123-4567'),
('Robert', 'Johnson', 'robert@example.com', '555-789-1234'),
('Sarah', 'Brown', 'sarah@example.com', '555-456-7890'),
('David', 'Lee', 'david@example.com', '555-987-6543'),
('Laura', 'Hall', 'laura@example.com', '555-234-5678'),
('Michael', 'Davis', 'michael@example.com', '555-876-5432'),
('Emma', 'Wilson', 'emma@example.com', '555-432-1098'),
('William', 'Taylor', 'william@example.com', '555-321-6547'),
('Olivia', 'Adams', 'olivia@example.com', '555-765-4321');

insert into Lease (customerID, vehicleID, startDate, endDate, type) values
(1, 1, '2023-01-01', '2023-01-05', 'Daily'),
(2, 2, '2023-02-15', '2023-02-28', 'Monthly'),
(3, 3, '2023-03-10', '2023-03-15', 'Daily'),
(4, 4, '2023-04-20', '2023-04-30', 'Monthly'),
(5, 5, '2023-05-05', '2023-05-10', 'Daily'),
(3, 3, '2023-06-15', '2023-06-30', 'Monthly'),
(7, 7, '2023-07-01', '2023-07-10', 'Daily'),
(8, 8, '2023-08-12', '2023-08-15', 'Monthly'),
(3, 3, '2023-09-07', '2023-09-10', 'Daily'),
(10, 10, '2023-10-10', '2023-10-31', 'Monthly');

insert into Payment (paymentID, leaseID, amount, paymentDate) values
(1, 1, 200.00, '2023-01-03'),
(2, 2, 1000.00, '2023-02-20'),
(3, 3, 75.00, '2023-03-12'),
(4, 4, 900.00, '2023-04-25'),
(5, 5, 60.00, '2023-05-07'),
(6, 6, 1200.00, '2023-06-18'),
(7, 7, 40.00, '2023-07-03'),
(8, 8, 1100.00, '2023-08-14'),
(9, 9, 80.00, '2023-09-09'),
(10, 10, 1500.00, '2023-10-25');

-- Viewing the data in the table --

select * from Vehicle; 
select * from Customer;
select * from Lease;
select * from Payment;

-- Queries --
-- 1
update Vehicle set dailyRate = 68.00 where make = 'Mercedes';

-- 2
delete from Payment where leaseID IN (SELECT leaseID from Lease where customerID = 10);
delete from Lease where customerID = 10;
delete from Customer where customerID = 10;

-- 3
alter table Payment RENAME column paymentDate to transactionDate;

-- 4
select * from Customer where email = 'michael@example.com';

-- 5
select * from Lease where customerID = 10 and '2023-01-25' between startDate and endDate;

-- 6
select P.* from Payment P
JOIN Lease L on P.leaseID = L.leaseID
JOIN Customer C on L.customerID = C.customerID where C.phoneNumber = '555-432-1098';

-- 7
select AVG(dailyRate) as avgDailyAvailableRate from Vehicle where status = 'available';

-- 8
select * from Vehicle ORDER BY dailyRate desc limit 1;

-- 10
select * from Lease ORDER BY endDate desc limit 1;

-- 11
select * from Payment where year(transactionDate) = 2023;

-- 13
select V.vehicleID, V.make, V.model, SUM(P.amount) as totalPayment
from Vehicle V
JOIN Lease L on V.vehicleID = L.vehicleID
JOIN Payment P on L.leaseID = P.leaseID
GROUP BY V.vehicleID, V.make, V.model;

-- 14
select C.customerID, C.firstName, C.lastName, SUM(P.amount) as totalPayment
from Customer C
JOIN Lease L on C.customerID = L.customerID
JOIN Payment P on L.leaseID = P.leaseID
GROUP BY C.customerID, C.firstName, C.lastName;

-- 15
select L.leaseID, V.make, V.model, L.startDate, L.endDate from Lease L
JOIN Vehicle V on L.vehicleID = V.vehicleID;


