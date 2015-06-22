create schema if not exists CaasDb;
use CaasDb;
SET foreign_key_checks = 0;

drop table if exists User;
create table User (id int auto_increment primary key, 
                     userName varchar(20), password varchar(25), firstName varchar(30),
                     lastName varchar(30), locked BOOL);
drop table if exists MenuCategory;
create table MenuCategory (id int auto_increment primary key, 
                     name varchar(50));

drop table if exists Menu;
create table Menu (id int auto_increment primary key, 
                    description varchar(50),
					price int,
					begin date,
					end date,
					categoryId int,
					FOREIGN KEY (categoryId) REFERENCES MenuCategory(id));

drop table if exists `Order`;
create table `Order` (id int auto_increment primary key, 
                    comment varchar(100),
					time timestamp,
					menuId int,
					userId int,
					FOREIGN KEY (menuId) REFERENCES Menu(id),
					FOREIGN KEY (userId) REFERENCES User(id));
					
SET foreign_key_checks = 1;
