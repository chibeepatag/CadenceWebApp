[1mdiff --git a/src/main/java/au/edu/cmu/model/Race.java b/src/main/java/au/edu/cmu/model/Race.java[m
[1mindex 7c8d33f..0467116 100644[m
[1m--- a/src/main/java/au/edu/cmu/model/Race.java[m
[1m+++ b/src/main/java/au/edu/cmu/model/Race.java[m
[36m@@ -104,6 +104,10 @@[m [mpublic class Race {[m
 [m
 	public void setCoach(User coach) {[m
 		this.coach = coach;[m
[31m-	}	[m
[32m+[m	[32m}[m[41m[m
[32m+[m[41m	[m
[32m+[m	[32mpublic String getStartTimeInMilliSeconds(){[m[41m		[m
[32m+[m		[32mreturn Long.toString(this.race_start.getTime());[m[41m[m
[32m+[m	[32m}[m[41m[m
 	[m
 }[m
[1mdiff --git a/src/main/webapp/WEB-INF/templates/shared/dashboard.html b/src/main/webapp/WEB-INF/templates/shared/dashboard.html[m
[1mindex a82c35b..355a161 100644[m
[1m--- a/src/main/webapp/WEB-INF/templates/shared/dashboard.html[m
[1m+++ b/src/main/webapp/WEB-INF/templates/shared/dashboard.html[m
[36m@@ -1,5 +1,7 @@[m
 <!DOCTYPE html>[m
[31m-<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">[m
[32m+[m[32m<html xmlns="http://www.w3.org/1999/xhtml"[m
[32m+[m	[32mxmlns:th="http://www.thymeleaf.org"[m
[32m+[m	[32mxmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">[m
 <head>[m
 <meta charset="ISO-8859-1" />[m
 <title>Dashboard</title>[m
[36m@@ -37,25 +39,32 @@[m
 	src="http://google-maps-utility-library-v3.googlecode.com/svn/tags/markerwithlabel/1.1.8/src/markerwithlabel.js"></script>[m
 </head>[m
 <body>[m
[31m-	<div data-role="page" id="page">		[m
[32m+[m	[32m<div data-role="page" id="page">[m
 		<div data-role="header" data-theme="a">[m
[31m-			<h1>Race Management System</h1>	[m
[31m-			<button disabled="" class="ui-btn ui-btn-inline ui-icon-user ui-btn-icon-left" sec:authentication="name">User</button>			[m
[32m+[m			[32m<h1 th:text="${currentRace.race_name}" id="raceName"></h1>[m
[32m+[m			[32m<button disabled=""[m
[32m+[m				[32mclass="ui-btn ui-btn-inline ui-icon-user ui-btn-icon-left"[m
[32m+[m				[32msec:authentication="name">User</button>[m
 			<a th:href="@{/j_spring_security_logout}" data-icon="power"[m
 				class="ui-btn-right">Logout</a>[m
 		</div>[m
 [m
 		<div data-role="content">[m
[31m-			[m
[32m+[m
 			<div class="ui-content" id="map-canvas">[m
[31m-					<!-- map loads here... -->[m
[31m-			</div>[m
[31m-			<div class="autocenterDiv">[m
[31m-				<span><input type="checkbox" id="autoCenter"/></span><span class="autocenter">Autocenter </span>[m
[32m+[m				[32m<!-- map loads here... -->[m
 			</div>[m
[32m+[m
[32m+[m			[32m<form>[m
[32m+[m				[32m<fieldset data-role="controlgroup" data-type="horizontal">[m
[32m+[m					[32m<input type="checkbox" name="checkbox-h-2a" id="checkbox-h-2a"/>[m
[32m+[m					[32m<label for="checkbox-h-2a">Autocenter</label>[m[41m					[m
[32m+[m				[32m</fieldset>[m
[32m+[m			[32m</form>[m
[32m+[m
 			<div id="raceDetails">[m
[31m-				Event: <span th:text="${currentRace.race_name}" id="raceName"></span><br />[m
[31m-				Start Time: <span th:text="${currentRace.race_start}"></span>[m
[32m+[m				[32m<span th:text="${currentRace.startTimeInMilliSeconds}" id="raceStartTime" class="hiddenField"></span>[m
[32m+[m				[32mRace duration: <span id="duration"></span>[m
 			</div>[m
 [m
 			<div>[m
[36m@@ -74,33 +83,34 @@[m
 				<div class="statisticTable"[m
 					th:replace="shared/statisticTable :: statTable"></div>[m
 				<div class="endAndNotes">[m
[31m-				<div>[m
[31m-					<a href="#messageAndNotes" data-rel="close"><button[m
[31m-							data-icon="edit">Message and Notes</button></a>[m
[31m-				</div>[m
[31m-				<div id="endRaceContainer">[m
[31m-					<div sec:authorize="hasRole('ROLE_ADMIN')">[m
[31m-						<a href="#endRaceDiv" data-rel="popup" data-position-to="window"[m
[31m-							data-transition="pop"[m
[31m-							class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-icon-delete ui-btn-icon-left ui-btn-b">End[m
[31m-							Race</a>[m
[32m+[m					[32m<div>[m
[32m+[m						[32m<a href="#messageAndNotes" data-rel="close"><button[m
[32m+[m								[32mdata-icon="edit">Message and Notes</button></a>[m
 					</div>[m
[31m-					<div data-role="popup" id="endRaceDiv" data-overlay-theme="b"[m
[31m-						data-theme="b" data-dismissible="false" style="max-width: 400px;">[m
[31m-						<div data-role="header" data-theme="a" >[m
[31m-							<h1>End Race</h1>[m
[32m+[m					[32m<div id="endRaceContainer">[m
[32m+[m						[32m<div sec:authorize="hasRole('ROLE_ADMIN')">[m
[32m+[m							[32m<a href="#endRaceDiv" data-rel="popup" data-position-to="window"[m
[32m+[m								[32mdata-transition="pop"[m
[32m+[m								[32mclass="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-icon-delete ui-btn-icon-left ui-btn-b">End[m
[32m+[m								[32mRace</a>[m
 						</div>[m
[31m-						<div role="main" class="ui-content">[m
[31m-							<h3 class="ui-title">Are you sure you want to end this race?</h3>[m
[31m-							<p>This action cannot be undone.</p>[m
[31m-							<a href="#"[m
[31m-								class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-b"[m
[31m-								data-rel="back">Cancel</a> <a href="#" id="endRaceBtn"[m
[31m-								class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-b"[m
[31m-								data-rel="back" data-transition="flow">End</a>[m
[32m+[m						[32m<div data-role="popup" id="endRaceDiv" data-overlay-theme="b"[m
[32m+[m							[32mdata-theme="b" data-dismissible="false" style="max-width: 400px;">[m
[32m+[m							[32m<div data-role="header" data-theme="a">[m
[32m+[m								[32m<h1>End Race</h1>[m
[32m+[m							[32m</div>[m
[32m+[m							[32m<div role="main" class="ui-content">[m
[32m+[m								[32m<h3 class="ui-title">Are you sure you want to end this[m
[32m+[m									[32mrace?</h3>[m
[32m+[m								[32m<p>This action cannot be undone.</p>[m
[32m+[m								[32m<a href="#"[m
[32m+[m									[32mclass="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-b"[m
[32m+[m									[32mdata-rel="back">Cancel</a> <a href="#" id="endRaceBtn"[m
[32m+[m									[32mclass="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-b"[m
[32m+[m									[32mdata-rel="back" data-transition="flow">End</a>[m
[32m+[m							[32m</div>[m
 						</div>[m
 					</div>[m
[31m-					</div>[m
 				</div>[m
 			</div>[m
 		</div>[m
[36m@@ -110,16 +120,18 @@[m
 			<div class="messageContainer" sec:authorize="hasRole('ROLE_ADMIN')">[m
 				<span>Message: &nbsp;</span>[m
 				<textarea rows="5" cols="45" id="messageTxt"></textarea>[m
[31m-				<div class="ui-grid-b">				[m
[31m-								[m
[32m+[m				[32m<div class="ui-grid-b">[m
[32m+[m
 					<button id="left" class="msgTemplate ui-block-a">Left</button>[m
[31m-					<button id="right" class="msgTemplate ui-block-b">Right</button>					[m
[31m-					[m
[31m-					<button id="breakAway" class="msgTemplate ui-block-a">Break </button>[m
[31m-					<button id="timeGap" class="msgTemplate ui-block-b">Time gap</button>[m
[31m-					[m
[32m+[m					[32m<button id="right" class="msgTemplate ui-block-b">Right</button>[m
[32m+[m
[32m+[m					[32m<button id="breakAway" class="msgTemplate ui-block-a">Break[m
[32m+[m					[32m</button>[m
[32m+[m					[32m<button id="timeGap" class="msgTemplate ui-block-b">Time[m
[32m+[m						[32mgap</button>[m
[32m+[m
 					<button id="safety" class="msgTemplate ui-block-b">Safety</button>[m
[31m-				</div>				[m
[32m+[m				[32m</div>[m
 				<button id="sendMsg">Send</button>[m
 				<button id="msgClearBtn">Reset</button>[m
 			</div>[m
[36m@@ -130,9 +142,9 @@[m
 				<button id="notesClearBtn">Reset</button>[m
 			</div>[m
 			<a href="downloadLogs" target="_blank">[m
[31m-			<button id="downloadMsgNotes"[m
[31m-				class="ui-btn ui-shadow ui-corner-all ui-btn-icon-left ui-icon-arrow-d">[m
[31m-				Messages and Notes</button>[m
[32m+[m				[32m<button id="downloadMsgNotes"[m
[32m+[m					[32mclass="ui-btn ui-shadow ui-corner-all ui-btn-icon-left ui-icon-arrow-d">[m
[32m+[m					[32mMessages and Notes</button>[m
 			</a>[m
 		</div>[m
 [m
[1mdiff --git a/src/main/webapp/javascript/dashboard.js b/src/main/webapp/javascript/dashboard.js[m
[1mindex 6ec3f02..647247c 100644[m
[1m--- a/src/main/webapp/javascript/dashboard.js[m
[1m+++ b/src/main/webapp/javascript/dashboard.js[m
[36m@@ -6,6 +6,7 @@[m [mvar markers = {};[m
 var interval;[m
 var map;[m
 var messageTemplateMap={"left":"Left", "right":"Right", "breakAway":"Break away", "timeGap":"Time Gap", "safety":"Safety"};[m
[32m+[m[32mvar startTime;[m
 [m
 $( document ).on( "pageinit", "#page", function() {[m
     [m
[36m@@ -72,6 +73,8 @@[m [mfunction findCenter(){[m
 [m
 $(document).ready(function(){		[m
 	interval = setInterval(refreshDashboard,5000);[m
[32m+[m	[32mvar startString = $("#raceStartTime").text();[m[41m [m
[32m+[m	[32mstartTime = new Date(startString);[m
 	$("#sendMsg").click(sendMsg);[m
 	$("#saveNote").click(saveNote);[m
 	$("#endRaceBtn").click(endRace);[m
[36m@@ -89,6 +92,7 @@[m [mfunction refreshDashboard(){[m
 		dataType: "json",[m
 		success: updateStatistics[m
 	});[m
[32m+[m	[32m//updateRaceDuration();[m
 }[m
 [m
 function updateStatistics(data){	[m
[36m@@ -127,7 +131,7 @@[m [mfunction updateStatistics(data){[m
 [m
 	[m
 [m
[31m-	var autoCenter = $('#autoCenter').prop('checked');[m
[32m+[m	[32mvar autoCenter = $('#checkbox-h-2a').prop('checked');[m
 	if(autoCenter){[m
 		map.setCenter(findCenter());[m
 		map.fitBounds(extendBounds());		[m
[36m@@ -135,6 +139,11 @@[m [mfunction updateStatistics(data){[m
 [m
 }[m
 [m
[32m+[m[32mfunction updateRaceDuration(){[m[41m	[m
[32m+[m[41m	[m
[32m+[m	[32m$("#duration").text();[m
[32m+[m[32m}[m
[32m+[m
 function endRace(){[m
 	$.ajax({[m
 		url: "../admin/endRace",[m
