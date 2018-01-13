<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Hotel Deals</title>

<link href="sbadmin/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="sbadmin/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
<link href="sbadmin/dist/css/sb-admin-2.css" rel="stylesheet">
<link href="sbadmin/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

</head>

<body>

	<div id="wrapper">
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Hotel Deals</h1>
					</div>
				</div>
				<div class="row">
					<div class="alert alert-danger" id="errors" style="display: none;"></div>
				</div>
				<div class="row">
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-6">
								<form role="form" action="api/deals/hotels" id="findForm">
									<div class="form-group">
										<label>Destination Name</label> <input class="form-control"
											name="destinationName">
									</div>

									<div class="form-group">
										<label>Min Trip Start Date</label> <input class="form-control"
											name="minTripStartDate">
									</div>

									<div class="form-group">
										<label>Max Trip Start Date</label> <input class="form-control"
											name="maxTripStartDate">
									</div>

									<div class="form-group">
										<label>Length Of Stay</label> <input class="form-control"
											name="lengthOfStay">
									</div>

									<div class="form-group">
										<label>Min Star Rating</label> <input class="form-control"
											name="minStarRating">
									</div>

									<div class="form-group">
										<label>Max Star Rating</label> <input class="form-control"
											name="maxStarRating">
									</div>

									<div class="form-group">
										<label>Min Total Rate</label> <input class="form-control"
											name="minTotalRate">
									</div>

									<div class="form-group">
										<label>Max Total Rate</label> <input class="form-control"
											name="maxTotalRate">
									</div>

									<div class="form-group">
										<label>Min Guest Rating</label> <input class="form-control"
											name="minGuestRating">
									</div>

									<div class="form-group">
										<label>Max Guest Rating</label> <input class="form-control"
											name="maxGuestRating">
									</div>
									<button type="submit" class="btn btn-default">Find</button>
									<button type="reset" class="btn btn-default">Reset</button>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- /.row -->
				<div class="row" id="results"></div>
			</div>
			<!-- /.container-fluid -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script src="sbadmin/vendor/jquery/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="sbadmin/vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="sbadmin/vendor/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="sbadmin/dist/js/sb-admin-2.js"></script>

	<script type="text/javascript">
	
	function formatDate(dateArr) {
		return dateArr.toString().replace(/,/g , "-");
	}
		
	var frm = $('#findForm');
		frm.submit(function(ev) {
			$.ajax({
				type : frm.attr('method'),
				url : frm.attr('action'),
				data : frm.serialize(),
				success : function(data) {
					if (data.hasOwnProperty("errors")) {
						resultsDiv = $('#results');
						resultsDiv.hide();
						errors = data["errors"];
						errorsDiv = $('#errors');
						errorsDiv.html("");
						$.each(errors, function(i, obj) {
							errorsDiv.append("<span>" + obj + "<span><br/>");
						});
						errorsDiv.show();
						var scrollPos =  errorsDiv.offset().top;
						 $(window).scrollTop(scrollPos);
					} else {
						errorsDiv = $('#errors');
						errorsDiv.html("");
						errorsDiv.hide();
						
						resultsDiv = $('#results');
						resultsDiv.html("");
						resultsDiv.show();
						hotels = data["offers"]["Hotel"]
						
						if(hotels == undefined) {
							resultsDiv.append("<h3>No data found.</h3>");
						} else {
						
						$.each(hotels, function(i, obj) {
							
						resultsDiv.append('<div class="well">' + 
												'<h4 class="card-title">'+ obj["hotelInfo"]["hotelName"] +'</h4>' + 
												'<img src="' + obj["hotelInfo"]["hotelImageUrl"] +'"><div class="caption"><h3>' + obj["hotelInfo"]["hotelName"] + '</h3>' +
												'<p> Location: ' + obj["hotelInfo"]["hotelCountryCode"] + ', ' + obj["hotelInfo"]["hotelCity"] + '</p>' +
												'<p> Travel Start Date: ' + formatDate(obj["offerDateRange"]["travelStartDate"]) + ' -  Travel End Date: ' + formatDate(obj["offerDateRange"]["travelEndDate"]) +' - Length Of Stay: ' + obj["offerDateRange"]["lengthOfStay"]+' </p>'+
												'<p> price: ' + obj["hotelPricingInfo"]["totalPriceValue"] + ' ' + obj["hotelPricingInfo"]["currency"] + '</p>' +
												'<p> Star Rating: ' + obj["hotelInfo"]["hotelStarRating"] + '</p>' +
												'<p> Guest Rating: ' + obj["hotelInfo"]["hotelGuestReviewRating"] + '</p></div></div></div>');
						
						});
						}
						resultsDiv.show();
						var scrollPos =  resultsDiv.offset().top;
						 $(window).scrollTop(scrollPos);
					}
				}
			});

			ev.preventDefault();
		});
	</script>

</body>

</html>
