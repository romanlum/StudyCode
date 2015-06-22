create schema if not exists CaasDb;
use CaasDb;
drop table if exists User;
create table User (id int auto_increment primary key, 
                     userName varchar(20), password varchar(25), firstName varchar(30),
                     lastName varchar(30), locked BOOL);
drop table if exists MenuCategory;
create table MenuCategory (id int auto_increment primary key, 
                     name varchar(50));

