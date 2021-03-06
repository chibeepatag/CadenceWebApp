/*
 * Google Maps documentation: http://code.google.com/apis/maps/documentation/javascript/basics.html
 * Geolocation documentation: http://dev.w3.org/geo/api/spec-source.html
 */
var markers = {};
var interval;
var map;
var messageTemplateMap={"left":"Left", "right":"Right", "breakAway":"Break away", "timeGap":"Time Gap", "safety":"Safety", "received":"Received"};
var startTime;

$( document ).on( "pageinit", "#page", function() {
	 $("#messageAndNotes").panel("open");
    drawMap();
        
    function drawMap() {
        var myOptions = {
            zoom: 17,
			minZoom: 10,
            maxZoom: 18,            
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
			opacity: 0.0,
			labelClass: "labels", // the CSS class for the label
		    labelInBackground: false,
		    labelContent: jersey,
		    draggable: false
		});
		markers[name] = marker;
	});
		
	map.setCenter(findCenter());	
});

function extendBounds(){
	var bounds = new google.maps.LatLngBounds();
	var lat= 0;
	var long =0;
		for (var i in markers){
		   	lat = markers[i].getPosition().lat();
		   	long = markers[i].getPosition().lng();
		   	var latlng = new google.maps.LatLng(lat, long);
		   	
			bounds.extend(latlng);
			}
 return bounds;
}
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
	var startString = $("#raceStartTime").text();
	console.log(startString.length);
	if(startString.length > 0){
		startTime = new Date(startString);
		$("#startRace").addClass("hiddenField");
		$("#endRace").removeClass("hiddenField");
		
		$("#raceDuration").removeClass("hiddenField");
	}else{
		$("#endRace").addClass("hiddenField");
		$("#startRace").removeClass("hiddenField");
	}
	var role = $("#role").text()
	var contains = role.indexOf("ROLE_ADMIN")
	if(contains>0){
		$("#sendMsg").click(sendMsg);
		$("#saveNote").click(saveNote);
		$("#endRaceBtn").click(endRace);
		$(".riderRow").click(selectRow);
		$("#msgClearBtn").click(clearMessage);
		$("#notesClearBtn").click(clearNotes);	
		$(".msgTemplate").click(messageTemplate);
		$(".newMessage").click(messageSeen);
		$("#startRace").click(startRace);
	}else{
		$("#configBtn").addClass("ui-disabled");
		$("#startRace").addClass("ui-disabled");
		$("#endRace").addClass("ui-disabled");
		$("#startRace").addClass("ui-disabled");
		$(".msgTemplate").addClass("ui-disabled");
		$(".msgNote").addClass("ui-disabled");
		$("textarea").addClass("ui-disabled");
	}
	
	if(startTime){
		interval = setInterval(refreshDashboard,1000);		
	}
	

});

function refreshDashboard(){
	$.ajax({
		url: "refreshStat",
		type: "GET",
		dataType: "json",
		success: updateStatistics
	});
	updateRaceDuration();
}

function updateStatistics(data){	
	for(var i = 0; i < data.length; i++){
		var message = data[i].message
		var stat = data[i].statistic;
		var riderId = stat.rider.rider_id;
		var speed = stat.speed;		
		var cadence = stat.cadence;
		var power = stat.power;
		var heartRate = stat.heart_rate;
		var longitude = stat.longitude;
		var latitude = stat.latitude;
		var row = $("#statTable").find(".riderRow").find(".rider_id").filter(function() { return $(this).text() === riderId.toString() }).parent().parent();
		var key = $(row).find(".name").text();
		var oldMsgId = $(row).find(".msgId").text();
		$(row).find(".speed").text(speed);
		$(row).find(".cadence").text(cadence);
		$(row).find(".power").text(power);
		$(row).find(".heartRate").text(heartRate);
		$(row).find(".longitude").text(longitude);
		$(row).find(".latitude").text(latitude);
		var newPosition = new google.maps.LatLng(latitude, longitude);
		markers[key].setPosition(newPosition);

		if(data[i].messageId>0){
			$(row).find(".message").find(".msgTxt").text(message);			
		}
		$(".message").click(messageSeen);
		if(message!= null && message.length > 0 && data[i].messageId!==parseInt(oldMsgId) && data[i].messageId > 0){			
			$(row).find(".message").addClass("newMessage");		
			$(row).find(".msgId").text(data[i].messageId);
		}

	}

	

	var autoCenter = $('#autocenter').prop('checked');
	if(autoCenter){
		map.setCenter(findCenter());
		map.fitBounds(extendBounds());		
	}	

}

function updateRaceDuration(){	
	var now = new Date();
	var millis = now - startTime;
	var diffHours = parseInt(millis / 1000 / 60 / 60); // diff in hours
	var diffMinutes = parseInt(millis / 1000 / 60) - diffHours*60; //remainder in minutes
	
	$("#durationHour").text(diffHours);
	$("#durationMin").text(diffMinutes);
}

function endRace(){
	$.ajax({
		url: "../admin/endRace",
		type: "GET",
		accepts: "html",
		success: function(data){
			clearInterval(interval);
			$("#raceStartTime").html(data);
			$("#endRace").addClass("ui-disabled");			
			var win = window.open('downloadLogs', '_blank');							
		}
	});	
}

function sendMsg(){
	var riderIds = $(".selectedRow").find(".rider_id");
	var messageTxt = $("#messageTxt").val();
	if(messageTxt.length > 0){
		var selectedRows;
		if(riderIds.length > 0){
			selectedRows = $(riderIds).map(function(){return $(this).text()}).get().join(",");
		}else{//if no rider is selected, the message is sent to all riders.
			selectedRows = $(".riderRow").find(".rider_id").map(function(){return $(this).text()}).get().join(",");
		}
		$.ajax({
			url: "../admin/saveMsg",
			type: "POST",	
			data: {"message": messageTxt, "recipientIds":selectedRows },
			success: function(data){
				$("#messageTxt").val("");
				$(".riderRow").removeClass("selectedRow");
			}
		});
	}	
}

function saveNote(){
	var note = $("#notesTxt").val();
	$.ajax({
		url: "../admin/saveNote",
		type: "POST",
		data: {"note": note},
		success: function(data){
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

function messageTemplate(){
	var caller = $(this).attr('id');
	var messageString = messageTemplateMap[caller];	
	$("#messageTxt").val(messageString);	
}

function messageSeen(){
	$(this).removeClass("newMessage");
}

function startRace(){
	$(this).addClass("hiddenField");
	$("#endRace").removeClass("hiddenField");	
	
	$.ajax({
		url: "../admin/startRace",
		type: "GET",
		success: function(data){
			interval = setInterval(refreshDashboard,1000);
			console.log('Start: ' + data.race_start);
			startTime = new Date(data.race_start);
			$("#raceStartTime").text(startTime);			
			$("#raceDuration").removeClass("hiddenField");
			$("#configBtn").addClass("ui-disabled");
		}
	});
	
	
	
	
	
}

