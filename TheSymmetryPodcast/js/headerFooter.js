/*
Name: headerFooter.js, (TSP Capstone)
Author: Leboutillier
Date Created: 24 JAN 2020
Date Modified:
*/

/*
At Document Ready, append pages' <header> and <footer> tags with
the appropriate html code
*/


//DOCUMENT READY -------------------------------------------------------
$(document).ready(function () {
	
	//header
	addHeader();

	//footer
	addFooter();

})

//HEADER ---------------------------------------------------------------
function addHeader() {

	$("header").html(
	
		`<div id="logInBoxes">
			<nav>
				<a class="btn" id="TSPadminNavButton" href="TSPadmin.html">Admin Portal</a>
				<a class="btn" id="myAccountNavButton" href="myAccount.html">My Account</a>
				<a class="btn" id="logOutNavButton">Log Out</a>
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

//FOOTER ---------------------------------------------------------------
function addFooter() {
	
	$("footer").html(
		
		`<p>Copyright © 2020 The Symmetry Podcast</p>
		<p>Created by LeBoot Websites</p>`
	
	);
	
}



