function startSending(){
	setInterval(sendData,5000);	
}

function sendData(){
	$.ajax({
		url: "http://localhost:8080/CadenceWebApp/riderData",
		data: getRandomData(),
		type: "POST",
		accepts: "html",
		success: function(data){
			$(".statisticTable").html(data);					
		}
	});
}

function getRandomData(){
	return { "nickname" : "Matt", 
	         "heart_rate": getRandomNumber(),
		      "speed": getRandomNumber(),
			  "latitude": getRandomNumber(),
			  "longitude": getRandomNumber(),
			  "elevation": getRandomNumber(),
			  "double": getRandomNumber(),
			  "distance": getRandomNumber(),
			  "cadence": getRandomNumber(),
			  "power":getRandomNumber()
		   }
}

function getRandomNumber(){
	return Math.floor((Math.random() * 100) + 1);
}

