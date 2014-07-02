$(document).ready(function(){
	setInterval(refreshDashboard,5000);	
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