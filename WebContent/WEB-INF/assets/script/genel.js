function tarihFonksiyonu() {
	var x;
	var d = new Date().getDay();
	switch (d) {
	case 0:
		x = "Bugün Pazar";
		break;
	case 1:
		x = "Bugün Pazartesi";
		break;
	case 2:
		x = "Bugün Salı";
		break;
	case 3:
		x = "Bugün Çarşamba";
		break;
	case 4:
		x = "Bugün Perşembe";
		break;
	case 5:
		x = "Bugün Cuma";
		break;
	case 6:
		x = "Bugün Cumartesi";
		break;
	}
	document.getElementById("demo").innerHTML = x;
};

$(document).ready(function() {
	if ($('#isim').val("")) {

		$('#kutu1').click(function() {

			alert("kayıt yok");
			return false;
		})

	} else {

		$('#kutu1').click(function() {
			if ($('#kutu1').is(':checked'))
				if (confirm("Silmek istediğinizden emin misiniz?"))

				{
					$('#isim').val("");
					$('#hissesi').val(0);
					$('#hisseFiyati').val(0);
					$('#telNo').val("");
					$('#referans').val("");

				} else
					return false;

		});
	}

	$('#kutu2').click(function() {
		if ($('#kutu2').is(':checked'))
			if (confirm("Silmek istediğinizden emin misiniz?"))

			{
				$('#isim2').val("");
				$('#hissesi2').val(0);
				$('#hisseFiyati2').val(0);
				$('#telNo2').val("");
				$('#referans2').val("");

			} else
				return false;
	});

	$('#kutu3').click(function() {
		if ($('#kutu3').is(':checked'))
			if (confirm("Silmek istediğinizden emin misiniz?"))

			{
				$('#isim3').val("");
				$('#hissesi3').val(0);
				$('#hisseFiyati3').val(0);
				$('#telNo3').val("");
				$('#referans3').val("");

			} else
				return false;
	});
	$('#kutu4').click(function() {
		if ($('#kutu4').is(':checked'))
			if (confirm("Silmek istediğinizden emin misiniz?"))

			{
				$('#isim4').val("");
				$('#hissesi4').val(0);
				$('#hisseFiyati4').val(0);
				$('#telNo4').val("");
				$('#referans4').val("");

			} else
				return false;
	});
	$('#kutu5').click(function() {
		if ($('#kutu5').is(':checked'))
			if (confirm("Silmek istediğinizden emin misiniz?"))

			{
				$('#isim5').val("");
				$('#hissesi5').val(0);
				$('#hisseFiyati5').val(0);
				$('#telNo5').val("");
				$('#referans5').val("");

			} else
				return false;
	});
	$('#kutu6').click(function() {
		if ($('#kutu6').is(':checked'))
			if (confirm("Silmek istediğinizden emin misiniz?"))

			{
				$('#isim6').val("");
				$('#hissesi6').val(0);
				$('#hisseFiyati6').val(0);
				$('#telNo6').val("");
				$('#referans6').val("");

			} else
				return false;
	});
	$('#kutu7').click(function() {
		if ($('#kutu7').is(':checked'))
			if (confirm("Silmek istediğinizden emin misiniz?"))

			{
				$('#isim7').val("");
				$('#hissesi7').val(0);
				$('#hisseFiyati7').val(0);
				$('#telNo7').val("");
				$('#referans7').val("");

			} else
				return false;
	});
});

$(document).ready(function() {
	$('#buyuk').click(function(x) {
		var page = $(this).attr('href').split(/\?/)[1];
		$.ajax({
			type : 'GET',
			url : './buyukbasEkleme',
			xhrFields : {
				withCredentials : true
			},
			data : page,
			beforeSend : function() {
				$("#progress").show();

			},
			success : function(content) {
				$('#mytable').empty();
				$('#mytable').append(content);// replace
				// window.location.replace('buyukbasEkleme');

				$('#progress').hide();
			},
			error : function(jqXHR, textStatus) {
				if (textStatus === 'timeout') {

					$("#progress").hide();
					// alert('Failed from timeout');
					// do something. Try again perhaps?
				}
			},
			timeout : 3000,
		});
		return false; // to stop link
	});
});
$(document).ready(function() {
	$('#satis').click(function(x) {
		var page = $(this).attr('href').split(/\?/)[1];
		$.ajax({
			type : 'GET',
			url : './satis',
			xhrFields : {
				withCredentials : true
			},
			data : page,
			beforeSend : function() {
				document.getElementById('progress').style.display = 'block';
				$('#mytable').empty();
			},
			success : function(content) {
				$('#mytable').append(content);// replace
				// window.location.replace('buyukbasEkleme');
				$('#progress').hide();
			}
		});
		return false; // to stop link
	});
});

$(document).ready(function() {
	$('#tahsilatyonlendirme').click(function(x) {
		var page = $(this).attr('href').split(/\?/)[1];
		$.ajax({
			type : 'GET',
			url : './tahsilatyonlendirme',
			// xhrFields : {
			// withCredentials : true
			// },
			// data : page,
			beforeSend : function() {
				$('#mytable').empty();
			},
			success : function(content) {
				$('#mytable').empty();
				$('#mytable').append(content);// replace
				// window.location.replace('buyukbasEkleme');
				$('#progress').hide();
			}
		});
		return false; // to stop link
	});
});
$(document).ready(function() {
	$('#tahsilat').click(function() {
		var page = $(this).attr('href').split(/\?/)[1];
		$.ajax({
			type : 'GET',
			url : './tahsilat',
			xhrFields : {

			},
			data : page,
			beforeSend : function() {
				$('#progress').show();
				$('#mytable').empty();
			},
			success : function(content) {
				// $('#mytable').empty();
				$('#mytable').append(content);// replace
				// window.location.replace('buyukbasEkleme');
				$('#progress').hide();
			},
			error : function(jqXHR, textStatus) {
				if (textStatus === 'timeout') {

					$("#progress").hide();
					// alert('Failed from timeout');
					// do something. Try again perhaps?
				}
			},
			timeout : 3000,
		});
		return false; // to stop link
	});
});
$(document).ready(function() {
	$('#tahsilat2').click(function() {
		alert("tahsilat 2 basıldı");
		var page = $(this).attr('href').split(/\?/)[1];
		$.ajax({
			type : 'GET',
			url : './tahsilat2',
			xhrFields : {
				withCredentials : true
			},
			data : page,
			beforeSend : function() {
				$("#progress").show();
				$('#mytable').empty();
				// document.getElementById('progress').style.display = 'block';
			},
			success : function(content) {
				$('#mytable').append(content);// replace
				// window.location.replace('buyukbasEkleme');
				$('#progress').hide();
			}
		});
		return false; // to stop link
	});
});
function ekleButonuGoster() {

	$("#sonSatir").fadeIn("slow");
	if ($("#isim").val(null)) {
		$("#sonSatir").hide();

	}
};

$(document).ready(function() {
	$('#gider').click(function(x) {
		var page = $(this).attr('href').split(/\?/)[1];
		$.ajax({
			type : 'GET',
			url : './giderler',
			data : page,
			beforeSend : function() {
				document.getElementById('progress').style.display = 'block';
			},
			success : function(content) {
				$('#mytable').html(content);// replace
				// window.location.replace('buyukbasEkleme');
				$('#progress').hide();
			}
		});
		return false; // to stop link
	});
});

function tahsilaticinhissedargetir(id) {
	var page = $(this).attr('href').split(/\?/)[1];
	jq.ajax({
		type : "GET",
		url : "./tahsilat",
		// data : page,
		data : {
			page : page,
			id : id
		},
		success : function(content) {
			$('#mytable').html(content);// replace
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(errorThrown);
			console.log(textStatus);
			console.log(jqXHR);
		}
	});
};

function ekleSatis23() {
	$.ajax({
		type : "POST",
		url : "./ekleSatis",
		data : "JSON",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		success : function(responseData, textStatus, jqXHR) {
			alert("data saved")
			$("#satir2").remove();
			$("#satir3").remove();
			$("#satir4").remove();
			$("#satir5").remove();
			$("#satir6").remove();
			$("#satir7").remove();
			$("span").text("1");
			$("a").show();
			$("#isim").val('');
			$("#telNo").val('');
			$("#referans").val('');
			$("#hissesi").val('0');
			$("#hisseFiyati").val('0');

		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(errorThrown);
			console.log(textStatus);
			console.log(jqXHR);
		}
	})
};

$("#personas").submit(
		function(e) {

			var url = "path/to/your/script.php"; // the script where you
			// handle the
			// form input.
			var data = $("#personas").serialize();
			console.log(data + " ****2");
			$.ajax({
				type : "POST",
				url : "./ekleSatis",
				data : data, // serializes the form's
				// elements.
				success : function(data) {
					alert("data: " + data); // show response from the php
					// script.

					var tblRow = "<tr>" + "<td>" + $("#hayvanNo").val()
							+ "</td>" + "<td>" + $("#hayvanNo").val() + "</td>"
							+ "<td>" + $("#satisFiyati").val() + "</td>"
							+ "<td>" + $("#isim").val() + "</td>" + "<td>"
							+ $("#hissesi").val() + "</td>" + "<td>"
							+ $("#hisseFiyati").val() + "</td>" + "<td>"
							+ $("#kesimSirasi").val() + "</td>" + "<td>"
							+ $("#telNo").val() + "</td>" + "<td>"
							+ $("#referans").val() + "</td>" + "<td>"
					"</td>" + "</tr>";

					$(tblRow).appendTo("#buyukbasSatisListesi");

					$("#satir2").remove();
					$("#satir3").remove();
					$("#satir4").remove();
					$("#satir5").remove();
					$("#satir6").remove();
					$("#satir7").remove();
					$("span").text("1");
					$("a").show();
					$("#isim").val('');
					$("#telNo").val('');
					$("#referans").val('');
					$("#hissesi").val('0');
					$("#hisseFiyati").val('0');

				}
			});

			e.preventDefault(); // avoid to execute the actual submit of the
			// form.
		});

function kCap(iO) {
	iO.value = iO.value.replace(/([\wöçşğüıİ])/gi, function(a, b) {
		return b.replace("I", "ı").toLowerCase()
	}).replace(/(^[a-zöçşğüı]|[\s][a-zöçşğüı])/g, function(c, d) {
		return d.replace("i", "İ").toUpperCase()
	});
}
function tahsilatKalan() {
	var hisseFiyati = parseInt(document.getElementById('hisseFiyati').value);
	var kalan = parseInt(document.getElementById('kalan').value);
	var odenen = parseInt(document.getElementById('odenen').value);
	if (kalan == 0) {

		document.getElementById('kalan').value = (hisseFiyati - odenen);
	} else {
		document.getElementById('kalan').value = (kalan - odenen);
	}

}
function bolme() {
	var degerSatisFiyati = parseInt($("#satisFiyati").val());
	var degerkesimFiyati = parseInt($("#kesimFiyati").val());

	var deger1 = $("#hissesi").val();

	if (!$("#hissesi2").val())

	{
		var deger2 = "0";
	} else {

		deger2 = $("#hissesi2").val();
	}

	if (!$("#hissesi3").val())

	{
		var deger3 = "0";
	} else {

		deger3 = $("#hissesi3").val();
	}
	if (!$("#hissesi4").val())

	{
		var deger4 = "0";
	} else {

		deger4 = $("#hissesi4").val();
	}
	if (!$("#hissesi5").val())

	{
		var deger5 = "0";
	} else {

		deger5 = $("#hissesi5").val();
	}

	if (!$("#hissesi6").val())

	{
		var deger6 = "0";
	} else {

		deger6 = $("#hissesi6").val();
	}
	if (!$("#hissesi7").val())

	{
		var deger7 = "0";
	} else {

		deger7 = $("#hissesi7").val();
	}

	// var deger3 = parseFloat($("#hissesi3").val('0'));
	// var deger4 = parseFloat($("#hissesi4").val('0'));
	//
	// var deger5 = parseFloat($("#hissesi5").val('0'));
	// var deger6 = parseFloat($("#hissesi6").val('0'));
	// var deger7 = parseFloat($("#hissesi7").val('0'));

	var toplam = parseFloat(deger1) + parseFloat(deger2) + parseFloat(deger3)
			+ parseFloat(deger4) + parseFloat(deger5) + parseFloat(deger6)
			+ parseFloat(deger7);

	// alert(toplam);
	// var toplam2 = deger1.toPrecision(2) + deger2.toPrecision(2);

	if (document.getElementById('hissesi').value == ''
			|| document.getElementById('hissesi').value == 0) {

		document.getElementById('hisseFiyati').value = "0";
	} else {

		document.getElementById('hisseFiyati').value = Math
				.ceil((degerSatisFiyati + degerkesimFiyati) / toplam * deger1);
	}

	if ($("#hissesi2").val() == null) {

		$("#hisseFiyati2").val(0);

	} else {

		$("#hisseFiyati2").val(
				Math.ceil((degerSatisFiyati + degerkesimFiyati) / toplam
						* deger2));
	}

	if ($("#hissesi3").val() == null) {

		$("#hisseFiyati3").val(0);
	} else {

		$("#hisseFiyati3").val(
				Math.ceil((degerSatisFiyati + degerkesimFiyati) / toplam
						* deger3));
	}

	if ($("#hissesi4").val() == null) {

		$("#hisseFiyati4").val(0);
	} else {

		$("#hisseFiyati4").val(
				Math.ceil((degerSatisFiyati + degerkesimFiyati) / toplam
						* deger4));
	}

	if ($("#hissesi5").val() == null) {

		$("#hisseFiyati5").val(0);
	} else {

		$("#hisseFiyati5").val(
				Math.ceil((degerSatisFiyati + degerkesimFiyati) / toplam
						* deger5));
	}

	if ($("#hissesi6").val() == null) {

		$("#hisseFiyati6").val(0);
	} else {

		$("#hisseFiyati6").val(
				Math.ceil((degerSatisFiyati + degerkesimFiyati) / toplam
						* deger6));
	}

	if ($("#hissesi7").val() == null) {

		$("#hisseFiyati7").val(0);
	} else {

		$("#hisseFiyati7").val(
				Math.ceil((degerSatisFiyati + degerkesimFiyati) / toplam
						* deger7));
	}

	var elem = $("#giristablosu");
	if (document.getElementById("hayvanNo").value != ""
			&& document.getElementById("kesimSirasi").value != ""
			&& document.getElementById("satisFiyati").value != ""
			&& document.getElementById("kesimSirasi").value != 0
			&& document.getElementById("satisFiyati").value != 0
			&& document.getElementById("kesimFiyati").value != "") {

		elem.show();

	} else {
		elem.hide();
	}

}

function kontrol() {
	var deger1 = parseInt(document.getElementById('satisFiyati').value);
	var deger1 = parseInt(document.getElementById('kesimSirasi').value);
	var deger2 = parseInt(document.getElementById('hissesi').value);

	if (deger1 == null || deger1 == 0 || deger2 == null || deger2 == 0)

		alert("\n" + "Lütfen Girdiğiniz Değerleri Kontrol Ediniz" + "\n")

}
function kontrol2(texstring) {
	var deger1 = parseFloat(document.getElementById('hissesi').value);
	var deger2 = parseFloat(document.getElementById('hissesi2').value);
	var deger3 = parseFloat(document.getElementById('hissesi3').value);
	var deger4 = parseFloat(document.getElementById('hissesi4').value);
	var deger5 = parseFloat(document.getElementById('hissesi5').value);
	var deger6 = parseFloat(document.getElementById('hissesi6').value);
	var deger7 = parseFloat(document.getElementById('hissesi7').value);

	var toplam = deger1 + deger2 + deger3 + deger4 + deger5 + deger6 + deger7;
	if (toplam > 7 || toplam == 0 || toplam < 0 || toplam == ''
			|| toplam == null) {

		document.all("uyari").style.display = "inline";
		document.all("bg").style.display = "inline";

	} else {
		hissedar.submit();

	}
}

function showProgress() {
	var sayi = $("#sayi").val();
	var xhr;
	console.log(sayi);
	$.ajax({
		url : './hesapla',
		data : {
			sayi : sayi
		},
		beforeSend : function() {
			$("#progress").show();
		},
		success : function(data) {
			console.log(data);
			$("#progress").hide();
			$("#sayi").val("");
			$("#girilenSayiToplami").html(":" + data);
			$("#girilenSayi").html(":" + sayi);
			$("#basari").text("Hesaplama Başarılı!");
		},
		error : function(jqXHR, textStatus) {
			if (textStatus === 'timeout') {

				$("#progress").hide();
				// alert('Failed from timeout');
				// do something. Try again perhaps?
			}
		},
		timeout : 3000,
	})

}

function Kapat() {

	document.all("uyari").style.display = "none";
	document.all("bg").style.display = "none";
}

function giderlerFormDogrulama() {
	var x = document.forms["myForm"]["cinsSec"].value;
	if (x == 0) {
		alert("Hayvan Cinsi Seçmediniz!!");
		return false;
	} else {

		$("#giderForm").submit();
	}
}

var jq = jQuery.noConflict();

function demirbassil(id) {// hem veritabanından hem de görüntülenen sayfadan
	// silmek için gereklidir.
	if (confirm("Sileyim mi " + id))
		;
	{
		jq('#satirno' + id).remove()// jsp sayfasından siler veritabanından
		// silmez
		jq.ajax({
			type : "POST",
			url : "./demirbassil",// veritabanından siler jspden silmez

			dataType : "JSON",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			data : {
				id : id
			},
			success : function(gelen) { // vtden silmesi için ne yaptın
				// alert("buraya kadar hata olacaK mı?");
				jq('#satirno' + id).remove();
			},
			error : function(xhr, textStatus, errorThrown) {
				alert(textStatus);
			}
		});
	}

	function login() {

		var user = $('#userid').val();
		var pwd = $('#pswrd').val();
		$.ajax({
			type : "POST",
			url : "./loginAraziEdindirme", // this is my servlet
			data : {
				"user" : user,
				"password" : pwd
			},
			success : function(data) {
				var url = "home page url here";
				$(location).attr('href', url);
			}
		});

	}

}