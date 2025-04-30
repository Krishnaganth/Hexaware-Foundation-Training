create database TicketBookingSystem;
use TicketBookingSystem;

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

