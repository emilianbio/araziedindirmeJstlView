<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
/* input {
	border: 2px solid lightblue;
	border-radius: 7px;
	outline: none;
} */
select {
	/*border: 2px solid lightblue;*/
	border-radius: 5px;
	outline: 2px;
}

.text-danger {
	color: #ff0000;
	font-style: italic;
}
</style>

<c:if test="${kullanici.id==0 }">
	<script type="text/javascript">
		var jq = jQuery.noConflict();

		jq(document).ready(function() {
			jq("#cepTelefonu").val('');
		});
	</script>

</c:if>
<div class="jumbatron">
	<div class="container">${errors }</div>

</div>

<form:form method="POST" action="kullaniciEkle" commandName="kullanici"
	enctype="multipart/form-data">

	<table class="table">
		<tr>
			<td>İsim Soyisim</td>
			<td><form:input path="adi" class="form-control input-xs" /></td>
			<td><form:errors path="*" cssClass="text-danger" /></td>
			<td>Kullanici Adı</td>
			<td><form:input path="isimSoyisim" /></td>
			<td><form:errors path="*" cssClass="text-danger" /></td>
			<c:if test="${cookie.id.value==1 }">
				<td>Rol</td>
				<td><form:select path="roles.id" items="${roller}"
						class="selectpicker" data-live-search="true" itemLabel="rollAdi"
						data-tokens="id" itemValue="id">
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
			<td><form:input path="cepTelefonu" id="cepTelefonu" />asd</td>
			<td><form:errors path="*" cssClass="text-danger" /></td>
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
			<td>Resim</td>

			<c:if test="${kullanici.id ne 0}">
				<td><form:input type="image" path="pic" width="100px"
						src="${pageContext.request.contextPath}/kullanici-islemleri/photoGoster2?id=${kullanici.id} " /></td>
			</c:if>
			<c:if test="${kullanici.id eq 0}">
				<td><form:input type="image" path="pic" width="50px"
						height="50px"
						src="${pageContext.request.contextPath}/assets/images/personelimage/avatar" /></td>
			</c:if>
			<td><input type="file" name="file" multiple="multiple" />
				${message }</td>
		</tr>
		<tr>
			<c:if test="${kullanici.id!=0 }">
				<td align="center"><input type="button" value="Vazgeç"
					onclick="javascript:location.href='./kullaniciVazgec'"
					class="btn btn-danger"></td>
			</c:if>
			<td colspan="6" align="center"><input type="submit"
				value="Kaydet" class="btn btn-info"></td>
		</tr>
	</table>
</form:form>
<c:if test="${cookie.id.value==1 }">

	<table class="table table-striped">
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
			<td>Resim</td>
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
				<td><c:if test="${!empty kullanici.pic  }">

						<a href="#"><img
							src="${pageContext.request.contextPath}/kullanici-islemleri/photoGoster2?id=${kullanici.id} "
							width="50px" height="50px" /></a>
					</c:if> <c:if test="${empty kullanici.pic  }">

						<img src="<c:url value="/assets/images/personelimage/avatar"/>"
							class="pic_ina " width="50px" height="50px" />

					</c:if></td>
				<!--${pageContext.request.contextPath}/kullanici-islemleri/productImage?id=${kullanici.id}  -->
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

<%-- <form class="form-horizontal">
	<fieldset>

		<!-- Form Name -->
		<legend>Kullanici Tanimlama</legend>

		<!-- Text input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="adi">Adı</label>
			<div class="col-md-4">
				<input id="adi" name="adi" type="text" placeholder="Adı"
					class="form-control input-xs" required=""> <span
					class="help-block">Ör:Emrah DENİZER</span>
			</div>
		</div>

		<!-- Text input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="kullaniciAdi">Kullanıcı
				Adı</label>
			<div class="col-md-4">
				<input id="kullaniciAdi" name="kullaniciAdi" type="text"
					placeholder="Kullanıcı Adı" class="form-control input-md"
					required="required"> <span class="help-block">Ör:emrah.denizer</span>
			</div>
		</div>

		<!-- Password input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="sifre">Şifre</label>
			<div class="col-md-4">
				<input id="sifre" name="sifre" type="password" placeholder="şifre"
					class="form-control input-md" required=""> <span
					class="help-block">Harf-karakter-rakam</span>
			</div>
		</div>

		<!-- Password input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="sifreTekrar">Şifre
				Tekrarı</label>
			<div class="col-md-4">
				<input id="sifreTekrar" name="sifreTekrar" type="password"
					placeholder="şifre" class="form-control input-md" required="">
				<span class="help-block">Harf-karakter-rakam</span>
			</div>
		</div>

		<!-- Text input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="unvan">Ünvan</label>
			<div class="col-md-4">
				<input id="unvan" name="unvan" type="text" placeholder="ünvan"
					class="form-control input-md" required=""> <span
					class="help-block">Ör:Mühendis</span>
			</div>
		</div>

		<!-- Text input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="sicilNo">Sicil No</label>
			<div class="col-md-4">
				<input id="sicilNo" name="sicilNo" type="text"
					placeholder="sicil no" class="form-control input-md" required="">
				<span class="help-block">Ör: 2012-1552</span>
			</div>
		</div>

		<!-- Text input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="birim">Birim</label>
			<div class="col-md-4">
				<input id="birim" name="birim" type="text" placeholder="birim"
					class="form-control input-md" required=""> <span
					class="help-block">Ör:Arazi Edindirme</span>
			</div>
		</div>

		<!-- Text input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="ePosta">E-Mail</label>
			<div class="col-md-4">
				<input id="ePosta" name="ePosta" type="text" placeholder="@mail"
					class="form-control input-md" required=""> <span
					class="help-block">Ör:emrah.denizer@tarim.gov.tr</span>
			</div>
		</div>

		<!-- Text input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="cepTelefonu">Cep
				Telefonu</label>
			<div class="col-md-4">
				<input id="cepTelefonu" name="cepTelefonu" type="text"
					placeholder="cep telefonu" class="form-control input-md"
					required=""> <span class="help-block">Ör:555 6667788</span>
			</div>
		</div>

		<!-- Multiple Radios -->
		<div class="form-group">
			<label class="col-md-4 control-label" for="izinHakki">İzin
				Hakkı</label>
			<div class="col-md-4">
				<div class="radio">
					<label for="izinHakki-0"> <input type="radio"
						name="izinHakki" id="izinHakki-0" value="2" checked="checked">
						20 gün
					</label>
				</div>
				<div class="radio">
					<label for="izinHakki-1"> <input type="radio"
						name="izinHakki" id="izinHakki-1" value="3"> 30 Gün
					</label>
				</div>
			</div>
		</div>

		<!-- Select Basic -->
		<div class="form-group">
			<label class="col-md-4 control-label" for="kullaniciRol">Rol</label>
			<div class="col-md-4">
				<select id="kullaniciRol" name="kullaniciRol" class="form-control">
					<option value="1">Option one</option>
					<option value="2">Option two</option>
				</select>
			</div>
		</div>

		<!-- Multiple Radios -->
		<div class="form-group">
			<label class="col-md-4 control-label" for="durum">User Status</label>
			<div class="col-md-4">
				<div class="radio">
					<label for="durum-0"> <input type="radio" name="durum"
						id="durum-0" value="1" checked="checked"> Active
					</label>
				</div>
				<div class="radio">
					<label for="durum-1"> <input type="radio" name="durum"
						id="durum-1" value="0"> Passive
					</label>
				</div>
			</div>
		</div>     
		<!-- Button -->
		<div class="form-group">
			<label class="col-md-4 control-label" for="submitButton"></label>
			<div class="col-md-4">
				<button id="submitButton" name="submitButton"
					class="btn btn-primary btn-md pull-right ">Kaydet</button>
			</div>
		</div>
		<!-- Button Drop Down -->
		<div class="form-group">
			<label class="col-md-4 control-label" for="buttondropdown">Button
				Drop Down</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="buttondropdown" name="buttondropdown"
						class="form-control" placeholder="placeholder" type="text"
						required="">
					<div class="input-group-btn">
						<button type="button" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown">
							Action <span class="caret"></span>
						</button>
						<ul class="dropdown-menu pull-right">
							<li><a href="#">Option one</a></li>
							<li><a href="#">Option two</a></li>
							<li><a href="#">Option three</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

	</fieldset>
</form>
 --%>
