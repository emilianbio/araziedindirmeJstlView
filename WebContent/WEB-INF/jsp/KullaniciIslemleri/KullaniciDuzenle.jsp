<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
input {
	border: 2px solid lightblue;
	border-radius: 7px;
	outline: none;
}

select {
	/*border: 2px solid lightblue;*/
	border-radius: 5px;
	outline: 2px;
}
/* table {
	width: 300px;
	float: left;
	display: inline;
	padding: 10px;
	float: left;
} */
</style>

<c:if test="${kullanici.id==0 }">
	<script type="text/javascript">
		var jq = jQuery.noConflict();

		jq(document).ready(function() {
			jq("#cepTelefonu").val('');
		});
	</script>

</c:if>
<form:form method="get" action="kullaniciEkle" commandName="kullanici">

	<table class="table">
		<tr>
			<td>İsim Soyisim</td>
			<td><form:input path="adi" /></td>
			<td>Kullanici Adı</td>
			<td><form:input path="isimSoyisim" /></td>
			<c:if test="${cookie.id.value==1 }">
				<td>Rol</td>
				<td><form:select path="roles.id" items="${roller}"
						itemLabel="rollAdi" itemValue="id">
						<%-- <form:option value="0">---Seçiniz------------</form:option>
					<form:option value="2">20 Gün</form:option>
					<form:option value="3">30 Gün</form:option> --%>
					</form:select></td>
			</c:if>


			<td>Şifre</td>
			<td><form:input path="sifre" /></td>

		</tr>
		<tr>
			<td>Ünvanı</td>
			<td><form:input path="unvan" /></td>
			<td>Sicil No</td>
			<td><form:input path="sicilNo" /></td>
			<td>Birimi</td>
			<td><form:input path="birim" /></td>
			<td>İzin Hakkı</td>
			<td><form:select path="izinHakki">
					<form:option value="0">---Seçiniz------------</form:option>
					<form:option value="2">20 Gün</form:option>
					<form:option value="3">30 Gün</form:option>
				</form:select></td>

		</tr>
		<tr>

			<td>E-Posta</td>
			<td><form:input path="ePosta" /></td>
			<td>Cep Telefonu</td>
			<td><form:input path="cepTelefonu" id="cepTelefonu" /></td>

			<c:if test="${cookie.id.value==1 }">
				<td>Durum</td>
				<td><form:select path="durum" id="durum">
						<form:option value="9">---Seçiniz------------</form:option>
						<form:option value="1">Aktif</form:option>
						<form:option value="0">Pasif</form:option>
					</form:select></td>
			</c:if>
		</tr>
		<tr>
			<c:if test="${kullanici.id!=0 }">
				<td align="center"><input type="button" value="Vazgeç"
					onclick="javascript:location.href='./kullaniciVazgec'"
					class="btn btn-danger"></td>
			</c:if>
			<td colspan="6" align="center"><input type="submit"
				value="Kaydet" class="btn btn-info"></td>
	</table>
</form:form>
<c:if test="${kullanici.id==1 }">

	<table class="table">
		<tr>
			<td>Sıra</td>
			<td>İsim Soyisim</td>
			<td>Rol</td>
			<td>Şifre</td>
			<td>Sicil No</td>
			<td>Birimi</td>
			<td>Ünvanı</td>
			<td>E-Posta</td>
			<td>Cep Telefonu</td>
			<td>Durumu</td>
		</tr>
		<c:forEach items="${kullaniciListesi}" var="kullanici"
			varStatus="siraNo">
			<tr>
				<td><a
					href="${pageContext.request.contextPath}/kullanici-islemleri/kullaniciGuncelle/${kullanici.id}">${siraNo.count }</a>
				</td>
				<td>${kullanici.adi}</td>
				<td>${kullanici.roles.rollAdi}</td>
				<td>**********</td>
				<td>${kullanici.sicilNo}</td>
				<td>${kullanici.birim}</td>
				<td>${kullanici.unvan}</td>
				<td>${kullanici.ePosta}</td>
				<td>${kullanici.cepTelefonu}</td>
				<c:if test="${kullanici.durum=='1'.charAt(0)}">
					<td>Aktif</td>
				</c:if>
				<c:if test="${kullanici.durum=='0'.charAt(0)}">
					<td>Pasif</td>
				</c:if>
				<c:if test="${kullanici.durum=='9'.charAt(0)}">
					<td>---HATA---</td>
				</c:if>


			</tr>
		</c:forEach>
	</table>
</c:if>