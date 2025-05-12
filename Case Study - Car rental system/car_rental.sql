-- Creating DB and schema for car rental system --

create database  IF NOT EXISTS car_rental;
use car_rental;

-- dropping incase of table present in DB --

DROP TABLE IF EXISTS Vehicle;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS  Lease;
DROP TABLE IF EXISTS Payment;

-- Creating the tables with constraints for car rental sytem db --

CREATE TABLE Vehicle (
    vehicleID INT PRIMARY KEY AUTO_INCREMENT,
    make VARCHAR(50),
    model VARCHAR(50),
    year INT,
    dailyRate DECIMAL(10,2),
    status ENUM('available', 'notAvailable'),
    passengerCapacity INT,
    engineCapacity INT
);

CREATE TABLE Customer (
    customerID INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    email VARCHAR(100),
    phoneNumber VARCHAR(15)
);

CREATE TABLE Lease (
    leaseID INT PRIMARY KEY AUTO_INCREMENT,
    vehicleID INT,
    customerID INT,
    startDate DATE,
    endDate DATE,
    type ENUM('Daily', 'Monthly'),
    FOREIGN KEY (vehicleID) REFERENCES Vehicle(vehicleID),
    FOREIGN KEY (customerID) REFERENCES Customer(customerID)
);

CREATE TABLE Payment (
    paymentID INT PRIMARY KEY AUTO_INCREMENT,
    leaseID INT,
    paymentDate DATE,
    amount DECIMAL(10,2),
    FOREIGN KEY (leaseID) REFERENCES Lease(leaseID)
);
-- Checking and verifying the structure of the table --

desc Vehicle;
desc Customer;
desc Lease;
desc Payment;

show databases;

-- To view the results --

select * from Customer;
select * from Vehicle;
select * from Lease;
select * from Payment;