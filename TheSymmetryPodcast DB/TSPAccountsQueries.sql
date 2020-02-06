-- Ensure that the schema is using the correct database.
USE TheSymmetryPodcastAccountsDB;

-- GET --------------------------------------------------------------------------------
-- GET for logIn.html, this needs to be case-verified elsewhere
SELECT * FROM AccountTbl WHERE MyUsername = "NikkiFromNikki" AND MyPassword = "NikolajGirl" AND AccountTypeID != 3;

-- GET for signUp.html, this needs to be case-verified elsewhere
SELECT * FROM AccountTbl WHERE MyUsername = "NikkiFromNikki" AND AccountTypeID != 3;

-- GET for myAccount.html, TSPadmin.html (edit)
SELECT * FROM AccountTbl WHERE AccountNumber = 1;

-- Get for TSPadmin.html (list)
SELECT * FROM AccountTbl;
SELECT * FROM ContactMessagesTbl;
SELECT * FROM ContactMessagesTbl WHERE ContactID = 1;
SELECT * FROM ContactMessagesTbl WHERE ContactStatusID = 1;

-- POST -----------------------------------------------------------------------------------
-- POST for contact.html, to add a new message
INSERT INTO ContactMessagesTbl (MyName, MyEmail, Message, Notes, MyTimeStamp, RegionID, ContactStatusID) VALUES
	("Randy Rae", "rr@website.com", "text", NULL, "2018-01-19 03:14:07", 8, 2);
    
-- POST for TSPadmin.html and signUp.html, to add new account
INSERT INTO AccountTbl (FirstName, LastName, MyEmail, MyUsername, MyPassword, StartDate, RegionID, AccountTypeID) VALUES
	("Lewis", "Hamilton", "LHamil@f1.com", "LewisH", "lewis", "2019-03-04", 4, 1);

-- POST, for TSPadmin.html and myAccount.html, to edit a contact (or "delete" from the user's perspective)
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

-- POST, for TSPadmin.html, to change a message status or add/delete notes
UPDATE ContactMessagesTbl SET
    MyName = "Some Name",
    MyEmail = "someEmail@website.com",
    Message = "TEXT",
    Notes = "",
    MyTimeStamp = "2018-01-19 03:14:07",
    RegionID = 3,
    ContactStatusID = 2
WHERE ContactID = 3;

-- DELETE  --------------------------------------------------------------------------
-- DELETE for TSPadmin.html
DELETE FROM AccountTbl WHERE AccountNumber = 3;


