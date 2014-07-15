/*
 * Google Maps documentation: http://code.google.com/apis/maps/documentation/javascript/basics.html
 * Geolocation documentation: http://dev.w3.org/geo/api/spec-source.html
 */
$( document ).on( "pageinit", "#xyz", function() {
    var defaultLatLng = new google.maps.LatLng(34.0983425, -118.3267434);  // Default to Hollywood, CA when no geolocation support
    if ( navigator.geolocation ) {
        function success(pos) {
            // Location found, show map with these coordinates
            drawMap(new google.maps.LatLng(pos.coords.latitude, pos.coords.longitude));
        }
        function fail(error) {
            drawMap(defaultLatLng);  // Failed to find location, show default map
        }
        // Find the users current position.  Cache the location for 5 minutes, timeout after 6 seconds
        navigator.geolocation.getCurrentPosition(success, fail, {maximumAge: 500000, enableHighAccuracy:true, timeout: 6000});
    } else {
        drawMap(defaultLatLng);  // No geolocation support, show default map
    }
    function drawMap(latlng) {
        var myOptions = {
            zoom: 10,
            center: latlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById("map-canvas"), myOptions);
        // Add an overlay to the map of current lat/lng
        var marker = new google.maps.Marker({
            position: latlng,
            map: map,
            title: "Greetings!"
        });
    }
});

var interval;
$(document).ready(function(){		
	interval = setInterval(refreshDashboard,5000);
	$("#sendMsg").click(sendMsg);
	$("#saveNote").click(saveNote);
	$("#endRaceBtn").click(endRace);
	$(".riderRow").click(selectRow);
	
	$("#msgClearBtn").click(clearMessage);
	$("#notesClearBtn").click(clearNotes);	
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
				$("#messageTxt").val("");
			}
		});
	}	
}

function saveNote(){
	var note = $("#notesTxt").val();
	$.ajax({
		url: "saveNote",
		type: "POST",
		data: {"note": note},
		success: function(data){
			console.log("Note saved.");
			$("#notesTxt").val("");	
		}
	});
}

function selectRow(){
	var isSelected = $(this).hasClass("selectedRow");	
	
	if(isSelected){
		$(this).removeClass("selectedRow");
	}else{
		$(this).addClass("selectedRow");		
	}
}

function clearMessage(){
	$("#messageTxt").val("");
}

function clearNotes(){
	$("#notesTxt").val("");	
}