<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Spring MVC file upload example</h1>

	<form method="POST"
		action="${pageContext.request.contextPath}/kullanici-islemleri/upload"
		enctype="multipart/form-data">
		<select name="id">
			<c:forEach items="${kullaniciListesi }" var="kullanici">

				<option value="${kullanici.id}" label="${kullanici.adi}">
			</c:forEach>


		</select> <input type="file" name="file" /><br /> <input type="submit"
			value="Submit" />
	</form>
	${message }
</body>
</html>