$(document).ready(function(){
	$(".riderRow").click(selectRow);
	$("#addRider").click(addRider);
});

function selectRow(){
	console.log("clicked row");
	var isSelected = $(this).hasClass("selectedRow");
	console.log(isSelected);
	
	if(isSelected){
		$(this).removeClass("selectedRow");
	}else{
		$(this).addClass("selectedRow");		
	}
}

function addRider(){	
	$.ajax({
		url: "addRider",
		data: $("#newRiderForm").serialize(),
		type: "POST",
		success: appendNewRider
	});
}

function appendNewRider(){
	console.log("append Rider")
}