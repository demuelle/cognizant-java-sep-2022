use train_tracker;

insert into customer(customer_id, first_name, last_name, email, `password`, mobile)
	values
    (1, 'Gary', 'Coleman', 'gcoleman@somewhere.com', 'password', '123.456.7890'),
    (2, 'Winston', 'Churchill', 'wchurchill@somewhere.com', 'password', '456.789.0123'),
    (3, 'Johnny', 'Cash', 'jcash@somewhere.com', 'password', '789.012.3456');

insert into train(train_id, `name`, model, manufacturer, `year`, total_seats, seat_layout, power_type)
	values
    (1, 'California Zephyr', 'Dash 8-32BWH', 'GE', 2015, 250, '2A', 'Diesel'),
    (2, 'Capitol Limited', 'Sprinter ACS-64', 'Siemens', 2014, 225, '2A', 'Diesel'),
    (3, 'Cardinal', 'Charger ALC-42', 'Siemens', 2021, 289, '4C', 'Electric');
    
insert into station (station_id, `name`, station_code)
	values
    (1, 'Auburn, CA', 'ARN'),
    (2, 'Rocklin, CA', 'RLN'),
    (3, 'Roseville, CA', 'RSV'),
    (4, 'Sacramento, CA - Sacramento Valley Station', 'SAC'),
    (5, 'Davis, CA', 'DAV'),
    (6, 'Fairfield-Vacaville, CA', 'FFV'),
    (7, 'Suisun-Fairfield, CA', 'SUI'),
    (8, 'Martinez, CA', 'MTZ'),
    (9, 'Richmond, CA', 'RIC'),
    (10, 'Berkeley, CA', 'BKY'),
    (11, 'Emeryville, CA', 'EMY'),
    (12, 'Oakland, CA - Jack London Square Station', 'OKJ'),
    (13, 'Oakland, CA - Coliseum/Airport', 'OAC'),
    (14, 'Hayward, CA', 'HAY'),
    (15, 'Fremont, CA - Amtrak/ACE Station', 'FMT'),
    (16, 'Santa Clara, CA - Great America', 'GAC'),
    (17, 'Santa Clara, CA - Santa Clara University', 'SCC'),
    (18, 'San Jose, CA - Diridon Station', 'SJC');

insert into ticket
	(route_id, customer_id, first_name, source_station_id, destination_station_id, price, ticket_date, seat_no)
	values(1, 1, 'Gary', 13, 10, 40.00, '2021-12-25' , '5A');

insert into route
	(route_id, route_name, train_id, source_station_id, destination_station_id)
	values (1, 'Capitol Corridor', 1, 18, 4);

insert into route_station
	(route_id, station_id, scheduled_arrival, scheduled_departure)
	values
    (1, 18, '04:25:00', '04:30:00'),
    (1, 17, '04:40:00', '04:45:00'),
    (1, 16, '04:55:00', '05:00:00'),
    (1, 15, '05:10:00', '05:15:00'),
    (1, 14, '05:25:00', '05:30:00'),
    (1, 13, '05:40:00', '05:45:00'),
    (1, 12, '05:55:00', '06:05:00'),
    (1, 11, '06:15:00', '06:20:00'),
    (1, 10, '06:30:00', '06:35:00'),
    (1, 9, '06:45:00', '06:50:00'),
    (1, 8, '07:00:00', '07:05:00'),
    (1, 7, '07:15:00', '07:20:00'),
    (1, 6, '07:30:00', '07:35:00'),
    (1, 5, '07:45:00', '07:50:00'),
    (1, 4, '08:00:00', '08:05:00');
