<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Signin Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link href='<c:url value="/assets/css/bootstrap.min.css"/>'
	rel="stylesheet">


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link
	href='<c:url value="/assets/css/ie10-viewport-bug-workaround.css"/>'
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href='<c:url value="/assets/css/signin.css" />' rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src='<c:url value="/assets/css/ie-emulation-modes-warning.js"/>'></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


<script type="text/javascript"
	src="<c:url value="/assets/script/lib/jquery-1.11.1.js"/>"></script>
<script type="text/javascript"
	src='<c:url value="/assets/script/dist/jquery.validate.js"/>'></script>
</head>

<body onload="document.loginForm.input['sifre'].focus();">
	<c:if test="${empty cookie.id.value }">
		<div class="container">
			<form class="form-signin" action="login" method="post" autocomplete="off">
				<h3 class="form-signin-heading">Please sign in</h3>

				<label for="inputEmail" class="sr-only">Email address</label> <input
					name="isimSoyisim" type="text" id="inputEmail" class="form-control"
					placeholder="Email address" required autofocus> <label
					for="inputPassword" class="sr-only">Password</label> <input
					name="sifre" type="password" id="inputPassword"
					class="form-control" placeholder="Password" required>
				<!-- <div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					Remember me
				</label>
			</div> -->
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
					in</button>
			</form>

		</div>
		<!-- /container -->
	</c:if>

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="<c:url value="/assets/css/ie10-viewport-bug-workaround.js"/>"></script>

</body>
</html>
<c:if test="${!empty cookie.id.value }">
	<jsp:include page="Calender.jsp"></jsp:include></c:if>