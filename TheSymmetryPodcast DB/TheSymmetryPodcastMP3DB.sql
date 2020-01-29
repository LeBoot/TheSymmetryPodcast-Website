-- Destroys existing database, if exists; this way, start from scratch each time.
DROP DATABASE IF EXISTS TheSymmetryPodcastMP3DB;

-- Create database.
CREATE DATABASE TheSymmetryPodcastMP3DB;

-- Ensure that the schema is using the correct database.
USE TheSymmetryPodcastMP3DB;

-- Create table ------------------------------------------------------------------------
CREATE TABLE MP3Tbl(
	episodeLink VARCHAR(60) PRIMARY KEY,
    episodeTitle VARCHAR(50) NOT NULL,
    episodeDate DATE NOT NULL,
    episodeDescription TEXT NOT NULL
);

-- Add Episodes
INSERT INTO MP3Tbl (episodeLink, episodeTitle, episodeDate, episodeDescription) VALUES
	("mp3s/TSP-S1-Trailer", "Introducing the Symmetry Podcast", "2018-04-02",
		"Hello! The Symmetry Podcast is a home for conversations about faith and humanity that are designed to help people grow.  We invite you to check out this trailer episode!"),
    ("mp3s/TSP-S1-E1", "1 Pilot (Word)", "2018-05-10",
		"An episode about Greek philosophy, the Koine Greek language, and faith."),
	("mp3s/TSP-S1-E2", "2 Boxes", "2018-07-03",
		"An episode about personality typing, ancient literature, and spirituality."),
	("mp3s/TSP-S1-B1", "Bonus: Leaven", "2018-07-25",
		"A special episode in which one of our co-hosts shares a homily about seeing well-known Christian stories through a new lens."),
	("mp3s/TSP-S1-E3", "3 Garlic", "2018-07-31",
		"An episode about alliaceous foods, the Talmud, and ancient prophecies."),
	("mp3/TSP-S1-E4", "4 Paths (with Rabbi Adam Grossman)", "2018-08-19",
		"An interview with Rabbi Adam Grossman, the CEO at University of Florida Hillel in Gainesville."),
	("mp3/TSP-S1-B2", "Bonus: Syrophoenician", "2018-09-10",
		"A special bonus episode in which one of our co-hosts shares the story of a woman from the ancient Roman province of Syria and how her story has parallels in life today."),
	("mp3/TSP-S1-E5", "5 Parable", "2018-09-24",
		"An episode about stories, parables, and humanity connecting with spirituality."),
	("mp3/TSP-S1-E6", "6 Believe (The Christmas Episode)", "2018-12-23",
		"An episode about Christmas, faith, and everything in between.");