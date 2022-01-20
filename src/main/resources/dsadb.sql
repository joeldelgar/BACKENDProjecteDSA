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

INSERT INTO Item (name, cost, description, avatar) values ("magicBerry", 5, "Restore Health", "images/magicBerry.png");
INSERT INTO Item (name, cost, description, avatar) values ("barKey", 20, "Key to access the bar", "images/key.png");
INSERT INTO Item (name, cost, description, avatar) values ("sacredCake", 0, "Cake that was made by the best pastry chefs", "images/sacredCake.png");
INSERT INTO Item (name, cost, description, avatar) values ("museumKey", 40, "Key to access the museum", "images/key.png");
INSERT INTO Item (name, cost, description, avatar) values ("goldenPot", 0, "Item to steal in the fourth level", "images/goldenPot.png");
INSERT INTO Item (name, cost, description, avatar) values ("directorOfficeKey", 60, "Key to access the director's office", "images/key.png");
INSERT INTO Item (name, cost, description, avatar) values ("directorPapers", 0, "Papers uncovering EETAC's affairs", "images/directorPapers.png");

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

INSERT INTO Enemy (name, damage, description, avatar) values ("securityGuard", 25, "As he sees you, he will not leave you alone", "images/securityGuard.png");
INSERT INTO Enemy (name, damage, description, avatar) values ("finalBoss", 100, "Stay away, it kills you with a slap", "images/finalBoss.png");