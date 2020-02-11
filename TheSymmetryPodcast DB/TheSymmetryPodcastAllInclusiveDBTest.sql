-- Destroys existing database, if exists; this way, start from scratch each time.
DROP DATABASE IF EXISTS TSPTest;

-- Create database.
CREATE DATABASE TSPTest;

-- Ensure that the schema is using the correct database.
USE TSPTest;

-- Create tables ------------------------------------------------------------------------
CREATE TABLE ContactStatusTbl(
	ContactStatusID INT PRIMARY KEY,
    ContactStatusName VARCHAR(12) NOT NULL
);

CREATE TABLE RegionTbl(
	RegionID INT PRIMARY KEY,
    RegionName VARCHAR(15) NOT NULL
);

CREATE TABLE AccountTypeTbl(
	AccountTypeID INT PRIMARY KEY,
    AccountTypeName VARCHAR(15) NOT NULL
);

CREATE TABLE AccountTbl(
	AccountNumber INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(25) NOT NULL,
    LastName VARCHAR(25) NOT NULL,
    MyEmail VARCHAR(40) NOT NULL,
    MyUsername VARCHAR(15) NOT NULL,
    MyPassword VARCHAR(20) NOT NULL,
    StartDate DATE NOT NULL,
    RegionID INT NOT NULL,
    AccountTypeID INT NOT NULL DEFAULT 1,
    FOREIGN KEY fk_RegionTbl_RegionID (RegionID)
		REFERENCES RegionTbl (RegionID),
	FOREIGN KEY fk_AccountTypeTbl_AccountTypeID (AccountTypeID)
		REFERENCES AccountTypeTbl (AccountTypeID)    
);

CREATE TABLE ContactMessagesTbl(
	ContactID INT PRIMARY KEY AUTO_INCREMENT,
    MyName VARCHAR(50) NOT NULL,
    MyEmail VARCHAR(50) NOT NULL,
    Message TEXT NOT NULL,
    Notes TEXT NULL,
    MyTimeStamp DATETIME NOT NULL,
    RegionID INT NOT NULL,
    ContactStatusID INT NOT NULL,
    FOREIGN KEY fk_RegionTbl_RegionID (RegionID)
		REFERENCES RegionTbl (RegionID),
	FOREIGN KEY fk_ContactStatusTbl_ContactStatusID (ContactStatusID)
		REFERENCES ContactStatusTbl (ContactStatusID)    
);

CREATE TABLE MP3Tbl(
	episodeKey INT PRIMARY KEY AUTO_INCREMENT,
    episodeLink VARCHAR(60),
    episodeTitle VARCHAR(50) NOT NULL,
    episodeDate VARCHAR(25) NOT NULL,
    episodeDescription TEXT NOT NULL
);

-- Fill some tables ---------------------------------------------------------------------
INSERT INTO ContactStatusTbl (ContactStatusID, ContactStatusName) VALUES
	(1, "unaddressed"),
    (2, "addressed"),
    (3, "tabled");
    
INSERT INTO RegionTbl (RegionID, RegionName) VALUES
	(1, "Africa"),
    (2, "Central Asia"),
    (3, "Eastern Asia"),
    (4, "Southern Asia"),
    (5, "Caribbean"),
    (6, "Europe"),
    (7, "Middle East"),
    (8, "North America"),
    (9, "South America"),
    (10, "Oceania");
    
INSERT INTO AccountTypeTbl (AccountTypeID, AccountTypeName) VALUES
	(1, "member"),
    (2, "administrator"),
    (3, "deactivated");