<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<script type="text/javascript">
	var jq = jQuery.noConflict();
	
	jq(document).ready(function() {
		var id=parseInt('${izinFormu.id}') ;
		var personelId= parseInt('${izinFormu.personelId.id}') ;
		var yedekPersonelId= parseInt('${izinFormu.yedekPersonel.id}') ;
		
		if(id!=0){		
			
			yedekPersonelGetir(yedekPersonelId); 
			personelGetir(personelId) ;			
			
			jq(".table-responsive").slideDown(5000);
			
			}else
			{
				jq(".table-responsive").hide();
				
			}
		
		if(personelId==0){
			jq("#devirIzinGunSayisi").val('');
			jq("#kalanIzinGunSayisi").val('');
			jq("#talepEdilenIzinGunSayisi").val('');
		}
		jq("#mazeretNedeni").hide();
	});
	
	
	
	
	
	function yazdir() {
		var x = jq('#izinFormuDiv')[0];

		var myWindow = window.open("", "_blank",
				"scrollbars=1,resizable=1,height=500,width=900");
		myWindow.document
				.write('<html><head><title>İzin Formu</title><link rel="stylesheet" type="text/css" href="../assets/css/izinYazdir.css"></head><body>');
		myWindow.document.write(x.innerHTML);
		myWindow.document.write('</body></html>');
		// myWindow.print();
		//myWindow.close(); 

	}

	function mazeretGoster(deger) {
		if (deger == 'Mazeret') {
			jq("#mazeretNedeni").show();
		} else {
			jq("#mazeretNedeni").hide();
		}
	}
	function personelGetir(id) {
		if(id==0){
			
			jq("#izinFormu  label").empty();
			
		}else{
			
		jq
				.ajax({
					type : "GET",
					url : '${pageContext.request.contextPath}/izin-islemleri/kullaniciGetir',
					data : {
						id : id
					},
					success : function(data) {
						
						console.log(data);
					/* 
						jq("#birimi").text(data.birim);
						jq("#unvani").text(data.unvan);
						jq("#sicilNo").text(data.sicilNo);
						jq("#cepTelefonu").text(data.cepTelefonu);
						jq("#ePosta").text(data.ePosta);

						console.log(data.birim);
						console.log(data.unvan);
						console.log(data.sicilNo);
						console.log(data.cepTelefonu); 
						console.log(data.ePosta); 
 					*/
	
						
						jq("#birimi").text(data[0][1]);
						jq("#unvani").text(data[0][2]);
						jq("#sicilNo").text(data[0][3]);
						jq("#cepTelefonu").text(data[0][4]);
						jq("#ePosta").text(data[0][5]);

						console.log(data[0][0]);
						console.log(data[0][1]);
						console.log(data[0][2]);
						console.log(data[0][3]); 
						
					},
					error : function(xhr, textStatus, errorThrown) {
						alert("Personel Getir: " +textStatus + "***" + xhr + "***" + errorThrown);
					}

				});}
	}

	function yedekPersonelGetir(id) {
		if(id==0){
			
			jq("#yedekPersonelUnvani").empty();
		
		}else{
			
		jq
				.ajax({
					type : "GET",
					url : '${pageContext.request.contextPath}/izin-islemleri/kullaniciGetir',
					data : {
						id : id
					},
					success : function(data) {
						console.log(data==null);
							 jq("#yedekPersonelUnvani").text(data[0][3]);

							console.log(data.birim);
							console.log(data.unvan);
							console.log(data.sicilNo);
							console.log(data.cepTelefonu); 
							console.log(data.ePosta); 

						//jq("#isimSoyisim").text(data[0][0]);

					},
					error : function(xhr, textStatus, errorThrown) {
						alert(textStatus + "***" + xhr + "***" + errorThrown);
					}

				});}
	}

	function izinFormuYazdir(id) {
		jq
				.ajax({
					type : "GET",
					url : '${pageContext.request.contextPath}/izin-islemleri/izinGetir.json',
					contentType : "application/x-www-form-urlencoded;charset=UTF-8",
					dataTaype:'JSON',
					data : {
						id : id
					},
					success : function(data) {
						
						
						var str=data[0].yedekPersonelAdi;
						console.log(str.substr(0,str.indexOf(' ')));
						console.log(str.substr(str.indexOf(' ')+1));
						
						
						var tarih1 = (data[0].izineCikisTarihi).split("-");
		                var tarih2 = (data[0].izindenDonusTarihi).split("-");
		                
		                
		                var tarih1Yazdir=tarih1[2]+"/"+tarih1[1]+"/"+tarih1[0];
		                var tarih2Yazdir=tarih2[2]+"/"+tarih2[1]+"/"+tarih2[0];
		                
		                console.log("******"+tarih1[2]+"/"+tarih1[1]+"/"+tarih1[0]);
		                
						
						
						
						var monthNames = [1, 2, 3,4, 5, 6, 7,8, 9, 10,11, 12];

						                var date = new Date();
						                var day = date.getDate();
						                var monthIndex = date.getMonth();
						                var year = date.getFullYear();

						                console.log(day, monthNames[monthIndex], year);
						                
						                jq("#formBaslamaTarihiDilekce").text(tarih1Yazdir);
						                jq("#formImzaTarihi").text(day + '/' + monthNames[monthIndex] + '/' + year);

						                jq("#formYedekPersonelIsim").text(data[0].yedekPersonelAdi);
						                jq("#formYedekPersonelUnvan").text(data[0].yedekPersonelUnvan);
						                jq("#formEPosta").text(data[0].ePosta);
						                jq("#formCepTelefonu").text(data[0].cepTelefonu);
						                jq("#formIzinBaslamaTarihi").text(tarih1Yazdir);
						                jq("#formIzinBitisTarihi").text(tarih2Yazdir);
						                
						                jq("#formIzinBaslamaTarihi2").text(tarih1Yazdir);
						                jq("#formIzinBitisTarihi2").text(tarih2Yazdir);
						                jq("#formToplamIzinSuresi").text( parseInt(data[0].kalanİzinSayisi)+parseInt(data[0].devirİzinSayisi));
						                jq("#formKullanilanIzinSuresi").text(data[0].istenenIzinGunSayisi);
						                jq("#formKalanIzinSuresi").text(data[0].kalanİzinSayisi);
						                jq("#formBakiyeIzinSuresi").text(data[0].devirİzinSayisi);
						                jq("#formIsimSoyisim").text(data[0].isimSoyisim);
						                jq("#formBirim").text(data[0].birim);
						                jq("#formUnvan").text(data[0].unvan);
						                jq("#formSicilNo").text(data[0].sicilNo);
						                jq("#formOlurSayisi").text(data[0].olurSayisi);
						                
						                if(data[0].izinTuru==='Mazeret'){
						                jq("#formIzinTuru2").val("X");
						                jq("#formIzinNedeni").val();}else{jq("#formIzinTuru").val("X");};
						                
						                
						                jq("#formTalepEdilenIzinSuresi").text(data[0].istenenIzinGunSayisi);
						
						
						                
						                
						
						console.log(data[0].id);
						console.log(data[0].isimSoyisim[0]);
						console.log(data[0].birim);
						console.log(data[0].unvan);
						console.log(data[0].cepTelefonu);
						console.log(data[0].ePosta);
						console.log(data[0].sicilNo);
						console.log(data[0].olurSayisi);
						console.log(data[0].izinTuru);
						console.log(data[0].mazeretNedeni);
						console.log(data[0].izineCikisTarihi);
						console.log(data[0].izindenDonusTarihi);
						console.log(data[0].yedekPersonelAdi);
						console.log(data[0].yedekPersonelUnvan);

						console.log(data[0].kalanİzinSayisi);
						console.log(data[0].devirİzinSayisi);
						console.log(data[0].istenenIzinGunSayisi);
						//jq("#isimSoyisim").text(data[0][0]);
					},
					complete:function(data){
						
						yazdir();
						
					},
					error : function(xhr, textStatus, errorThrown) {
						console.log( xhr+" *** " +textStatus  + " *** " + errorThrown);
					}

				});
	}
	function  izinEkle() {
		
		if(jq("#personelId").val()==0){
			
			jq( "#personelId" ).focus();
			
			/* jq( "#personelId" ).outerWidth( 10 ) */
			/* jq( "#personelId" ).css("outline", "red solid 5p"); */
			jq( "#personelId" ).css("outline-color", "red");jq( "#uyari").focus();
            jq( "#uyari").show();
            jq( "#uyari").fadeOut(5000);
			alert("Hata ...!!!");
		}else if (jq("#izinTuru").val()==0) {
		
			alert("Hata ...!!!");
            jq( "#izinTuru" ).focus();
            jq( "#izinTuru" ).css("outline-color", "red");
            jq( "#uyari").focus();
            jq( "#uyari").show();
            jq( "#uyari").fadeOut(5000);
            
		}else if (jq("#yedekPersonelId").val()==0) {
			alert("Hata ...!!!");
   	        jq( "#yedekPersonelId" ).focus();
   	     jq( "#yedekPersonelId" ).css("outline-color", "red");
   	  jq( "#uyari").focus();
      jq( "#uyari").show();
      jq( "#uyari").fadeOut(5000);
		}else{
			jq('form').submit();
		}
	}
	function kalanHesapla() {
	console.log("hesaplama");
	var kullanilan=	parseInt(jq("#talepEdilenIzinGunSayisi").val());
	var bakiye=parseInt(jq("#devirIzinGunSayisi").val());
	var kalan=parseInt(jq("#kalanIzinGunSayisi").val())	;
	
	
	if(bakiye>0 && kullanilan<bakiye){
		jq("#kalanIzinGunSayisi").val(kalan);
		bakiye -=kullanilan;
		console.log(kalan+" "+bakiye);
		
		return jq("#devirIzinGunSayisi").val(bakiye);
	}else if (bakiye>0 && kullanilan>bakiye||bakiye==kullanilan) {
		
		kalan=kalan+bakiye-kullanilan;
		
		bakiye=0;
		jq("#kalanIzinGunSayisi").val(kalan);
		jq("#devirIzinGunSayisi").val(bakiye);
		return true;
		
	}else if  (bakiye==0) {
		
		kalan -=kullanilan;
		return jq("#kalanIzinGunSayisi").val(kalan);
	}
		
	return true;}
	
	function izindenErkenDonus() {
		console.log("izindenErkenDonus");
		var eklenen=parseInt(jq("#eklenenIzinGunSayisi").val());
		var bakiye=parseInt(jq("#devirIzinGunSayisi").val());
		var kalan=parseInt(jq("#kalanIzinGunSayisi").val())	;
		
		
		if(bakiye>0 && kullanilan<bakiye){
			jq("#kalanIzinGunSayisi").val(kalan);
			bakiye -=kullanilan;
			
			jq("#devirIzinGunSayisi").val(bakiye);
			
			
		}else if (bakiye>0 && kullanilan>bakiye) {
			
			kalan=kalan+bakiye-kullanilan;
			
			bakiye=0;
			jq("#kalanIzinGunSayisi").val(kalan);
			jq("#devirIzinGunSayisi").val(bakiye);
			return true;
			
		}else if  (bakiye==0) {
			kalan -=kullanilan;
			
			
			return jq("#kalanIzinGunSayisi").val(kalan);
		}
			
		return true;}
</script>


<div id="uyari" style="display: none">jhgjgjhgjgj</div>

<div class="table-responsive">
	<form:form commandName="izinFormu" class="form-horizontal" method="get"
		action="izinEkle" id="izinFormu">

		<form:hidden path="id" />
		<table class="table">
			<thead>
				<tr>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Adı ve Soyadı</td>
					<td><form:select path="personelId.id" style="border: none;"
							id="personelId" onchange="personelGetir(this.value)">
							<form:option value="0">---Seç------------</form:option>
							<form:options items="${kullaniciListesi}" itemValue="id"
								itemLabel="isimSoyisim" />
						</form:select></td>
				</tr>
				<tr>
					<td>Birimi</td>
					<td><label id="birimi"></label></td>
				</tr>
				<tr>
					<td>Unvanı</td>
					<td><label id="unvani"></label></td>
				</tr>
				<tr>
					<td>Sicil No</td>
					<td><label id="sicilNo"></label></td>
				</tr>
				<tr>
					<td>Cep Telefonu</td>
					<td><label id="cepTelefonu"></label></td>
				</tr>
				<tr>
					<td>e-Posta</td>
					<td><label id="ePosta"></label></td>
				</tr>
				<tr>
					<td>İzin Kullanma Olur Tarih ve Sayısı</td>
					<td><form:input path="olurSayisi" /></td>
				</tr>
				<tr>
					<td>Devreden İzin Süresi</td>
					<td><form:input path="devirIzinGunSayisi"
							id="devirIzinGunSayisi" /></td>
				</tr>
				<tr>
					<td>Kalan İzin Süresi</td>
					<td><form:input path="kalanIzinGunSayisi"
							id="kalanIzinGunSayisi" /></td>
				</tr>
				<tr>
					<td onclick="kalanHesapla();">Talep Edilen İzin Süresi</td>
					<td><form:input path="talepEdilenIzinGunSayisi"
							id="talepEdilenIzinGunSayisi" /></td>
				</tr>
				<tr>
					<td>İzin Türü</td>
					<td><form:select path="izinTuru" style="border:none;"
							id="izinTuru" onchange="mazeretGoster(this.value)">
							<form:option value="0">---Seçiniz-----------------</form:option>
							<form:option value="Yıllık">Yıllık</form:option>
							<form:option value="Mazeret">Mazeret</form:option>
						</form:select></td>
					<td><form:input id="mazeretNedeni" path="mazeretNedeni" /></td>
				</tr>
				<tr>
					<td>İzin Başlama Tarihi</td>
					<td><form:input path="izineCikisTarihi" type="date" /></td>
				</tr>
				<tr>
					<td>Göreve Başlama Tarihi</td>
					<td><form:input path="izindenDonusTarihi" type="date" /></td>
				</tr>
				<tr>
					<td>Yerine Bakacak Personel</td>
					<td><form:select path="yedekPersonel.id" style="border: none;"
							id="yedekPersonelId" onchange="yedekPersonelGetir(this.value)">
							<form:option value="0">---Seç------------</form:option>
							<form:options items="${kullaniciListesi}" itemValue="id"
								itemLabel="isimSoyisim" />
						</form:select></td>
					<td><label id="yedekPersonelUnvani"></label></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<c:if test="${izinFormu.id !=0}">
						<td><input type="button" value="Vazgeç"
							onclick="javascript:window.location.href='./izinVazgec'"
							class="btn btn-danger"></td>
					</c:if>
					<td colspan="3"><input type="button" value="Ekle"
						onclick="izinEkle();" class="btn btn-info"></td>
				</tr>
			</tfoot>
		</table>
	</form:form>
</div>
<div id="izinListesi">
	<table class="table">
		<thead>
			<tr>
				<th>Sıra No</th>
				<th>İsim</th>
				<th>Şube</th>
				<th>Toplam İzin Gün Sayısı</th>
				<th>Devir İzin Gün Sayısı</th>
				<th>Kalan İzin Gün Sayısı</th>
				<th>Talep Edilen İzin Gün Sayısı</th>
				<th>Olur Tarih/Sayısı</th>
				<th>Yerine Bakacak Personel</th>
				<th>İzine Çıkış</th>
				<th>İzinden Dönüş</th>
				<th>asdada</th>
				<th>asdada</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${izinListesi}" var="izin" varStatus="siraNo">
				<tr>


					<%-- <a href="#"
						onclick='javascript:window.open("http://localhost:8080/demirbas/deneme", "_blank", "scrollbars=1,resizable=1,height=950,width=1000")> </a> --%>
					<td><a href="javascript:;"
						onclick='izinFormuYazdir(${izin.id})'> ${siraNo.count}</a></td>
					<td><a
						onclick="location.href='${pageContext.request.contextPath}/izin-islemleri/izinGetirr/${izin.id}'"
						href="javascript:;">${izin.personelId.isimSoyisim}</a></td>
					<td>${izin.personelId.birim}</td>
					<td>${izin.devirIzinGunSayisi+izin.kalanIzinGunSayisi}</td>
					<td>${izin.devirIzinGunSayisi}</td>
					<td>${izin.kalanIzinGunSayisi}</td>
					<td>${izin.talepEdilenIzinGunSayisi}</td>
					<td>${izin.olurSayisi}</td>
					<td>${izin.yedekPersonel.isimSoyisim}</td>
					<td>${izin.izineCikisTarihi}</td>
					<td>${izin.izindenDonusTarihi}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


</div>
<div id="izinFormuDiv" style="display: none">
	<table border="1" cellspacing=0 cellpadding=0 width="750px">

		<col style="width: 35px;" />
		<!--iletişim bilgileri		-->
		<col style="width: 125px;" />
		<!--	İznin Geçirileceği Adres ve Tarihler -->
		<col style="width: 150px;" />
		<col style="width: 150px;" />
		<!--elif özer-->
		<col style="width: 150px;" />
		<!--ziraat mühendisi-->

		<thead>
			<tr class="opacity border">
				<td colspan=2 rowspan=7
					style="border-style: double; border-width: 3px; border-right: 1px solid grey"><img
					src="${pageContext.request.contextPath}/assets/gthbLogo2.png"
					style="border: 0;"></img></td>
				<td colspan=4 class="blank"
					style="border-top-style: double; border-top-width: 3px; border-right-style: double; border-right-width: 3px;"></td>
			</tr>
			<tr class=" ">
				<td colspan=4 class="blank"
					style="border-right-style: double; border-right-width: 3px;"></td>
			</tr>
			<tr class=" tc">
				<td colspan=4
					style="border-right-style: double; border-right-width: 3px;">T.C.</td>
			</tr>
			<tr class="valilik">
				<td class="" colspan=4
					style="border-right-style: double; border-right-width: 3px;">ADANA
					VALİLİĞİ</td>
			</tr>
			<tr class="tarım">
				<td class="" colspan=4
					style="border-right-style: double; border-right-width: 3px;">İL
					GIDA, TARIM VE HAYVANCILIK MÜDÜRLÜĞÜ</td>
			</tr>
			<tr class="border">
				<td class="blank" colspan=4
					style="border-bottom-style: double; border-bottom-width: 3px; border-right-style: double; border-right-width: 3px; border-bottom-color: grey"></td>
			</tr>
		</thead>


		<tr>
			<td colspan=6 class="noBorder blank"></td>
		</tr>
		<tr>
			<td colspan=6 class="noBorder blank"></td>
		</tr>
		<tr class="opacity">
			<td colspan=6 class="noBorder"
				style="text-align: center; border: none">YILLIK ve MAZERET İZİN
				FORMU</td>
		</tr>
		<tr>
			<td colspan=6 class="noBorder blank"></td>
		</tr>

		<tr>
			<td colspan=6 class="noBorder">&nbsp;&nbsp;&nbsp;657 Sayılı
				Devlet Memurları ve 4857 Sayılı İş Kanunu hükümleri uyarınca <label
				id="formBaslamaTarihiDilekce"></label> tarihinden başlamak üzere
			</td>
		</tr>
		<tr>
			<td colspan=6 class="noBorder">aşağıda belirtilen süre
				içerisinde 2 günlük yıllık izin verilmesini müsaadelerinize arz
				ederim.</td>
		</tr>
		<tr>
			<td colspan=5 class="noBorder"></td>
			<td class="noBorder" style="text-align: center"><label
				id="formImzaTarihi"></label></td>
		</tr>
		<tr>
			<td colspan=5 class="noBorder"></td>
			<td class="noBorder" style="text-align: center">İmza</td>
		</tr>
		<tr>
			<td colspan=3>Adı ve Soyadı</td>
			<td colspan="3" style="border-right: 1px solid grey;"><label
				id="formIsimSoyisim"></label></td>
		</tr>
		<tr>
			<td colspan=3>Birimi</td>
			<td colspan="3" style="border-right: 1px solid grey;"><label
				id="formBirim"></label></td>
		</tr>
		<tr>
			<td colspan=3>Unvanı</td>
			<td colspan="3" style="border-right: 1px solid grey;"><label
				id="formUnvan"></label></td>
		</tr>
		<tr>
			<td colspan=3>Sicil Numarası</td>
			<td colspan="3" style="border-right: 1px solid grey;"><label
				id="formSicilNo"></label></td>
		</tr>
		<tr>
			<td colspan=3>İzin Kullanma Olur Tarih ve Sayısı</td>
			<td colspan="3" style="border-right: 1px solid grey;"><label
				id="formOlurSayisi"></label></td>
		</tr>
		<tr>
			<td colspan=3>Talep Edilen İzin Süresi</td>
			<td colspan="3" style="border-right: 1px solid grey;"><label
				id="formTalepEdilenIzinSuresi"></label></td>
		</tr>
		<tr class="arkaRenkli">
			<td colspan="6" align="center" style="border-right: 1px solid grey;"><strong>Bu
					Bölüm Personel Birimi Sorumlusu Tarafından Doldurulacaktır.</strong></td>
		</tr>

		<tr class="arkaRenkli">
			<td rowspan="2" colspan=3>İZİN TÜRÜ</td>
			<td colspan=2>YILLIK <input style="width: 30px; border: none;"
				readonly="readonly" id="formIzinTuru">
			</td>
			<td style="border-right: 1px solid grey;"></td>
		</tr>
		<tr class="arkaRenkli">
			<td colspan=2 style="border-bottom: 1px solid grey">MAZERET <input
				style="width: 30px; border: none;" readonly="readonly"
				id="formIzinTuru2"></td>
			<td
				style="border-right: 1px solid grey; border-bottom: 1px solid grey">Nedeni:<input
				style="border: none;" id="formIzinNedeni"></input></td>
		</tr>
		<tr class="arkaRenkli sagCizgi">
			<td rowspan="5" colspan=3 style="border-right: 1px solid grey;">KULLANILABİLİR
				İZİN SÜRESİ</td>
			<td colspan="3" class="noBorder"
				style="border-right: 1px solid grey;">2015 Yılından <label
				id="formBakiyeIzinSuresi"></label> Gün İzni Vardır.
			</td>
		</tr>
		<tr class="arkaRenkli ">
			<td colspan="3" class="noBorder"
				style="border-right: 1px solid grey;">2016 Yılından <label
				id="formKalanIzinSuresi"></label> Gün İzni Vardır
			</td>
		</tr>
		<tr class="arkaRenkli ">
			<td colspan="3" class="noBorder"
				style="border-right: 1px solid grey;">2015 Yılından <label
				id="formKullanilanIzinSuresi"></label> Gün İzin Kullandıktan Sonra
			</td>
		</tr>
		<tr class="arkaRenkli ">
			<td colspan="3" class="noBorder"
				style="border-right: 1px solid grey;">Toplam: <label
				id="formToplamIzinSuresi"></label> Gün İzni Kalmaktadır.
			</td>
		</tr>
		<tr class="arkaRenkli ">
			<td colspan="3" class="noBorder"
				style="text-align: right; border-right: 1px solid grey;">
				Personel Sorumlusu Paraf......................................</td>
		</tr>
		<tr>
			<td colspan=3>İzin Başlama Tarihi</td>
			<td colspan="3" style="border-right: 1px solid grey;"><label
				id="formIzinBaslamaTarihi"></label></td>
		</tr>
		<tr>
			<td colspan=3>Göreve Başlama Tarihi</td>
			<td colspan="3" style="border-right: 1px solid grey;"><label
				id="formIzinBitisTarihi"></label></td>
		</tr>
		<tR>
			<td rowspan=6 class="arkaRenkli"><img alt=""
				src="${pageContext.request.contextPath}/assets/iletisimbilgileri.png"></td>
			<td rowspan="4">İznin Geçirileceği Adres ve Tarihler</td>
			<td border=0>Tarih</td>
			<td colspan="3" rowspan=2 style="border-right: 1px solid grey;">Adres
				Yurtiçi Seyahat</td>
		</tR>
		<tR>
			<td><label id="formIzinBaslamaTarihi2"></label></td>
		</tR>
		<tR>
			<td>Tarih</td>
			<td colspan=7 rowspan=2 style="border-right: 1px solid grey;">Adres
				Yurtiçi Seyahat</td>
		</tR>
		<tR>
			<td><label id="formIzinBitisTarihi2"></label></td>
		</tR>
		<tR>
			<td colspan="2">Cep Telefonu</td>
			<td colspan=3 style="border-right: 1px solid grey;"><label
				id="formCepTelefonu"></label></td>
		</tR>
		<tR>
			<td colspan="2">E-Posta Adresi</td>
			<td colspan=3 style="border-right: 1px solid grey;"><label
				id="formEPosta"></label></td>
		</tR>
		<tR>
			<td rowspan=2 colspan=3><strong>İzin Sırasında Yerine
					“Vekalet Edecek” / “Görev Devri Yapılacak” Personelin;</strong></td>
			<td>Adı Soyadı</td>
			<td>Ünvanı</td>
			<td style="border-right: 1px solid grey;">İmzası</td>
		</tR>
		<tr>
			<td><label id="formYedekPersonelIsim"></label></td>
			<td><label id="formYedekPersonelUnvan"></label></td>
			<td style="border-right: 1px solid grey;"></td>
		</tR>
		<tr>
			<td class="noBorder" colspan="6"><strong>ÖNEMLİ:</strong>
				Personelin iznini geçireceği adreslerde herhangi bir değişiklik
				olması halinde bu değişikliği ilgili birimine bildirmesi
				gerekmektedir.</td>
		</tr>

		<tr>
			<td class="noBorder" colspan="5"></td>
			<td class="noBorder" align="center">Şube Müdürü</td>
		</tr>
		<tr>
			<td class="noBorder" colspan="5"></td>
			<td class="noBorder" align="center">Mehmet OĞULTEKİN</td>
		</tr>
		<tr>
			<td class="noBorder" colspan="3" align="left">Uygun görüşle arz
				ederim</td>
		</tr>
		<tr>
			<td class="noBorder" colspan="2" align="center">…../…../20….</td>
		</tr>
		<tr>
			<td class="noBorder" colspan="2" align="center"><select
				style="border: none; -webkit-appearance: none;"><option>İl
						Müdür Yardımcısı</option>
					<option>İl Müdür Yardımcısı V.</option>
			</select></td>
		</tr>
		<tr>
			<td class="noBorder" colspan="2" align="center"><select
				style="border: none; -webkit-appearance: none;"><option>Fatih
						ASAK</option>
					<option>Ali KANSU</option>
					<option>Erol SATICI</option>
			</select></td>
		</tr>
		<tr>
			<td class="noBorder" colspan="6" align="center"><strong>OLUR</strong></td>
		</tr>
		<tr>
			<td class="noBorder" colspan="6" align="center"><strong>…./
					…. / 20…</strong></td>
		</tr>
		<tr>
			<td class="noBorder" colspan="6" align="center"><select
				style="border: none; -webkit-appearance: none;"><option><strong>İl
							Müdürü</strong></option>
					<option><strong>İl Müdürü V.</strong></option>
			</select></td>
		</tr>
		<tr>
			<td class="noBorder" colspan="6" align="center"><strong>Muhammet
					Ali TEKİN </strong></td>
		</tr>
	</table>
</div>