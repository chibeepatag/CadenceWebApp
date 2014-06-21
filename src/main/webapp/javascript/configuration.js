$(document).ready(function(){
	$(".riderRow").click(selectRow);
	$("#addRider").click(addRider);
	$("#deleteRiders").click(deleteRiders);
});

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
}

function deleteRiders(){
	var ids = $( "input:checked" ).parent().find("span").map(function(){return $(this).text()})
	$.ajax({
		url: "deleteRiders",
		data: {"ids":ids},
		dataType: "json",
		type: "POST",
		success: removeRidersFromTable
	});
}

function removeRidersFromTable(){
	console.log("deleted");
}