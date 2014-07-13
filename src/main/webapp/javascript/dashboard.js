var interval;
$(document).ready(function(){		
	interval = setInterval(refreshDashboard,5000);
	$("#sendMsg").click(sendMsg);
	$("#endRaceBtn").click(endRace);
});

function refreshDashboard(){
	$.ajax({
		url: "refreshStat",
		type: "GET",
		accepts: "html",
		success: function(data){
			$(".statisticTable").html(data);					
		}
	});
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
	var messageTxt = $("#messageTxt").val();
	console.log(messageTxt);
	$.ajax({
		url: "saveMsg",
		type: "POST",	
		data: {"message": messageTxt, "recipientIds":"1" },
		success: function(data){
			console.log(data);
		}
	});
	
}