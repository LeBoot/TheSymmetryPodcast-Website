/*
Name: logIn.js, (TSP Capstone)
Author: Leboutillier
Date Created: 27 JAN 2020
Date Modified:
*/

/*
This document contains the code to verify and submit information from the logIn page.
Additionally, it modifies the session-status-div
*/

/* TO DO LIST
1) 
*/


//VARIABLES ------------------------------------------------------------



//DOCUMENT READY -------------------------------------------------------
$(document).ready(function () {	

}) //End Document Ready

function logInRequested() {
	
	var attemptedUsername = document.getElementById("userNameInput").value;
	var attemptedPassword = document.getElementById("passwordInput").value;
	
	
	//AJAX Post call: send the attempted values; return user (with status) or something different if the user is not found
	
	
	if (userType == 1) {
		$(".sessionStatusDiv").html("LIN");
	} else if (userType == 2) {
		$(".sessionStatusDiv").html("LAM");
	} else {
		//message, sorry--- not found
	}	
	
}