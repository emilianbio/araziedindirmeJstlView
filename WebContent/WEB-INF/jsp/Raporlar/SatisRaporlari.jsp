<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript">
	var jq = jQuery.noConflict();
	/* islemTipineGöreTabloGetir('SATIŞ (5403)');
	islemTipineGöreTabloGetir('MİRAS');
	islemTipineGöreTabloGetir('3083');
	islemTipineGöreTabloGetir('VASIF'); */
	//islemlerToplami();
	function islemlerToplami() {
		jq
				.ajax({
					type : "GET",
					url : '${pageContext.request.contextPath}/raporlar/toplam',
					contentType : "application/x-www-form-urlencoded;charset=UTF-8",
					success : function(data) {
						var tblRow = "<td>"
								+ numeral(data.devriIstenenParselSayisiToplami)
										.format('0,0')
								+ "</td><td>"
								+ numeral(data.devriIstenenParselAlaniToplami)
										.format('0,0.00')
								+ "&nbsp;m<sup>2</sup></td><td>"
								+ numeral(data.izinVerilenParselSayisiToplami)
										.format('0,0')
								+ "</td><td>"
								+ numeral(data.izinVerilenParselAlaniToplami)
										.format('0,0.00')
								+ "&nbsp;m<sup>2</sup></td><td>"
								+ numeral(
										data.izinVerilmeyenParselSayisiToplami)
										.format('0,0')
								+ "</td><td>"
								+ numeral(data.izinVerilmeyenParselAlaniToplami)
										.format('0,0.00')
								+ " &nbsp;m<sup>2</sup></td>";
						jq("#toplam td:nth-child(n+2)").remove();
						jq(tblRow).appendTo("#toplam");

					},
					error : function(xhr, textStatus, errorThrown) {
						alert(textStatus + "***" + xhr + "***" + errorThrown);
					}

				});
	}
	function islemTipineGöreTabloGetir() {
		ucAylikToplam();
		var islemTipi = jq("#islemTipi3").val();

		jq
				.ajax({
					type : "GET",
					url : '${pageContext.request.contextPath}/raporlar/islemTipineGoreUcAylikToplamgetir.json',
					contentType : "application/x-www-form-urlencoded;charset=UTF-8",
					data : {
						islemTipi : islemTipi,
						yil : jq("#yil3").val(),
						birinciAy : jq("#birinciAy3").val(),
						ikinciAy : jq("#ikinciAy3").val(),
					},
					success : function(data) {
						var toplamDevriIstenenParselAlani = 0;
						var toplamDevriIstenenParselSayisi = 0;
						var toplamIzinVerilenParselSayisi = 0;
						var toplamIzinVerilenParselAlani = 0;
						var toplamIzinVerilmeyenParselSayisi = 0;
						var toplamIzinVerilmeyenParselAlani = 0;

						var tblRow;
						var array = [];
						for (var i = 0; i < data.length; i++) {

							tblRow = [ data[i].ilce, data[i].tarih,
									data[i].islemTipi, data[i].mahalle,
									data[i].nitelik ];
							//console.log(tblRow);
							array.push(tblRow);

							toplamDevriIstenenParselSayisi += parseInt(data[i].devriIstenenParselSayisi);
							toplamDevriIstenenParselAlani += parseInt(data[i].devriIstenenParselAlani);
							toplamIzinVerilenParselSayisi += data[i].izinVerilenParselSayisi;
							toplamIzinVerilenParselAlani += data[i].izinVerilenParselAlani;
							toplamIzinVerilmeyenParselSayisi += data[i].izinVerilmeyenParselSayisi;
							toplamIzinVerilmeyenParselAlani += data[i].izinVerilmeyenParselAlani;

						}
						tblRow = "<td>" + toplamDevriIstenenParselSayisi
								+ "</td><td>" + toplamDevriIstenenParselAlani
								+ "</td><td>" + toplamIzinVerilenParselSayisi
								+ "</td><td>" + toplamIzinVerilenParselAlani
								+ "</td><td>"
								+ toplamIzinVerilmeyenParselSayisi
								+ "</td><td>" + toplamIzinVerilmeyenParselAlani
								+ "</td>";

						var islemTipi = jq('#islemTipi3').val();
						if (islemTipi == "SATIŞ (5403)") {
							jq('#satis td:nth-child(n+2)').remove();
							jq(tblRow).appendTo("#satis");

						} else if (islemTipi == "MİRAS") {
							jq('#miras td:nth-child(n+2)').remove();
							jq(tblRow).appendTo("#miras");
						} else if (islemTipi == "3083") {
							jq('#3083 td:nth-child(n+2)').remove();
							jq(tblRow).appendTo("#3083");
						} else if (islemTipi == "İFRAZ")

						{
							jq('#ifraz td:nth-child(n+2)').remove();
							jq(tblRow).appendTo("#ifraz");
						} else if (islemTipi == "KİRALAMA") {
							jq('#kira td:nth-child(n+2)').remove();
							jq(tblRow).appendTo("#kira");

						} else {
							jq('#vasif td:nth-child(n+2)').remove();
							jq(tblRow).appendTo("#vasif");

						}

						//jQueryDataTable(array);

						/*data[i].id, data[i].devriIstenenParselSayisi,data[i].evrakNo,
											data[i].devriIstenenParselAlani,
											data[i].izinVerilenParselSayisi,
											data[i].izinVerilenParselAlani,
											data[i].izinVerilmeyenParselSayisi,
											data[i].izinVerilmeyenParselAlani, */

					},
					complete : function(data) {

					},
					error : function(xhr, textStatus, errorThrown) {
						alert(textStatus + "***" + xhr + "***" + errorThrown);
					}

				});
	}

	function ilceyeGöreTabloGetir() {
		jq
				.ajax({
					type : "GET",
					url : '${pageContext.request.contextPath}/raporlar/ilceyeGöreListeGetir.json',
					contentType : "application/x-www-form-urlencoded;charset=UTF-8",
					data : {
						ilceID : jq("#ilceID").val(),
						ilceBirinciAy : jq("#ilceBirinciAy").val(),
						ilceIkinciAy : jq("#ilceIkinciAy").val(),
					},
					success : function(data) {

						/* console.log(data.devriIstenenParselSayisiToplami);
						var toplamDevriIstenenParselAlani = 0;
						var toplamDevriIstenenParselSayisi = 0;
						var toplamIzinVerilenParselSayisi = 0;
						var toplamIzinVerilenParselAlani = 0;
						var toplamIzinVerilmeyenParselSayisi = 0;
						var toplamIzinVerilmeyenParselAlani = 0;

						var tblRow;
						var array = [];
						for (var i = 0; i < data.length; i++) {

							tblRow = [ data[i].ilce, data[i].tarih,
									data[i].islemTipi, data[i].mahalle,
									data[i].nitelik ];
							//console.log(tblRow);
							array.push(tblRow);

							toplamDevriIstenenParselSayisi += parseInt(data[i].devriIstenenParselSayisi);
							toplamDevriIstenenParselAlani += parseInt(data[i].devriIstenenParselAlani);
							toplamIzinVerilenParselSayisi += data[i].izinVerilenParselSayisi;
							toplamIzinVerilenParselAlani += data[i].izinVerilenParselAlani;
							toplamIzinVerilmeyenParselSayisi += data[i].izinVerilmeyenParselSayisi;
							toplamIzinVerilmeyenParselAlani += data[i].izinVerilmeyenParselAlani;

						} */
						/* 	tblRow = "<td>" + toplamDevriIstenenParselSayisi
									+ "</td><td>" + toplamDevriIstenenParselAlani
									+ "</td><td>" + toplamIzinVerilenParselSayisi
									+ "</td><td>" + toplamIzinVerilenParselAlani
									+ "</td><td>"
									+ toplamIzinVerilmeyenParselSayisi
									+ "</td><td>" + toplamIzinVerilmeyenParselAlani
									+ "</td>";
						 */
						tblRow = "<td>" + data.devriIstenenParselSayisiToplami
								+ "</td><td>"
								+ data.devriIstenenParselAlaniToplami
								+ "</td><td>"
								+ data.izinVerilenParselSayisiToplami
								+ "</td><td>"
								+ data.izinVerilenParselAlaniToplami
								+ "</td><td>"
								+ data.izinVerilmeyenParselSayisiToplami
								+ "</td><td>"
								+ data.izinVerilmeyenParselAlaniToplami
								+ "</td>";

						jq("#ilceToplam td:nth-child(n+2)").remove();
						jq('label').text(jq("#ilceID").val());
						jq(tblRow).appendTo("#ilceToplam");

					},
					complete : function(data) {

					},
					error : function(xhr, textStatus, errorThrown) {
						alert(textStatus + "***" + xhr + "***" + errorThrown);
					}

				});
	}

	function ucAylikToplam() {

		/* 	if (jq("#ucuncuAy").val() != 0) {
				if (jq("#birinciAy").val() == jq("#ikinciAy").val()
						|| jq("#ucuncuAy").val() == jq("#ikinciAy").val()
						|| jq("#birinciAy").val() == jq("#ucuncuAy").val()) {

					alert("Farklı aylar seçmelisiniz...!!!!")

					return false;
				}
			} */

		jq
				.ajax({
					type : "GET",
					url : '${pageContext.request.contextPath}/raporlar/ucayliktoplamgetir.json',
					contentType : "application/x-www-form-urlencoded;charset=UTF-8",
					data : {
						yil : jq("#yil3").val(),
						birinciAy : jq("#birinciAy3").val(),
						ikinciAy : jq("#ikinciAy3").val(),
					},
					success : function(data) {

						console.log(data.devriIstenenParselSayisiToplami);

						var tblRow;
						tblRow = "<td>" + data.devriIstenenParselSayisiToplami
								+ "</td><td>"
								+ data.devriIstenenParselAlaniToplami
								+ "</td><td>"
								+ data.izinVerilenParselSayisiToplami
								+ "</td><td>"
								+ data.izinVerilenParselAlaniToplami
								+ "</td><td>"
								+ data.izinVerilmeyenParselSayisiToplami
								+ "</td><td>"
								+ data.izinVerilmeyenParselAlaniToplami
								+ "</td>";
						jq("#toplam td:nth-child(n+2)").remove();
						jq(tblRow).appendTo("#toplam");
						/* jq("#ucAyToplam td:nth-child(n+2)").remove();
						jq(tblRow).appendTo("#ucAyToplam");
						 */
					},
					complete : function(data) {

					},
					error : function(xhr, textStatus, errorThrown) {

						alert(textStatus + "***" + xhr + "***" + errorThrown);
					}

				});
	}

	function islemTipineGoreUcAylikToplam() {

		jq
				.ajax({
					type : "GET",
					url : '${pageContext.request.contextPath}/raporlar/islemTipineGoreUcAylikToplamgetir.json',
					contentType : "application/x-www-form-urlencoded;charset=UTF-8",
					data : {
						islemTipi : jq("#islemTipi2").val(),
						yil : jq("#yil2").val(),
						birinciAy : jq("#birinciAy2").val(),
						ikinciAy : jq("#ikinciAy2").val(),
					},
					success : function(data) {

						console.log(data.devriIstenenParselSayisiToplami);

						var tblRow;
						tblRow = "<td>" + data.devriIstenenParselSayisiToplami
								+ "</td><td>"
								+ data.devriIstenenParselAlaniToplami
								+ "</td><td>"
								+ data.izinVerilenParselSayisiToplami
								+ "</td><td>"
								+ data.izinVerilenParselAlaniToplami
								+ "</td><td>"
								+ data.izinVerilmeyenParselSayisiToplami
								+ "</td><td>"
								+ data.izinVerilmeyenParselAlaniToplami
								+ "</td>";
						jq("#islemTipineGoreUcAyToplam td:nth-child(n+2)")
								.remove();
						jq(tblRow).appendTo("#islemTipineGoreUcAyToplam");

					},
					complete : function(data) {

					},
					error : function(xhr, textStatus, errorThrown) {

						alert(textStatus + "***" + xhr + "***" + errorThrown);
					}

				});
	}

	jq("#sonuc").show();

	/* var dataSet = [
			[ "Tiger Nixon", "System Architect", "Edinburgh", "5421",
					"2011/04/25", "$320,800" ],
			[ "Garrett Winters", "Accountant", "Tokyo", "8422", "2011/07/25",
					"$170,750" ],
			[ "Ashton Cox", "Junior Technical Author", "San Francisco", "1562",
					"2009/01/12", "$86,000" ],
			[ "Cedric Kelly", "Senior Javascript Developer", "Edinburgh",
					"6224", "2012/03/29", "$433,060" ],
			[ "Airi Satou", "Accountant", "Tokyo", "5407", "2008/11/28",
					"$162,700" ],
			[ "Brielle Williamson", "Integration Specialist", "New York",
					"4804", "2012/12/02", "$372,000" ],
			[ "Herrod Chandler", "Sales Assistant", "San Francisco", "9608",
					"2012/08/06", "$137,500" ],
			[ "Rhona Davidson", "Integration Specialist", "Tokyo", "6200",
					"2010/10/14", "$327,900" ],
			[ "Colleen Hurst", "Javascript Developer", "San Francisco", "2360",
					"2009/09/15", "$205,500" ],
			[ "Sonya Frost", "Software Engineer", "Edinburgh", "1667",
					"2008/12/13", "$103,600" ],
			[ "Jena Gaines", "Office Manager", "London", "3814", "2008/12/19",
					"$90,560" ],
			[ "Quinn Flynn", "Support Lead", "Edinburgh", "9497", "2013/03/03",
					"$342,000" ],
			[ "Charde Marshall", "Regional Director", "San Francisco", "6741",
					"2008/10/16", "$470,600" ],
			[ "Haley Kennedy", "Senior Marketing Designer", "London", "3597",
					"2012/12/18", "$313,500" ],
			[ "Tatyana Fitzpatrick", "Regional Director", "London", "1965",
					"2010/03/17", "$385,750" ],
			[ "Michael Silva", "Marketing Designer", "London", "1581",
					"2012/11/27", "$198,500" ],
			[ "Paul Byrd", "Chief Financial Officer (CFO)", "New York", "3059",
					"2010/06/09", "$725,000" ],
			[ "Gloria Little", "Systems Administrator", "New York", "1721",
					"2009/04/10", "$237,500" ],
			[ "Bradley Greer", "Software Engineer", "London", "2558",
					"2012/10/13", "$132,000" ],
			[ "Dai Rios", "Personnel Lead", "Edinburgh", "2290", "2012/09/26",
					"$217,500" ],
			[ "Jenette Caldwell", "Development Lead", "New York", "1937",
					"2011/09/03", "$345,000" ],
			[ "Yuri Berry", "Chief Marketing Officer (CMO)", "New York",
					"6154", "2009/06/25", "$675,000" ],
			[ "Caesar Vance", "Pre-Sales Support", "New York", "8330",
					"2011/12/12", "$106,450" ],
			[ "Doris Wilder", "Sales Assistant", "Sidney", "3023",
					"2010/09/20", "$85,600" ],
			[ "Angelica Ramos", "Chief Executive Officer (CEO)", "London",
					"5797", "2009/10/09", "$1,200,000" ],
			[ "Gavin Joyce", "Developer", "Edinburgh", "8822", "2010/12/22",
					"$92,575" ],
			[ "Jennifer Chang", "Regional Director", "Singapore", "9239",
					"2010/11/14", "$357,650" ],
			[ "Brenden Wagner", "Software Engineer", "San Francisco", "1314",
					"2011/06/07", "$206,850" ],
			[ "Fiona Green", "Chief Operating Officer (COO)", "San Francisco",
					"2947", "2010/03/11", "$850,000" ],
			[ "Shou Itou", "Regional Marketing", "Tokyo", "8899", "2011/08/14",
					"$163,000" ],
			[ "Michelle House", "Integration Specialist", "Sidney", "2769",
					"2011/06/02", "$95,400" ],
			[ "Suki Burks", "Developer", "London", "6832", "2009/10/22",
					"$114,500" ],
			[ "Prescott Bartlett", "Technical Author", "London", "3606",
					"2011/05/07", "$145,000" ],
			[ "Gavin Cortez", "Team Leader", "San Francisco", "2860",
					"2008/10/26", "$235,500" ],
			[ "Martena Mccray", "Post-Sales support", "Edinburgh", "8240",
					"2011/03/09", "$324,050" ],
			[ "Unity Butler", "Marketing Designer", "San Francisco", "5384",
					"2009/12/09", "$85,675" ] ];*/
	function jQueryDataTable(dataSet) {
		jq(document)
				.ready(
						function() {
							var table = jq('#myTable')
									.DataTable(
											{
												"language" : {
													"url" : "../assets/dataTableTurkce/dil.json"
												},
												"lengthMenu" : [
														[ 5, 15, 50, -1 ],
														[ 5, 15, 50,
																"Tüm Kayıtlar" ] ],
												/* "dom" : 'R<"#titrePlaceholder"><"H"lfr>t<"F"ip>', */
												//	"jQueryUI" : true,
												rowReorder : true,
												autoFill : true,
												stateSave : true,
												destroy : true,
												searching : true,
												data : dataSet,
												columns : [ {
													title : "İşlem Tipi"
												}, {
													title : "Tarih"
												}, {
													title : "İlçe"
												}, {
													title : "Evrak No"
												}, {
													title : "Start date"
												} ]
											});
						})
	};

	function printDiv(box) {
		//Get the HTML of div
		var divElements = jq('#tableDiv').html();
		//Get the HTML of whole page
		var oldPage = document.body.innerHTML;
		var src = "${pageContext.request.contextPath}/assets/gthbLogo.png";
		//Reset the page's HTML with div's HTML only
		var body = "<html><head><title>ADASDASD</title></head><body><table border=0 align='center'> <tr><td align=center> <image src="+src+"/>"
				+ "</td></tr></table>" + divElements + "</body>";

		jq('body').html(body);
		//Print Page
		window.print();

		//Restore orignal HTML
		document.body.innerHTML = oldPage;
	}

	function printDiv2() {
		//Get the HTML of div
		var divElements = jq('#tableDiv2').html();
		//Get the HTML of whole page
		var oldPage = document.body.innerHTML;
		var src = "${pageContext.request.contextPath}/assets/gthbLogo.png";
		//Reset the page's HTML with div's HTML only
		var body = "<html><head><title>ADASDASD</title></head><body><table border=0 align='center'> <tr><td align=center> <image src="+src+"/>"
				+ "</td></tr></table>" + divElements + "</body>";

		jq('body').html(body);

		//Print Page
		window.print();

		//Restore orignal HTML
		document.body.innerHTML = oldPage;
	}

	function printDiv3(box2) {
		//Get the HTML of div
		var divElements = jq('#tableDiv3').html();

		//Get the HTML of whole page
		var oldPage = document.body.innerHTML;
		var src = "${pageContext.request.contextPath}/assets/gthbLogo.png";
		//Reset the page's HTML with div's HTML only
		var body = "<html><head><title>ADASDASD</title></head><body><table border=0 align='center'> <tr><td align=center> <image src="+src+"/>"
				+ "</td></tr></table>" + divElements + "</body>";

		jq('body').html(body);
		//Print Page
		window.print();

		//Restore orignal HTML
		document.body.innerHTML = oldPage;
	}



	jq(document).ready(function() {
		jq('select option').css('background-color', 'rgba(255,0,0,0.0)');
		jq('table').addClass("table table-striped bg-info");
		jq('table').css({
			"opacity" : "1"
		});
	});

	jq("#btnExport").on('click', function(e) {

		alert("export basıldı");
		window.open('data:application/msword,' + jq('#divTable3').html());
		e.preventDefault();
	});
</script>
<br>

<div class="table-responive">
	<button type="button" class="btn btn-info" data-toggle="collapse"
		data-target="#demo">TÜM SATIŞ İSTATİSTİKLERİ</button>
	<div id="demo" class="collapse">
		<form>
			<select style="border: none;" name="yil" id="yil3">
				<option value="" label="--- Seçiniz ---" />
				<option value="2016" label="2016" />
				<option value="2017" label="2017" />
			</select> <select style="border: none;" name="birinciAy" id="birinciAy3">
				<option value="" label="--- Seçiniz ---" />
				<option value="2017-01-01" label="Ocak"></option>
				<option value="2017-02-01" label="Şubat"></option>
				<option value="2017-03-01" label="Mart"></option>
				<option value="2017-04-01" label="Nisan"></option>
				<option value="2017-05-01" label="Mayıs"></option>
				<option value="2017-06-01" label="Haziran"></option>
				<option value="2017-07-01" label="Temmuz"></option>
				<option value="2017-08-01" label="Ağustos"></option>
				<option value="2017-09-01" label="Eylül"></option>
				<option value="2017-10-01" label="Ekim"></option>
				<option value="2017-11-01" label="Kasım"></option>
				<option value="2017-12-01" label="Aralık"></option>
			</select> <select style="border: none;" name="ikinciAy" id="ikinciAy3">
				<option value="" label="--- Seçiniz ---" />
				<option value="2017-01-31" label="Ocak"></option>
				<option value="2017-02-31" label="Şubat"></option>
				<option value="2017-03-31" label="Mart"></option>
				<option value="2017-04-31" label="Nisan"></option>
				<option value="2017-05-31" label="Mayıs"></option>
				<option value="2017-06-31" label="Haziran"></option>
				<option value="2017-07-31" label="Temmuz"></option>
				<option value="2017-08-31" label="Ağustos"></option>
				<option value="2017-09-31" label="Eylül"></option>
				<option value="2017-10-31" label="Ekim"></option>
				<option value="2017-11-31" label="Kasım"></option>
				<option value="2017-12-31" label="Aralık"></option>
			</select> <select id="islemTipi3" style="border: none;" name="islemTipi">
				<option value="0">Lütfen İşlem Tipini Seçiniz..---</option>
				<option value="SATIŞ (5403)">SATIŞ (5403)</option>
				<option value="VASIF">VASIF</option>
				<option value="MİRAS">MİRAS</option>
				<option value="3083">3083</option>
				<option value="İFRAZ">İFRAZ</option>
				<option value="KİRALAMA">KİRALAMA</option>
			</select> <input type="button" onclick="islemTipineGöreTabloGetir()"
				value="Getir">
		</form>
		<div id="tableDiv">
			<table id="sonuc1" class="table table-striped bg-info">
				<thead>
					<tr>
						<th>İŞLEM</th>
						<th>Devri İstenen Parsel Sayısı</th>
						<th>Devri İstenen Parsel Alanı</th>
						<th>izin Verilen Parsel Sayısı</th>
						<th>izin Verilen Parsel Alanı</th>
						<th>İzin Verilmeyen Parsel Sayısı</th>
						<th>İzin Verilmeyen Parsel Alanı</th>
					</tr>
				</thead>
				<tbody>
					<tr id="satis">
						<td><input type="text" value="SATIŞ (5403)"
							style="border: none" readonly="readonly"></td>
					</tr>
					<tr id="miras">
						<td><input type="text" value="MİRAS" style="border: none"
							readonly="readonly"></td>
					</tr>
					<tr id="3083">
						<td><input type="text" value="3083" style="border: none"
							readonly="readonly"></td>
					</tr>
					<tr id="kira">
						<td><input type="text" value="KİRA" style="border: none"
							readonly="readonly"></td>
					</tr>
					<tr id="ifraz">
						<td><input type="text" value="İFRAZ" style="border: none"
							readonly="readonly"></td>
					</tr>
					<tr id="vasif">
						<td><input type="text" value="VASIF" style="border: none"
							readonly="readonly"></td>
					</tr>
					<tr id="toplam">

						<td><input type="text" value="TOPLAM" style="border: none"
							readonly="readonly"></td>

						<td><fmt:formatNumber type="number" maxFractionDigits="3"
								value="${devriIstenenParselSayis}" /></td>

						<td id="devriIstenenParselAlani"><fmt:formatNumber
								type="number" maxFractionDigits="3" value="" />&nbsp;m<sup>2</sup></td>
						<%-- 
				<td><fmt:formatNumber type="number" maxFractionDigits="3"
						value="${izinVerilenParselSayisi}" /></td>
				<td><fmt:formatNumber type="number" maxFractionDigits="3"
						value="${izinVerilenParselAlani}" />&nbsp;m<sup>2</sup></td>
				<td><fmt:formatNumber type="number" maxFractionDigits="3"
						value="${izinVerilmeyenParselSayisi}" /></td>
				<td><fmt:formatNumber type="number" maxFractionDigits="3"
						value="${izinVerilmeyenParselAlani}" />&nbsp;m<sup>2</sup></td> --%>
					</tr>
				</tbody>
			</table>
		</div>
		<button type="button" id="toExcel"
			onclick="javascript:printDiv('box')" class="btn btn-default"
			value="Yazdır - PDF Olarak Kaydet">
			<span class="fa fa-print"></span> &nbsp;Yazdır - PDF Olarak Kaydet
		</button>
	</div>
	<br> <br>
	<!------------------------------------------------------------------------------------------------------------------>
	<hr />
	<button type="button" class="btn btn-info" data-toggle="collapse"
		data-target="#demo2">ÜÇ AYLIK RAPOR</button>
	<div id="demo2" class="collapse">
		<fieldset class="fsStyle">
			<legend>Aylara Göre Rarporlama</legend>
			<form class="form">

				<select style="border: none;" name="yil" id="yil"
					onchange="ucAylikToplam();">
					<option value="" label="--- Seçiniz ---" />
					<option value="2016" label="2016" />
					<option value="2017" label="2017" />
				</select> <select style="border: none;" name="birinciAy" id="birinciAy"
					onchange="ucAylikToplam()">
					<option value="" label="--- Seçiniz ---" />
					<option value="2017-01-01" label="Ocak"></option>
					<option value="2017-02-01" label="Şubat"></option>
					<option value="2017-03-01" label="Mart"></option>
					<option value="2017-04-01" label="Nisan"></option>
					<option value="2017-05-01" label="Mayıs"></option>
					<option value="2017-06-01" label="Haziran"></option>
					<option value="2017-07-01" label="Temmuz"></option>
					<option value="2017-08-01" label="Ağustos"></option>
					<option value="2017-09-01" label="Eylül"></option>
					<option value="2017-10-01" label="Ekim"></option>
					<option value="2017-11-01" label="Kasım"></option>
					<option value="2017-12-01" label="Aralık"></option>
				</select> <select style="border: none;" name="ikinciAy" id="ikinciAy"
					onchange="ucAylikToplam()">
					<option value="" label="--- Seçiniz ---" />
					<option value="2017-01-31" label="Ocak"></option>
					<option value="2017-02-31" label="Şubat"></option>
					<option value="2017-03-31" label="Mart"></option>
					<option value="2017-04-31" label="Nisan"></option>
					<option value="2017-05-31" label="Mayıs"></option>
					<option value="2017-06-31" label="Haziran"></option>
					<option value="2017-07-31" label="Temmuz"></option>
					<option value="2017-08-31" label="Ağustos"></option>
					<option value="2017-09-31" label="Eylül"></option>
					<option value="2017-10-31" label="Ekim"></option>
					<option value="2017-11-31" label="Kasım"></option>
					<option value="2017-12-31" label="Aralık"></option>
				</select>

			</form>

			<div id="tableDiv2" class="col-md-12">

				<table id="sonuc2" class="table table-striped">
					<thead>

						<tr>
							<th>İŞLEM</th>
							<th>Devri İstenen Parsel Sayısı</th>
							<th>Devri İstenen Parsel Alanı</th>
							<th>izin Verilen Parsel Sayısı</th>
							<th>izin Verilen Parsel Alanı</th>
							<th>İzin Verilmeyen Parsel Sayısı</th>
							<th>İzin Verilmeyen Parsel Alanı</th>
						</tr>
					</thead>
					<tbody>

						<tr id="ucAyToplam">
							<td><input type="text" value="TOPLAM" style="border: none"
								readonly="readonly"></td>

						</tr>
					</tbody>
				</table>
			</div>
			<button type="button" id="toExcel"
				onclick="javascript:printDiv2('box2')" class="btn btn-default"
				value="Yazdır - PDF Olarak Kaydet">
				<span class="fa fa-print"></span> &nbsp;Yazdır - PDF Olarak Kaydet
			</button>


		</fieldset>
	</div>


	<!------------------------------------------------------------------------------------------------------------------>
	<br> <br>
	<hr />
	<button type="button" class="btn btn-info" data-toggle="collapse"
		data-target="#demo3">İLÇELERE GÖRE RAPOR</button>
	<div id="demo3" class="collapse">
		<form:form commandName="araziIslem">
			<fieldset>
				<legend>İlçelere Göre Rapor Alma</legend>
				<form:select style="border: none;" path="ilce" id="ilceID"
					onchange="ilceyeGöreTabloGetir();">
					<form:option value="NONE" label="--- Seçiniz ---" />
					<form:options items="${ilceler}"></form:options>
				</form:select>

				<select style="border: none;" name="ilceBirinciAy"
					id="ilceBirinciAy" onchange="ilceyeGöreTabloGetir();">
					<option value="" label="--- Seçiniz ---" />
					<option value="2017-01-01" label="Ocak"></option>
					<option value="2017-02-01" label="Şubat"></option>
					<option value="2017-03-01" label="Mart"></option>
					<option value="2017-04-01" label="Nisan"></option>
					<option value="2017-05-01" label="Mayıs"></option>
					<option value="2017-06-01" label="Haziran"></option>
					<option value="2017-07-01" label="Temmuz"></option>
					<option value="2017-08-01" label="Ağustos"></option>
					<option value="2017-09-01" label="Eylül"></option>
					<option value="2017-10-01" label="Ekim"></option>
					<option value="2017-11-01" label="Kasım"></option>
					<option value="2017-12-01" label="Aralık"></option>
				</select> <select style="border: none;" name="ilceIkinciAy" id="ilceIkinciAy"
					onchange="ilceyeGöreTabloGetir();">
					<option value="" label="--- Seçiniz ---" />
					<option value="2017-01-31" label="Ocak"></option>
					<option value="2017-02-31" label="Şubat"></option>
					<option value="2017-03-31" label="Mart"></option>
					<option value="2017-04-31" label="Nisan"></option>
					<option value="2017-05-31" label="Mayıs"></option>
					<option value="2017-06-31" label="Haziran"></option>
					<option value="2017-07-31" label="Temmuz"></option>
					<option value="2017-08-31" label="Ağustos"></option>
					<option value="2017-09-31" label="Eylül"></option>
					<option value="2017-10-31" label="Ekim"></option>
					<option value="2017-11-31" label="Kasım"></option>
					<option value="2017-12-31" label="Aralık"></option>

				</select>
				<!-- <button type="button" onclick="ilceyeGöreTabloGetir();"
							class="btn btn-default" value="Getir">
							<span class="fa fa-print"></span>Yazdır - PDF Olarak Kaydet
						</button> -->
			</fieldset>
		</form:form>

		<div id="tableDiv3">

			<table id="sonuc2" class="table">
				<thead>
					<!--Tablodan İlçe Seçildikten Sonra Bu Etikete Seçilen İlçe Yazılıyor  -->
					<tr>
						<td><label> </label></td>
					</tr>
					<tr>
						<th>İŞLEM</th>
						<th>Devri İstenen Parsel Sayısı</th>
						<th>Devri İstenen Parsel Alanı</th>
						<th>izin Verilen Parsel Sayısı</th>
						<th>izin Verilen Parsel Alanı</th>
						<th>İzin Verilmeyen Parsel Sayısı</th>
						<th>İzin Verilmeyen Parsel Alanı</th>
					</tr>
				</thead>
				<tbody>

					<tr id="ilceToplam">
						<td><input type="text" value="TOPLAM" style="border: none"
							readonly="readonly"></td>

					</tr>
				</tbody>
			</table>
		</div>
		<button type="button" id="btnExport" class="btn btn-default"
			value="Yazdır - PDF Olarak Kaydet" onclick="javascript:printDiv3()">
			<span class="fa fa-print"></span>&nbsp;Yazdır - PDF Olarak Kaydet
		</button>
	</div>
</div>
