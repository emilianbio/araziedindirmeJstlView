<%@ page language="java" contentType="text/html; charset=UTF-8"
	session="true" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>{title}</title>
</head>
<body>
	<div id="content" class="col-lg-12">
		<div class="container">
			<div class="row">
				<div class="span12">
					<div class="hero-unit center">
						<h1>Rol Yönetimi</h1>
						<div class="col-xs-5">
						<div class="table-responsive">
							<form:form action="rolEkle" commandName="roll" method="post">
								<form:hidden path="id" />
								<table class="table table-striped bg-info">
									<tr>
										<td>Rol Adı</td>
										<td><form:input path="rollAdi" /></td>
									<tr>
										<td><input type="submit" value="Ekle"></td>
									</tr>
								</table>
							</form:form>
						</div></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>