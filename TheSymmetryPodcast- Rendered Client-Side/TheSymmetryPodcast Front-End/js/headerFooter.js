/*
Name: headerFooter.js, (TSP Capstone)
Author: Leboutillier
Date Created: 24 JAN 2020
Date Modified: 6 FEB 2020
*/

/*
At Document Ready, append page's <header> and <footer> tags with the appropriate html code
*/

//ERROR DIV -------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------
function addToErrorDiv(Message) {
		
	$(".errorDiv").append(
		'<p class="alert alert-danger">' + Message + '</p>'
	);
	
}

function emptyErrorDiv() {
	$(".errorDiv").empty();
}


//DOCUMENT READY --------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------
$(document).ready(function () {
	
	//clear errors
	emptyErrorDiv();
	
	//header
	AJAXcallForSessionStatus();

	//footer
	addFooter();
	
	
	//just for testing
	//displayAllHeader();

})

//HEADER ----------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------
function AJAXcallForSessionStatus() {
	
	$.ajax({
        type: 'GET',
        url: 'http://localhost:8080/session-status/get',
        success: function(SessionStats) {
			emptyErrorDiv();
			addToAccountIdDiv(SessionStats.rbCurrentAccount);
			addHeader(SessionStats.rbSessionStatus);
        },
        error: function (jqXHR, textStatus, errorThrown) {
			addHeader("USER");
			addToErrorDiv("Session Status is not loading from the back-end.");
        }
    });	
	
}

function addToAccountIdDiv(userAccountNum) {
	console.log("Account Number: " + userAccountNum);
	$(".accountIdDiv").text(userAccountNum);
}

function addHeader(sessionStatus) {
	
	if (sessionStatus == "MEMBER") {
		displayMemberHeader();
	} else if (sessionStatus == "ADMIN") {
		displayAdminHeader();
	} else {
		displayUserHeader();
	}
	
}

function displayMemberHeader() {

	$("header").html("");
	$("header").html(
	
		`<div id="logInBoxes">
			<nav>
				<a class="btn" id="myAccountNavButton" href="myAccount.html">My Account</a>
				<a class="btn" id="logOutNavButton" onClick="logOutFromWebsite()">Log Out</a>
			</nav>
		</div>
		<div id="TspLogo">
			<a href="home.html">
				<img id="TspLogoImage" src="Images/TSPlogo.jpeg" alt="Logo for The Symmetry Podcast." class="img-responsive"/>
			</a>
		</div>
		<div id="navChoices">
			<nav>
				<a class="btn" id="homeNavButton" href="home.html">home</a>
				<a class="btn" id="listenNavButton" href="listen.html">listen</a>
				<a class="btn" id="blogNavButton" href="blog.html">blog</a>
				<a class="btn" id="aboutNavButton" href="about.html">about</a>
				<a class="btn" id="contactNavButton" href="contact.html">contact</a>
			</nav>
		</div>`
	
	);
	
}

function displayAdminHeader() {
	
	$("header").html("");
	$("header").html(
	
		`<div id="logInBoxes">
			<div style="background-color: plum; max-width: 1300px; margin: 5px 30px; text-align: center; padding: 2px 5px; border-radius: 4px;">
				You are logged in as an administrator.
			</div>
			<nav>
				<a class="btn" id="TSPadminNavButton" href="TSPadmin.html">Admin Portal</a>
				<a class="btn" id="myAccountNavButton" href="myAccount.html">My Account</a>
				<a class="btn" id="logOutNavButton" onClick="logOutFromWebsite()">Log Out</a>
			</nav>
		</div>
		<div id="TspLogo">
			<a href="home.html">
				<img id="TspLogoImage" src="Images/TSPlogo.jpeg" alt="Logo for The Symmetry Podcast." class="img-responsive"/>
			</a>
		</div>
		<div id="navChoices">
			<nav>
				<a class="btn" id="homeNavButton" href="home.html">home</a>
				<a class="btn" id="listenNavButton" href="listen.html">listen</a>
				<a class="btn" id="blogNavButton" href="blog.html">blog</a>
				<a class="btn" id="aboutNavButton" href="about.html">about</a>
				<a class="btn" id="contactNavButton" href="contact.html">contact</a>
			</nav>
		</div>`
	
	);
	
}

function displayUserHeader() {
	
	$("header").html("");
	$("header").html(
	
		`<div id="logInBoxes">
			<nav>
				<a class="btn" id="logInNavButton" href="logIn.html">Log In</a>
				<a class="btn" id="signUpNavButton" href="signUp.html">Sign Up</a>
			</nav>
		</div>
		<div id="TspLogo">
			<a href="home.html">
				<img id="TspLogoImage" src="Images/TSPlogo.jpeg" alt="Logo for The Symmetry Podcast." class="img-responsive"/>
			</a>
		</div>
		<div id="navChoices">
			<nav>
				<a class="btn" id="homeNavButton" href="home.html">home</a>
				<a class="btn" id="listenNavButton" href="listen.html">listen</a>
				<a class="btn" id="blogNavButton" href="blog.html">blog</a>
				<a class="btn" id="aboutNavButton" href="about.html">about</a>
				<a class="btn" id="contactNavButton" href="contact.html">contact</a>
			</nav>
		</div>`
	
	);
	
}

//used for testing purposes
function displayAllHeader() {
	
	$("header").html("");
	$("header").html(
	
		`<div id="logInBoxes">
			<nav>
				<a class="btn" id="TSPadminNavButton" href="TSPadmin.html">Admin Portal</a>
				<a class="btn" id="myAccountNavButton" href="myAccount.html">My Account</a>
				<a class="btn" id="logOutNavButton" onClick="logOutFromWebsite()">Log Out</a>
				<a class="btn" id="logInNavButton" href="logIn.html">Log In</a>
				<a class="btn" id="signUpNavButton" href="signUp.html">Sign Up</a>
			</nav>
		</div>
		<div id="TspLogo">
			<a href="home.html">
				<img id="TspLogoImage" src="Images/TSPlogo.jpeg" alt="Logo for The Symmetry Podcast." class="img-responsive"/>
			</a>
		</div>
		<div id="navChoices">
			<nav>
				<a class="btn" id="homeNavButton" href="home.html">home</a>
				<a class="btn" id="listenNavButton" href="listen.html">listen</a>
				<a class="btn" id="blogNavButton" href="blog.html">blog</a>
				<a class="btn" id="aboutNavButton" href="about.html">about</a>
				<a class="btn" id="contactNavButton" href="contact.html">contact</a>
			</nav>
		</div>`
	
	);
	
}

//FOOTER ----------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------
function addFooter() {
	
	$("footer").html(
		
		`<p>Copyright © 2020 The Symmetry Podcast</p>
		<p>Created by LeBoot Websites</p>`
	
	);
	
}



