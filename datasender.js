var lat = -34.92725;
var long = 138.59999;
function startSending(){
	setInterval(sendData,5000);	
}

function sendData(){
	$.ajax({
		url: "http://localhost:8080/CadenceWebApp/riderData",
		data: getRandomData(),
		type: "GET",
		accepts: "JSON",
		dataType: "json",
		success: function(data){
			$(".statisticTable").html(data);					
		}
	});
}

function getRandomData(){
	lat = lat + .00001;
	long = long + .00001;
	return { "nickname" : "Matt", 
	         "heart_rate": getRandomNumber(),
		      "speed": getRandomNumber(),
			  "latitude": lat,
			  "longitude": long,
			  "elevation": getRandomNumber(),
			  "distance": getRandomNumber(),
			  "cadence": getRandomNumber(),
			  "power":getRandomNumber()
		   }
}

function getRandomNumber(){
	return Math.floor((Math.random() * 100) + 1);
}
//http://localhost:8080/CadenceWebApp/riderData?nickname=Matt&message=hello&heart_rate=0&speed=0&latitude=0&longitude=0&cadence=0&power=0&elevation=0&distance=0
