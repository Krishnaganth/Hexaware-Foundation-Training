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


-- Tasks 3: Aggregate functions, Having, Order By, GroupBy and Joins: --


-- 1. Write a SQL query to List Events and Their Average Ticket Prices. --
select event_name, format (AVG(ticket_price) ,2) as average_ticket_price from event GROUP BY event_name;

-- 2. Write a SQL query to Calculate the Total Revenue Generated by Events. --
select event_id, SUM(total_cost) as total_revenue from Booking GROUP BY event_id;

-- 3. Write a SQL query to find the event with the highest ticket sales. --
select event_id, SUM(num_tickets) as total_tickets from Booking GROUP BY event_id
ORDER BY total_tickets desc limit 1;

-- 4. Write a SQL query to Calculate the Total Number of Tickets Sold for Each Event. --
select event_id, SUM(num_tickets) as total_tickets_sold from Booking GROUP BY event_id;

-- 5. Write a SQL query to Find Events with No Ticket Sales. --
select e.event_id, e.event_name from event e
LEFT JOIN Booking b on e.event_id = b.event_id
where b.booking_id=0 or null;

-- 6. Write a SQL query to Find the User Who Has Booked the Most Tickets. --
select c.customer_name, SUM(b.num_tickets) as total_tickets from Customer c
JOIN Booking b on c.customer_id = b.customer_id
GROUP BY c.customer_id
ORDER BY total_tickets desc limit 1;

-- 7. Write a SQL query to List Events and the total number of tickets sold for each month. --
select month(booking_date) as Month, e.event_name, SUM(b.num_tickets) as tickets_sold from Booking b
JOIN event e ON b.event_id = e.event_id
GROUP BY month(booking_date), e.event_name ORDER BY Month;

-- 8. Write a SQL query to calculate the average Ticket Price for Events in Each Venue. --
select v.venue_name, AVG(e.ticket_price) as avg_price from event e
JOIN Venu v on e.venue_id = v.venue_id
GROUP BY v.venue_name;

-- 9. Write a SQL query to calculate the total Number of Tickets Sold for Each Event Type. --
select event_type, SUM(b.num_tickets) as tickets_sold from event e
JOIN Booking b on e.event_id = b.event_id
GROUP BY event_type;

-- 10. Write a SQL query to calculate the total Revenue Generated by Events in Each Year. --
select YEAR(booking_date) as Year, SUM(total_cost) as total_revenue from Booking
GROUP BY YEAR(booking_date);

-- 11. Write a SQL query to list users who have booked tickets for multiple events. --
select customer_id, COUNT(DISTINCT event_id) as event_count from Booking
GROUP BY customer_id
HAVING COUNT(DISTINCT event_id) > 1;

-- 12. Write a SQL query to calculate the Total Revenue Generated by Events for Each User. --
select c.customer_name, SUM(b.total_cost) as total_spent from Customer c
JOIN Booking b on c.customer_id = b.customer_id
GROUP BY c.customer_id;

-- 13. Write a SQL query to calculate the Average Ticket Price for Events in Each Category and Venue. --
select e.event_type, v.venue_name, AVG(e.ticket_price) as avg_price from event e
JOIN Venu v on e.venue_id = v.venue_id
GROUP BY e.event_type, v.venue_name;

-- 14. Write a SQL query to list Users and the Total Number of Tickets They've Purchased in the Last 30 Days. --
select c.customer_name, SUM(b.num_tickets) as total_tickets from Customer c
JOIN Booking b on c.customer_id = b.customer_id
where booking_date >= CURDATE() - INTERVAL 30 DAY
GROUP BY c.customer_id;


--  Tasks 4: Subquery and its types --

-- 1. Calculate the Average Ticket Price for Events in Each Venue Using a Subquery. --
select venue_id,(select AVG(ticket_price) from event e2
where e2.venue_id = e1.venue_id) as avg_ticket_price from event e1
GROUP BY venue_id;

-- 2. Find Events with More Than 50% of Tickets Sold using subquery. --
select event_id, event_name from event where event_id IN (
    select event_id from Booking
    GROUP BY event_id
    HAVING SUM(num_tickets) > (select total_seats * 0.5 from event e2 WHERE e2.event_id = Booking.event_id) );
    
-- 3. Calculate the Total Number of Tickets Sold for Each Event. --
select event_id,
       (select SUM(num_tickets) from Booking b
        WHERE b.event_id = e.event_id) as total_tickets from event e;
        
-- 4. Find Users Who Have Not Booked Any Tickets Using a NOT EXISTS Subquery. --
select * from Customer c WHERE NOT EXISTS ( select 1 from Booking b
    WHERE b.customer_id = c.customer_id );
    
-- 5. List Events with No Ticket Sales Using a NOT IN Subquery. --
select * from event WHERE event_id NOT IN (
    SELECT DISTINCT event_id
    from Booking );
    
-- 6. Calculate the Total Number of Tickets Sold for Each Event Type Using a Subquery in the FROM Clause. --
select event_type, SUM(tickets_sold) as total_tickets from (
    select e.event_type, b.num_tickets as tickets_sold from event e
    JOIN Booking b on e.event_id = b.event_id) as sub
GROUP BY event_type;

-- 7. Find Events with Ticket Prices Higher Than the Average Ticket Price Using a Subquery in the WHERE Clause. --
select * from event WHERE ticket_price > (
    select AVG(ticket_price) from event );
    
-- 8. Calculate the Total Revenue Generated by Events for Each User Using a Correlated Subquery. --
select customer_name,
       (select SUM(total_cost) from Booking b
        WHERE b.customer_id = c.customer_id) as total_revenue from Customer c;
        
-- 9. List Users Who Have Booked Tickets for Events in a Given Venue Using a Subquery in the WHERE Clause. --
select * from Customer WHERE customer_id IN (
    select DISTINCT b.customer_id from Booking b
    JOIN event e ON b.event_id = e.event_id
    WHERE e.venue_id = 3 );
    
-- 10. Calculate the Total Number of Tickets Sold for Each Event Category Using a Subquery with GROUP BY. --
select event_type, SUM(num_tickets) as total_tickets from 
    (select e.event_type, b.num_tickets from Booking b
    JOIN event e ON b.event_id = e.event_id) as sub
GROUP BY event_type;

-- 11. Find Users Who Have Booked Tickets for Events in each Month Using a Subquery with DATE_FORMAT. --
select customer_name from Customer 
WHERE customer_id IN (
    select DISTINCT customer_id from Booking
    WHERE DATE_FORMAT(booking_date, '%Y-%m') = '2024-05');
    
-- 12. Calculate the Average Ticket Price for Events in Each Venue Using a Subquery --
select v.venue_name,
       (select AVG(e2.ticket_price) from event e2
        WHERE e2.venue_id = v.venue_id) as avg_price from Venu v;