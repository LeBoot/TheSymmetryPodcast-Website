/*
Name: myAccount.js, (TSP Capstone)
Author: Leboutillier
Date Created: 25 JAN 2020
Date Modified: 7 FEB 2020
*/

/*
This document contains the code to make an interactive myAccount page.
*/

//VARIABLES AND CONSTANTS ----------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------
const JSblockedOutPassword = "*******";

//filled from page
var JSaccountNumber;

//filled from AJAX (GET): These never change except when the AJAX GET is called
var JSfirstName;
var JSlastName;
var JSemail;
var JSusername;
var JSpassword;
var JSregionID;
var	JSregionName;
var JSaccountTypeId;

//filled from GET AJAX, then fields, then (if error) from AJAX (POST)
//these change with user input, but are reset if the AJAX (POST) fails
var JSfirstNameP;
var JSlastNameP;
var JSemailP;
var JSusernameP;
var JSpasswordP;
var JSregionIDP;


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
	setTimeout(function(){
		JSaccountNumber = $(".accountIdDiv").text();
		prepForAJAXcall();
	}, 500);
	
}) //End Document Ready


//LOAD ACCOUNT INTO FIELDS ----------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------

function prepForAJAXcall() {
	
	emptyAllFields();
	$("#pageLoadingDiv").addClass("hiddenElement");
	$("#normalDisplay").removeClass("hiddenElement");
	
	//if the account number hasn't been obtained yet, try again; if it fails this time, the AJAX call will
		//error and display as much
	if (JSaccountNumber == null) {
		setTimeout(function(){
			JSaccountNumber = $(".accountIdDiv").text();
		}, 500);
	}
	
	//verify account number is not "-1".  That would mean that no one is logged in
	if (JSaccountNumber == -1) {
		window.location.href = 'logIn.html';
		return false;
	} else {
		AJAXcallForAccount();
	}	
	
}

function AJAXcallForAccount() {
	
	$.ajax({
        type: 'GET',
        url: 'http://localhost:8080/account/get/' + JSaccountNumber,
        success: function(account) {			
			//these only ever are set from this success function
			JSaccountNumber = account.accountnumber;
			JSfirstName = account.firstName;
			JSlastName = account.lastName;
			JSemail = account.email;
			JSusername = account.username;
			JSpassword = account.password;
			JSregionID = account.region.regionid;
			JSregionName = account.region.regionName;
			JSaccountTypeId = account.accountType.accounttypeid;
			
			//these can change with user input, but will be reset if POST fails
			JSfirstNameP = JSfirstName;
			JSlastNameP = JSlastName;
			JSemailP = JSemail;
			JSusernameP = JSusername;
			JSpasswordP = JSpassword;
			JSregionIDP = JSregionID;
			
			fillAllFields();
        },
        error: function (jqXHR, textStatus, errorThrown) {
			addToErrorDiv("Failure to load account from database.");
        }
    });

}

function fillAllFields() {
	
	$("#firstNameDisplay").text(JSfirstName);
	$("#lastNameDisplay").text(JSlastName);
	$("#emailDisplay").text(JSemail);
	$("#usernameDisplay").text(JSusername);
	$("#passwordDisplay").text(JSblockedOutPassword);
	$("#regionDisplay").text(JSregionName);
	
}

function emptyAllFields() {
	
	$("#firstNameDisplay").empty();
	$("#lastNameDisplay").empty();
	$("#emailDisplay").empty();
	$("#usernameDisplay").empty();
	$("#passwordDisplay").empty();
	$("#regionDisplay").empty();
	
}


//DELETE ----------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------
function sendAlert() {
	var output;
	var input = prompt("To delete your account, please enter your password:", "");
	
	if (input == JSpassword) {
		deleteAccount();
	}
	else if ((input == null) || (input == "")) {
		return false;
	}
	else {
		alert("Incorrect Password");
	}
}

function deleteAccount() {
	
	$.ajax({
		type: 'POST',
		url: 'http://localhost:8080/account/deactivate',
		data: JSON.stringify({
			accountId : JSaccountNumber,
			firstName : JSfirstName,
			lastName : JSlastName,
			email : JSemail,
			username : JSusername,
			password : JSpassword,
			regionId : JSregionID,
			accountTypeId : 3
		}),
		headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
		success: function() {
			$("#normalDisplay").hide();
			$("#deletedAccountDiv").removeClass("hiddenElement");
			alert("We're sorry to see you go.  You're account has been successfully deleted.");
			window.location.href = 'home.html';
		},
		error: function(jqXHR, textStatus, errorThrown) {
			addToErrorDiv("That's weird.  We're having some trouble deleting your account.");
        }
	});
	
}


//EDIT/SAVE INFO ONCLICK FUNCTIONS --------------------------------------------------------------
//-----------------------------------------------------------------------------------------------

function AJAXCallToEditAccount() {
	
	emptyErrorDiv();	
	
	$.ajax({
		type: 'POST',
		url: 'http://localhost:8080/account/edit',
		data: JSON.stringify({
			accountId : JSaccountNumber,
			firstName : JSfirstNameP,
			lastName : JSlastNameP,
			email : JSemailP,
			username : JSusernameP,
			password : JSpasswordP,
			regionId : JSregionIDP,
			accountTypeId : JSaccountTypeId
		}),
		headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
		success: function() {
			prepForAJAXcall();
		},
		error: function(jqXHR, textStatus, errorThrown) {
			//reset variables
			JSfirstNameP = JSfirstName;
			JSlastNameP = JSlastName;
			JSemailP = JSemail;
			JSusernameP = JSusername;
			JSpasswordP = JSpassword;
			JSregionIDP = JSregionID;
			
			//display errors
			addToErrorDiv("Whoops.  Those changes were not valid.");
			addToErrorDiv("If you were attempting to change your username, you may have attempted one that is already taken.");
        }
	});
}


function changeFirstNameFieldsFromEditToSave() {
	$("#firstNameDisplay").html(	
		`<input type="text" class="text full-width inputBox" placeholder="First Name" id="firstNameInput" maxlength="25">`	
	);
	
	$("#firstNameInput").val(JSfirstName);
		
	$("#firstNameEditButtonDiv").html(	
		`<button id="firstNameSaveButton" onClick="changeFirstNameFieldsFromSaveToEdit()">Save</button>`	
	);
}

function changeFirstNameFieldsFromSaveToEdit() {		
		//gather from field
		var possibleNewData = document.getElementById("firstNameInput").value;
		
		//reset display
		$("#firstNameDisplay").text(JSfirstName);
		$("#firstNameEditButtonDiv").html(
			`<button id="firstNameEditButton" onClick="changeFirstNameFieldsFromEditToSave()">Edit</button>`
		);
		
		//if anything was entered, attempt AJAX call
		if ((possibleNewData != "") && (possibleNewData != null)) {
			if ((possibleNewData.length < 2) || (possibleNewData.length > 25)) {
				addToErrorDiv("First name must be between 2 and 25 characters.");
			}
			else {
				//prepare for and make AJAX call
				JSfirstNameP = possibleNewData;
				AJAXCallToEditAccount();
			}
		}
		
}

function changeLastNameFieldsFromEditToSave() {
	$("#lastNameDisplay").html(	
		`<input type="text" class="text full-width inputBox" placeholder="Last Name" id="lastNameInput" maxlength="25">`	
	);
		
	$("#lastNameInput").val(JSlastName);	
		
	$("#lastNameEditButtonDiv").html(	
		`<button id="lastNameSaveButton" onClick="changeLastNameFieldsFromSaveToEdit()">Save</button>`	
	);
}

function changeLastNameFieldsFromSaveToEdit() {		
		//gather from field
		var possibleNewData = document.getElementById("lastNameInput").value;
		
		//reset display
		$("#lastNameDisplay").text(JSlastName);
		$("#lastNameEditButtonDiv").html(
			`<button id="lastNameEditButton" onClick="changeLastNameFieldsFromEditToSave()">Edit</button>`
		);
		
		//if anything was entered, attempt AJAX call
		if ((possibleNewData != "") && (possibleNewData != null)) {
			if ((possibleNewData.length < 2) || (possibleNewData.length > 25)) {
				addToErrorDiv("Last name must be between 2 and 25 characters.");
			}
			else {				
				//prepare for and make AJAX call
				JSlastNameP = possibleNewData;
				AJAXCallToEditAccount();
			}
		}

}

function changeEmailFieldsFromEditToSave() {
	$("#emailDisplay").html(	
		`<input type="text" class="text full-width inputBox" placeholder="Email" id="emailInput" maxlength="40">`	
	);
		
	$("#emailInput").val(JSemail);
	
	$("#emailEditButtonDiv").html(	
		`<button id="emailSaveButton" onClick="changeEmailFieldsFromSaveToEdit()">Save</button>`	
	);
}

function changeEmailFieldsFromSaveToEdit() {		
		//gather from field
		var possibleNewData = document.getElementById("emailInput").value;
		
		//reset display
		$("#emailDisplay").text(JSemail);
		$("#emailEditButtonDiv").html(
			`<button id="emailEditButton" onClick="changeEmailFieldsFromEditToSave()">Edit</button>`
		);
			
		//if anything was entered, attempt AJAX call
		if ((possibleNewData != "") && (possibleNewData != null)) {
			if ((possibleNewData.length < 5) || (possibleNewData.length > 40) || (!possibleNewData.includes("@")) || (!possibleNewData.includes("."))) {
				addToErrorDiv("Email must be valid and between 4 and 40 characters.");
			}
			else {
				//prepare for and make AJAX call
				JSemailP = possibleNewData;
				AJAXCallToEditAccount();
			}
		}
		
}

function changeUsernameFieldsFromEditToSave() {
	$("#usernameDisplay").html(	
		`<input type="text" class="text full-width inputBox" placeholder="Username" id="usernameInput" maxlenght="15">`	
	);
		
	$("#usernameInput").val(JSusername);
		
	$("#usernameButtonDiv").html(	
		`<button id="usernameSaveButton" onClick="changeUsernameFieldsFromSaveToEdit()">Save</button>`	
	);
}

function changeUsernameFieldsFromSaveToEdit() {		
		//gather from the field
		var possibleNewData = document.getElementById("usernameInput").value;
		
		//reset display
		$("#usernameDisplay").text(JSusername);
		$("#usernameButtonDiv").html(
			`<button id="usernameEditButton" onClick="changeUsernameFieldsFromEditToSave()">Edit</button>`
		);
		
		//if anything was entered, attempt AJAX call
		if ((possibleNewData != "") && (possibleNewData != null)) {
			if ((possibleNewData.length < 2) || (possibleNewData.length > 20)) {
				addToErrorDiv("Username must be between 2 and 15 characters.");
			}
			else {
				//prepare for and make AJAX call
				JSusernameP = possibleNewData;
				AJAXCallToEditAccount();
			}
		}
		
}

function changePasswordFieldsFromEditToSave() {
	$("#passwordDisplay").html(	
		`<input type="text" class="text full-width inputBox" placeholder="Password" id="passwordInput" maxlength="20">`	
	);
		
	$("#passwordInput").val(JSpassword);	
		
	$("#passwordButtonDiv").html(	
		`<button id="passwordSaveButton" onClick="changePasswordFieldsFromSaveToEdit()">Save</button>`	
	);
}

function changePasswordFieldsFromSaveToEdit() {		
		//gather from the field
		var possibleNewData = document.getElementById("passwordInput").value;
		
		//reset display
		$("#passwordDisplayButton").html("Show");
		$("#passwordDisplay").html(JSblockedOutPassword);
		$("#passwordButtonDiv").html(
			`<button id="passwordEditButton" onClick="changePasswordFieldsFromEditToSave()">Edit</button>`
		);

		//if anything was entered, attempt AJAX call
		if ((possibleNewData != "") && (possibleNewData != null)) {
			if ((possibleNewData.length < 2) || (possibleNewData.length > 20)) {
				addToErrorDiv("Password must be between 2 and 20 characters.");
			}
			else {
				//prepare for and make AJAX call
				JSpasswordP = possibleNewData;
				AJAXCallToEditAccount();
			}
		}
		
}

function changeRegionFieldsFromEditToSave() {
	$("#regionDisplay").html(	
		`<select id="regionInput">
		</select>`	
	);
		
	$("#regionButtonDiv").html(	
		`<button id="regionSaveButton" onClick="changeRegionFieldsFromSaveToEdit()">Save</button>`	
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
				var regionsInput;
				if (region.regionid == JSregionID) {
					var regionsInput = '<option value="' + region.regionid + '" selected>' + region.regionName + '</option>'
				} else {
					var regionsInput = '<option value="' + region.regionid + '">' + region.regionName + '</option>'
				}
				regionsDiv.append(regionsInput);
            })
        },
        error: function (jqXHR, textStatus, errorThrown) {
            addToErrorDiv("Failure to load regions from database into dropdown box.");
        }
    });

}

function changeRegionFieldsFromSaveToEdit() {		
		//gather data from the field
		var possibleNewData = document.getElementById("regionInput").value;
		
		//reset display
		$("#regionDisplay").text(JSregionName);
		$("#regionButtonDiv").html(
			`<button id="regionEditButton" onClick="changeRegionFieldsFromEditToSave()">Edit</button>`
		);
		
		JSregionIDP = possibleNewData;
		
		AJAXCallToEditAccount();

}

function toggleDisplayPassword() {
	var whatDoesButtonSay = $("#passwordDisplayButton").html();
	
	if (whatDoesButtonSay == "Show") {
		$("#passwordDisplayButton").html("Hide");
		$("#passwordDisplay").html(JSpassword);
	}
	else {
		$("#passwordDisplayButton").html("Show");
		$("#passwordDisplay").html(JSblockedOutPassword);
	}
}



