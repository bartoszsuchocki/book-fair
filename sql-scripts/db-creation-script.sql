create database book_fair;
create user 'book_fair'@'localhost' identified by 'book_fair';
grant all privileges on book_fair.* to 'book_fair'@'localhost'; 

use book_fair;
create table if not exists users
(
	username varchar(50) not null,
    password char(68) not null,
    enabled tinyint(1) not null,
    first_name nvarchar(45),
    last_name nvarchar(45),
    email varchar(50) not null,
    school nvarchar(100),
    primary key(username)
);

create table if not exists authorities
(
	username varchar(50) not null,
    authority varchar(50) not null,
	constraint unique_username_plus_authority unique(username,authority),
    constraint users_fk foreign key(username) references users(username)
);
