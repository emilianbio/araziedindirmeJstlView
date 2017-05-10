<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
input {
	width: 5em;
}

#date {
	width: 9em;
}

label {
	display: inline-block;
}
</style>



<script type="text/javascript">
	var jq = jQuery.noConflict();
	var id = parseInt('${araziIslem.id}');
	jq("#yanlisBilgi").hide();

	jq(document).ready(
			function() {
				console.log("güncellenecek Id: " + id);
				/* console.log(jq("#satirno" + id)); */
				if (id == 0) {
					islemTipineGöreTabloGetir('0');
					jq("#kaydet").show();
					jq("#guncelle").hide();

					jq("#evrakNo").val(null);
					jq("#devriIstenenParselSayisi").val(null);
					jq("#izinVerilenParselSayisi").val(null);
					jq("#izinVerilmeyenParselSayisi").val(null);

				} else {

					islemTipineGöreTabloGetir('${araziIslem.islemTipi}');
					jq("#evrakNo").val('${araziIslem.evrakNo}');
					jq("#devriIstenenParselSayisi").val(
							'${araziIslem.devriIstenenParselSayisi}');
					jq("#izinVerilenParselSayisi").val(
							'${araziIslem.izinVerilenParselSayisi}');
					jq("#izinVerilmeyenParselSayisi").val(
							'${araziIslem.izinVerilmeyenParselSayisi}');
					jq("#kaydet").hide();
					jq("#guncelle").show();

				}

			});

	function islemTipineGöreTabloGetir(islemTipi) {

		jq("#islemTablosu").find("tr:gt(6)").empty();//toggle(1000).empty();
		jq
				.ajax({
					type : "GET",
					url : '${pageContext.request.contextPath}/satis-cesitleri/islemTipineGöreListeGetir.json',
					contentType : "application/x-www-form-urlencoded;charset=UTF-8",
					data : {
						islemTipi : islemTipi
					},
					success : function(data) {
						var toplamDevriIstenenParselAlani = 0;
						var toplamDevriIstenenParselSayisi = 0;
						var toplamIzinVerilenParselSayisi = 0;
						var toplamIzinVerilenParselAlani = 0;
						var toplamIzinVerilmeyenParselSayisi = 0;
						var toplamIzinVerilmeyenParselAlani = 0;
						// vtden silmesi için ne yaptın
						// alert("buraya kadar hata olacaK mı?");

						for (var i = 0; i < data.length; i++) {
							var tblRow = "<tr class="+"xx id="+"satirno"+data[i].id +" >"
									+ "<td>"
									+ "<a href="+"${pageContext.request.contextPath}/satis-cesitleri/araziIslemDuzelt/" + data[i].id+">"
									+ data[i].islemTipi
									+ "</a>  </td>"
									+ "<td>"
									+ data[i].tarih
									+ "</td>"
									+ "<td>"
									+ data[i].ilce
									+ "</td>"
									+ "<td>"
									+ data[i].evrakNo
									+ "</td>"
									+ "<td>"
									+ data[i].mahalle
									+ "</td>"
									+ "<td>"
									+ data[i].devriIstenenParselSayisi
									+ "</td>"
									+ "<td>"
									+ data[i].devriIstenenParselAlani
									+ "</td>"
									+ "<td>"
									+ data[i].izinVerilenParselSayisi
									+ "</td>"
									+ "<td>"
									+ data[i].izinVerilenParselAlani
									+ "</td>"
									+ "<td>"
									+ data[i].izinVerilmeyenParselSayisi
									+ "</td>"
									+ "<td>"
									+ data[i].izinVerilmeyenParselAlani
									+ "</td>"
									+ "<td>"
									+ data[i].nitelik
									+ "</td>" + "</tr>";
							//	jq(tblRow).insertAfter(".ayirici :nth-child(n)");//#islemTablosu tr:last-child

							jq(tblRow).appendTo("#islemTablosu").last().show();
							//	hide().toggle(2250);

							toplamDevriIstenenParselSayisi += data[i].devriIstenenParselSayisi;
							toplamDevriIstenenParselAlani += data[i].devriIstenenParselAlani;
							toplamIzinVerilenParselSayisi += data[i].izinVerilenParselSayisi;
							toplamIzinVerilenParselAlani += data[i].izinVerilenParselAlani;
							toplamIzinVerilmeyenParselSayisi += data[i].izinVerilmeyenParselSayisi;
							toplamIzinVerilmeyenParselAlani += data[i].izinVerilmeyenParselAlani;

						}
						console.log(toplamDevriIstenenParselSayisi);
						if (id != 0) {
							//jq("#satirno" + id).css('background-color', '#f00');
							jq("#satirno" + id).addClass("info");
							jq("#satirno" + id).insertAfter(
									"#islemTablosu tr:nth-child(7)").next();

						}
						/* console.log("toplam izin verlen: " + toplam); */
						if (data.length == '0') {

							var kayitYok = "<tr class="+"xx1"+ "><td colspan="+"12"+  ">"
									+ "GÖSTERİLECEK KAYIT YOK" + "</td></tr>";
							jq("#islemTablosu").find("tr:gt(6)").remove();
							jq(kayitYok).insertAfter(".ayirici");

						}
						/* console.log(tblRow); */

					},
					error : function(xhr, textStatus, errorThrown) {
						alert(textStatus, xhr, errorThrown);
					}

				})
	};
	/* $(document).ready(function() {
	$("#tipLabelTxt").empty();
	$("#chartContainer").show();

	}) */
	function ekle() {

		if (jq("#date").val() == "" || jq("#date").val() == null) {
			alert("! Tarih Seçiniz")
			return true;
		}

		if (jq("#izinVerilenParselSayisi").val() == ""
				|| jq("#izinVerilenParselSayisi").val() == null) {
			jq("#izinVerilenParselSayisi").val("0")
		}
		if (jq("#izinVerilenParselAlani").val() == ""
				|| jq("#izinVerilenParselAlani").val() == null) {
			jq("#izinVerilenParselAlani").val("0")
		}
		if (jq("#izinVerilmeyenParselSayisi").val() == ""
				|| jq("#izinVerilmeyenParselSayisi").val() == null) {
			jq("#izinVerilmeyenParselSayisi").val("0")
		}
		if (jq("#izinVerilmeyenParselAlani").val() == ""
				|| jq("#izinVerilmeyenParselAlani").val() == null) {
			jq("#izinVerilmeyenParselAlani").val("0")
		}

		if (parseInt(jq("#izinVerilenParselSayisi").val())
				+ parseInt(jq("#izinVerilmeyenParselSayisi").val()) != parseInt(jq(
				"#devriIstenenParselSayisi").val())
				|| parseInt(jq("#izinVerilenParselAlani").val())
						+ parseInt(jq("#izinVerilmeyenParselAlani").val()) != parseInt(jq(
						"#devriIstenenParselAlani").val())
				|| jq("#date").val() == "" || jq("#date").val() == null) {
			console.log("------------");
			console.log(jq("#devriIstenenParselSayisi").val());
			console.log(parseInt(jq("#izinVerilenParselSayisi").val())
					+ parseInt(jq("#izinVerilmeyenParselSayisi").val()));
			jq("#yanlisBilgi").show();
			return false;
		}
		console.log(new Date());
		var form = jq('#myForm').serialize();
		if (!confirm("Eklemek İstediğinize Emin misiniz?"))
			return false;
		jq
				.ajax({
					data : form,
					type : 'post',
					dataType : 'html',
					url : '${pageContext.request.contextPath}/satis-cesitleri/ekle',
					xhrFields : {
						withCredentials : true
					},
					success : function(content) {
						alert("Bilgiler Başarıyla Kaydedildi...");
					},
					complete : function(data) {

						jq
								.ajax({
									type : 'get',
									dataType : 'html',
									url : '${pageContext.request.contextPath}/satis-cesitleri/id',
									success : function(idData) {
										if (('${araziIslem.id}') != 0) {

											window.location.href = '${pageContext.request.contextPath}/satis-cesitleri/satis';

										} else {
											var girilenDegerler = "<tr class="+"ayirici" + "><td>"
													+ "<a href="
													+ "${pageContext.request.contextPath}/satis-cesitleri/araziIslemDuzelt/"
													+ idData
													+ ">"
													+ jq("#satisTipi").val()
													+ "</a></td><td>"
													+ jq("#date").val()
													+ "</td><td>"
													+ jq("#ilce").val()
													+ "</td><td>"
													+ jq("#evrakNo").val()
													+ "</td><td>"
													+ jq("#mahalle").val()
													+ "</td><td>"
													+ jq(
															"#devriIstenenParselSayisi")
															.val()
													+ "</td><td>"
													+ jq(
															"#devriIstenenParselAlani")
															.val()
													+ "</td><td>"
													+ jq(
															"#izinVerilenParselSayisi")
															.val()
													+ "</td><td>"
													+ jq(
															"#izinVerilenParselAlani")
															.val()
													+ "</td><td>"
													+ jq(
															"#izinVerilmeyenParselSayisi")
															.val()
													+ "</td><td>"
													+ jq(
															"#izinVerilmeyenParselAlani")
															.val()
													+ "</td><td>"
													+ jq("#nitelik").val()
													+ "</td></tr>";
										}

										jq(".xx1").remove();
										jq("#kaydetBtn").val("Kaydet");
										jq("#kaydet").show();
										jq("#guncelle").hide();
										//jq(".ayirici :nth-child(n+2)").remove();
										//jq("#islemTablosu").find("tr:gt(6)").remove();
										jq(girilenDegerler)
												.insertAfter(
														"#islemTablosu tr:nth-child(7)")
												.hide().fadeIn(2000);

										jq("#islemTablosu tr:nth-child(n+7)")
												.hide().fadeIn(2000);

										console
												.log("Ekleme Başarılı"
														+ " // "
														+ jq(
																'#tipSelect option:selected')
																.text());/* jq('#list option:selected').text() */
										jq("#myForm")[0].reset();
										//jq("#devriIstenenParselAlani").val("");
										jq("#satisTipi")
												.val(
														jq(
																'#tipSelect option:selected')
																.text());
										jq("#izinVerilmeyenParselAlani").val(
												null);
										jq("#izinVerilmeyenParselSayisi").val(
												null);

									},
									error : function(xhr, textStatus,
											errorThrown) {
										alert(textStatus + " /" + xhr + "/ "
												+ errorThrown);
									}
								})

					},

				});
	}
	function tipDegistir() {

		if (jq("#tipSelect").val() == "0") {
			jq("#nitelik").val("Satış");
			jq("#satisTipi").val("");
			jq("#tipLabelTxt").text("");
			/* jq("#chartContainer").hide("Slow"); */
			jq(".xx").remove();
			/* function deleteCookie(c_name) {
				document.cookie = encodeURIComponent(c_name)
						+ "=deleted; expires=" + new Date(0).toUTCString();
			}
			; */
		}
		;

		if (jq("#tipSelect").val() == "SATIŞ (5403)") {
			jq("#tipLabelTxt").text("SATIŞ (5403)");
			jq("#satisTipi").val("SATIŞ (5403)");
			jq("#nitelik").val("Satış");
			jq("#chartContainer").show("fade");
		}
		;

		if (jq("#tipSelect").val() == "VASIF") {
			jq("#tipLabelTxt").text("VASIF");
			jq("#satisTipi").val("VASIF");
			jq("#nitelik").val("Satış");
			jq("#chartContainer").show("fade");
		}
		;

		if (jq("#tipSelect").val() == "MİRAS") {
			jq("#tipLabelTxt").text("MİRAS");
			jq("#satisTipi").val("MİRAS");
			jq("#nitelik").val("İntikal");
			jq("#chartContainer").show("fade");
		}
		;
		if (jq("#tipSelect").val() == "3083") {
			jq("#tipLabelTxt").text("3083");
			jq("#satisTipi").val("3083");
			jq("#nitelik").val("Satış");
			jq("#chartContainer").show("fade");
		}

		if (jq("#tipSelect").val() == "İFRAZ") {
			jq("#tipLabelTxt").text("İFRAZ");
			jq("#satisTipi").val("İFRAZ");
			jq("#nitelik").val("Satış");
			jq("#chartContainer").show("fade");
		}
	}

	function kapat() {

		jq("#yanlisBilgi").hide();

	}
</script>
<br>
<select id="tipSelect" style="border: none;" name="islemTipi"
	onchange="islemTipineGöreTabloGetir(this.value);tipDegistir()">
	<option value="0">Lütfen İşlem Tipini Seçiniz..---</option>
	<option value="SATIŞ (5403)">SATIŞ (5403)</option>
	<option value="VASIF">VASIF</option>
	<option value="MİRAS">MİRAS</option>
	<option value="3083">3083</option>
	<option value="İFRAZ">İFRAZ</option>
</select>



<div id="chartContainer" style="border: 1px; border-color: red;">

	<div id='yanlisBilgi'
		style='z-index: 20000; display: none; margin-left: 50px; border-radius: 5px; text-align: center; top: 50%; left: 75%; box-sizing: border-box; left: 50%; border: 5px groove #ffb3d1; font-size: 20px; position: fixed; background-color: white; -webkit-transform: translate(-50%, -50%); opacity: 1; filter: alpha(opacity = 90); width: 70%; height: 35%;'>



		<table class="table table-striped">
			<thead>
				<tr>
					<td><img width="50px" src="../assets/images/loading.gif"></td>
				</tr>
			</thead>

			<tr>
				<td>--Girilen parsel sayıları veya alanları birbirini tutmuyor
					olabilir</td>
			</tr>
			<tr>
				<td>Lütfen Kontrol Ederek Tekrar Deneyiniz...</td>
			</tr>
			<tr>
				<td><input type="button" value="KAPAT" onclick="kapat();"
					class="btn btn-success"></td>
			</tr>

		</table>


	</div>


	<form:form action="#" method="post" commandName="araziIslem"
		id="myForm">
		<form:hidden path="id" />
		<table border="" class="table" style="text-align: center;"
			id="islemTablosu">
			<tr align="center" style="text-align: center">
				<td colspan="12">TARIM ARAZİLERİNİN <strong><label
						style="text-align: center;" id="tipLabelTxt"> &nbsp;</label> </strong>
					YOLUYLA DEVRİ
				</td>

			</tr>
			<tr>
				<td rowspan="2">İŞLEM TİPİ</td>
				<td rowspan="2">TARİH</td>
				<td rowspan="2">İLÇE</td>
				<td rowspan="2">EVRAK NO</td>
				<td rowspan="2">MAHALLE</td>
				<td colspan="2">DEVRİ İSTENEN</td>
				<td colspan="2">İZİN VERİLEN</td>
				<td colspan="2">İZİN VERİLMEYEN</td>
				<td rowspan="2">NİTELİK</td>
			</tr>
			<tr>
				<td>Parsel Sayısı</td>
				<td>Alan (m<sup>2</sup>)
				</td>
				<td>Parsel Sayısı</td>
				<td>Alan (m<sup>2</sup>)
				</td>
				<td>Parsel Sayısı</td>
				<td>Alan (m<sup>2</sup>)
				</td>
			</tr>
			<tr>
				<td><form:input path="islemTipi" id="satisTipi"
						style="border: none;" readonly="readonly"
						placeHolder="Satış Tipi..." /></td>
				<td width="300px"><form:input id="date" type="date"
						path="tarih" style="border: none;" placeholder="Tarih......" /></td>
				<td><form:select style="border: none;" path="ilce" id="ilce">
						<form:option value="NONE" label="--- Seçiniz ---" />
						<form:options items="${ilceler}" />
					</form:select></td>
				<td><form:input id="evrakNo" style="border: none;"
						placeholder="Evrak No" path="evrakNo" /></td>
				<td><form:input id="mahalle" style="border: none;"
						placeholder="Mahalle" path="mahalle" /></td>
				<td><form:input id="devriIstenenParselSayisi"
						style="border: none;" placeholder="Parsel Sayısı"
						path="devriIstenenParselSayisi" /></td>
				<td><form:input id="devriIstenenParselAlani"
						style="border: none;" placeholder="Alan(m²)"
						path="devriIstenenParselAlani" /></td>
				<td><form:input id="izinVerilenParselSayisi"
						style="border: none;" placeholder="Parsel Sayısı"
						path="izinVerilenParselSayisi" /></td>
				<td><form:input id="izinVerilenParselAlani"
						style="border: none;" placeholder="Alan(m²)"
						path="izinVerilenParselAlani" /></td>
				<td><form:input id="izinVerilmeyenParselSayisi"
						style="border: none;" placeholder="Parsel Sayısı"
						path="izinVerilmeyenParselSayisi" /></td>
				<td><form:input id="izinVerilmeyenParselAlani"
						style="border: none;" placeholder="Alan(m²)"
						path="izinVerilmeyenParselAlani" /></td>
				<td><form:select style="border: none;" path="nitelik"
						id="nitelik">
						<form:option value="Satış">Satış</form:option>
						<form:option value="İntikal">İntikal</form:option>
					</form:select> <!-- <input id="nitelik"
					style="border: none; size: 5px; margin: 0px; padding: 0px;"
					placeholder="Nitelik" name="nitelik">--></td>
			</tr>
			<%-- <c:if test="${tusYazisi == 'Kaydet'}"> --%>
			<tr style="background-color: #ffd480" id="kaydet">
				<td colspan="12"><input id="kaydetBtn" type="button"
					onclick="ekle()" class="btn btn-success" value="${tusYazisi }"
					style="border: none; background-color: #e68a00"></td>
			</tr>
			<%-- </c:if>
			<c:if test="${tusYazisi == 'Güncelle'}"> --%>
			<tr style="background-color: #ffd480" id="guncelle">
				<td colspan="12"><input id="guncelleBtn" type="button"
					onclick="ekle()" class="btn btn-success" value="${tusYazisi }"
					style="border: none; background-color: #80bfff"> <input
					type="button" class="btn btn-success" value="Vazgeç"
					onclick="javascript:location.href='./vazgec'"
					style="border: none; background-color: #e68a00"></td>
			</tr>
			<%-- </c:if> --%>
			<tr align="center" style="text-align: center; font: bold"
				class="ayirici">
				<td colspan="12">İŞLEM BİLGİLERİ</td>
			</tr>
			<%-- <c:forEach items="${islemListesi}" var="islem" varStatus="sira">

				<tr id="islemListeSatiri">
					<td><a
						href="${pageContext.request.contextPath}/satis-cesitleri/araziIslemDuzelt/${islem.id}">${islem.islemTipi}</a>
					</td>
					<td>${islem.tarih}</td>
					<td>${islem.ilce}</td>
					<td>${islem.evrakNo}</td>
					<td>${islem.mahalle}</td>
					<td>${islem.devriIstenenParselSayisi}</td>
					<td>${islem.devriIstenenParselAlani}</td>
					<td>${islem.izinVerilenParselSayisi}</td>
					<td>${islem.izinVerilenParselAlani}</td>
					<td>${islem.izinVerilmeyenParselSayisi}</td>
					<td>${islem.izinVerilmeyenParselAlani}</td>
					<td>${islem.nitelik}</td>
				</tr>
			</c:forEach> --%>
		</table>
	</form:form>

</div>