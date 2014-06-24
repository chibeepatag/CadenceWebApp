$(document).ready(function() {
	tableRowColor();
	selectAllRiders();
	$(".riderRow").click(selectRiderRow);
	var map;
	
	google.maps.event.addDomListener(window, 'load', initialize);
	$("#logoutButton").click(logout);
			
});

function logout(){
	 window.location = "login.html";
}


function initialize() {
	var mapOptions = {
		zoom : 15,
		center : new google.maps.LatLng(-34.92820, 138.60091)
	};
	map = new google.maps.Map(document.getElementById('map-canvas'),
			mapOptions);
}

/*
 * Paints the table rows in alternating colors.
 */
function tableRowColor() {
	$("table").find("tr").addClass(function(index) {
		var classResult = index % 2 == 0 ? "oddRowBlue" : "evenRowBlue";
		return classResult;
	});
}

function selectAllRiders() {
	$('#checkAll').click(function(event) { // on click
		if (this.checked) { // check select status
			$('.riderCheck').each(function() { // loop through each checkbox
				this.checked = true; // select all checkboxes with class
										// "checkbox1"
			});
		} else {
			$('.riderCheck').each(function() { // loop through each checkbox
				this.checked = false; // deselect all checkboxes with class
										// "checkbox1"
			});
		}
	});

}

function selectRiderRow(){
	var checkBox=$(this).find(".riderCheck");
	var isCheck = $(checkBox).is(':checked');
	console.log(isCheck);
	
	if(isCheck){		
		$(checkBox).prop('checked', false); 
	}else{
		$(checkBox).prop('checked', true); 
	}
}