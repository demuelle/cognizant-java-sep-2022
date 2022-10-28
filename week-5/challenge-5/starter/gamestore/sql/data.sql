use game_store;

insert into game(title, esrb_rating, description, price, studio, quantity)
	values ('World of Goo', 'PG', 'puzzle video game', 12.99, '2D Boy', 25);

insert into console(model, manufacturer, memory_amount, processor, price, quantity)
	values ('Nintendo Switch', 'Nintendo', '4GB', 'ARM 4 Cortex-A57', 299.99, 7);
    
insert into tshirt (size, color, description, price, quantity)
	values ('Med', 'Yellow', 'Science', 14.95, 0);
    
insert into invoice
	(`name`, street, city, state, zipcode, item_type, item_id, unit_price, quantity, subtotal, tax, processing_fee, total)
	values('Eng',null,'Eagan','MN','55555','Console',1,249.99,1,249.99,10.00,2,261.99);

insert into fee
	(product_type, fee)
	values ('Console', 14.99),('Game', 1.49),('T-Shirt', 1.98);

insert into tax
	(state, rate)
	values ('AK',0.06),('AL',0.05),('AR',0.06),('AZ',0.04),('CA',0.06),('CO',0.04),('CT',0.03),('DE',0.05),('FL',0.06),('GA',0.07),('HI',0.05),('IA',0.04),('ID',0.03),('IL',0.05),('IN',0.05),('KS',0.06),('KY',0.04),('LA',0.05),('MA',0.05),('MD',0.07),('ME',0.03),('MI',0.06),('MN',0.06),('MO',0.05),('MS',0.05),('MT',0.03),('NC',0.05),('ND',0.05),('NE',0.04),('NH',0.06),('NJ',0.05),('NM',0.05),('NV',0.04),('NY',0.06),('OH',0.04),('OK',0.04),('OR',0.07),('PA',0.06),('RI',0.06),('SC',0.06),('SD',0.06),('TN',0.05),('TX',0.03),('UT',0.04),('VA',0.06),('VT',0.07),('WA',0.05),('WI',0.03),('WV',0.05),('WY',0.04);
