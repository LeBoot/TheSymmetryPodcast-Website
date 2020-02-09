/*
Name: TSPadmin.js, (TSP Capstone)
Author: Leboutillier
Date Created: 26 JAN 2020
Date Modified: 9 FEB 2020
*/

/*
1) This document contains the code to make an interactive TSPadmin page.
2) There are three main options: "view accounts", "view messages", and "view mp3s"
	- each option will present the user with a new group of selections
*/

/* TO DO LIST
1) Toggle account
2) Toggle MP3

*/


//VARIABLES ---------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------
var AdministratorPassword;


//ERROR DIV ---------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------
function addToErrorDiv(Message) {
		
	$(".errorDiv").append(
		'<p class="alert alert-danger">' + Message + '</p>'
	);
	
}

function emptyErrorDiv() {
	$(".errorDiv").empty();
}


//DOCUMENT READY -----------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------
$(document).ready(function () {
	
	emptyErrorDiv();
	hideAllDivs();
	redirectIfNecessary();
	

}) //End Document Ready

function redirectIfNecessary() {

	setTimeout(function(){
		accountNum = $(".accountIdDiv").text();
		
		$.ajax({
			type: 'GET',
			url: 'http://localhost:8080/account/get/' + accountNum,
			success: function(Account) {
				if (Account.accountType.accounttypeid == 2) {
					AdministratorPassword = Account.password;
				}
				else {
					redirect();
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				redirect();
			}
		});	
		
	}, 500);
	
}

function redirect() {
	
	alert("Sorry, but you do not have administrator privileges.");
	window.location.href = 'home.html';
	
}


//TOGGLE PRIMARY OPTIONS ---------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------
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


//TOGGLE ACCOUNT OPTIONS ---------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------


// VIEW/DELETE ACCOUNTS --------------------------------------------------------------------------------------
function viewAccounts() {	
	
	emptyErrorDiv();
	
	$("#extraSpace").hide();
	
	$("#accountChoiceDiv").html(
	
		`<h3 class="accountChoiceHeader">View All Accounts</h3>
		<table id="allAccountsTable">
			<tr>
				<th width="5%">Number</th>
				<th width="10%">First Name</th>
				<th width="10%">Last Name</th>
				<th width="20%">Email</th>
				<th width="13%">Username</th>
				<th width="9%">Start Date</th>
				<th width="11%">Region</th>
				<th width="10%">Type</th>
				<th width="6%"></th>
				<th width="6%"></th>
			</tr>
		</table>`
	
	);
	
	appendAccountTable();
	
}

function appendAccountTable() {
	
	$.ajax({
        type: 'GET',
        url: 'http://localhost:8080/account/get-all',
        success: function(AccountArray) {
            //get a reference to the table to append
            var tableToAppend = $('#allAccountsTable');

            //for each, build a table, then add values
            $.each(AccountArray, function(index, account) {
                var htmlToAdd = `<tr>`;
				htmlToAdd += `<td id="table-row-accountNum-` + index + `"></td>`;
				htmlToAdd += `<td id="table-row-firstName-` + index + `"></td>`;
				htmlToAdd += `<td id="table-row-lastName-` + index + `"></td>`;
				htmlToAdd += `<td id="table-row-email-` + index + `"></td>`;
				htmlToAdd += `<td id="table-row-username-` + index + `"></td>`;
				htmlToAdd += `<td id="table-row-startDate-` + index + `"></td>`;
				htmlToAdd += `<td id="table-row-region-` + index + `"></td>`;
				htmlToAdd += `<td id="table-row-type-` + index + `"></td>`;
				htmlToAdd += `<td><button type="button" class="listAllAccountsEditButton" onClick="editAccountView(` + account.accountnumber + `)">Edit</button></td>`;
				htmlToAdd += `<td><button type="button" class="listAllAccountsDeleteButton" onClick="deleteAccount(` + account.accountnumber + `, '` + account.firstName + `', '` + account.lastName + `')">Delete</button></td>`;
				htmlToAdd += `</tr>`;
				
				tableToAppend.append(htmlToAdd);
				
				$("#table-row-accountNum-" + index).text(account.accountnumber);
				$("#table-row-firstName-" + index).text(account.firstName);
				$("#table-row-lastName-" + index).text(account.lastName);
				$("#table-row-email-" + index).text(account.email);
				$("#table-row-username-" + index).text(account.username);
				$("#table-row-startDate-" + index).text(account.startDate);
				$("#table-row-region-" + index).text(account.region.regionName);
				$("#table-row-type-" + index).text(account.accountType.accountTypeName);
				
            })
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Failure to load accounts from database into table.");
        }
    });	

}

function deleteAccount(accountNumber, Fname, Lname) {
	
	var output;
	var input = prompt("Are you sure you want to permanantly delete the account for " + Fname + " " + Lname + "?  If so, enter your administrator password:", "");
	
	if (input == AdministratorPassword) {
		deleteAccountAJAXCall(accountNumber);
	}
	else if ((input == null) || (input == "")) {
		return false;
	}
	else {
		alert("Incorrect Password");
	}

}

function deleteAccountAJAXCall(accountNumber) {
	
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8080/account/delete/' + accountNumber,
		success: function() {
			emptyErrorDiv();
			alert("Account Deleted.");
			viewAccounts();
		},
		error: function(jqXHR, textStatus, errorThrown) {
			addToErrorDiv("Failure to delete that account.");
        }
	});
	
}


// CREATE ACCOUNTS ---------------------------------------------------------------------------
function createAccountView() {
	
	emptyErrorDiv();
	
	$("#extraSpace").hide();
	
	$("#accountChoiceDiv").html(
	
		`<h3 class="accountChoiceHeader">Create An Account</h3>
		<form id="createAccountForm">
			<div>
				<div id="firstNameArea">
					<input type="text" class="text full-width" placeholder="First Name*" id="firstNameInputNewAcc" required>
				</div>
				<div id="lastNameArea">
					<input type="text" class="text full-width" placeholder="Last Name*" id="lastNameInputNewAcc" required>
				</div>
				<div id="emailArea">
					<input type="email" class="text full-width" placeholder="Email*" id="emailInputNewAcc" required>
				</div>
				<div id="userNameArea">
					<input type="text" class="text full-width" placeholder="Username*" id="userNameInputNewAcc" required>
				</div>
				<div id="passwordArea">
					<input type="password" class="text full-width" placeholder="Password*" id="passwordInputNewAcc" required>
				</div>
				<div id="accountTypeArea">
					<select id="accountTypeSelectNewAcc" required>
					</select>
				</div>
				<div id="regionArea">
					<select id="regionSelectNewAcc" required>
					</select>
				</div>
			</div>
			<div style="text-align: center;">
				<button type="button" class="accountOptionsBtn" id="createAccountButton" onClick="createAccountRequest()">Create Account</button>
			</div>
		</form>`
	
	);
	
	//AJAX to dipslay account type
	$.ajax({
        type: 'GET',
        url: 'http://localhost:8080/community/accountTypes',
        success: function(accountTypeArray) {
            //get a reference to the dropdown
            var referenceSelect = $('#accountTypeSelectNewAcc');

            //go through each of the returned values and append
            $.each(accountTypeArray, function(index, type) {
                var toBeAppended = '<option value="' + type.accounttypeid + '">' + type.accountTypeName + '</option>'
				referenceSelect.append(toBeAppended);
            })
        },
        error: function (jqXHR, textStatus, errorThrown) {
            addToErrorDiv("Failure to load account-types from database into dropdown box.");
        }
    });
	
	
	//AJAX to display reigons
	$.ajax({
        type: 'GET',
        url: 'http://localhost:8080/community/regions',
        success: function(regionArray) {
            //get a reference to the regions dropdown
            var regionsDiv = $('#regionSelectNewAcc');

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

function createAccountRequest() {
	
	emptyErrorDiv();
	
	var proceedWithAjaxCall = true;
	
	const inputFirst = $("#firstNameInputNewAcc").val();
	const inputLast = $("#lastNameInputNewAcc").val();
	const inputEmail = $("#emailInputNewAcc").val();
	const inputUsername = $("#userNameInputNewAcc").val();
	const inputPass = $("#passwordInputNewAcc").val();
	const inputRegionId = document.getElementById("regionSelectNewAcc").value;
	const inputAccountTypeId = document.getElementById("accountTypeSelectNewAcc").value;
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
	if ((inputAccountTypeId == "Default") || (inputAccountTypeId == null)) {
		proceedWithAjaxCall = false;
		addToErrorDiv("You must select an account type.");
	}
	if ((inputRegionId == "Default") || (inputRegionId == null)) {
		proceedWithAjaxCall = false;
		addToErrorDiv("You must select a region.");
	}
	
	if (proceedWithAjaxCall == true) {
		AJAXcallToCreateAccount(inputFirst, inputLast, inputEmail, inputUsername, inputPass, inputAccountTypeId, inputRegionId);
	}
	
}

function AJAXcallToCreateAccount(inputFirst, inputLast, inputEmail, inputUsername, inputPass, inputAccountTypeId, inputRegionId) {
	
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
			accountTypeId : inputAccountTypeId
		}),
		headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
		success: function() {
			emptyErrorDiv();
			alert("New account successfully created");
			viewAccounts();
		},
		error: function(jqXHR, textStatus, errorThrown) {
			addToErrorDiv("That account cannot be created.  Most likely, that username is already taken.  Feel free to try again!");
        }
	});
	
}


//EDIT ACCOUNTS -----------------------------------------------------------------------------------
//(the rest of the edit functionality is at the bottom of this page)

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
	const accountToView = $("#accountNumberToEdit").val();
	editAccountView(accountToView);
}

function goBackToViewAccounts() {
	$("#accountsDiv").hide();
	toggleAccountsDiv();
	viewAccounts();
}

//TOGGLE MESSAGE OPTIONS ---------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------

function viewAllMessages() {
	prepMessageChoiceDiv("View All Messages");
	appendMessageTable(0);
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
			<thead>
				<tr>
					<th width="5%">ID</th>
					<th width="22%">Name</th>
					<th width="22%">Email</th>
					<th width="12%">Region</th>
					<th width="15%">TimeStamp</th>
					<th width="14%">Status</th>
					<th width="10%"></th>
				</tr>
			</thead>
			<tbody  id="messageTableBody">
			</tbody>
		</table>`
	
	);
}

function appendMessageTable(statusIdentification) {
	
	if (statusIdentification == 0) {
		getAllMessagesAJAXCall();
	}
	else {	
		getSomeMessagesAJAXCall(statusIdentification);
	}
	
}

function getAllMessagesAJAXCall() {

	$.ajax({
        type: 'GET',
        url: 'http://localhost:8080/contact/messages/all',
        success: function(messagesArray) {
            //get a reference to the table to append
            var tableToAppend = $('#messageTableBody');

            //go through each of the returned values and append
            $.each(messagesArray, function(index, message) {
                var htmlToAdd = '<tr>';
				htmlToAdd += '<td>' + message.contactid + '</td>';
				htmlToAdd += '<td>' + message.myName + '</td>';
				htmlToAdd += '<td>' + message.myEmail + '</td>';
				htmlToAdd += '<td>' + message.region.regionName + '</td>';
				htmlToAdd += '<td>' + message.timeStamp + '</td>';
				htmlToAdd += '<td>' + message.contactStatus.contactStatusName + '</td>';
				htmlToAdd += '<td><button type="button" class="viewMessageButton" onClick="viewMessage(' + message.contactid + ', 0)">View</button></td>';
				htmlToAdd += '</tr>';
				
				tableToAppend.prepend(htmlToAdd);
            })
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Failure to load messages from database into table.");
        }
    });	

}

function getSomeMessagesAJAXCall(originalLocation) {
	$.ajax({
        type: 'GET',
        url: 'http://localhost:8080/contact/messages/' + originalLocation,
        success: function(messagesArray) {
            //get a reference to the table to append
            var tableToAppend = $('#messageTableBody');

            //go through each of the returned values and append
            $.each(messagesArray, function(index, message) {
                var htmlToAdd = '<tr>';
				htmlToAdd += '<td>' + message.contactid + '</td>';
				htmlToAdd += '<td>' + message.myName + '</td>';
				htmlToAdd += '<td>' + message.myEmail + '</td>';
				htmlToAdd += '<td>' + message.region.regionName + '</td>';
				htmlToAdd += '<td>' + message.timeStamp + '</td>';
				htmlToAdd += '<td>' + message.contactStatus.contactStatusName + '</td>';
				htmlToAdd += '<td><button type="button" class="viewMessageButton" onClick="viewMessage(' + message.contactid + ', ' 
					+ originalLocation + ')">View</button></td>';
				htmlToAdd += '</tr>';
				
				tableToAppend.prepend(htmlToAdd);
            })
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Failure to load messages from database into table.");
        }
    });	

}

function viewMessage(thisMessageID, originalLocation) {
	
	$("#messageChoiceDiv").empty();
	
	$.ajax({
        type: 'GET',
        url: 'http://localhost:8080/contact/messages/get-by-id/' + thisMessageID,
        success: function(message) {
			
			var incomingNote = message.notes;
			if (incomingNote == null) {
				incomingNote = "None.";
			}
				
			$("#messageChoiceDiv").html(
				
				`<h3 class="messagesChoiceHeader">View A Message</h3>				
				<div id="individualMessageDisplayDiv">
					<div class="messageDetails"><b>Message ID: </b>` + thisMessageID + `</div>
					<div class="messageDetails"><b>Name: </b>` + message.myName + `</div>
					<div class="messageDetails"><b>Email: </b>` + message.myEmail + `</div>
					<div class="messageDetails"><b>Region: </b>` + message.region.regionid + `</div>
					<div class="messageDetails"><b>Timestamp: </b>` + message.timeStamp + `</div>
					<div class="messageDetails"><b>Status: </b>` + message.contactStatus.contactStatusName + `</div>
					<div class="messageNotesText"><b>Notes: </b>` + incomingNote + `</div>
					<div class="messageText"><b>Message: </b>` + message.messageText + `</div>
					<div class="flex-container" id="viewMessageButtons" style="margin-top: 10px;">
						<button type="button" class="editMessageBtn" onClick="editMessageStatusBtn(` + thisMessageID + `, ` + originalLocation + `)">Edit Status</button>
						<button type="button" class="editMessageBtn" onClick="editMessageNotesBtn(` + thisMessageID + `, ` + originalLocation + `, '` + incomingNote + `')">Edit Notes</button>
						<button type="button" class="editMessageBtn" onClick="returnToPrevious(` + originalLocation + `)">Back</button>
					</div>
					<div id="editMessageStatusDiv">
						<!-- html dynamically added with TSPadmin.js -->
					</div>
						<div id="editMessageNoteDiv">
						<!-- html dynamically added with TSPadmin.js -->
					</div>
				</div>`
				
			);

        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Failure to load message from database.");
        }
    });
	
}

function returnToPrevious(originalLocation) {
	if (originalLocation == 1) {
		viewUnaddressedMessages();
	}
	else if (originalLocation == 2) {
		viewAddressedMessages();
	}
	else if (originalLocation == 3) {
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

function editMessageStatusBtn(messageID, originalLocation) {
	$("#editMessageNoteDiv").empty();
	
	$("#editMessageStatusDiv").html(
	
		`<h3>Edit Status</h3>
		<form>
			<select id="selectMessageStatus" style="background-color: white;">
				<option value="1">Unaddressed</option>
				<option value="2">Addressed</option>
				<option value="3">Tabled</option>
			</select>
			<div>
				<button type="button" class="saveCancelMessageEditBtn" id="saveMessageEditStatus" onClick="saveEditMessageStatus(` + messageID + `, ` + originalLocation + `)">Save</button>
				<button type="button" class="saveCancelMessageEditBtn" id="cancelMessageEditStatus" onClick="cancelEditMessageStatus()">Cancel</button>
			</div>
		</form>`
	
	);
}


function editMessageNotesBtn(messageID, originalLocation, messageNotes) {
	$("#editMessageStatusDiv").empty();
	
	console.log("editMessageNotesBtn function: " + messageNotes);
	
	$("#editMessageNoteDiv").html(
	
		`<h3>Edit Note</h3>
		<form>
			<textarea style="display:none;"></textarea> <!-- Need this for some reason to format the textarea below -->
			<textarea type="text" id="editMessageNoteInput">` + messageNotes + `</textarea>

			<div>
				<button type="button" class="saveCancelMessageEditBtn" id="saveMessageEditNote" onClick="saveEditMessageNote(` + messageID + `, ` + originalLocation + `)">Save</button>
				<button type="button" class="saveCancelMessageEditBtn" id="cancelMessageEditNote" onClick="cancelEditMessageNote()">Cancel</button>
			</div>
		</form>`
	
	);
}

function saveEditMessageStatus(messageID, originalLocation) {
	var newStatus = document.getElementById("selectMessageStatus").value;
	
	AJAXCallEditMessageStatus(messageID, newStatus, originalLocation);
	
}

function AJAXCallEditMessageStatus(messageID, newStatus, originalLocation) {
	
	$.ajax({
		type: 'POST',
		url: 'http://localhost:8080/contact/edit-message/status',
		data: JSON.stringify({
			contactId: messageID,
			rbStatusId: newStatus
		}),
		headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
		success: function() {
			viewMessage(messageID, originalLocation);
		},
		error: function(jqXHR, textStatus, errorThrown) {
            alert('Unfortunately that edit did not go through.');
        }
	});	
	
}


function saveEditMessageNote(messageID, originalLocation) {
	var newNote = document.getElementById("editMessageNoteInput").value;

	if (newNote.length > 5000) {
		addToErrorDiv("Your note may not exceed 5000 characters.");
	}
	if ((newNote.includes("<")) || newNote.includes(">") || newNote.includes("\`") || newNote.includes("\"") || newNote.includes("\'") || newNote.includes("\\")) {
		addToErrorDiv("Sorry, but your input cannot contain characters such as <, >, \`, \", \', \\, etc.");
	}	
	else {
		AJAXCallEditMessageNote(messageID, newNote, originalLocation);
	}
	
}

function AJAXCallEditMessageNote(messageID, newNote, originalLocation) {
	
	$.ajax({
		type: 'POST',
		url: 'http://localhost:8080/contact/edit-message/note',
		data: JSON.stringify({
			contactId: messageID,
			rbNotes: newNote
		}),
		headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
		success: function() {
			viewMessage(messageID, originalLocation);
		},
		error: function(jqXHR, textStatus, errorThrown) {
            alert('Unfortunately that edit did not go through.');
        }
	});	
	
}



//TOGGLE MP3 OPTIONS -------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------




//EDIT ACCOUNT FUNCTIONALITY -----------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------

//VARIABLES --------------------------------------------------------------------
var JSaccountNumber;

//filled from AJAX (GET): These never change except when the AJAX GET is called
var JSfirstName;
var JSlastName;
var JSemail;
var JSusername;
var JSpassword
var JSregionID;
var	JSregionName;
var JSaccountTypeId;
var JSaccountTypeName;

//filled from GET AJAX, then fields, then (if error) from AJAX (POST)
//these change with user input, but are reset if the AJAX (POST) fails
var JSfirstNameP;
var JSlastNameP;
var JSemailP;
var JSusernameP;
var JSregionIDP;
var JSaccountTypeIdP;

//DISPLAY ACCOUNT --------------------------------------------------------------

function editAccountView(accountNumber) {
	
	JSaccountNumber = accountNumber;
	
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
	
	emptyAllEditFields();
	
	AJAXcallForAccount();
	
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
			JSaccountTypeName = account.accountType.accountTypeName;
			
			//these can change with user input, but will be reset if POST fails
			JSfirstNameP = JSfirstName;
			JSlastNameP = JSlastName;
			JSemailP = JSemail;
			JSusernameP = JSusername;
			JSregionIDP = JSregionID;
			JSaccountTypeIdP = JSaccountTypeId;
			
			fillAllEditFields();
        },
        error: function (jqXHR, textStatus, errorThrown) {
			addToErrorDiv("Failure to load account from database.");
        }
    });

}

function fillAllEditFields() {
	
	$("#firstNameDisplay").text(JSfirstName);
	$("#lastNameDisplay").text(JSlastName);
	$("#emailDisplay").text(JSemail);
	$("#usernameDisplay").text(JSusername);
	$("#regionDisplay").text(JSregionName);
	$("#typeDisplay").text(JSaccountTypeName);
	
}

function emptyAllEditFields() {
	
	$("#firstNameDisplay").empty();
	$("#lastNameDisplay").empty();
	$("#emailDisplay").empty();
	$("#usernameDisplay").empty();
	$("#regionDisplay").empty();
	$("#typeDisplay").empty();
	
}

//EDIT/SAVE INFO ONCLICK FUNCTIONS -------------------------------------------------------

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
			password : JSpassword,
			regionId : JSregionIDP,
			accountTypeId : JSaccountTypeIdP
		}),
		headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
		success: function() {
			editAccountView(JSaccountNumber);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			//reset variables
			JSfirstNameP = JSfirstName;
			JSlastNameP = JSlastName;
			JSemailP = JSemail;
			JSusernameP = JSusername;
			JSregionIDP = JSregionID;
			JSaccountTypeIdP = JSaccountTypeId;
			
			//display errors
			addToErrorDiv("Whoops.  Those changes were not valid.");
			addToErrorDiv("If you were attempting to change the username, you may have attempted one that is already taken.");
        }
	});
}


function changeFirstNameFieldsFromEditToSave() {
	$("#firstNameDisplay").html(	
		`<input type="text" class="editAccountInfoInput" placeholder="First Name" id="firstNameInput" maxlength="25">`	
	);
	
	$("#firstNameInput").val(JSfirstName);
		
	$("#firstNameEditButtonDiv").html(	
		`<button class="AccountEditSaveBtn" id="firstNameSaveButton" onClick="changeFirstNameFieldsFromSaveToEdit()">Save</button>`	
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
		`<input type="text" class="editAccountInfoInput" placeholder="Last Name" id="lastNameInput" maxlength="25">`	
	);
		
	$("#lastNameInput").val(JSlastName);	
		
	$("#lastNameEditButtonDiv").html(	
		`<button class="AccountEditSaveBtn" id="lastNameSaveButton" onClick="changeLastNameFieldsFromSaveToEdit()">Save</button>`	
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
		`<input type="text" class="editAccountInfoInput" placeholder="Email" id="emailInput" maxlength="40">`	
	);
		
	$("#emailInput").val(JSemail);
	
	$("#emailEditButtonDiv").html(	
		`<button class="AccountEditSaveBtn" id="emailSaveButton" onClick="changeEmailFieldsFromSaveToEdit()">Save</button>`	
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
		`<input type="text" class="editAccountInfoInput" placeholder="Username" id="usernameInput" maxlenght="15">`	
	);
		
	$("#usernameInput").val(JSusername);
		
	$("#usernameButtonDiv").html(	
		`<button class="AccountEditSaveBtn" id="usernameSaveButton" onClick="changeUsernameFieldsFromSaveToEdit()">Save</button>`	
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

function changeRegionFieldsFromEditToSave() {
	$("#regionDisplay").html(	
		`<select class="editAccountInfoInput" id="regionInput">
		</select>`	
	);
		
	$("#regionButtonDiv").html(	
		`<button class="AccountEditSaveBtn" id="regionSaveButton" onClick="changeRegionFieldsFromSaveToEdit()">Save</button>`	
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
			`<button class="AccountEditSaveBtn" id="regionEditButton" onClick="changeRegionFieldsFromEditToSave()">Edit</button>`
		);
		
		JSregionIDP = possibleNewData;
		
		AJAXCallToEditAccount();

}

//account type

function changeTypeFieldsFromEditToSave() {
	$("#typeDisplay").html(	
		`<select class="editAccountInfoInput" id="accountTypeInput">
		</select>`
	);
		
	$("#typeButtonDiv").html(	
		`<button class="AccountEditSaveBtn" id="accountTypeSaveButton" onClick="changeTypeFieldsFromSaveToEdit()">Save</button>`	
	);
	
	configureDropdownAjaxTypeCall();
}

function configureDropdownAjaxTypeCall() {

	$.ajax({
        type: 'GET',
        url: 'http://localhost:8080/community/accountTypes',
        success: function(accountTypeArray) {
            //get a reference to the dropdown
            var referenceSelect = $('#accountTypeInput');

            //go through each of the returned values and append
            $.each(accountTypeArray, function(index, type) {
                var toBeAppended;
				if (type.accounttypeid == JSaccountTypeId) {
					toBeAppended = '<option selected value="' + type.accounttypeid + '">' + type.accountTypeName + '</option>';
				} else {
					toBeAppended = '<option value="' + type.accounttypeid + '">' + type.accountTypeName + '</option>';
				}				
				referenceSelect.append(toBeAppended);
            })
        },
        error: function (jqXHR, textStatus, errorThrown) {
            addToErrorDiv("Failure to load account-types from database into dropdown box.");
        }
    });

}

function changeTypeFieldsFromSaveToEdit() {		
		//gather data from the field
		var possibleNewData = document.getElementById("accountTypeInput").value;
		
		//reset display
		$("#typeDisplay").text(JSregionName);
		$("#typeButtonDiv").html(
			`<button class="AccountEditSaveBtn" type="button" class="AccountEditSaveBtn" id="regionEditButton" onClick="changeTypeFieldsFromEditToSave()">Edit</button>`
		);
		
		JSaccountTypeIdP = possibleNewData;
		
		AJAXCallToEditAccount();

}


