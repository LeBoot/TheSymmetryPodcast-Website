-- Ensure that the schema is using the correct database.
USE TheSymmetryPodcastAccountsDB;

-- GET (read) method(s) -------------------------------------------------------------
SELECT * FROM AccountTbl;

SELECT * FROM AccountTbl WHERE AccountNumber = 3;

SELECT * FROM ContactMessagesTbl;

SELECT * FROM ContactMessagesTbl WHERE ContactID = 3;

SELECT * FROM ContactMessagesTbl WHERE ContactStatusID = 1;

-- DELETE (delete) method(s) --------------------------------------------------------
DELETE FROM AccountTbl WHERE AccountNumber = 3;

-- POST (edit, create) methods(s) ---------------------------------------------------
INSERT INTO AccountTbl (FirstName, LastName, MyEmail, MyUsername, MyPassword, StartDate, RegionID, AccountTypeID) VALUES
	("Lewis", "Hamilton", "LHamil@f1.com", "LewisH", "lewis", "2019-03-04", 4, 1);

INSERT INTO ContactMessagesTbl (MyName, MyEmail, Message, Notes, MyTimeStamp, RegionID, ContactStatusID) VALUES
	("Randy Rae", "rr@website.com", "text", NULL, "2018-01-19 03:14:07", 8, 2);
    
-- POST (edit) method(s) ------------------------------------------------------------
UPDATE AccountTbl SET
    FirstName = "Zacheriah",
    LastName = "Zebulum",
    MyEmail = "ZionMen@website.com",
    MyUsername = "ZZTop",
    MyPassword = "IAMZZman$",
    StartDate = "2020-01-27",
    RegionID = 5,
    AccountTypeID = 1
WHERE AccountNumber = 3;

UPDATE ContactMessagesTbl SET
    MyName = "Some Name",
    MyEmail = "someEmail@website.com",
    Message = "TEXT",
    Notes = "",
    MyTimeStamp = "2018-01-19 03:14:07",
    RegionID = 3,
    ContactStatusID = 2
WHERE ContactID = 3;
