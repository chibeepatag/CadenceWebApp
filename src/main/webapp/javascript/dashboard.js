var interval;
$(document).ready(function(){		
	interval = setInterval(refreshDashboard,5000);
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