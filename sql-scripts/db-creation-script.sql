create database book_fair;
create user 'book_fair'@'localhost' identified by 'book_fair';
grant all privileges on book_fair.* to 'book_fair'@'localhost'; 

use book_fair;
create table if not exists school
(
	id int not null auto_increment,
    name nvarchar(60) not null,
    address nvarchar(60),
    primary key(id)
);
insert into school(name, address) values
	('28LO im Jana Kochanowskiego','Wiktorska 99, Warszawa'),
	('XIV Liceum Ogólnokształcące im. St. Staszica','Nowowiejska 37A, Warszawa'),
	('VIII Liceum Ogólnokształcące im. Władysława IV','Jagiellońska 38, Warszawa'),
    ('VI Liceum Ogólnokształcące im. Tadeusza Reytana','Wiktorska 30/32'),
	('IX LO im. Klementyny Hoffmanowej','Hoża 88');
create table if not exists user
(
	username varchar(50) not null,
    password varchar(68) not null,
    enabled tinyint(1) not null,
    first_name nvarchar(45),
    last_name nvarchar(45),
    email varchar(50),
    school int,
    primary key(username),
    constraint school_fk foreign key(school) references school(id)
);
create table if not exists role
(
	name varchar(50), 
    description nvarchar(500),
    primary key(name)
);
create table if not exists user_role
(
	username varchar(50) not null,
    authority varchar(50) not null,
    constraint roles_fk foreign key(authority) references role(name),
    constraint users_fk foreign key(username) references user(username),
	constraint unique_username_plus_authority unique(username,authority)
);
insert into role values ('ROLE_SELLER','Is able to sell books');
create table if not exists book
(
	id int not null auto_increment,
    creation_date datetime,
    title nvarchar(45) not null,
    author nvarchar(70) not null,
    book_owner varchar(45) not null,
    purchaser varchar(45),
    price float not null,
    description nvarchar(1000),
    book_condition nvarchar(30) not null,
    school_type varchar(45) not null,
	class int,
    topic nvarchar(45), 
    primary key(id),
    constraint owner_fk foreign key(book_owner) references user(username),
    constraint purchaser_fk foreign key(purchaser) references user(username)
);
