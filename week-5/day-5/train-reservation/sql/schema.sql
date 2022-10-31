drop database if exists train_tracker;
create database train_tracker;
use train_tracker;

create table customer (
    customer_id int primary key auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(255) not null,
    `password` varchar(255) not null,
    mobile varchar(50) not null
);

create table train (
    train_id int primary key auto_increment,
    `name` varchar(50) not null,
    model varchar(50) not null,
    manufacturer varchar(50) not null,
    `year` int not null,
    total_seats int not null,
    seat_layout varchar(20) not null,
    power_type varchar(20)
);

create table station (
    station_id int primary key auto_increment,
    `name` varchar(255) not null,
    station_code varchar(5) not null
);

create table ticket (
    ticket_id int primary key auto_increment,
    route_id int not null,
    customer_id int,
    first_name varchar(50) not null,
    source_station_id int not null,
    destination_station_id int not null,
    price decimal(8,2) not null,
    ticket_date date not null,
    seat_no varchar(20)
);

create table route (
    route_id int primary key auto_increment,
    route_name varchar(50) not null,
    train_id int not null,
    source_station_id int not null,
    destination_station_id int not null
);

create table route_station (
    route_station_id int primary key auto_increment,
    route_id int not null,
    station_id int not null,
    scheduled_arrival time not null,
    scheduled_departure time not null
);
