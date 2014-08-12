var lat = -34.92725;
var long = 138.59999;
function startSending(){
	setInterval(sendData,1000);	
}

function sendData(){
	$.ajax({
		url: "http://localhost:8080/CadenceWebApp/riderData",
		data: getRandomData("Matt"),
		type: "GET",
		accepts: "JSON",
		dataType: "json",
		success: function(data){
			$(".statisticTable").html(data);					
		}
	});
	
	$.ajax({
		url: "http://localhost:8080/CadenceWebApp/riderData",
		data: getRandomData("Nelson"),
		type: "GET",
		accepts: "JSON",
		dataType: "json",
		success: function(data){
			$(".statisticTable").html(data);					
		}
	});
	
	$.ajax({
		url: "http://localhost:8080/CadenceWebApp/riderData",
		data: getRandomData("Rob"),
		type: "GET",
		accepts: "JSON",
		dataType: "json",
		success: function(data){
			$(".statisticTable").html(data);					
		}
	});
	
	$.ajax({
		url: "http://localhost:8080/CadenceWebApp/riderData",
		data: getRandomData("Irish"),
		type: "GET",
		accepts: "JSON",
		dataType: "json",
		success: function(data){
			$(".statisticTable").html(data);					
		}
	});
	
	$.ajax({
		url: "http://localhost:8080/CadenceWebApp/riderData",
		data: getRandomData("German"),
		type: "GET",
		accepts: "JSON",
		dataType: "json",
		success: function(data){
			$(".statisticTable").html(data);					
		}
	});
}

function getRandomData(nickname){
	lat = lat + .00001;
	long = long + .00001;
	return { "nickname" : nickname, 
	         "heart_rate": getRandomNumber(),
		      "speed": getRandomNumber(),
		      "distance" :getRandomNumber(),
			  "latitude": lat,
			  "longitude": long,
			  "cadence": getRandomNumber(),
			  "power":getRandomNumber(),
			  "message": "hello"
		   }
}

function getRandomNumber(){
	return Math.floor((Math.random() * 100) + 1);
}
startSending();
//http://localhost:8080/CadenceWebApp/riderData?nickname=Matt&message=borntobewild&heart_rate=0&speed=0&latitude=-34.92687&longitude=138.59983&cadence=0&power=0&elevation=0&distance=0
