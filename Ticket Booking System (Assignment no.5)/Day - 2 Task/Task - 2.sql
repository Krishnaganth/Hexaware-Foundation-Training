-- TASK 1 Database Design --

-- 1. Create the database named "TicketBookingSystem" --
create database TicketBookingSystem;
use TicketBookingSystem;

-- 2,4.Write SQL scripts to create the mentioned tables i.Venu ii.Event iii.Customers iv.Booking / Create appropriate Primary Key and Foreign Key --
create table Venu (
    venue_id int PRIMARY KEY auto_increment,
    venue_name varchar(100) not null,
    address varchar(300) not null
);

create table Event (
    event_id int PRIMARY KEY auto_increment,
    event_name varchar(30) not null,
    event_date date not null,
    event_time time not null,
    venue_id int,
    total_seats int not null,
    available_seats int not null,
    ticket_price decimal(8, 2),
    event_type enum('Movie', 'Sports', 'Concert'),
    booking_id int,
    FOREIGN KEY (venue_id) references Venu(venue_id)
    );
    
create table Booking (
    booking_id int PRIMARY KEY auto_increment,
    customer_id int,
    event_id int,
    num_tickets int not null,
    total_cost decimal(10, 2),
    booking_date date,
    FOREIGN KEY (event_id) references Event(event_id)
);
    
create table Customer (
    customer_id int PRIMARY KEY auto_increment,
    customer_name varchar(30) not null,
    email varchar(50),
    phone_number varchar(10),
    booking_id int,
    FOREIGN KEY (booking_id) references Booking(booking_id)
);

show tables;

select * from venu; desc venu;
select * from event; desc event;
alter table event add FOREIGN KEY (booking_id) references Booking(booking_id);
select * from customer; desc customer;
select * from booking; desc booking;
alter table booking add FOREIGN KEY (customer_id) references Customer(customer_id);


 -- Task 2 Select, Where, Between, AND, LIKE: -- 
 
 
 -- 1. Write a SQL query to insert at least 10 sample records into each table. --
insert into Venu (venue_name, address) values
('Stadium One', 'Downtown Ave'),
('City Theater', 'Main Street'),
('Concert Dome', 'Highland Park'),
('Multiplex A', 'Central Plaza'),
('Auditorium Z', 'West Side'),
('Arena Max', 'Uptown'),
('Cinema Royale', 'Market Road'),
('Expo Hall', 'East Bay'),
('Open Air Stage', 'Beach Road'),
('Classic Pavilion', 'Museum Blvd');
desc venu;
select * from venu;

insert into Customer (customer_name, email, phone_number) values
('John Doe', 'john@example.com', '9876543000'),
('Jane Smith', 'jane@example.com', '9876543001'),
('Tom Hardy', 'tom@example.com', '9876540000'),
('Emma Watson', 'emma@example.com', '9876541234'),
('Chris Evans', 'chris@example.com', '9876511111'),
('Scarlett J', 'scarlett@example.com', '9876544321'),
('Mark Ruffalo', 'mark@example.com', '9876500000'),
('Robert D', 'robert@example.com', '9876522222'),
('Natalie P', 'natalie@example.com', '9876533333'),
('Paul Rudd', 'paul@example.com', '9876544444');
desc customer;
select * from customer;

insert into Event (event_name, event_date, event_time, venue_id, total_seats, available_seats, ticket_price, event_type) values
('Rock Concert', '2024-05-25', '18:30:00', 3, 10000, 2500, 1500.00, 'Concert'),
('Football Cup Final', '2024-06-01', '20:00:00', 1, 25000, 10000, 2200.00, 'Sports'),
('Movie Premiere', '2024-05-28', '17:00:00', 7, 500, 100, 500.00, 'Movie'),
('Jazz Night', '2024-05-30', '19:00:00', 5, 2000, 2000, 1800.00, 'Concert'),
('Theatre Play', '2024-06-05', '18:00:00', 2, 1200, 100, 1200.00, 'Concert'),
('Indie Movie Fest', '2024-06-10', '16:00:00', 4, 1000, 850, 800.00, 'Movie'),
('Cup Classic Match', '2024-06-03', '21:00:00', 6, 30000, 5000, 2500.00, 'Sports'),
('Rock & Roll Cup', '2024-06-02', '19:30:00', 3, 15000, 15000, 2000.00, 'Concert'),
('Drama Festival', '2024-06-06', '17:30:00', 9, 500, 450, 1300.00, 'Concert'),
('Sci-Fi Night', '2024-06-07', '20:00:00', 8, 800, 800, 1600.00, 'Movie');
desc event;
select * from event;

insert into Booking (customer_id, event_id, num_tickets, total_cost, booking_date) values
(1, 1, 2, 3000.00, '2024-05-10'),
(2, 2, 4, 8800.00, '2024-05-12'),
(3, 3, 1, 500.00, '2024-05-15'),
(4, 4, 3, 5400.00, '2024-05-16'),
(5, 5, 2, 2400.00, '2024-05-17'),
(6, 6, 1, 800.00, '2024-05-18'),
(7, 7, 6, 15000.00, '2024-05-19'),
(8, 8, 5, 10000.00, '2024-05-20'),
(9, 9, 2, 2600.00, '2024-05-21'),
(10, 10, 4, 6400.00, '2024-05-22');
desc booking;
select * from booking;

-- 2. Write a SQL query to list all Events. --
select * from event;

-- 3. Write a SQL query to select events with available tickets. --
select * from event where available_seats > 0;

-- 4. Write a SQL query to select events name partial match with ‘cup’.--
select * from event where event_name LIKE '%cup%';

-- 5. Write a SQL query to select events with ticket price range is between 1000 to 2500. --
select * from event where ticket_price BETWEEN 1000 AND 2500;

-- 6. Write a SQL query to retrieve events with dates falling within a specific range. --
select * from event where event_date BETWEEN '2024-06-01' AND '2024-06-07';

-- 7.Write a SQL query to retrieve events with available tickets that also have "Concert" in their name. --
select * from event where available_seats > 0 AND event_name LIKE '%Concert%';

-- 8.Write a SQL query to retrieve users in batches of 5, starting from the 6th user. --
select * from Customer LIMIT 5 OFFSET 5;

-- 9. Write a SQL query to retrieve bookings details contains booked no of ticket more than 4.--
select * from Booking where num_tickets > 4;

-- 10. Write a SQL query to retrieve customer information whose phone number end with ‘000’ --
select * from Customer where phone_number LIKE '%000';

-- 11.Write a SQL query to retrieve the events in order whose seat capacity more than 15000. --
select * from event where total_seats > 15000
order by total_seats;

-- 12. Write a SQL query to select ebookingvents name not start with ‘x’, ‘y’, ‘z’ --
select * from event where event_name NOT LIKE 'x%' AND event_name NOT LIKE 'y%' AND event_name NOT LIKE 'z%';
