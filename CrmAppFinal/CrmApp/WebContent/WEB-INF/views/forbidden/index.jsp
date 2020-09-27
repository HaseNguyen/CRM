<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" type="image/png" sizes="16x16"
	href="<c:url value = "/assets/plugins/images/favicon.png"/>">
<title>Permission not allow!</title>
<!-- Bootstrap Core CSS -->
<link
	href="<c:url value = "/assets/bootstrap/dist/css/bootstrap.min.css"/>"
	rel="stylesheet">
<!-- animation CSS -->
<link href="<c:url value = "/assets/css/animate.css"/>" rel="stylesheet">
<!-- Custom CSS -->
<link href="<c:url value = "/assets/css/style.css"/>" rel="stylesheet">
<!-- color CSS -->
<link href="<c:url value = "/assets/css/colors/blue.css"/>" id="theme"
	rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>
	<!-- Preloader -->
	<div class="preloader">
		<div class="cssload-speeding-wheel"></div>
	</div>
	<section id="wrapper" class="error-page">
		<div class="error-box">
			<div class="error-body text-center">
				<h1>403</h1>
				<h3 class="text-uppercase">Permission not allow!</h3>
				<a href="<c:url value="/home"/>"
					class="btn btn-info btn-rounded waves-effect waves-light m-b-40">Back
					to home</a>
			</div>
			<footer class="footer text-center">2018 © Pixel Admin.</footer>
		</div>
	</section>
	<!-- jQuery -->
	<script
		src="<c:url value = "/assets/plugins/bower_components/jquery/dist/jquery.min.js"/>"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="<c:url value = "/assets/bootstrap/dist/js/bootstrap.min.js"/>"></script>
	<script type="text/javascript">
		$(function() {
			$(".preloader").fadeOut();
		});
	</script>
</body>

</html>
