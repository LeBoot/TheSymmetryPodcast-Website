-- Ensure that the schema is using the correct database.
USE TheSymmetryPodcastMP3DB;

-- GET (read) method(s) -------------------------------------------------------------
SELECT * FROM MP3Tbl;

-- DELETE (delete) method(s) --------------------------------------------------------
DELETE FROM MP3Tbl WHERE episodeLink = "mp3s/episode";

-- POST (edit, create) methods(s) ---------------------------------------------------
INSERT INTO MP3Tbl (episodeLink, episodeTitle, episodeDate, episodeDescription) VALUES
    ("mp3s/TSP-S1-E1", "1 Pilot (Word)", "2018-05-10",
		"An episode about Greek philosophy, the Koine Greek language, and faith.");

-- POST (edit) method(s) -------------------------------------------------------------
UPDATE MP3Tbl SET
	episodeLink = "mp3s/TSP-S1-E1",
    episodeTitle = "1 Pilot (Word)",
    episodeDate = "2018-05-10",
    episodeDescription = "TEXT"
WHERE episodeLink = "mp3s/TSP-S1-E1";