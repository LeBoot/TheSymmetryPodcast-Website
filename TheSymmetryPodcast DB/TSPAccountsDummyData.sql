-- Ensure that the schema is using the correct database.
USE TheSymmetryPodcastAccountsDB;

-- Create users
INSERT INTO AccountTbl (FirstName, LastName, MyEmail, MyUsername, MyPassword, StartDate, RegionID, AccountTypeID) VALUES
	("Ben", "LeBoot", "BLeBoot@website.com", "BenLeBoot", "root", "2018-01-01", 8, 2),
    ("Brad", "Collins", "bradson@website.com", "BradCollins", "root", "2018-01-01", 8, 2),
	("Lewis", "Hamilton", "LHamil@f1.com", "LewisH", "lewis", "2019-03-04", 4, 1),
    ("Valtteri", "Bottas", "vBottas@website.com", "Bottas!", "bottasPass", "2019-05-19", 1, 1),
    ("Sebastian", "Vettel", "sVettSVett@f1.com", "SebastianHere", "vettMan", "2019-09-22", 5, 1),
    ("Charles", "Leclerc", "cLecLec@f1.com", "CharlesLeclerc", "Chucky5", "2020-01-04", 9, 1),
    ("Alex", "Albon", "AlexAlexAlbon@website.com", "AlbonAA", "password2937", "2018-09-25", 10, 1),
    ("Max", "Verstappen", "MaxVerstap@yep.com", "VerstMan", "MaxVer1234", "2019-10-19", 9, 1),
    ("Lando", "Norris", "StarWarsName@f1.com", "Lando", "landMan", "2019-01-31", 8, 1),
    ("Carlos", "Sainz", "Carlosf1@f1.com", "Sainz55", "sainz55", "2020-01-10", 7, 1),
    ("Daniel", "Ricciardo", "DRicc@f1.com", "Ricciardo3", "dannyRic", "2019-09-30", 6, 1),
    ("Esteban", "Ocon", "Esteban@website.com", "Ocon31", "esteOcon", "2019-11-09", 8, 1),
    ("Adam", "Addington", "adamAA@website.com", "adam101", "ThisIsMyPass", "2018-02-14", 8, 1),
    ("Billy", "Billingsgate", "sailorLife@nautilus.com", "billyBilly2", "AnotherPassword", "2020-01-05", 10, 1),
    ("Charles", "Cottonsworth", "luckyChuck@website.com", "BestFriend$$", "1234", "2019-05-23", 8, 1),
    ("Denny", "Davenport", "Denny1@gmail.com", "DennyDave", "IAmDenny%%", "2019-12-01", 6, 1),
    ("Erik", "Erikson", "Viking@viking.com", "NorseMan5", "myPassword", "2018-12-10", 6, 3),
    ("Francais", "France", "frenchGirl@website.com", "Frenchy", "ILoveGrease", "2018-05-04", 8, 1),
    ("Goelum", "Grant", "thisGuy@website.com", "GG69", "UncreativePassword", "2019-02-22", 5, 1),
    ("Hailey", "Harper", "AllHailHailey@Hailey.com", "HaileyRules", "HaileyRocks", "2020-01-26", 3, 1),
    ("Irene", "Idle", "iAmIrene@website.com", "YourFriendIrene", "myPassword55", "2019-06-17", 2, 3),
    ("Jason", "Jameson", "whiskeyMan@taco.com", "Jameson", "JJisMe2839", "2018-12-08", 8, 1),
    ("Kaitlyn", "Kenny", "kkgirl@website.com", "KKgirl", "anotherPassword", "2019-08-29", 10, 1),
    ("Lenny", "Lennison", "lennyLL@lenny.com", "LennyHere!", "pass4Lenny", "2019-03-25", 8, 1),
    ("Miriam", "McMae", "miriamMMM@website.com", "Miriam", "maryMiriam", "2020-01-20", 7, 1),
    ("Nicole", "Nikolaj", "Nikki@website.com", "NikkiFromNikki", "NikolajGirl", "2019-10-07", 3, 3),
    ("Oscar", "Odoule", "OdulesWinner@website.com", "OOOOhMan", "OdoulePass", "2018-09-19", 6, 1),
    ("Peter", "Peterson", "PetePeterson@pete.com", "2Peters", "PeteMan", "2019-05-08", 9, 1),
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
        "Follow Up in 2020", "2019-02-28 13:20:10", 5, 3),
	("Uncle Sam", "goServe@usa.gov",
		"Sem justo egestas lectus, nec laoreet orci nulla ut ipsum. Maecenas volutpat lacus leo. Curabitur pretium gravida lacinia. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aenean tristique nulla ut suscipit malesuada. Morbi imperdiet bibendum orci, eu volutpat magna maximus vel. Donec velit nisl, vulputate et magna quis, lacinia tincidunt lectus. Proin sagittis lacus nisl, vel molestie neque pellentesque ac. Nulla eget mollis ante, eu posuere risus.",
        NULL, "2019-05-05 13:20:10", 8, 2),
	("Vikki", "vSauce@website.com",
		"Quisque in diam enim. Nam elementum ultrices accumsan. Sed maximus blandit tincidunt.",
        NULL, "2020-01-05 13:20:10", 8, 1);