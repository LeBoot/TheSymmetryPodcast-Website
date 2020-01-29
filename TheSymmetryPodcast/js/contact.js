/*
Name: contact.js, (TSP Capstone)
Author: Leboutillier
Date Created: 27 JAN 2020
Date Modified:
*/

/*
This document contains the code to verify and submit information from a "contact us" form.
1) When user click "edit" button, the display changes.
2) Link myAccount.html to backend.
*/

/* TO DO LIST
1) AJAX Call for Dropdown Box
2) Tie to DB for submission
*/


//VARIABLES ------------------------------------------------------------
var userName;
var userEmail;
var userMessage;
var userRegion;


//DOCUMENT READY -------------------------------------------------------
$(document).ready(function () {
	
	$("#nameArea").empty();
	$("#nameArea").html(`<input type="text" class="text full-width" placeholder="Name*" id="nameInput" required>`);
	
	$("#emailArea").empty();
	$("#emailArea").html(`<input type="email" class="text full-width" placeholder="Email*" id="emailInput" required>`);
	
	$("#messageArea").empty();
	$("#messageArea").html(`<textarea type="text" class="text full-width" placeholder="What's your message?*" id="messageInput" required></textarea>`);
	
	configureDropdown();

}) //End Document Ready

function configureDropdown() {
	
	$("#regionInput").html(
	
	`<option value="Default" disabled selected hidden>Where are you listening from?*</option>`
	
	);
	
	//call options from DB with AJAX call
	//for each... <option value="Africa">Africa</option>
	//$("#regionInput").append(selectionOption);
	
	//hard code for now...
	$("#regionInput").append(
	
		`<option value="1">Africa</option>
		<option value="2">Central Asia</option>
		<option value="3">Eastern Asia</option>
		<option value="4">Southern Asia</option>
		<option value="5">Caribbean</option>
		<option value="6">Europe</option>
		<option value="7">Middle East</option>
		<option value="8">North America</option>
		<option value="9">South America</option>
		<option value="10">Oceania</option>`
	
	);
	
}

function submitClicked() {
	
	const inputName = document.getElementById("nameInput").value;
	const inputEmail = document.getElementById("emailInput").value;
	const inputMessage = document.getElementById("messageInput").value;
	const inputRegion = document.getElementById("regionInput").value;
	
	if (
		(inputName.length < 2) ||
		(inputEmail.length < 3 ) || (!inputEmail.includes("@")) ||
		(inputMessage.length < 2) ||
		(inputRegion == "Default")
		) {
		alert ("You must fill out all fields!");
	} else {
		$("#nameInput").val("");
		$("#emailInput").val("");
		$("#messageInput").val("");
		
		userName = inputName;
		userEmail = inputEmail;
		userMessage = inputMessage;
		userRegion = inputRegion;
		
		//push to database via AJAX
		
		$("#contactForm").hide();
		$("#thankYouDiv").html(
		
			`<div id="thankYouPt1">Message Sent!</div>
			<div id="thankYouPt2">Thank You For Contacting Us.</div>`
		
		);
	}	
}

