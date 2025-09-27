create database WebFashionShop;
go

use WebFashionShop;
go

if object_id('Users', 'U') is not null drop table Users;
create table Users (
    id int primary key identity(1,1),
    username nvarchar(50) not null unique,
    password nvarchar(255) not null,
    email nvarchar(100) not null unique,
    role nvarchar(20) not null default 'user',
    resetCode nvarchar(6) null,
    resetExpiry datetime null
	fullname NVARCHAR(100) NULL,
	phone NVARCHAR(20) NULL,
	imageUrl NVARCHAR(255) NULL
);
go

if object_id('Categories', 'U') is not null drop table Categories;
create table Categories (
    id int primary key identity(1,1),
    name nvarchar(100) not null unique,
    description nvarchar(255) null
);
go

if object_id('Products', 'U') is not null drop table Products;
create table Products (
    id int primary key identity(1,1),
    category_id int not null,
    name nvarchar(150) not null,
    price decimal(18,2) not null,
    description nvarchar(1000) null,
    image_url nvarchar(500) null,
    constraint FK_Products_Categories foreign key (category_id) references Categories(id) on delete cascade
);
go

if not exists (select 1 from Users where username = 'admin')
    insert into Users(username, password, email, role)
    values ('admin', '123456', 'admin@gmail.com', 'admin');
go