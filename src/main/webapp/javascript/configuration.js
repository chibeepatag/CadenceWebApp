$(document).ready(function(){
	attachEventHandlers();
	
		                
});

function attachEventHandlers(){
	$(".riderRow").click(selectRow);
	$(".riderRow").dblclick(selectEditRider);
	$("#addRider").click(addRider);
	$("#deleteRiders").click(deleteRiders);
	$("#createRace").click(createRace);
	$("#dashboard").click(goToDashboard);
}

function selectRow(){
	var isSelected = $(this).hasClass("selectedRow");	
	
	if(isSelected){
		$(this).removeClass("selectedRow");
	}else{
		$(this).addClass("selectedRow");		
	}
}

function addRider(){	
	var riderId = $("#riderIdInput").val();
	if(riderId > 0){		
		$.ajax({
			url: "editRider",
			data: $("#newRiderForm").serialize(),
			dataType: "json",
			type: "POST",
			success: updateEditedRider
		});
	}else{
		$.ajax({
			url: "addRider",
			data: $("#newRiderForm").serialize(),
			dataType: "json",
			type: "POST",
			success: appendNewRider
		});
	}	
}

function selectEditRider(){
	var firstName = $(this).find(".riderFirstName").text();
	var lastName = $(this).find(".riderLastName").text();
	var nickname = $(this).find(".riderNickname").text();
	var phone = $(this).find(".riderPhone").text();
	var jersey = $(this).find(".riderJersey").text();
	var rider_id = $(this).find(".rider_id").text();
		
	$("#firstNameInput").val(firstName);
	$("#lastNameInput").val(lastName);
	$("#nicknameInput").val(nickname);
	$("#phoneInput").val(phone);
	$("#jerseyNoInput").val(jersey);
	$("#riderIdInput").val(rider_id);	
}

function appendNewRider(data){	
	var lastRowClone = $(".riderTable").find("tr:last" ).clone();
	$(lastRowClone).find(".riderFirstName").text(data.first_Name);
	$(lastRowClone).find(".riderLastName").text(data.last_name);
	$(lastRowClone).find(".riderNickname").text(data.nickname);
	$(lastRowClone).find(".riderPhone").text(data.phone);
	$(lastRowClone).find(".riderJersey").text(data.jersey_no);
	$(lastRowClone).find(".rider_id").text(data.rider_id);
	$(".riderTable").append(lastRowClone);
	$(lastRowClone).removeClass("selectedRow");
	$(lastRowClone).click(selectRow);
}

function updateEditedRider(data){
	var riderid = data.rider_id;
	var row = $(".riderTable").find(".riderRow").find(".rider_id").filter(function() { return $(this).text() === riderid.toString() }).parent().parent();
	$(row).find(".riderFirstName").text(data.first_Name);
	$(row).find(".riderLastName").text(data.last_name);
	$(row).find(".riderNickname").text(data.nickname);
	$(row).find(".riderPhone").text(data.phone);
	$(row).find(".riderJersey").text(data.jersey_no);
	$(row).find(".rider_id").text(data.rider_id);	
}

function deleteRiders(){
	var deleteRows = $(".selectedRow");
	var ids = $(deleteRows).find(".rider_id").map(function(){return $(this).text()}).get().join(",");

	$.ajax({
		url: "deleteRiders",
		data: {"ids":ids},
		dataType: "json",
		type: "POST",		
	});	
	$(deleteRows).remove();
		
}

function removeRidersFromTable(){
	console.log("deleted");
}

function createRace(){
	var raceName = $("#raceName").val();
		
	if(raceName){
		console.log("creating race");
		var riderIds = $(".selectedRow").find(".rider_id");		
		if(riderIds.length > 0){
			var selectedRows = $(riderIds).map(function(){return $(this).text()}).get().join(",");
			
			$.ajax({
				url: "createRace", 
				data: {"raceName":raceName, "ids":selectedRows},
				dataType: "json",
				type: "POST",
				success: displayNewRace,
				error: failedToCreateRace
			});
		}else{
			$(".errorPopup").text("You must select at least one rider.");
			$("#errorButton").click();
		}
				
	}else{
		$(".errorPopup").text("You must enter a race name");
		$("#errorButton").click();
	}
}

function displayNewRace(data){
	console.log("displayNewRace");
	$("#raceCreatedName").text(data.race_name);
				
	for (var rider in data.riders){
		var riderLi = "<li>" + data.riders[rider].nickname +"</li>";
		$("#raceCreatedRiders").append(riderLi);			
	}
	
	$("#raceCreatedBtn").click();		
}

function goToDashboard(){
	console.log("go to dashboard");
}

function failedToCreateRace(jqXHR, textStatus, errorThrown){
	$("raceCreated").html(textStatus + " " + errorThrown);
	$("#raceCreated").removeClass("hiddenField");
}