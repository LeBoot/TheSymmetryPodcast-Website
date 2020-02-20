/*
Name: logOff.js, (TSP Capstone)
Author: Leboutillier
Date Created: 5 FEB 2020
Date Modified: 5 FEB 2020
*/

/*
This script handles calls from log-out/sign-out buttons.
It makes an AJAX GET call to change the session status to "USER" (default); then, it redirects to the home.html.
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

})

function logOutFromWebsite() {

	emptyErrorDiv();

	$.ajax({
        type: 'GET',
        url: 'http://localhost:8080/session-status/logout',
        success: function(String) {
			window.location.href = 'home';
        },
        error: function (jqXHR, textStatus, errorThrown) {
			addToErrorDiv("Unable to contact the back-end to log out.");
        }
    });	
	
}