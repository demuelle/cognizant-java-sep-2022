create schema record_collection;
use record_collection;

create table album (
	album_id int not null auto_increment primary key,
    title varchar(50) not null,
    artist_id int not null,
    release_date date not null,
    label_id int not null,
    list_price decimal(6,2) not null
);

create table track (
	track_id int not null auto_increment primary key,
    album_id int not null,
    title varchar(50) not null,
    runtime int not null
);

create table artist (
	artist_id int not null auto_increment primary key,
    name varchar(50) not null,
    instagram varchar(50),
    twitter varchar(50)
);

create table label (
	label_id int not null auto_increment primary key,
    name varchar(50) not null,
    website varchar(50)
);

/* Foreign Keys: album */
alter table album add constraint fk_album_artist foreign key (artist_id) references artist(artist_id);
alter table album add constraint fk_album_label foreign key (label_id) references label(label_id);

/* Foreign Keys: track */
alter table track add constraint fk_track_album foreign key (album_id) references album(album_id);
