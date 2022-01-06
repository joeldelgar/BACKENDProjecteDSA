DROP DATABASE IF EXISTS dsadb;
CREATE DATABASE dsadb;

USE dsadb;

CREATE TABLE User(

    id VARCHAR(30),
	name VARCHAR(30) PRIMARY KEY NOT NULL,
	password VARCHAR(30),
	mail VARCHAR(50),
	health INT,
	coins INT

)ENGINE = InnoDB;

CREATE TABLE Inventory(

	name userName(30),
	cost INT,
    description VARCHAR(100),
    avatar VARCHAR(100)
	
)ENGINE = InnoDB;

CREATE TABLE Item(

	userName VARCHAR(30),
	itemName VARCHAR(30),
	itemQuantity INT,
    itemDescription VARCHAR(100),
    itemAvatar VARCHAR(100)

)ENGINE = InnoDB;

INSERT INTO Item (name, cost, description, avatar) values ("magicBerry", 5, "Restore Health", "public/images/magicBerry.jpeg");
INSERT INTO Item (name, cost, description, avatar) values ("level1Item", 20, "Item to steal in the first level", "public/images/level1Item.jpeg");
INSERT INTO Item (name, cost, description, avatar) values ("level2Item", 20, "Item to steal in the second level", "public/images/level2Item.jpeg");
INSERT INTO Item (name, cost, description, avatar) values ("level3Item", 20, "Item to steal in the third level", "public/images/level3Item.jpeg");
INSERT INTO Item (name, cost, description, avatar) values ("level4Item", 20, "Item to steal in the fourth level", "public/images/level4Item.jpeg");
INSERT INTO Item (name, cost, description, avatar) values ("level5Item", 20, "Item to steal in the fifth level", "public/images/level5Item.jpeg");


CREATE TABLE Game(

   	userName VARCHAR(30),
	points INT

)ENGINE = InnoDB;

CREATE TABLE Enemy(

	name VARCHAR(30),
	damage INT,
    avatar VARCHAR(100)

)ENGINE = InnoDB;