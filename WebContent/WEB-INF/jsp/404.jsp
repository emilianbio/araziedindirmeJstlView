<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML>


<html>
<head>
<meta http-equiv="Content-Type"
	content="application/vnd.ms-excel; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="<c:url value="/assets/gthbLogo.png"/>" />
<title>404 Sayfa Bulunamadı</title>
<!-- <link rel="stylesheet"
	href="http://openlayers.org/en/v3.18.2/css/ol.css" type="text/css"> -->
<!-- The line below is only needed for old environments like Internet Explorer and Android 4.x -->
<!-- <script
	src="http://cdn.polyfill.io/v2/polyfill.min.js?features=requestAnimationFrame,Element.prototype.classList,URL"></script> -->
<link href="<c:url value="/assets/css/bootstrap.min.css" />"
	rel="stylesheet">
	
	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
	
<link
	href="<c:url value="/assets/font-awesome/css/font-awesome.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/assets/css/style.css" />" rel="stylesheet">
<link rel="stylesheet"
	href="<c:url value="/assets/css/jquery.dataTables.min.css" />"
	type="text/css">
<!-- scripts -->
<script src="<c:url value="/assets/script/jquery-3.1.0.min.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/assets/script/bootstrap.min.js" />"
	type="text/javascript"></script>
<script src='<c:url value="/assets/script/canvasjs.min.js"   ></c:url>'
	type="text/javascript"></script>
<!-- <script src="http://openlayers.org/en/v3.18.2/build/ol.js"
	type="text/javascript"></script> -->


<!-- <script src="//code.jquery.com/jquery-1.12.3.js"></script>  datatable js  -->
<script
	src='<c:url value="/assets/script/jquery.dataTables.min.js"  ></c:url>'></script>
<script
	src='<c:url value="/assets/script/jquery.table2excel.js"  ></c:url>'></script>

<script src='<c:url value="/assets/script/genel2.js"  ></c:url>'></script>
<script>
	jQuery(function() {
		// #sidebar-toggle-button
		jQuery('#sidebar-toggle-button').on('click', function() {
			jQuery('#sidebar').toggleClass('sidebar-toggle');
			jQuery('#page-content-wrapper').toggleClass('page-content-toggle');
			fireResize();
		});

		// sidebar collapse behavior
		jQuery('#sidebar').on('show.bs.collapse', function() {
			jQuery('#sidebar').find('.collapse.in').collapse('hide');
		});

		// To make current link active
		(function addActiveToNestedList() {
			var pageURL = jQuery(location).attr('href');
			var URLSplits = pageURL.split('/');

			var routeURL = '/' + URLSplits[URLSplits.length - 2] + '/'
					+ URLSplits[URLSplits.length - 1];
			var activeNestedList = jQuery(
					'.sub-menu > li > a[href$="' + routeURL + '"]').parent();

			if (activeNestedList.length !== 0
					&& !activeNestedList.hasClass('active')) {
				jQuery('.sub-menu > li').removeClass('active');
				activeNestedList.addClass('active');
			}
		})();

		function fireResize() {
			if (document.createEvent) { // W3C
				var ev = document.createEvent('Event');
				ev.initEvent('resize', true, true);
				window.dispatchEvent(ev);
			} else { // IE
				element = document.documentElement;
				var event = document.createEventObject();
				element.fireEvent("onresize", event);
			}
		}

	})
</script>
<style>
.center {
	text-align: center;
	margin-left: auto;
	margin-right: auto;
	margin-bottom: auto;
	margin-top: auto;
}
</style>


</head>
<body>

	<!-- header -->
	<jsp:include page="header.jsp" />
	<!-- /header -->

	
	<div id="content" class="col-md-8 col-md-offset-1 col-xs-12">
	
<div class="container">
	<div class="row">
		<div class="span12">
			<div class="hero-unit center">
				<h1>
					Page Not Found <small><font face="Tahoma" color="red">Error
							404</font></small>
				</h1>
				<br />
				<p>
					The page you requested could not be found, either contact your
					webmaster or try again. Use your browsers <b>Back</b> button to
					navigate to the page you have prevously come from
				</p>
				<p>
					<b>Or you could just press this neat little button:</b>
				</p>
				<a href="#" class="btn btn-large btn-info"><i
					class="icon-home icon-white"></i> Take Me Home</a>
			</div>
			<br />
			<div class="thumbnail">
				<center>
					<h2>Recent Content :</h2>
				</center>
			</div>
			<br />
			<div class="thumbnail span3 center">
				<h3>Try This...</h3>
				<p>write about your error page conent here and give some fool a
					good load of information or not</p>
				<div class="hero-unit">
					<img src="http://placehold.it/100x100">
					<!--Why Not Put a Picture To Celebrate Your 404-->
					<p></p>
				</div>
				<a href="#" class="btn btn-danger btn-large"><i
					class="icon-share icon-white"></i> Take Me There...</a>
			</div>
			<div class="thumbnail span3 center">
				<h3>Try This...</h3>
				<p>write about your error page conent here and give some fool a
					good load of information or not</p>
				<div class="hero-unit">
					<img src="http://placehold.it/100x100">
					<!--Why Not Put a Picture To Celebrate Your 404-->
					<p></p>
				</div>
				<a href="#" class="btn btn-danger btn-large"><i
					class="icon-share icon-white"></i> Take Me There...</a>
			</div>
			<div class="thumbnail span3 center">
				<h3>Try This...</h3>
				<p>write about your error page conent here and give some fool a
					good load of information or not</p>
				<div class="hero-unit">
					<img src="http://placehold.it/100x100">
					<!--Why Not Put a Picture To Celebrate Your 404-->
					<p></p>
				</div>
				<a href="#" class="btn btn-danger btn-large"><i
					class="icon-share icon-white"></i> Take Me There...</a>
			</div>
			<div class="thumbnail span3 center">
				<h3>Try This...</h3>
				<p>write about your error page conent here and give some fool a
					good load of information or not</p>
				<div class="hero-unit">
					<img src="http://placehold.it/100x100">
					<!--Why Not Put a Picture To Celebrate Your 404-->
					<p></p>
				</div>
				<a href="#" class="btn btn-danger btn-large"><i
					class="icon-share icon-white"></i> Take Me There...</a>
			</div>
			<br />
			<p></p>
			<!-- By ConnerT HTML & CSS Enthusiast -->
		</div>
	</div>
</div>
	 
					</div>
				

			
</body>
</html>