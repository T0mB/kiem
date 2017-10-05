<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
	function getValueShowField() {
		$('#list-value').on('click', function() {
			$('#datebox').val($(this).text());
		});
	}
</script>

<script>
	$(document)
			.ready(
					function() {
						$("#sel1")
								.change(
										function() {
											console.log($(
													"#sel1 option:selected")
													.text());
											$
													.getJSON(
															'http://localhost:8080/app/api/sensorservice/getsensorlist/'
																	+ $(
																			"#sel1 option:selected")
																			.text(),
															function(sensor) {
																$
																		.each(
																				sensor,
																				function(
																						index) {
																					console
																							.log(sensor[index].id);
																					$(
																							'#sel2')
																							.append(
																									$(
																											"<option />")
																											.val(
																													sensor[index].idsensor)
																											.text(
																													sensor[index].idsensor)); //.text(sensor[index].type)
																				});
															});
										});
					});

	$(document)
			.ready(
					function() {
						$("#sel2")
								.change(
										function() {
											$
													.getJSON(
															'http://localhost:8080/app/api/sensorservice/getsensorlist/'
																	+ $(
																			"#sel1 option:selected")
																			.text(),
															function(sensor) {
																$
																		.each(
																				sensor,
																				function(
																						index) {
																					if (sensor[index].idsensor == $(
																							"#sel2")
																							.val()) {
																						console
																								.log(sensor[index].type);
																						$(
																								'#typesensor')
																								.val(
																										sensor[index].type);
																					}
																				});
															});
										});
					});

	$(document)
			.ready(
					function() {
						$("#sel3")
								.change(
										function() {
											console.log($(
													"#sel1 option:selected")
													.text());
											$
													.getJSON(
															'http://localhost:8080/app/api/sensorservice/getsensorlist/'
																	+ $(
																			"#sel3 option:selected")
																			.text(),
															function(sensor) {
																$
																		.each(
																				sensor,
																				function(
																						index) {
																					console
																							.log(sensor[index].id);
																					$(
																							'#sel4')
																							.append(
																									$(
																											"<option />")
																											.val(
																													sensor[index].idsensor)
																											.text(
																													sensor[index].idsensor));
																				});
															});
										});
					});

	$(document)
			.ready(
					function() {
						$("#sel5")
								.change(
										function() {
											console.log($(
													"#sel1 option:selected")
													.text());
											$
													.getJSON(
															'http://localhost:8080/app/api/sensorservice/getsensorlist/'
																	+ $(
																			"#sel5 option:selected")
																			.text(),
															function(sensor) {
																$
																		.each(
																				sensor,
																				function(
																						index) {
																					console
																							.log(sensor[index].id);
																					$(
																							'#sel7')
																							.append(
																									$(
																											"<option />")
																											.val(
																													sensor[index].idsensor)
																											.text(
																													sensor[index].idsensor));
																				});
															});

											$
													.getJSON(
															'http://localhost:8080/app/api/huisservice/getlist+'
																	+ $(
																			"#sel5 option:selected")
																			.text(),
															function(huis) {
																$
																		.each(
																				huis,
																				function(
																						index) {
																					console
																							.log(huis[index].id);
																					$(
																							'#sel6')
																							.append(
																									$(
																											"<option />")
																											.val(
																													huis[index].id)
																											.text(
																													huis[index].id));
																				});
															});

										});
					});
</script>

<script>
	function showCreate() {
		$("#formcreate").show();
		$("#formupdate").hide();
		$("#formdelete").hide();
		$("#formadd").hide();
	}

	function showUpdate() {

		$("#formupdate").show();
		$("#formdelete").hide();
		$("#formcreate").hide();
		$("#formadd").hide();
	}

	function showDelete() {
		$("#formdelete").show();
		$("#formcreate").hide();
		$("#formupdate").hide();
		$("#formadd").hide();
	}

	function showAdd() {
		$("#formdelete").hide();
		$("#formcreate").hide();
		$("#formupdate").hide();
		$("#formadd").show();
	}
</script>

<script>
	function create() {

		var t = $('#inputtype').val();
		var d = $('#db').val();
		var url = 'http://localhost:8080/app/api/sensorservice/createsensor/'
				+ d + '+' + t;

		$.post(url, function(data) {
			console.log(data);
		});
	}

	function update() {

		var db = $("#sel1 option:selected").text();
		var id = $("#sel2 option:selected").text();
		var tp = $('#typesensor').val();

		var uri = 'http://localhost:8080/app/api/sensorservice/updateSensor/'
				+ db + '+' + id + '+' + tp;
		alert(tp);

		$.ajax({
			url : uri,
			method : 'PUT', // method is any HTTP method
			data : {}, // data as js object
			success : function() {
				console.log("gelukt");
			}
		});

	}

	function delete1() {
		var db = $("#sel3 option:selected").text();
		var id = $("#sel4 option:selected").text();

		var uri = 'http://localhost:8080/app/api/sensorservice/deletesensor/'
				+ db + '+' + id;

		$.ajax({
			url : uri,
			method : 'DELETE', // method is any HTTP method
			data : {}, // data as js object
			success : function() {
				console.log("gelukt");
			}
		});
	}

	function addToHouse() {
		var db = $("#sel5 option:selected").text();
		var hid = $("#sel6 option:selected").text();
		var sid = $("#sel7 option:selected").text();

		var url = 'http://localhost:8080/app/api/sensorservice/addSensorToHouse/'+ db + '+' + sid +'+'+ hid;

		$.post(url, function(data) {
			console.log(data);
		});

	}
</script>

<style>
#formcreate {
	display: none;
}

#formupdate {
	display: none;
}

#formdelete {
	display: none;
}
</style>

</head>
<body>

	<h1>LITTLE LESS WORK TO DOOOOO</h1>
	<div class="dropdown">
		<button class="btn btn-primary dropdown-toggle" type="button"
			data-toggle="dropdown" id="list-value">
			What do you wanna do? <span class="caret"></span>
		</button>
		<ul class="dropdown-menu">
			<li><button onclick="showCreate()" id="crbut">Create</button></li>
			<li><button onclick="showUpdate()">Update</button></li>
			<li><button onclick="showDelete()">Delete</button></li>
			<li><button onclick="showAdd()">Add a sensor to house</button></li>
		</ul>
	</div>

	<div class="divd">
		<form id="formcreate">
			<input name="database" id="db" type="text" placeholder="database"
				size="45"> <input name="type" id="inputtype" type="text"
				placeholder="type of sensor" size="45">
			<button onclick="create()">submit</button>
		</form>

		<form id="formupdate">
			<label for="sel1">Select database:</label> <select
				class="form-control" id="sel1">
				<option>choose a database</option>
				<option>wijk_binnenstad</option>
				<option>wijk_noordwest</option>
				<option>wijk_overvecht</option>
				<option>wijk_noordoost</option>
				<option>wijk_oost</option>
				<option>wijk_west</option>
				<option>wijk_zuid</option>
				<option>wijk_zuidwest</option>
				<option>wijk_leidsche_rijn</option>
				<option>wijk_vleuten_de_meern</option>
			</select> <label for="sel1">Select id:</label> <select class="form-control"
				id="sel2">
				<option>choose a sensor id</option>
			</select> <br /> <input name="type" id="typesensor" type="text"
				placeholder="type of sensor" size="45">
			<button onclick="update()">submit</button>
		</form>

		<form id="formdelete">
			<label for="sel1">Select database:</label> <select
				class="form-control" id="sel3">
				<option>choose a database</option>
				<option>wijk_binnenstad</option>
				<option>wijk_noordwest</option>
				<option>wijk_overvecht</option>
				<option>wijk_noordoost</option>
				<option>wijk_oost</option>
				<option>wijk_west</option>
				<option>wijk_zuid</option>
				<option>wijk_zuidwest</option>
				<option>wijk_leidsche_rijn</option>
				<option>wijk_vleuten_de_meern</option>
			</select> <label for="sel1">Select id:</label> <select class="form-control"
				id="sel4">

			</select>
			<button onclick="delete1()">submit</button>
		</form>

		<form id="formadd">
			<label for="sel1">Select database:</label> <select
				class="form-control" id="sel5">
				<option>choose a database</option>
				<option>wijk_binnenstad</option>
				<option>wijk_noordwest</option>
				<option>wijk_overvecht</option>
				<option>wijk_noordoost</option>
				<option>wijk_oost</option>
				<option>wijk_west</option>
				<option>wijk_zuid</option>
				<option>wijk_zuidwest</option>
				<option>wijk_leidsche_rijn</option>
				<option>wijk_vleuten_de_meern</option>
			</select> <label for="sel1">Select huisid:</label> <select
				class="form-control" id="sel6"></select> <label for="sel1">Select
				sensorid:</label> <select class="form-control" id="sel7">

			</select>
			<button onclick="addToHouse()">submit</button>
		</form>


	</div>

</body>
</html>