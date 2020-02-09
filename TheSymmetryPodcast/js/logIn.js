/*
Name: logIn.js, (TSP Capstone)
Author: Leboutillier
Date Created: 27 JAN 2020
Date Modified: 6 FEB 2020
*/

/*
1) This document contains the code to verify and submit information from the log-in page.
2) If call is successful, it redirects to the home page

*/

/* TO DO LIST
1) POST method (success) not working on the back-end
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

	emptyErrorDiv();

}) //End Document Ready



//HANDLE LOGIN ATTMEPT --------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------
function logInRequested() {
	
	emptyErrorDiv();
	
	var proceedWithAjaxCall = true;
	
	var attemptedUsername = document.getElementById("userNameInput").value;
	var attemptedPassword = document.getElementById("passwordInput").value;
	
	if ((attemptedUsername.length < 2) || (attemptedUsername.length > 15)) {
		addToErrorDiv("A valid username must be between 2 and 15 characters");
		proceedWithAjaxCall = false;
	}
	if ((attemptedPassword.length < 2) || (attemptedPassword.length > 20)) {
		addToErrorDiv("A valid password must be between 2 and 20 characters");
		proceedWithAjaxCall = false;
	}
	
	if (proceedWithAjaxCall == true) {
		AJAXcallToVerifyInput(attemptedUsername, attemptedPassword);
	}
	
}

function AJAXcallToVerifyInput(attemptedUsername, attemptedPassword) {
	
	$.ajax({
		type: 'POST',
		url: 'http://localhost:8080/session-status/login',
		data: JSON.stringify({
			inputUsername: attemptedUsername,
			inputPassword : attemptedPassword
		}),
		headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
		success: function() {
			emptyErrorDiv();
			window.location.href = 'home.html';
		},
		error: function(jqXHR, textStatus, errorThrown) {
			addToErrorDiv("That username, password, or both are incorrect.");
        }
	});
	
}