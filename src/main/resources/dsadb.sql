DROP DATABASE IF EXISTS dsadb;
CREATE DATABASE dsadb;

USE dsadb;

CREATE TABLE User(

	id VARCHAR(30),
	name VARCHAR(30) PRIMARY KEY NOT NULL,
	password VARCHAR(30),
	mail VARCHAR(50),
	coins INT

)ENGINE = InnoDB;

CREATE TABLE Inventory(

	userName VARCHAR(30),
	itemName VARCHAR(30),
	itemQuantity INT,
	itemDescription VARCHAR(100),
	itemAvatar VARCHAR(100)

)ENGINE = InnoDB;

CREATE TABLE Item(

	name VARCHAR(30),
	cost INT,
	description VARCHAR(100),
	avatar VARCHAR(100)

)ENGINE = InnoDB;

INSERT INTO Item (name, cost, description, avatar) values ("magicBerry", 5, "Restore Health", "images/magicBerry.jpeg");
INSERT INTO Item (name, cost, description, avatar) values ("level1Item", 20, "Item to steal in the first level", "images/level1Item.jpeg");
INSERT INTO Item (name, cost, description, avatar) values ("level2Item", 20, "Item to steal in the second level", "images/level2Item.jpeg");
INSERT INTO Item (name, cost, description, avatar) values ("level3Item", 20, "Item to steal in the third level", "images/level3Item.jpeg");
INSERT INTO Item (name, cost, description, avatar) values ("level4Item", 20, "Item to steal in the fourth level", "images/level4Item.jpeg");
INSERT INTO Item (name, cost, description, avatar) values ("level5Item", 20, "Item to steal in the fifth level", "images/level5Item.jpeg");

CREATE TABLE Game(

   	userName VARCHAR(30),
	points INT

)ENGINE = InnoDB;

CREATE TABLE Enemy(

	name VARCHAR(30),
	damage INT,
	description VARCHAR(100),
	avatar VARCHAR(100)

)ENGINE = InnoDB;

INSERT INTO Enemy (name, damage, description, avatar) values ("securityGuard", 25, "As he sees you, he will not leave you alone", "images/securityGuard.jpeg");
INSERT INTO Enemy (name, damage, description, avatar) values ("finalBoss", 100, "Stay away, it kills you with a slap", "images/finalBoss.jpeg");