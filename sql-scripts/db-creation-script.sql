create database book_fair;
create user 'book_fair'@'localhost' identified by 'book_fair';
grant all privileges on book_fair.* to 'book_fair'@'localhost'; 

use book_fair;
create table if not exists user
(
	username varchar(50) not null,
    password char(68) not null,
    enabled tinyint(1) not null,
    first_name nvarchar(45),
    last_name nvarchar(45),
    email varchar(50),
    school nvarchar(100),
    primary key(username)
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