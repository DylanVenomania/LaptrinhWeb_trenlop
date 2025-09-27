create database WebFashionShop;
go

use WebFashionShop;
go

create table Users (
    id int primary key identity(1,1),
    username nvarchar(50) not null unique,
    password nvarchar(50) not null,
    email nvarchar(100) not null unique
);
GO