-- Destroys existing database, if exists; this way, start from scratch each time.
DROP DATABASE IF EXISTS TheSymmetryPodcastDB;

-- Create database.
CREATE DATABASE TheSymmetryPodcastDB;

-- Ensure that the schema is using the correct database.
USE TheSymmetryPodcastDB;

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
    
    
-- Add Dummy Data ------------------------------------------------------------------------------

-- Add Episodes
INSERT INTO MP3Tbl (episodeKey, episodeLink, episodeTitle, episodeDate, episodeDescription) VALUES
	(1, "mp3/TSP-S1-Trailer.mp3", "Introducing the Symmetry Podcast", "April 2, 2018",
		"Hello! The Symmetry Podcast is a home for conversations about faith and humanity that are designed to help people grow.  We invite you to check out this trailer episode!"),
    (2, "mp3/TSP-S1-E1.mp3", "1 Pilot (Word)", "May 10, 2018",
		"An episode about Greek philosophy, the Koine Greek language, and faith."),
	(3, "mp3/TSP-S1-E2.mp3", "2 Boxes", "July 3, 2018",
		"An episode about personality typing, ancient literature, and spirituality."),
	(4, "mp3/TSP-S1-B1.mp3", "Bonus: Leaven", "July 25, 2018",
		"A special episode in which one of our co-hosts shares a homily about seeing well-known Christian stories through a new lens."),
	(5, "mp3/TSP-S1-E3.mp3", "3 Garlic", "July 31, 2018",
		"An episode about alliaceous foods, the Talmud, and ancient prophecies."),
	(6, "mp3/TSP-S1-E4.mp3", "4 Paths (with Rabbi Adam Grossman)", "August 19, 2018",
		"An interview with Rabbi Adam Grossman, the CEO at University of Florida Hillel in Gainesville."),
	(7, "mp3/TSP-S1-B2.mp3", "Bonus: Syrophoenician", "September 9, 2018",
		"A special bonus episode in which one of our co-hosts shares the story of a woman from the ancient Roman province of Syria and how her story has parallels in life today."),
	(8, "mp3/TSP-S1-E5.mp3", "5 Parable", "September 24, 2018",
		"An episode about stories, parables, and humanity connecting with spirituality."),
	(9, "mp3/TSP-S1-E6.mp3", "6 Believe (The Christmas Episode)", "December 23, 2018",
		"An episode about Christmas, faith, and everything in between.");
        
        
-- Create users
INSERT INTO AccountTbl (FirstName, LastName, MyEmail, MyUsername, MyPassword, StartDate, RegionID, AccountTypeID) VALUES
	("Ben", "LeBoot", "BenLeBoot@gmail.com", "BenLeBoot", "root", "2017-10-30", 8, 2),
    ("Brad", "Collins", "bradson@website.com", "BradCollins", "root", "2017-10-30", 8, 2),
    ("Morganna", "Ciffy", "mmkk@tsp.com", "MK", "root", "2018-01-01", 8, 2),
	("Lewis", "Hamilton", "LHamil@f1.com", "LewisH", "lewis", "2018-01-10", 4, 1),
    ("Valtteri", "Bottas", "vBottas@website.com", "Bottas!", "bottasPass", "2018-01-19", 1, 1),
    ("Sebastian", "Vettel", "sVettSVett@f1.com", "SebastianHere", "vettMan", "2018-02-22", 5, 1),
    ("Charles", "Leclerc", "cLecLec@f1.com", "CharlesLeclerc", "Chucky5", "2018-02-22", 9, 1),
    ("Alex", "Albon", "AlexAlexAlbon@website.com", "AlbonAA", "password2937", "2018-03-16", 10, 1),
    ("Max", "Verstappen", "MaxVerstap@yep.com", "VerstMan", "MaxVer1234", "2018-04-02", 9, 1),
    ("Lando", "Norris", "StarWarsName@f1.com", "Lando", "landMan", "2018-06-30", 8, 1),
    ("Carlos", "Sainz", "Carlosf1@f1.com", "Sainz55", "sainz55", "2018-07-13", 7, 1),
    ("Daniel", "Ricciardo", "DRicc@f1.com", "Ricciardo3", "dannyRic", "2018-09-30", 6, 1),
    ("Esteban", "Ocon", "Esteban@website.com", "Ocon31", "esteOcon", "2018-11-09", 8, 1),
    ("Adam", "Addington", "adamAA@website.com", "adam101", "ThisIsMyPass", "2018-11-14", 8, 1),
    ("Billy", "Billingsgate", "sailorLife@nautilus.com", "billyBilly2", "AnotherPassword", "2018-11-15", 10, 1),
    ("Charles", "Cottonsworth", "luckyChuck@website.com", "BestFriend$$", "1234", "2019-01-02", 8, 1),
    ("Denny", "Davenport", "Denny1@gmail.com", "DennyDave", "IAmDenny%%", "2019-02-21", 6, 1),
    ("Erik", "Erikson", "Viking@viking.com", "NorseMan5", "myPassword", "2019-03-09", 6, 3),
    ("Francais", "France", "frenchGirl@website.com", "Frenchy", "ILoveGrease", "2019-05-04", 8, 1),
    ("Goelum", "Grant", "thisGuy@website.com", "GG69", "UncreativePassword", "2019-05-22", 5, 1),
    ("Hailey", "Harper", "AllHailHailey@Hailey.com", "HaileyRules", "HaileyRocks", "2019-05-26", 3, 1),
    ("Irene", "Idle", "iAmIrene@website.com", "YourFriendIrene", "myPassword55", "2019-06-17", 2, 3),
    ("Jason", "Jameson", "whiskeyMan@taco.com", "Jameson", "JJisMe2839", "2019-06-18", 8, 1),
    ("Kaitlyn", "Kenny", "kkgirl@website.com", "KKgirl", "anotherPassword", "2019-08-29", 10, 1),
    ("Lenny", "Lennison", "lennyLL@lenny.com", "LennyHere!", "pass4Lenny", "2019-08-29", 8, 1),
    ("Miriam", "McMae", "miriamMMM@website.com", "Miriam", "maryMiriam", "2019-10-20", 7, 1),
    ("Nicole", "Nikolaj", "Nikki@website.com", "NikkiFromNikki", "NikolajGirl", "2019-10-27", 3, 3),
    ("Oscar", "Odoule", "OdulesWinner@website.com", "OOOOhMan", "OdoulePass", "2019-11-19", 6, 1),
    ("Peter", "Peterson", "PetePeterson@pete.com", "2Peters", "PeteMan", "2020-01-08", 9, 1),
    ("Nicole", "Nikolaj", "Nikki@website.com", "NikkiFromNikki", "NikolajGirl", "2020-01-10", 3, 1);
    
-- create Contact Us submissions
INSERT INTO ContactMessagesTbl (MyName, MyEmail, Message, Notes, MyTimeStamp, RegionID, ContactStatusID) VALUES
	("Randy Rae", "rr@website.com", 
		"Suspendisse ligula mauris, dictum nec suscipit vitae, ornare eu velit. Integer pulvinar in ipsum tristique mollis.",
        NULL, "2018-01-19 03:14:07", 8, 2),
	("Sierra", "sandy@sandy.com",
		"Integer viverra dui sed lectus consequat tincidunt. Nullam nec diam quis est maximus rutrum sit amet eget magna. Etiam quis ligula fringilla, pharetra eros at, ultricies mauris. Cras quis vehicula felis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae",
        NULL, "2018-12-19 13:14:07", 8, 2),
	("Tokyo T", "TTman@website.com",
		"Proin suscipit, ligula id auctor dictum",
        NULL, "2019-02-28 13:20:10", 5, 2),
	("Uncle Sam", "goServe@usa.gov",
		"Sem justo egestas lectus, nec laoreet orci nulla ut ipsum. Maecenas volutpat lacus leo. Curabitur pretium gravida lacinia. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aenean tristique nulla ut suscipit malesuada. Morbi imperdiet bibendum orci, eu volutpat magna maximus vel. Donec velit nisl, vulputate et magna quis, lacinia tincidunt lectus. Proin sagittis lacus nisl, vel molestie neque pellentesque ac. Nulla eget mollis ante, eu posuere risus.",
        "Waiting for another message", "2019-05-05 13:20:10", 8, 3),
	("Vikki", "vSauce@website.com",
		"Quisque in diam enim. Nam elementum ultrices accumsan. Sed maximus blandit tincidunt.",
        NULL, "2019-08-05 13:20:10", 8, 2),
	("Willie Washington", "ww@email.com",
		"Aenean nec nulla nulla. Donec facilisis nisi non mollis dignissim. Vivamus rhoncus tortor a est gravida, in faucibus turpis ultrices. Donec consectetur sed enim id mattis. Phasellus ultricies justo posuere rhoncus euismod. Maecenas accumsan lacinia risus, in lobortis dui consequat sed. Nullam neque dolor, pellentesque volutpat nisi eu, venenatis malesuada urna.",
        NULL, "2020-01-10 12:59:30", 5, 1),
	("Xavier X", "theXMan@website.com",
		"Morbi malesuada sodales sollicitudin",
        "Follow Up in March", "2020-02-01 05:20:32", 6, 3),
	("Yehsua", "vav@this.com",
		"Nunc volutpat tellus vitae tellus fringilla luctus. Aenean molestie leo et ultrices aliquet. Vivamus non est nisl. Suspendisse tincidunt diam vitae mauris convallis sagittis.",
        NULL, "2020-02-05 05:45:31", 4, 1),
	("Zoe", "checkers@website.com",
		"Morbi tempor euismod venenatis. Aenean urna erat, congue at nunc vel, sodales blandit augue.",
        NULL, "2020-02-11 11:55:10", 9, 1);