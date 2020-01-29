/*
Name: myAccount.js, (TSP Capstone)
Author: Leboutillier
Date Created: 25 JAN 2020
Date Modified:
*/

/*
This document contains the code to make an interactive myAccount page.
1) When user click "edit" button, the display changes.
2) Link myAccount.html to backend.
*/

/* TO DO LIST
1) Load information from DB
2) Set variables with info from DB
3) Persist information after it has been saved
4) Configure log out button
6) Take care of drop-down box options
7) Tie Delete Function to BackEnd
*/


//VARIABLES ------------------------------------------------------------
const blockedOutPassword = "*******";
var firstName = "John";
var lastName = "Johnson";
var email = "JJ@website.com";
var username = "JohnnyJ";
var password = "JJRULES";
var region = "Europe";


//DOCUMENT READY -------------------------------------------------------
$(document).ready(function () {
	
	fillFields();

}) //End Document Ready


//FUNCTIONS CALLED FROM DOCUMENT READY ----------------------------------------------------
function fillFields() {
	$("#firstNameDisplay").html(firstName);
	$("#lastNameDisplay").html(lastName);
	$("#emailDisplay").html(email);
	$("#usernameDisplay").html(username);
	$("#passwordDisplay").html(blockedOutPassword);
	$("#regionDisplay").html(region);
}


//DELETE --------------------------------------------------------------------
function sendAlert() {
	var output;
	var input = prompt("To delete your account, please enter your password:", "");
	
	if (input == password) {
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
	
	
	//tie to back end
	
	$("#normalDisplay").hide();
	$("#deletedAccountDiv").removeClass("hiddenElement");
	
}


//EDIT/SAVE INFO ONCLICK FUNCTIONS ------------------------------------------------------------
function changeFirstNameFieldsFromEditToSave() {
	$("#firstNameDisplay").html(	
		`<input type="text" class="text full-width inputBox" placeholder="First Name" id="firstNameInput">`	
	);
		
	$("#firstNameEditButtonDiv").html(	
		`<button id="firstNameSaveButton" onClick="changeFirstNameFieldsFromSaveToEdit()">Save</button>`	
	);
}

function changeFirstNameFieldsFromSaveToEdit() {		
		//determine whether or not the user actually typed anything new
		var possibleNewData = document.getElementById("firstNameInput").value;
		
		//if input is null, keep the old input and display it
		if (possibleNewData == "") {
			$("#firstNameDisplay").html(	
				firstName
			);
		}
		else {
			//Will need to persist data
		
			firstName = possibleNewData;
			$("#firstNameDisplay").html(	
				firstName
			);
		
		}
		
		//convert "save" button to "edit" button
		$("#firstNameEditButtonDiv").html(
			`<button id="firstNameEditButton" onClick="changeFirstNameFieldsFromEditToSave()">Edit</button>`
		);
}

function changeLastNameFieldsFromEditToSave() {
	$("#lastNameDisplay").html(	
		`<input type="text" class="text full-width inputBox" placeholder="Last Name" id="lastNameInput">`	
	);
		
	$("#lastNameEditButtonDiv").html(	
		`<button id="lastNameSaveButton" onClick="changeLastNameFieldsFromSaveToEdit()">Save</button>`	
	);
}

function changeLastNameFieldsFromSaveToEdit() {		
		//determine whether or not the user actually typed anything new
		var possibleNewData = document.getElementById("lastNameInput").value;
		
		//if input is null, keep the old input and display it
		if (possibleNewData == "") {
			$("#lastNameDisplay").html(	
				lastName
			);
		}
		else {
			//Will need to persist data
		
			lastName = possibleNewData;
			$("#lastNameDisplay").html(	
				lastName
			);
		
		}
		
		//convert "save" button to "edit" button
		$("#lastNameEditButtonDiv").html(
			`<button id="lastNameEditButton" onClick="changeLastNameFieldsFromEditToSave()">Edit</button>`
		);
}

function changeEmailFieldsFromEditToSave() {
	$("#emailDisplay").html(	
		`<input type="text" class="text full-width inputBox" placeholder="Email" id="emailInput">`	
	);
		
	$("#emailEditButtonDiv").html(	
		`<button id="emailSaveButton" onClick="changeEmailFieldsFromSaveToEdit()">Save</button>`	
	);
}

function changeEmailFieldsFromSaveToEdit() {		
		//determine whether or not the user actually typed anything new
		var possibleNewData = document.getElementById("emailInput").value;
		
		//if input is null, keep the old input and display it
		if (possibleNewData == "") {
			$("#emailDisplay").html(	
				email
			);
		}
		else {
			//Will need to persist data
		
			email = possibleNewData;
			$("#emailDisplay").html(	
				email
			);
		
		}
		
		//convert "save" button to "edit" button
		$("#emailEditButtonDiv").html(
			`<button id="emailEditButton" onClick="changeEmailFieldsFromEditToSave()">Edit</button>`
		);
}

function changeUsernameFieldsFromEditToSave() {
	$("#usernameDisplay").html(	
		`<input type="text" class="text full-width inputBox" placeholder="Username" id="usernameInput">`	
	);
		
	$("#usernameButtonDiv").html(	
		`<button id="usernameSaveButton" onClick="changeUsernameFieldsFromSaveToEdit()">Save</button>`	
	);
}

function changeUsernameFieldsFromSaveToEdit() {		
		//determine whether or not the user actually typed anything new
		var possibleNewData = document.getElementById("usernameInput").value;
		
		//if input is null, keep the old input and display it
		if (possibleNewData == "") {
			$("#usernameDisplay").html(	
				username
			);
		}
		else {
			//Will need to persist data
		
			username = possibleNewData;
			$("#usernameDisplay").html(	
				username
			);
		
		}
		
		//convert "save" button to "edit" button
		$("#usernameButtonDiv").html(
			`<button id="usernameEditButton" onClick="changeUsernameFieldsFromEditToSave()">Edit</button>`
		);
}

function changePasswordFieldsFromEditToSave() {
	$("#passwordDisplay").html(	
		`<input type="text" class="text full-width inputBox" placeholder="Password" id="passwordInput">`	
	);
		
	$("#passwordButtonDiv").html(	
		`<button id="passwordSaveButton" onClick="changePasswordFieldsFromSaveToEdit()">Save</button>`	
	);
}

function changePasswordFieldsFromSaveToEdit() {		
		//determine whether or not the user actually typed anything new
		var possibleNewData = document.getElementById("passwordInput").value;
		
		//if input is null, keep the old input and display it
		if (possibleNewData == "") {
			$("#passwordDisplay").html(	
				password
			);
		}
		else {
			//Will need to persist data
		
			password = possibleNewData;
			$("#passwordDisplay").html(	
				password
			);
		
		}
		
		//convert "save" button to "edit" button
		$("#passwordButtonDiv").html(
			`<button id="passwordEditButton" onClick="changePasswordFieldsFromEditToSave()">Edit</button>`
		);
}

function changeRegionFieldsFromEditToSave() {
	$("#regionDisplay").html(	
		`<select id="regionInput">
			<option value="Africa">Africa</option>
			<option value="CentralAsia">Central Asia</option>
			<option value="EasternAsia">Eastern Asia</option>
			<option value="SouthernAsia">Southern Asia</option>
			<option value="Caribbean">Caribbean</option>
			<option value="Europe">Europe</option>
			<option value="MiddleEast">Middle East</option>
			<option value="NorthAmerica">North America</option>
			<option value="SouthAmerica">South America</option>
			<option value="Oceania">Oceania</option>
		</select>`	
	);
		
	$("#regionButtonDiv").html(	
		`<button id="regionSaveButton" onClick="changeRegionFieldsFromSaveToEdit()">Save</button>`	
	);
}

function changeRegionFieldsFromSaveToEdit() {		
		//determine whether or not the user actually typed anything new
		var possibleNewData = document.getElementById("regionInput").value;
		
		//if input is null, keep the old input and display it
		if (possibleNewData == "") {
			$("#regionDisplay").html(	
				region
			);
		}
		else {
			//Will need to persist data
		
			region = possibleNewData;
			$("#regionDisplay").html(	
				region
			);
		
		}
		
		//convert "save" button to "edit" button
		$("#regionButtonDiv").html(
			`<button id="regionEditButton" onClick="changeRegionFieldsFromEditToSave()">Edit</button>`
		);
}

function toggleDisplayPassword() {
	var whatDoesButtonSay = $("#passwordDisplayButton").html();
	
	if (whatDoesButtonSay == "Show") {
		$("#passwordDisplayButton").html("Hide");
		$("#passwordDisplay").html(password);
	}
	else {
		$("#passwordDisplayButton").html("Show");
		$("#passwordDisplay").html(blockedOutPassword);
	}
}



