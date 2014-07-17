/*
 * Google Maps documentation: http://code.google.com/apis/maps/documentation/javascript/basics.html
 * Geolocation documentation: http://dev.w3.org/geo/api/spec-source.html
 */
var markers = {};
var interval;
var map;

$( document ).on( "pageinit", "#page", function() {
    
    drawMap();
        
    function drawMap() {
        var myOptions = {
            zoom: 17,            
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        map = new google.maps.Map(document.getElementById("map-canvas"), myOptions);              
    }
    
	$("#statTable").find(".riderRow").each(function(){
		var lat = $(this).find(".latitude").text();
		var long = $(this).find(".longitude").text();
		var name = $(this).find(".name").text();
		var jersey = $(this).find(".jersey").text();		
		var latlng = new google.maps.LatLng(lat, long);
		var marker = new MarkerWithLabel({
			position: latlng,
			map: map,
			title: name,
			labelClass: "labels", // the CSS class for the label
		    labelInBackground: false,
		    labelContent: jersey,
		    draggable: false
		});
		markers[name] = marker;
	});
	
	map.setCenter(findCenter());	
});

function findCenter(){
	var sumLat = 0;
	var sumLong = 0;
	var count = 0;
	for(var i in markers){
		sumLat +=  markers[i].getPosition().lat();
		sumLong += markers[i].getPosition().lng();
		count++;
	}
	
	var averageLat = sumLat / count;
	var averageLong = sumLong / count;
	var center = new google.maps.LatLng(averageLat, averageLong);
	return center;
}

$(document).ready(function(){		
//	interval = setInterval(refreshDashboard,5000);
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
		var key = $(row).find(".name").text();
		$(row).find(".speed").text(speed);
		$(row).find(".cadence").text(cadence);
		$(row).find(".power").text(power);
		$(row).find(".hearRate").text(heartRate);
		$(row).find(".longitude").text(longitude);
		$(row).find(".latitude").text(latitude);
		$(row).find(".elevation").text(elevation);
		var newPosition = new google.maps.LatLng(latitude, longitude);
		markers[key].setPosition(newPosition);
	}
	map.setCenter(findCenter());
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