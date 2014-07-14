var interval;
$(document).ready(function(){		
	interval = setInterval(refreshDashboard,5000);
	$("#sendMsg").click(sendMsg);
	$("#endRaceBtn").click(endRace);
	$(".riderRow").click(selectRow);
});

function refreshDashboard(){
	$.ajax({
		url: "refreshStat",
		type: "GET",
		dataType: "json",
		success: updateStatistics
	});
}

function updateStatistics(data){
	for(var i = 0; i < data.length; i++){
		var stat = data[i];
		var riderId = stat.rider.rider_id;
		var speed = stat.speed;		
		var cadence = stat.cadence;
		var power = stat.power;
		var heartRate = stat.heart_rate;
		var longitude = stat.logitude;
		var latitude = stat.latitude;
		var elevation = stat.elevation;
		var row = $("#statTable").find(".riderRow").find(".rider_id").filter(function() { return $(this).text() === riderId.toString() }).parent().parent();
		$(row).find(".speed").text(speed);
		$(row).find(".cadence").text(cadence);
		$(row).find(".power").text(power);
		$(row).find(".hearRate").text(heartRate);
		$(row).find(".longitude").text(longitude);
		$(row).find(".latitude").text(latitude);
		$(row).find(".elevation").text(elevation);			
	}
}

function endRace(){
	$.ajax({
		url: "endRace",
		type: "GET",
		accepts: "html",
		success: function(data){
			clearInterval(interval);
			$("#raceDetails").html(data);
			$("#endRaceContainer").remove();
		}
	});	
}

function sendMsg(){
	var riderIds = $(".selectedRow").find(".rider_id");
	var messageTxt = $("#messageTxt").val();
	if(riderIds.length > 0 && messageTxt.length > 0){
		var selectedRows = $(riderIds).map(function(){return $(this).text()}).get().join(",");		
		
		$.ajax({
			url: "saveMsg",
			type: "POST",	
			data: {"message": messageTxt, "recipientIds":selectedRows },
			success: function(data){
				console.log("Message sent.");
			}
		});
	}

	
}

function selectRow(){
	var isSelected = $(this).hasClass("selectedRow");	
	
	if(isSelected){
		$(this).removeClass("selectedRow");
	}else{
		$(this).addClass("selectedRow");		
	}
}