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
<title>Error |AraziEdindirme</title>
<!-- <link rel="stylesheet"
	href="http://openlayers.org/en/v3.18.2/css/ol.css" type="text/css"> -->
<!-- The line below is only needed for old environments like Internet Explorer and Android 4.x -->
<!-- <script
	src="http://cdn.polyfill.io/v2/polyfill.min.js?features=requestAnimationFrame,Element.prototype.classList,URL"></script> -->
<link href="<c:url value="/assets/css/bootstrap.min.css" />"
	rel="stylesheet">
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



</head>
<body>

	<!-- header -->
	<jsp:include page="header.jsp" />
	<!-- /header -->

	<!-- sidebar -->
	<jsp:include page="sidebar.jsp" />
	<!-- /sidebar -->
	<h1>HATA</h1>
	<h1>${errorMessage}</h1>
	<c:if test="${empty errorMessage}">
		<div id="page-content-wrapper" class="page-content-toggle">


			<div class="container-fluid">

				<div class="row">
					<div id="content" class="col-md-8 col-md-offset-1 col-xs-12">
						<h3>Sayfa Bulunamadı...</h3>
						<p>
						<h4>
							İşlem aypmak için lütfen <a href="/araziedindirme">giriş</a>
							yapınız...
						</h4>
						</p>


					</div>
				</div>
				<!-- /row -->
			</div>

			<!-- footer -->
			<jsp:include page="footer.jsp" />
			<!-- /footer -->
		</div>
	</c:if>
</body>
</html>