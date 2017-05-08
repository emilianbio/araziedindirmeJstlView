<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div>
	<marquee direction=left>
		HOŞGELDİNİZ....
		<c:if test="${empty cookie.id.value }">
			<a href="/araziedindirme">Lütfen Giriş Yapınız....</a>
		</c:if>
	</marquee>

	<c:if test="${empty cookie.id.value }">
		<form:form action="login" method="post" commandName="kullanici"
			>
			<form:hidden path="id" />
			<table class="table">
				<tr>
					<td align="left">Kullanıcı Adınız</td>
					<td>: <form:input path="isimSoyisim" type="text"
							style="border-radius: 5px; border: none;"
							placeholder=" Lütfen adınızı giriniz..!" />
					</td>
				</tr>
				<tr>
					<td align="left">Şifreniz</td>
					<td>: <form:input path="sifre" type="password"
							style="border-radius: 5px; border: none;"
							placeholder=" Lütfen şifrenizi giriniz..!" />
					</td>
				</tr>
			</table>
			<input type="submit" value='GİRİŞ' class="btn btn-success">
		</form:form>
	</c:if>
</div>

