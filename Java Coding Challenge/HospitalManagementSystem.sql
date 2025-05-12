-- Creating new DB and schema for Hospital Management System --

create database  IF NOT EXISTS HospitalManagementSystem;
use HospitalManagementSystem;

-- dropping incase of table present in DB --

DROP TABLE IF EXISTS Patient;
DROP TABLE IF EXISTS Doctor;
DROP TABLE IF EXISTS Appointment;

-- Creating the tables with constraints for Hospital Management System db --

-- Create table for Patient --
CREATE TABLE Patient (
    patientId INT PRIMARY KEY,
    firstName VARCHAR(30),
    lastName VARCHAR(30),
    dateOfBirth DATE,
    gender VARCHAR(10),
    contactNumber VARCHAR(15),
    address VARCHAR(255)
);


-- Create table for Doctor --
CREATE TABLE Doctor (
    doctorId INT PRIMARY KEY,
    firstName VARCHAR(30),
    lastName VARCHAR(30),
    specialization VARCHAR(100),
    contactNumber VARCHAR(15)
);

-- Create table for Appointment --
CREATE TABLE Appointment (
    appointmentId INT PRIMARY KEY,
    patientId INT,
    doctorId INT,
    appointmentDate DATE,
    description TEXT,
    FOREIGN KEY (patientId) REFERENCES Patient(patientId),
    FOREIGN KEY (doctorId) REFERENCES Doctor(doctorId)
);

-- Checking and verifying the structure of the table --

desc Patient;
desc Doctor;
desc Appointment;

show databases;

select * from Patient;
select * from Doctor;
select * from Appointment;


