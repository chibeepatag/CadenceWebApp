$(document).ready(function(){
	attachEventHandlers();
	
		                
});

function attachEventHandlers(){
	$(".riderRow").click(selectRow);
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
	$.ajax({
		url: "addRider",
		data: $("#newRiderForm").serialize(),
		dataType: "json",
		type: "POST",
		success: appendNewRider
	});
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
	var selectedRows = $(".selectedRow").find(".rider_id").map(function(){return $(this).text()}).get().join(",");

	$.ajax({
		url: "createRace", 
		data: {"raceName":raceName, "ids":selectedRows},
		dataType: "json",
		type: "POST",
		success: displayNewRace,
		error: failedToCreateRace
	});
}

function displayNewRace(data){
	$("#raceCreatedName").text(data.race_name);
				
	for (var rider in data.riders){
		var riderLi = "<li>" + data.riders[rider].nickname +"</li>";
		$("#raceCreatedRiders").append(riderLi);			
	}
			
	$("#raceCreated").removeClass("hiddenField");
	
}

function goToDashboard(){
	console.log("go to dashboard");
}

function failedToCreateRace(jqXHR, textStatus, errorThrown){
	$("raceCreated").html(textStatus + " " + errorThrown);
	$("#raceCreated").removeClass("hiddenField");
}