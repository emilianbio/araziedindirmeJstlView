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

.alert {
	outline-color: red;
	border-color: red;
	display: none;
}

.uyariYazisi {
	font: bold;
	color: red;
}
</style>
<script type="text/javascript">

var today = new Date();
var dd = today.getDate();
var mm = today.getMonth()+1; //January is 0!
var yyyy = today.getFullYear();
jq(document).ready(function(){
 if(dd<10){
        dd='0'+dd
    } 
    if(mm<10){
        mm='0'+mm
    } 

today = yyyy+'-'+mm+'-'+dd;
document.getElementById("date").setAttribute("max", today);
});


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
		
	console.log("tarih: "+jq('#date').val());
	console.log("ilceID"+jq('#slctMarka').val());
		if(jq('#slctMarka').val()==null||jq('#slctMarka').val()==""||jq('#slctMarka').val()==0||jq('#date').val()==""||jq('#kullaniciList').val()==0||jq('#kullaniciList').val()==""||jq('#aciklama').val()==""
			||jq('#aciklama').val()==null)
			{
			if(jq('#kullaniciList').val()==0||jq('#kullaniciList').val()==""){
				jq('.personel').addClass('alert');
				jq('.personel>.uyariYazisi').text("En az '1' en fazla '5' personel seçebilirsiniz");
				jq('.alert').show();
				}else{
					
					jq('.personel').removeClass('alert');
					jq('.personel>.uyariYazisi').remove();
					
				}
				
			if(jq('#slctMarka').val()==null){
				jq('.mahalle').addClass('alert');
				jq('.mahalle>.uyariYazisi').text("Mahalle seçiniz");
				jq('.alert').show();				
				}else{
					jq('.mahalle>.uyariYazisi').remove();
					jq('.mahalle').removeClass('alert');
				}
			
			if(jq('#date').val()==""){
				jq('.tarih').addClass('alert');
				jq('.tarih>.uyariYazisi').text("Eksik yada yanlış tarih girdiniz!");
				jq('.alert').show();
				}else{
					jq('.tarih>.uyariYazisi').remove();
					jq('.tarih').removeClass('alert');
					
				}
			
			if(jq('#aciklama').val()==""){
				jq('.aciklama').addClass('alert');
				jq('.aciklama>.uyariYazisi').text("Lütfen açıklama giriniz..");
				jq('.alert').show();
				}else{
					
					jq('.aciklama>.uyariYazisi').remove();
					jq('.aciklama').removeClass('alert');
				}

			//jq('.uyariYazisi').text("asdasdad");
			//alert("Eksik Bilgileri Tamamlayınız....");
	
	return true;}
	else{
		
		arac.submit();
	}
	};
	

	jq(document).ready(function () {
		jq(".personelGoster").hide();
		
	});
	
	
	jq(document).ready(function () {
		
	jq( '#goster' ).click(function() {
		jq(".personelGoster").toggle(1000);
		
		});
	});
	
	jq(document).ready(function () {	
		
		var guncellenecekID=parseInt('${arac.id}');
		var ilceID=parseInt('${arac.ilce.id}');
		var mahalleID=parseInt('${arac.mahalle.id}');
		
		/*alert(!isNaN(guncellenecekID))*/
	if(!isNaN(guncellenecekID)){
		console.log("ilceID: "+ilceID);
			ikisibiradamarkala(ilceID);
			
			jq("#slctMarka").attr("itemValue", mahalleID);
			jq("#slctMarka").attr("itemLable", '${arac.mahalle.isim}');
	}
		
	jq(".chosen-select").chosen({max_selected_options: 5,
	no_results_text: "Kayıt Yok!",width: "200px"}
	
	);
	
	jq("#slctAltTip,.donem").chosen({
		no_results_text: "Kayıt Yok!",width: "100px"}
	
	);
	});
	
</script>
<table style="width: 500px !important; text-align: center;"
	class="table">

	<tr>
		<td rowspan="2">PERSONEL</td>
		<td rowspan="2">ARAÇ PLAKASI</td>
		<td rowspan="2">ÖZEL ARAÇ <input type="checkbox" id="ozelChckBox"></td>
		<td colspan="2" style="text-align: center;">GİDİLEN YER</td>
		<td rowspan="2" style="text-align: center;">TARİH</td>
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

			<td style="width: 200px;"><div class="personel">
					<form:select data-placeholder="Personel Seç." path="kullaniciList"
						style="height:200px;" name="kullaniciList"
						class="chosen-select chosen-ltr">
						<form:options items="${girisYapanKullanici}" itemValue="id"
							itemLabel="adi" />
					</form:select>
					<span class="uyariYazisi"></span>
				</div></td>

			<td><form:select path="resmiPlaka" id="resmiPlaka">
					<form:option value="01R9567">01 R 9567</form:option>
				</form:select></td>
			<td><form:input path="ozelPlaka" type="text" id="ozelPlaka"
					onkeyup="buyukHarf();" /></td>
			<!--ilce-->
			<td>
				<div class="ilce">
					<form:select path="ilce.id" id="slctAltTip" class="ilceSec"
						onChange="ikisibiradamarkala(this.value)">
						<form:option value=""></form:option>
						<form:options items="${ilceListesi}" itemValue="id"
							itemLabel="isim" />

					</form:select>
				</div>
			</td>
			<td>
				<div class="mahalle">
					<form:select path="mahalle.id" id="slctMarka" class="mahalleSec">

						<form:options items="${markaListesi}" itemValue="id"
							itemLabel="isim" />
					</form:select>
					<span class="uyariYazisi"></span>
				</div>
			</td>

			<td><div class="tarih">

					<form:input path="tarih" type="date" onkeydown="return false"
						id="date" />
					<span class="uyariYazisi"></span>
				</div></td>
			<td><form:select path="cikisSaati" items="${saatler}">

				</form:select></td>
			<td><form:select path="girisSaati" items="${saatler}">
				</form:select></td>
			<td><div class="aciklama">
					<form:input path="aciklama" type="text" id="aciklama" />
					<span class="uyariYazisi"></span>
				</div></td>
		</tr>

		<tr>
			<td colspan="8" align="right"><input type="button"
				onclick="formControl();" class="btn btn-primary"
				value="${tusYazisi}" /></td>

			<c:if test="${tusYazisi=='Güncelle' }">

				<td><button class="btn btn-danger"
						onclick="javascript:location.href='./vazgec'">Vazgeç</button></td>

			</c:if>
		</tr>
	</form:form>
</table>

<table class="table table-striped"
	style="width: 100% !important; text-align: center;">

	<form:form action="donemeGoreGetir" method="get" >
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td>Yıl<select data-placeholder="Yıl Seç." style="border: none;"
				name="donemYil" id="donemYil" class="donem">
					<option value="" label="--- Seçiniz ---" />
					<c:forEach items="${yillar }" var="yil">
						<option value="${yil}" label="">${yil }</option>
					</c:forEach>

			</select></td>

			<td>Ay<select data-placeholder="Ay Seç." style="border: none;"
				name="donemAy" id="donemAy" class="donem">
					<option value="" label="--- Seçiniz ---" />
					<c:forEach items="${aylar }" var="ay">
						<option value="${ay}" label="">${ay}.&nbsp;Ay</option>
					</c:forEach>
			</select></td>

			<td>Personel<select data-placeholder="Personel Seç." name="id"
				class="chosen-select">
					<option value=""></option>
					<c:if
						test="${!empty kullaniciListesi}">
						<c:forEach items="${kullaniciListesi}" var="kullanici"
							varStatus="sira">
							<option value="${kullanici[0]}">${kullanici[1]}</option>

						</c:forEach>
					</c:if>

					<c:if test="${!empty kullaniciListesi2}">
						<c:forEach items="${kullaniciListesi2}" var="kullanici"
							varStatus="sira">
							<option value="${kullanici.id}">${kullanici.adi}</option>

						</c:forEach>
					</c:if>



			</select></td>

			<td colspan="9"><input type="submit" value="Getir"
				class="btn btn-default"></td>
		</tr>
	</form:form>

	<tr>
		<th>Sil</th>
		<th>Edit</th>
		<th>Sıra</th>
		<th width="150px" id="goster">Personel</th>
		<th>Plaka</th>
		<th>Gidilen Yer</th>
		<th>Çıkış Tarihi</th>
		<th>Çıkış Saati</th>
		<th>Giriş Saati</th>
		<th>İşin Özeti</th>
		<th>Kaydeden</th>
		<th>Kayıt Zamani</th>
	</tr>
	<c:forEach items="${aracCikisListesi}" var="cikis" varStatus="sira">

		<c:if test="${!empty cikis.kullaniciList }">
			<tr class="satirno${cikis.id}">

				<td><img src="<c:url value="/assets/images/Delete-32.png" />"
					width="21px" onclick="tipsil(${cikis.id})"
					title="Silmek İçin Tıklayın" /></td>
				<td><a href="./duzenle/${cikis.id}"><img
						src="<c:url value="/assets/images/duzenle.png" />" width="21px"
						title="Değiştirmek İçin Tıklayın" /></a></td>


				<td>${sira.count-114}</td>
				<td width="150px"><c:forEach items="${cikis.kullaniciList}"
						var="kullanici" varStatus="index">
						<span class="personelGoster">${index.count}-${kullanici.adi}</span>
					</c:forEach><span id="goster">Göster</span></td>




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
		</c:if>
	</c:forEach>
</table>



