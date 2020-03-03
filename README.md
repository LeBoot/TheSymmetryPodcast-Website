# TheSymmetryPodcast-Website
A full-stack website to fulfill the requirements of the Software Guild's full-stack bootcamp.  Project began late January 2020 and was submitted mid February 2020.
This website is not hosted live.

## Contents
  * [Use](#Use)
  * [Requirements](#Requirements)
  * [Technologies](#Technologies)
  * [Notes](#Notes)
  * [Enhancements](#Enhancements)

## Use
  * Before running this program, go to the mail service layer and enter the appropriate username + password for Gmail account
  * In Gmail account, change security sessions to allow access from less-secure apps
  * Run in NetBeans
  * Go to localhost:8080 in your favorite browser

## Requirements
SoftwareGuild requirements were to build a full-stack website including
  * Security sessions (log in, log out)
  * Database with at least 4 relations
  * Client-side rendering
  * Technology not learned during the bootcamp
  * MVC architecture
  * Full complement of testing
  
## Technologies
Front-End Technologies:
  * HTML, CSS, Bootstrap
  * JavaScript (with jQuery)
  * AJAX (with JSON)

Back-End Technologies:
  * Java with Spring
  * Lombok
  * Java Persistence API (JPA)
  * Java Mail API

Database Technologies:
  * MySQL
  * MySQL Workbench

IDE and Text Editors:
  * NetBeans
  * Visual Studio Code (VS Code)
  * Notepad++

## Notes
  * Home page stylized using flex-box
  * Headers and footers are dynamically included using JavaScript
  * Java Mail API used to send emails (use Gmail as SMTL server)
  * Java Persistence API to interact with database; some native queries used
  * Validation on both front and back ends
  * Lots of AJAX used; Thymeleaf was not used
  * Custom securities used; Spring security not used
  * File uploader not incorporated
  
 ## Enhancements
 A stronger version of this site experiment with or include the following:
  * Stronger security: 1) Password encryption.  2) Instead of redirecting users without proper credentials, never display the page in the first place, even for a moment
  * Server-side rendering: Use a mix of both client- and server-side rendering, as each page dictates
  * File uploader: Incorporate a file uploader for administrators to add mp3s; right now, they have to be entered into NetBeans by hand; note that doing this will require refactoring across all layers of the applicaiton, including database
  * Custom error messages from the backend: As is, all back-end validation will only return a bad HTML status.
  * Form errors to accompany each form element.  As is, all errors are displayed at the top of the page.
 
 Other ideas to consider:
  * Ability to host live shows
  * Ability to track which episodes are listened to
  * Internal search engine
  * Page tabs for each season (right now, there is only one season)
  
