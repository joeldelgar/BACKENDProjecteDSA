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

    userName VARCHAR(30),
	magicBerry INT,
    level1Item INT,
    level1Key INT,
    level2Item INT,
    level2Key INT,
    level3Item INT,
    level3Key INT,
    level4Item INT,
    level4Key INT,
	level5Item INT,
    level5Key INT
	
)ENGINE = InnoDB;

CREATE TABLE Item(

	name VARCHAR(30),
	cost INT,
    description VARCHAR(100),
    avatar VARCHAR(100)

)ENGINE = InnoDB;

INSERT INTO Item (name, cost, description, avatar) values ("magicBerry", 5, "Restore Health", "images/magicBerry.jpeg");
INSERT INTO Item (name, cost, description, avatar) values ("level1Item", 20, "Item to steal in the first level", "images/level1Item.jpeg");
INSERT INTO Item (name, cost, description, avatar) values ("level1Key", 50, "Key to finish the first level", "images/level1Key.jpeg");
INSERT INTO Item (name, cost, description, avatar) values ("level2Item", 20, "Item to steal in the second level", "images/level2Item.jpeg");
INSERT INTO Item (name, cost, description, avatar) values ("level2Key", 50, "Key to finish the first second", "images/level2Key.jpeg");
INSERT INTO Item (name, cost, description, avatar) values ("level3Item", 20, "Item to steal in the third level", "images/level3Item.jpeg");
INSERT INTO Item (name, cost, description, avatar) values ("level3Key", 50, "Key to finish the third level", "images/level3Key.jpeg");
INSERT INTO Item (name, cost, description, avatar) values ("level4Item", 20, "Item to steal in the fourth level", "images/level4Item.jpeg");
INSERT INTO Item (name, cost, description, avatar) values ("level4Key", 50, "Key to finish the fourth level", "images/level4Key.jpeg");
INSERT INTO Item (name, cost, description, avatar) values ("level5Item", 20, "Item to steal in the fifth level", "images/level5Item.jpeg");
INSERT INTO Item (name, cost, description, avatar) values ("level4Key", 50, "Key to finish the fifth level", "images/level5Key.jpeg");


CREATE TABLE Game(

   	userName VARCHAR(30),
	points INT

)ENGINE = InnoDB;

CREATE TABLE Enemy(

	name VARCHAR(30),
	damage INT,
    avatar VARCHAR(100)

)ENGINE = InnoDB;