/*
Name: TSPadmin.js, (TSP Capstone)
Author: Leboutillier
Date Created: 26 JAN 2020
Date Modified:
*/

/*
This document contains the code to make an interactive TSPadmin page.
1) There are three main options: "view accounts", "view messages", and "view mp3s"
	- each option will present the user with a new group of selections
*/

/* TO DO LIST
1)

*/


//DOCUMENT READY -------------------------------------------------------
$(document).ready(function () {
	
	hideAllDivs();

}) //End Document Ready


//TOGGLE PRIMARY OPTIONS -----------------------------------------------
function hideAllDivs() {
	$("#messagesDiv").hide();
	$("#mp3sDiv").hide();
	$("#accountsDiv").hide();
}	

function toggleAccountsDiv() {
	$("#accountsDiv").toggle();
	$("#messagesDiv").hide();
	$("#mp3sDiv").hide();
	$("#extraSpace").show();
	
	$("#accountChoiceDiv").html("");
}

function toggleMessagesDiv() {
	$("#accountsDiv").hide();
	$("#messagesDiv").toggle();
	$("#mp3sDiv").hide();
	$("#extraSpace").show();
}

function toggleMp3sDiv() {
	$("#accountsDiv").hide();
	$("#messagesDiv").hide();
	$("#mp3sDiv").toggle();
	$("#extraSpace").show();
}


//TOGGLE ACCOUNT OPTIONS -----------------------------------------------------

function viewAccounts() {	
	
	$("#extraSpace").hide();
	
	$("#accountChoiceDiv").html(
	
		`<h3 class="accountChoiceHeader">View All Accounts</h3>
		<table id="allAccountsTable">
			<tr>
				<th width="5%">Number</th>
				<th width="10%">First Name</th>
				<th width="10%">Last Name</th>
				<th width="20%">Email</th>
				<th width="10%">Username</th>
				<th width="10%">Password</th>
				<th width="7%">Start Year</th>
				<th width="10%">Region</th>
				<th width="6%">Type</th>
				<th width="6%"></th>
				<th width="6%"></th>
			</tr>

		</table>`
	
	);
	
	appendAccountTable();
	
}

function appendAccountTable() {
	//dummy variables that need to be auto-generated when back-end is connected
	var accountNum1 = "1";
	var firstName1 = "Benjamin";
	var lastName1 = "Leboutillier";
	var email1 = "benleboot@website.com";
	var username1 = "benleboot";
	var password1 = "password1";
	var year1 = "2018";
	var region1 = "North America";
	var type1 = "1";
	
	$("#allAccountsTable").append(
	
		`<tr>
			<td>` + accountNum1 + `</td>
			<td>` + firstName1 + `</td>
			<td>` + lastName1 + `</td>
			<td>` + email1 + `</td>
			<td>` + username1 + `</td>
			<td>` + password1 + `</td>
			<td>` + year1 + `</td>
			<td>` + region1 + `</td>
			<td>` + type1 + `</td>
			<td><button type="button" class="listAllAccountsEditButton" onClick="editAccountView(` + accountNum1 + `)">Edit</button></td>
			<td><button type="button" class="listAllAccountsDeleteButton" onClick="deleteAccount(` + accountNum1 + `)">Delete</button></td>
		</tr>`
	
	);
}

function createAccountView() {
	
	$("#extraSpace").hide();
	
	$("#accountChoiceDiv").html(
	
		`<h3 class="accountChoiceHeader">Create An Account</h3>
		<form id="createAccountForm">
			<div>
				<div id="firstNameArea">
					<input type="text" class="text full-width" placeholder="First Name*" id="firstNameInput" required>
				</div>
				<div id="lastNameArea">
					<input type="text" class="text full-width" placeholder="Last Name*" id="lastNameInput" required>
				</div>
				<div id="emailArea">
					<input type="email" class="text full-width" placeholder="Email*" id="emailInput" required>
				</div>
				<div id="userNameArea">
					<input type="text" class="text full-width" placeholder="Username*" id="userNameInput" required>
				</div>
				<div id="passwordArea">
					<input type="text" class="text full-width" placeholder="Password*" id="passwordInput" required>
				</div>
				<div id="accountTypeArea">
					<input type="text" class="text full-width" placeholder="Account Type" id="accountTypeInput">
				</div>
				<div id="regionArea">
					<select id="regionSelect" id="regionInput">
						<option value="Default" disabled selected hidden>Region?</option>
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
					</select>
				</div>
			</div>
			<div style="text-align: center;">
				<button type="button" class="accountOptionsBtn" id="createAccountButton" onClick="createAccountRequest()">Create Account</button>
			</div>
		</form>`
	
	);
}

function addEditAccountHeader() {
	$("#accountChoiceDiv").html(`<h3 class="accountChoiceHeader">Edit An Account</h3>`);
}

function askEditAccountNumber() {
	
	$("#accountChoiceDiv").html("");
	
	addEditAccountHeader();
	
	$("#accountChoiceDiv").append(
	
		`<div id="editFormDiv">
			<form id="accountNumberToEditQuestionForm">
				<div>
					<label style="margin-right: 5px;">Account Number: </label>
					<input type="text" id="accountNumberToEdit"></input>
				</div>
				<div>
					<button type="button" class="editAccountEditCancelBtn" id="searchForAccountToEditBtn" onClick="getAccountToEditId()">Edit</button>
					<button type="button" class="editAccountEditCancelBtn" id="cancelSearchForAccountToEditBtn" onClick="goBackToViewAccounts()">Cancel</button>
				</div>
			</form>
		</div>`
	
	);
}

function getAccountToEditId() {
	const accountToView = 2;
	//const accountToView = $("#accountNumberToEdit").value();
	editAccountView(accountToView);
}

function goBackToViewAccounts() {
	$("#accountsDiv").hide();
	toggleAccountsDiv();
	viewAccounts();
}

//TOGGLE MESSAGE OPTIONS -----------------------------------------------------

function viewAllMessages() {
	prepMessageChoiceDiv("View All Messages");
	appendMessageTable();
}

function viewUnaddressedMessages() {
	prepMessageChoiceDiv("View Unaddressed Messages");
	appendMessageTable(1);

}

function viewAddressedMessages() {
	prepMessageChoiceDiv("View Addressed Messages");
	appendMessageTable(2);
}

function viewTabledMessages() {
	prepMessageChoiceDiv("View Tabled Messages");
	appendMessageTable(3);
}

function prepMessageChoiceDiv(prompt) {
	$("#extraSpace").hide();
	$("messageChoiceDiv").html("");
	$("#messageChoiceDiv").html(
	
		`<h3 class="messagesChoiceHeader">` + prompt + `</h3>
		<table id="messagesTable">
			<tr>
				<th width="5%">ID</th>
				<th width="22%">Name</th>
				<th width="22%">Email</th>
				<th width="12%">Region</th>
				<th width="15%">TimeStamp</th>
				<th width="14%">Status</th>
				<th width="10%"></th>
			</tr>
		</table>`
	
	);
}

function appendMessageTable(statusIdentification) {
	var dummyID = 1;
	var dummyName = "John Johnson";
	var dummyEmail = "JJohnson@website.com";
	var dummyRegion = "North America";
	var dummyTimestamp = "2019-05-05 08:12:12";
	var dummyStatus = "unaddressed";	
	
	/*
	If statusIdentification == 0 --> AJAX call for all.
	If statusIdentification == 1 --> AJAX call for unaddressed.
	If statusIdentification == 2 --> AJAX call for addressed.
	If statusIdentification == 3 --> AJAX call for tabled.
	
	*/
	
	
	$("#messagesTable").append(
	
		`<tr>
			<td>` + dummyID + `</td>
			<td>` + dummyName + `</td>
			<td>` + dummyEmail + `</td>
			<td>` + dummyRegion + `</td>
			<td>` + dummyTimestamp + `</td>
			<td>` + dummyStatus + `</td>
			<td><button type="button" class="viewMessageButton" onClick="viewMessage(` + dummyID + `, ` + statusIdentification + `)">View</button></td>
		</tr>`
	
	);
	
}

function viewMessage(thisMessageID, statusIdentification) {
	
	$("#messageChoiceDiv").html("");
	
	$("#messageChoiceDiv").html(
	
		`<h3 class="messagesChoiceHeader">View A Message</h3>				
		<div id="individualMessageDisplayDiv">
			<div class="messageDetails"><b>Message ID: </b>2</div>
			<div class="messageDetails"><b>Name: </b>Jack Jackson</div>
			<div class="messageDetails"><b>Email: </b>IAmJack@longwebsitenamehere.com</div>
			<div class="messageDetails"><b>Region: </b>South America</div>
			<div class="messageDetails"><b>Timestamp: </b>2019-09-12 12:12:12</div>
			<div class="messageDetails"><b>Status: </b>Unaddressed</div>
			<div class="messageNotesText"><b>Notes: </b> some text here </div>
			<div class="messageText"><b>Message: </b> some text here </div>
			<div class="flex-container" id="viewMessageButtons" style="margin-top: 10px;">
				<button type="button" class="editMessageBtn" onClick="editMessageStatusBtn(` + thisMessageID + `)">Edit Status</button>
				<button type="button" class="editMessageBtn" onClick="editMessageNotesBtn(` + thisMessageID + `)">Edit Notes</button>
				<button type="button" class="editMessageBtn" onClick="returnToPrevious(` + statusIdentification + `)">Back</button>
			</div>
			<div id="editMessageStatusDiv">
				<!-- html dynamically added with TSPadmin.js -->
			</div>
				<div id="editMessageNoteDiv">
				<!-- html dynamically added with TSPadmin.js -->
			</div>
		</div>`
	
	);
	
}

function returnToPrevious(statusIdentification) {
	if (statusIdentification == 1) {
		viewUnaddressedMessages();
	}
	else if (statusIdentification == 2) {
		viewAddressedMessages();
	}
	else if (statusIdentification == 3) {
		viewTabledMessages();
	}
	else {
		viewAllMessages();
	}
}

function cancelEditMessageStatus() {
	$("#editMessageStatusDiv").empty();
}

function cancelEditMessageNote() {
	$("#editMessageNoteDiv").empty();
}

function editMessageStatusBtn(messageID) {
	$("#editMessageNoteDiv").empty();
	
	$("#editMessageStatusDiv").html(
	
		`<h3>Edit Status</h3>
		<form>
			<select id="selectMessageStatus">
				<option value="1">Unaddressed</option>
				<option value="2">Addressed</option>
				<option value="3">Tabled</option>
			</select>
			<div>
				<button type="button" class="saveCancelMessageEditBtn" id="saveMessageEditStatus" onClick="saveEditMessageStatus(` + messageID + `)">Save</button>
				<button type="button" class="saveCancelMessageEditBtn" id="cancelMessageEditStatus" onClick="cancelEditMessageStatus()">Cancel</button>
			</div>
		</form>`
	
	);
}

function saveEditMessageStatus(messageID) {
	var newStatus = document.getElementById("selectMessageStatus").value;
	
	//AJAX call to persist data with messageID and updated info
	
	alert("The new status has been saved.");
	
	$("#editMessageStatusDiv").empty();
	
}

function editMessageNotesBtn(messageID) {
	$("#editMessageStatusDiv").empty();
	
	$("#editMessageNoteDiv").html(
	
		`<h3>Edit Note</h3>
		<form>
			<textarea style="display:none;"></textarea> <!-- Need this for some reason to format the textarea below -->
			<textarea type="text" placeholder="Type note here." id="editMessageNoteInput"></textarea>
			<div>
				<button type="button" class="saveCancelMessageEditBtn" id="saveMessageEditNote" onClick="saveEditMessageNote(` + messageID + `)">Save</button>
				<button type="button" class="saveCancelMessageEditBtn" id="cancelMessageEditNote" onClick="cancelEditMessageNote()">Cancel</button>
			</div>
		</form>`
	
	);
}

function saveEditMessageNote(messageID) {
	var newNote = document.getElementById("editMessageNoteInput").value;
	
	if (newNote == "") {
	
	}
	else {
	
		//AJAX call to persist data with messageID and updated info
		
		alert("The new note has been saved.");
	}
	
	$("#editMessageNoteDiv").empty();
	
	
}



//TOGGLE MP3 OPTIONS ---------------------------------------------------------




//EDIT ACCOUNT FUNCTIONALITY ------------------------------------------------------
function editAccountView(accountNumber) {
	$("#extraSpace").hide();
	$("#accountChoiceDiv").html("");
	
	addEditAccountHeader();

	$("#accountChoiceDiv").append(
	
		`<div id="editAccountDisplayDiv">
			<div class="infoAndEditField" >
				<div class="displayInfoField  flex-container" style="border-top: solid grey 2px;">
					<div class="infoTitleDiv">
						First Name:
					</div>
					<div class="infoDisplayDiv" id="firstNameDisplay">
						Ben
					</div>
					<div class="editButtonDiv" id="firstNameEditButtonDiv">
						<button type="button" class="AccountEditSaveBtn" id="firstNameEditButton" onClick="changeFirstNameFieldsFromEditToSave()">Edit</button>
					</div>
				</div>
			</div>
	
			<div class="infoAndEditField">
				<div class="displayInfoField  flex-container">
					<div class="infoTitleDiv">
						Last Name:
					</div>
					<div class="infoDisplayDiv" id="lastNameDisplay">
						LeBoot
					</div>
					<div class="editButtonDiv" id="lastNameEditButtonDiv">
						<button type="button" class="AccountEditSaveBtn" id="lastNameEditButton" onClick="changeLastNameFieldsFromEditToSave()">Edit</button>
					</div>
				</div>
			</div>
			
			<div class="infoAndEditField">
				<div class="displayInfoField  flex-container">
					<div class="infoTitleDiv">
						Email:
					</div>
					<div class="infoDisplayDiv" id="emailDisplay">
						benLeBoot@website.com
					</div>
					<div class="editButtonDiv" id="emailEditButtonDiv">
						<button type="button" class="AccountEditSaveBtn" id="emailEditButton" onClick="changeEmailFieldsFromEditToSave()">Edit</button>
					</div>
				</div>
			</div>
			
			<div class="infoAndEditField">
				<div class="displayInfoField  flex-container">
					<div class="infoTitleDiv">
						Username:
					</div>
					<div class="infoDisplayDiv" id="usernameDisplay">
						benLeBoot
					</div>
					<div class="editButtonDiv" id="usernameButtonDiv">
						<button type="button" class="AccountEditSaveBtn" id="usernameEditButton" onClick="changeUsernameFieldsFromEditToSave()">Edit</button>
					</div>
				</div>
			</div>
			
			<div class="infoAndEditField">
				<div class="displayInfoField  flex-container">
					<div class="infoTitleDiv">
						Password:
					</div>
					<div class="infoDisplayDiv" id="passwordDisplay">
						Password
					</div>
					<div class="editButtonDiv" id="passwordButtonDiv">
						<button type="button" class="AccountEditSaveBtn" id="passwordEditButton" onClick="changePasswordFieldsFromEditToSave()">Edit</button>
					</div>
				</div>
			</div>
			
			<div class="infoAndEditField">
				<div class="displayInfoField  flex-container">
					<div class="infoTitleDiv">
						Region:
					</div>
					<div class="infoDisplayDiv" id="regionDisplay">
						North America
					</div>
					<div class="editButtonDiv" id="regionButtonDiv">
						<button type="button" class="AccountEditSaveBtn" id="regionEditButton" onClick="changeRegionFieldsFromEditToSave()">Edit</button>
					</div>
				</div>
			</div>
			
			<div class="infoAndEditField">
				<div class="displayInfoField  flex-container">
					<div class="infoTitleDiv">
						Type:
					</div>
					<div class="infoDisplayDiv" id="typeDisplay">
						Administrator
					</div>
					<div class="editButtonDiv" id="typeButtonDiv">
						<button type="button" class="AccountEditSaveBtn" id="regionEditButton" onClick="changeTypeFieldsFromEditToSave()">Edit</button>
					</div>
				</div>
			</div>
			
			<div id="cancelEditAnAccountBtnDiv">
				<button type="button" class="adminOptionsBtn" id="cancelEditAnAccountBtn" onClick="goBackToViewAccounts()">Done Editing</button>
			</div>
			
		</div>`
	
	);
}


var firstName = "John";
var lastName = "Johnson";
var email = "JJ@website.com";
var username = "JohnnyJ";
var password = "JJRULES";
var region = "Europe";
var type = "Memeber";

function loadEditDiv() {
	fillFields();
}

function fillFields() {
	$("#firstNameDisplay").html(firstName);
	$("#lastNameDisplay").html(lastName);
	$("#emailDisplay").html(email);
	$("#usernameDisplay").html(username);
	$("#passwordDisplay").html(password);
	$("#regionDisplay").html(region);
	$("#typeDisplay").html(type);
}

function changeFirstNameFieldsFromEditToSave() {
	$("#firstNameDisplay").html(	
		`<input type="text" class="inputBox" placeholder="First Name" id="firstNameInput">`	
	);
		
	$("#firstNameEditButtonDiv").html(	
		`<button type="button" class="AccountEditSaveBtn" id="firstNameSaveButton" onClick="changeFirstNameFieldsFromSaveToEdit()">Save</button>`	
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
			`<button type="button" class="AccountEditSaveBtn" id="firstNameEditButton" onClick="changeFirstNameFieldsFromEditToSave()">Edit</button>`
		);
}

function changeLastNameFieldsFromEditToSave() {
	$("#lastNameDisplay").html(	
		`<input type="text" class="inputBox" placeholder="Last Name" id="lastNameInput">`	
	);
		
	$("#lastNameEditButtonDiv").html(	
		`<button type="button" class="AccountEditSaveBtn" id="lastNameSaveButton" onClick="changeLastNameFieldsFromSaveToEdit()">Save</button>`	
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
			`<button type="button" class="AccountEditSaveBtn" id="lastNameEditButton" onClick="changeLastNameFieldsFromEditToSave()">Edit</button>`
		);
}

function changeEmailFieldsFromEditToSave() {
	$("#emailDisplay").html(	
		`<input type="text" class="inputBox" placeholder="Email" id="emailInput">`	
	);
		
	$("#emailEditButtonDiv").html(	
		`<button type="button" class="AccountEditSaveBtn" id="emailSaveButton" onClick="changeEmailFieldsFromSaveToEdit()">Save</button>`	
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
			`<button type="button" class="AccountEditSaveBtn" id="emailEditButton" onClick="changeEmailFieldsFromEditToSave()">Edit</button>`
		);
}

function changeUsernameFieldsFromEditToSave() {
	$("#usernameDisplay").html(	
		`<input type="text" class="inputBox" placeholder="Username" id="usernameInput">`	
	);
		
	$("#usernameButtonDiv").html(	
		`<button type="button" class="AccountEditSaveBtn" id="usernameSaveButton" onClick="changeUsernameFieldsFromSaveToEdit()">Save</button>`	
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
			`<button type="button" class="AccountEditSaveBtn" id="usernameEditButton" onClick="changeUsernameFieldsFromEditToSave()">Edit</button>`
		);
}

function changePasswordFieldsFromEditToSave() {
	$("#passwordDisplay").html(	
		`<input type="text" class="inputBox" placeholder="Password" id="passwordInput">`	
	);
		
	$("#passwordButtonDiv").html(	
		`<button type="button" class="AccountEditSaveBtn" id="passwordSaveButton" onClick="changePasswordFieldsFromSaveToEdit()">Save</button>`	
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
			`<button type="button" class="AccountEditSaveBtn" id="passwordEditButton" onClick="changePasswordFieldsFromEditToSave()">Edit</button>`
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
		`<button type="button" class="AccountEditSaveBtn" id="regionSaveButton" onClick="changeRegionFieldsFromSaveToEdit()">Save</button>`	
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
			`<button type="button" class="AccountEditSaveBtn" id="regionEditButton" onClick="changeRegionFieldsFromEditToSave()">Edit</button>`
		);
}

function changeTypeFieldsFromEditToSave() {
	$("#typeDisplay").html(	
		`<select id="typeInput">
			<option value="1">Member</option>
			<option value="2">Administrator</option>
			<option value="3">Deleted</option>
		</select>`	
	);
		
	$("#typeButtonDiv").html(	
		`<button type="button" class="AccountEditSaveBtn" id="typeSaveButton" onClick="changeTypeFieldsFromSaveToEdit()">Save</button>`	
	);
}

function changeTypeFieldsFromSaveToEdit() {		
		//determine whether or not the user actually typed anything new
		var possibleNewData = document.getElementById("typeInput").value;
		
		//if input is null, keep the old input and display it
		if (possibleNewData == "") {
			$("#typeDisplay").html(	
				type
			);
		}
		else {
			//Will need to persist data
		
			type = possibleNewData;
			$("#typeDisplay").html(	
				type
			);
		
		}
		
		//convert "save" button to "edit" button
		$("#typeButtonDiv").html(
			`<button type="button" class="AccountEditSaveBtn" id="typeEditButton" onClick="changeTypeFieldsFromEditToSave()">Edit</button>`
		);
}







