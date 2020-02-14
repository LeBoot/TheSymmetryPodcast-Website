/*
Name: signUp.js, (TSP Capstone)
Author: Leboutillier
Date Created: 7 FEB 2020
Date Modified: 7 FEB 2020
*/

/*
1) This document contains the script to verify and submit information from the sign-up page.
2) If an account is successfully created, the session status is changed and the user is redirected to the home page
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
	
	configureDropdown();

}) //End Document Ready


//CONFIGURE DROPDOWN ----------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------
function configureDropdown() {
	
	$("#regionSelect").html(
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
            var regionsDiv = $('#regionSelect');

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

//HANDLE CREATION ATTMEPT -----------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------

function createNewAccount() {
	
	emptyErrorDiv();
	
	var proceedWithAjaxCall = true;
	
	const inputFirst = $("#firstNameInput").val();
	const inputLast = $("#lastNameInput").val();
	const inputEmail = $("#emailInput").val();
	const inputUsername = $("#userNameInput").val();
	const inputPass = $("#passwordInput").val();
	const inputRegionId = document.getElementById("regionSelect").value;
	// const inputRegionId = $("#regionSelect").val();

	if ((inputFirst.length < 2) || (inputFirst.length > 24)) {
		addToErrorDiv("First name must be between 2 and 25 characters");
		proceedWithAjaxCall = false;
	}
	if ((inputLast.length < 2) || (inputLast.length > 24)) {
		addToErrorDiv("Last name must be between 2 and 25 characters");
		proceedWithAjaxCall = false;
	}
	if ((inputEmail.length < 2) || (inputEmail.length > 40) || (!inputEmail.includes(".")) || (!inputEmail.includes("@"))) {
		addToErrorDiv("Email must be valid and contain between 2 and 40 characters");
		proceedWithAjaxCall = false;
	}
	if ((inputUsername.length < 2) || (inputUsername.length > 15)) {
		addToErrorDiv("Username must be between 2 and 15 characters");
		proceedWithAjaxCall = false;
	}
	if ((inputPass.length < 2) || (inputPass.length > 20)) {
		addToErrorDiv("Password must be between 2 and 20 characters");
		proceedWithAjaxCall = false;
	}
	if (inputRegionId == "Default") {
		proceedWithAjaxCall = false;
		addToErrorDiv("You must select a region.");
	}
	
	if (proceedWithAjaxCall == true) {
		AJAXcallToCreateAccount(inputFirst, inputLast, inputEmail, inputUsername, inputPass, inputRegionId);
	}
}

function AJAXcallToCreateAccount(inputFirst, inputLast, inputEmail, inputUsername, inputPass, inputRegionId) {
	
	$.ajax({
		type: 'POST',
		url: 'http://localhost:8080/account/create',
		data: JSON.stringify({
			firstName : inputFirst,
			lastName : inputLast,
			email : inputEmail,
			username : inputUsername,
			password : inputPass,
			regionId : inputRegionId,
			accountTypeId : 1
		}),
		headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
		success: function() {
			emptyErrorDiv();
			AJAXCallToLogin(inputUsername, inputPass);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			addToErrorDiv("That account cannot be created.  Most likely, that username is already taken.  Feel free to try again!");
        }
	});
	
}

function AJAXCallToLogin(inputU, inputP) {
	
	$.ajax({
		type: 'POST',
		url: 'http://localhost:8080/session-status/login',
		data: JSON.stringify({
			inputUsername: inputU,
			inputPassword : inputP
		}),
		headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
		success: function() {
			emptyErrorDiv();
			window.location.href = 'home';
			alert("Congratulations on creating a new account.  You're logged in and set to go!");
		},
		error: function(jqXHR, textStatus, errorThrown) {
			addToErrorDiv("You're all signed up, but not logged in yet.");
			window.location.href = "logIn";
        }
	});
	
}