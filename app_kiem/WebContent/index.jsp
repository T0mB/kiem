<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<script>
	
	function myFunction() {
	
		
		$(".demo").html("");

		$(".demo").append('ids are:');
	    $.getJSON('http://localhost:8080/app/api/huisservice/getlist+wijk_binnenstad', function(huis) {
	    	
	        $.each(huis, function(index) {
	            console.log(huis[index].id);
	            $('.demo').append('<br/>' + huis[index].id );
	        });
	    });
		
	}
</script>

<script>
	
	function getSensorData() {
	
		
		$(".sensordata").html("");
		$.getJSON('http://localhost:8080/app/api/recordingservice/getdataforhouse/wijk_binnenstad/' + $('#huisidfield').val(), function(sensor) {
	    	
	        $.each(sensor, function(index) {
	            $('.sensordata').append('<br/>' + sensor[index].id );
	            $('.sensordata').append('<br/>' + sensor[index].data );
	        });
	    });
		
	}
</script>


</head>
<body>

	<div class="container">
		<div id="divhuis">
			<h2>Houses</h2>
			<p class="demo"></p>
		</div>
	</div>

	<button onclick="myFunction()">Click me for house ids</button> <p/>
	
	<input type="text" id="huisidfield" placeholder="typ house id"/><br/>
	<button onclick="getSensorData()">Click me for sensor data</button><br/>
	<p class="sensordata"></p>
	
	<p class="links">
		<a href="http://localhost:8080/app/addSensor.jsp">Add a sensor</a>
	</p>


</body>

</html>