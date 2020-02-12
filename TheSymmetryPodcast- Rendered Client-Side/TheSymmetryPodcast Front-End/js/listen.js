/*
Name: listen.js, (TSP Capstone)
Author: Leboutillier
Date Created: 10 FEB 2020
Date Modified:
*/

/*
1) This document contains the code for listen.html.
	-- Validate logged-in status
	-- retrieve all mp3s from backend
*/


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
	displayTwoEpisodes();
	determineSessionStatus();

}) //End Document Ready


//VERIFY SESSION STATUS ----------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------

function determineSessionStatus() {
	
	setTimeout(function(){
		accountNumber = $(".accountIdDiv").text();
		if (accountNumber != -1) {
			$("#newestTwoOnly").html("");
			$("#restrictedAccessDiv").html("");
			displayAllEpisodes();
		}
	}, 500);
	
}

function displayAllEpisodes() {
	
	var divToAppend = $("#forMembersOnly");
	
	$.ajax({
        type: 'GET',
        url: 'http://localhost:8080/episode/get/all',
        success: function(episodeArray) {
			
			emptyErrorDiv();

            $.each(episodeArray, function(index, episode) {
				
				divToAppend.prepend(
					
					`<div class="episodeDiv">
						<div class="episodeTitleDiv" id="episodeTitleDiv-` + index + `" ></div>
						<div class="episodeDateDiv" id="episodeDateDiv-` + index + `"></div>
						<div class="episodeDescriptionDiv" id="episodeDescriptionDiv-` + index + `"></div>
						<div class="audioDiv">
							<audio controls class="audioTag">
								<source src="` + episode.episodeLink + `" type="audio/mpeg">
								Your browser does not support this audio element.
							</audio>
						</div>
					</div>`
				
				);
				
				$("#episodeTitleDiv-" + index).text(episode.episodeTitle);
				$("#episodeDateDiv-" + index).text(episode.episodeDate);
				$("#episodeDescriptionDiv-" + index).text(episode.episodeDescription);
				
            })

        },
        error: function (jqXHR, textStatus, errorThrown) {
			addToErrorDiv("All episodes are not loading from the back-end.");
        }
    });	
	
}

function displayTwoEpisodes() {
	
	var divToAppend = $("#newestTwoOnly");
	
	$.ajax({
        type: 'GET',
        url: 'http://localhost:8080/episode/get/two',
        success: function(episodeArray) {
			emptyErrorDiv();

            $.each(episodeArray, function(index, episode) {
				
				divToAppend.append(
					
					`<div class="episodeDiv">
						<div class="episodeTitleDiv" id="episodeTitleDiv-` + index + `" ></div>
						<div class="episodeDateDiv" id="episodeDateDiv-` + index + `"></div>
						<div class="episodeDescriptionDiv" id="episodeDescriptionDiv-` + index + `"></div>
						<div class="audioDiv">
							<audio controls class="audioTag">
								<source src="` + episode.episodeLink + `" type="audio/mpeg">
								Your browser does not support this audio element.
							</audio>
						</div>
					</div>`
				
				);
				
				$("#episodeTitleDiv-" + index).text(episode.episodeTitle);
				$("#episodeDateDiv-" + index).text(episode.episodeDate);
				$("#episodeDescriptionDiv-" + index).text(episode.episodeDescription);
				
            })


        },
        error: function (jqXHR, textStatus, errorThrown) {
			addToErrorDiv("Newest two episodes are not loading from the back-end.");
        }
    });	
	
	
}











