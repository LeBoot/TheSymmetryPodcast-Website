/*
Name: contact.js, (TSP Capstone)
Author: Leboutillier
Date Created: 27 JAN 2020
Date Modified: 6 FEB 2020
*/

/*
1) This document contains the code to verify and submit information from a "contact us" form.
2) When user click "edit" button, the display changes.
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
	
	$("#nameArea").empty();
	$("#nameArea").html(`<input type="text" class="text full-width" placeholder="Name*" id="nameInput" required>`);
	
	$("#emailArea").empty();
	$("#emailArea").html(`<input type="email" class="text full-width" placeholder="Email*" id="emailInput" required>`);
	
	$("#messageArea").empty();
	$("#messageArea").html(`<textarea type="text" class="text full-width" placeholder="What's your message?*" id="messageInput" required></textarea>`);
	
	configureDropdown();
	

}) //End Document Ready


//CONFIGURE DROPDOWN ----------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------
function configureDropdown() {
	
	$("#regionInput").html(
		`<option value="Default" disabled selected hidden>Where are you listening from?*</option>`
	);
	
	configureDropdownAjaxCall();
	
}

function configureDropdownAjaxCall() {

	$.ajax({
        type: 'GET',
        url: 'http://localhost:8080/community/regions',
        success: function(regionArray) {
            //get a reference to the regions dropdown
            var regionsDiv = $('#regionInput');

            //go through each of the returned values and append
            $.each(regionArray, function(index, region) {
                var regionsInput = '<option value="' + region.regionid + '">' + region.regionName + '</option>'
				regionsDiv.append(regionsInput);
            })
        },
        error: function (jqXHR, textStatus, errorThrown) {
            addToErrorDiv("Failure to load regions from database into dropdown box.");
        }
    });

}


//SUBMIT MESSAGE --------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------

function submitClicked() {
	
	const inputName = document.getElementById("nameInput").value;
	const inputEmail = document.getElementById("emailInput").value;
	const inputMessage = document.getElementById("messageInput").value;
	const inputRegion = document.getElementById("regionInput").value;
	var isInputValid = true;
	
	emptyErrorDiv();
	
	if ((inputName.length < 2) || (inputName.length > 49)) {
		isInputValid = false;
		addToErrorDiv("You must enter a name that between 2 and 50 characters.");
	}
	if ((inputEmail.length < 3 ) || (!inputEmail.includes("@")) || (inputEmail.length > 49)) {
		isInputValid = false;
		addToErrorDiv("You must enter a valid email that between 3 and 50 characters.");
	}
	if ((inputMessage.length < 2) || (inputMessage.length > 5000)) {
		isInputValid = false;
		addToErrorDiv("You message must be between 2 and 5000 characters.");
	}
	if (inputRegion == "Default") {
		isInputValid = false;
		addToErrorDiv("You must select a region.");
	}
	
	if (
		(inputName.includes("<")) || inputName.includes(">") || inputName.includes("\`") || inputName.includes("\"") || inputName.includes("\'") || inputName.includes("\\") ||
		(inputEmail.includes("<")) || inputEmail.includes(">") || inputEmail.includes("\`") || inputEmail.includes("\"") || inputEmail.includes("\'") || inputEmail.includes("\\") ||
		(inputMessage.includes("<")) || inputMessage.includes(">") || inputMessage.includes("\`") || inputMessage.includes("\"") || inputMessage.includes("\'") || inputMessage.includes("\\")
		) {
		isInputValid = false;
		addToErrorDiv("Sorry, but your input cannot contain characters such as <, >, \`, \", \', \\, etc.");
	}

	if (isInputValid == true) {
		$("#nameInput").val("");
		$("#emailInput").val("");
		$("#messageInput").val("");
		
		submitMessageAJAXCall(inputName, inputEmail, inputMessage, inputRegion);
	}
	else {
		return false;
	}
	
}

function submitMessageAJAXCall(inputName, inputEmail, inputMessage, inputRegion) {
	
	$.ajax({
		type: 'POST',
		url: 'http://localhost:8080/contact/new-message',
		data: JSON.stringify({
			rbName: inputName,
			rbEmail: inputEmail,
			rbMessageText: inputMessage,
			rbRegionId: inputRegion
		}),
		headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
		success: function() {
			displayThankYouMessage();
		},
		error: function(jqXHR, textStatus, errorThrown) {
            addToErrorDiv('Whoops!  Something happened on our end, and unfortunately that message did not go through.');
        }
	});

}

function displayThankYouMessage() {

	$("#contactForm").hide();
	$("#thankYouDiv").html(	
		`<div id="thankYouPt1">Message Sent!</div>
		<div id="thankYouPt2">Thank You For Contacting Us.</div>`
	);

}








