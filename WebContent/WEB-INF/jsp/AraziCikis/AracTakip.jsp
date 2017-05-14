<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
input {
	width: 100px;
}

select {
	outline: none !important;
}

#date {
	width: 10em;
}
</style>

<%-- <c:if test="${ arac.mahalle.id != 0}">
	<script type="text/javascript">
		alert("${arac.mahalle.id}")
		ikisibiradamarkala("${arac.ilce.id}")
	</script>
</c:if> --%>
<script type="text/javascript">
	jq(document).ready(function() {
		jq('#ozelPlaka').keypress(function(e) {
			var regex = new RegExp("^[a-zA-Z0-9-]+$");
			var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
			if (regex.test(str)) {
				return true;
			}

			e.preventDefault();
			return false;
		});

	})

	function buyukHarf() {
		jq('input[type=text]').val(function() {
			/* this.value.replace(" ", ""); */

			return this.value.toUpperCase().replace(" ", "");
		})

	};

	

	function formControl(deger) {
		
	
		if(jq('#slctMarka').val()==0)
			{
			 alert("Mahalle Seçiniz..");
		return true;
	}else{
		
		arac.submit();
	}
	};
	
	function raporAlFormControl() {
		
		if(jq('#personelID').val()==0)
			{
			 alert("Personel Seçiniz....");
		return true;
	}else{
		
		jq('#raporAl').submit();
	}
	};
	
</script>
<table style="width: 500px !important; text-align: center;"
	class="table">

	<tr>
		<td rowspan="2">PERSONEL</td>
		<td rowspan="2">ARAÇ PLAKASI</td>
		<td rowspan="2">ÖZEL ARAÇ <input type="checkbox" id="ozelChckBox"></td>
		<td colspan="2" style="text-align: center;">GİDİLEN YER</td>
		<td ROWspan="2">TARİH</td>
		<td rowspan="2">ÇIKIŞ SAATİ</td>
		<td rowspan="2">GİRİŞ SAATİ</td>
		<td rowspan="2">İŞİN ÖZETİ</td>
	</tr>
	<tr>

		<td style="text-align: center;">İLÇE</td>
		<td style="text-align: center;">MAHALLE</td>

	</tr>
	<form:form commandName="arac" method="post" action="araziCikisEkle">
		<form:hidden path="id" />
		<tr>
			<td style="width: 200px;">
				<%-- <select name="kullanici.id"
				multiple="multiple" id="slctAltTip">
					<option value="0">Seçiniz</option>
					<c:forEach items="${girisYapanKullanici}" var="kullanici">
						<option value="${kullanici.id }">${kullanici.adi }</option>
					</c:forEach>

			</select> --%> <form:select path="kullaniciList" name="kullaniciList">
					<form:option value="0">Seçiniz</form:option>
					<form:options items="${girisYapanKullanici}" itemValue="id"
						itemLabel="adi" />
				</form:select>
				<%--  <form:checkboxes items="${girisYapanKullanici}"
					path="kullaniciList" itemLabel="id" itemValue="id"
					name="kullaniciList" id="kullaniciList" /> --%>

			</td>
			<td><form:select path="resmiPlaka" id="resmiPlaka">
					<form:option value="01R9567">01 R 9567</form:option>
				</form:select></td>
			<td><form:input path="ozelPlaka" type="text" id="ozelPlaka"
					onkeyup="buyukHarf();" /></td>
			<!--ilce-->
			<td><form:select path="ilce.id" id="slctAltTip"
					onChange="ikisibiradamarkala(this.value)">
					<form:option value="0">Seçiniz</form:option>
					<form:options items="${ilceListesi}" itemValue="id"
						itemLabel="isim" />
				</form:select></td>
			<td><form:select path="mahalle.id" id="slctMarka">
					<form:option value="0">Seçiniz</form:option>
					<form:options items="${markaListesi}" itemValue="id"
						itemLabel="isim" />
				</form:select></td>

			<td><form:input path="tarih" type="date" id="date" /></td>

			<td><form:select path="cikisSaati">
					<form:option value="08:00">08:00</form:option>
					<form:option value="08:30">08:30</form:option>
					<form:option value="09:00">09:00</form:option>
					<form:option value="09:30">09:30</form:option>
					<form:option value="10:00">10:00</form:option>
					<form:option value="10:30">10:30</form:option>
					<form:option value="11:00">11:00</form:option>
					<form:option value="11:30">11:30</form:option>
					<form:option value="12:00">12:00</form:option>
					<form:option value="12:30">12:30</form:option>
					<form:option value="13:00">13:00</form:option>
					<form:option value="13:30">13:30</form:option>
					<form:option value="14:00">14:00</form:option>
					<form:option value="14:30">14:30</form:option>
					<form:option value="15:00">15:00</form:option>
					<form:option value="15:30">15:30</form:option>
					<form:option value="15:00">16:00</form:option>
					<form:option value="15:30">16:30</form:option>
					<form:option value="15:00">17:00</form:option>
				</form:select></td>
			<td><form:select path="girisSaati">
					<form:option value="08:00">08:00</form:option>
					<form:option value="08:30">08:30</form:option>
					<form:option value="09:00">09:00</form:option>
					<form:option value="09:30">09:30</form:option>
					<form:option value="10:00">10:00</form:option>
					<form:option value="10:30">10:30</form:option>
					<form:option value="11:00">11:00</form:option>
					<form:option value="11:30">11:30</form:option>
					<form:option value="12:00">12:00</form:option>
					<form:option value="12:30">12:30</form:option>
					<form:option value="13:00">13:00</form:option>
					<form:option value="13:30">13:30</form:option>
					<form:option value="14:00">14:00</form:option>
					<form:option value="14:30">14:30</form:option>
					<form:option value="15:00">15:00</form:option>
					<form:option value="15:30">15:30</form:option>
					<form:option value="15:00">16:00</form:option>
					<form:option value="15:30">16:30</form:option>
					<form:option value="15:00">17:00</form:option>
				</form:select></td>
			<td><form:input path="aciklama" type="text" /></td>
		</tr>

		<tr>
			<td colspan="9" align="right"><input type="button"
				onclick="formControl();" class="btn btn-primary" value="Ekle" /></td>
		</tr>
	</form:form>
</table>

<table class="table table-striped"
	style="width: 100% !important; text-align: center;">

	<form:form action="raporAl" method="get" id="raporAl">
		<tr>
			<td colspan="">
			<td><select name="id" id="personelID">

					<option value="0">Seç----</option>
					<c:forEach items="${girisYapanKullanici}" var="kullanici">
						<option value="${kullanici.id}">${kullanici.adi }</option>
					</c:forEach>
			</select></td>
			<td><input type="button" value="Rapor Al"
				onclick="raporAlFormControl();"></td>
			<td colspan="10"><a href="../arazi-cikislari/raporAl">TÜM
					ÇIKIŞ RAPORLARI</a></td>


			</td>
		</tr>
	</form:form>
	<tr>
		<th>Sil</th>
		<th>Edit</th>
		<th>Sıra</th>
		<th>Personel</th>
		<th>Plaka</th>
		<th>Gidilen Yer</th>
		<th>Çıkış Tarihi</th>
		<th>Çıkış Saati</th>
		<th>Giriş Saati</th>
		<th>İşin Özeti</th>
		<th>Kaydeden</th>
		<th>Kayıt Zamani</th>
	
	 <c:forEach items="${aracCikisListesi}" var="cikis" varStatus="sira">
		<tr class="satirno${cikis.id}">

			<td><img src="<c:url value="/assets/images/Delete-32.png" />"
				width="21px" onclick="tipsil(${cikis.id})"
				title="Silmek İçin Tıklayın" /></td>
			<td><a href="./duzenle/${cikis.id}"><img
					src="<c:url value="/assets/images/duzenle.png" />" width="21px"
					title="Değiştirmek İçin Tıklayın" /></a></td>


			<td>${sira.count }</td>
			<td>${cikis.kullaniciList[sira.index].adi}</td>
			<c:if test="${!empty cikis.ozelPlaka}">
				<td>Ö-${cikis.ozelPlaka }</td>
			</c:if>
			<c:if test="${!empty cikis.resmiPlaka }">
				<td>R-${cikis.resmiPlaka }</td>
			</c:if>
			<td>${cikis.ilce.isim}-${cikis.mahalle.isim}</td>
			<td>${cikis.tarih}</td>
			<td>${cikis.cikisSaati}</td>
			<td>${cikis.girisSaati}</td>
			<td>${cikis.aciklama}</td>
			<td>${cikis.islemyapan.adi}</td>
			<td>${cikis.islemZamani}</td>
		</tr>
	</c:forEach> 

</table>



