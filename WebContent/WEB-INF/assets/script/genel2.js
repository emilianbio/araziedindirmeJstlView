var jq = jQuery.noConflict();
function altTipleriGetir(ustTipId) {
	jq.ajax({
		type : "POST",
		url : "./alttiplerigetir",
		dataType : "JSON",
		contentType : "application/x-www-form-urlencoded;charset=iso-8859-9",
		data : {
			katid : ustTipId
		},
		success : function(gelen) {
			// modelleriListele(gelen);
			var select = jq('#slctAltTip');
			if (select.prop) {
				var options = select.prop('options');
			} else {
				var options = select.attr('options');
			}
			jq('option', select).remove();
			options[options.length] = new Option("Seçiniz", 0);
			jq.each(gelen, function(id, adi) {

				options[options.length] = new Option(adi, id);
			});
		},
		error : function(xhr, textStatus, errorThrown) {
			// alert(textStatus+"hata burdamı <br>___"+xhr.responseText);
		}

	});
}

function ikisibirada(id) {
	altTipleriGetir(id);
	modelGetir(id);
}

function ikisibiradamarkalar(id) {
	if (id == 0) {
		ikisibirada(jq("#slctTipler").val());
	} else {
		 markaGetir(id);
		 modelGetir(id);
	}
}


function ikisibirad(id) {
	altTipleriGetir(id);
	modelGetir(id);
}
function ikisibiradamarkala(id) {
	if (id == 0) {
		ikisibirada(jq("#slctTipler").val());
	} else {
		// markaGetir(id);
		markaGeti(id);
		// modelGetir(id);
	}
}

function modelGetir(altTipId) {
	// alert(altTipId);
	if (altTipId == 0 && jq("#slctTipler").val() > 0) {
		ikisibiradamarkalar(jq("#slctAltTip").val())
	} else {
		jq
				.ajax({
					type : "POST",
					url : "./modelgetir",
					dataType : "JSON",
					contentType : "application/x-www-form-urlencoded;charset=iso-8859-9",
					data : {
						altTipId : altTipId,
						durum : jq('#durum').val()
					},
					success : function(gelen) {
						modelleriListele(gelen)
					},
					error : function(xhr, textStatus, errorThrown) {
						// alert(textStatus+"hata burdamı
						// <br>___"+xhr.responseText);
					}
				});
	}
}

function modelleriListele(gelen) {
	// alert("modellerilistele");
	var trHTML = '<tr><th>Sil</th><th>Düzenle</th><th>Sıra No</th><th>İsim</th><th>Ekleme Tarihi</th><th>Durum</th><th>Kaydeden</th></tr>';
	jq
			.each(
					gelen,
					function(i, item) {

						// var date = moment(); // Get the current date
						// date.format("YYYY-MM-DD"); // 2014-07-10

						trHTML += '<tr id="satirno'
								+ item.id
								+ '"><td><input type="image" src="../assets/images/Delete-32.png" width="25px" onclick=tipsil('
								+ item.id
								+ ') title="Silmek İçin Tıklayın" />'
								+

								'</td><td>'
								+ '<a href="../yer-ekleme/edit/'
								+ item.id
								+ '"><input type="image" src="../assets/images/duzenle.png" width="25px" title="Değiştirmek İçin Tıklayın" /></a>'
								+ '</td><td>' + parseInt(i + 1) + '</td><td>'
								+ item.isim + '</td><td>' + item.ekleme
								+ '</td><td>' + item.durum + '</td><td>'
								+ item.kaydeden + '</td></tr>';
					});

	jq('.table-striped').html(trHTML);
}

function tipsil(id) {
	if (confirm("Sileyim mi " + id)) {
		// jq('#satirno'+id).remove();
		jq.ajax({
			type : "POST",
			url : "./tipsil",
			dataType : "JSON",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			data : {
				id : id
			},
			success : function(gelen) { // vtden silmesi için ne yaptın
				// alert("buraya kadar hata olacaK mı?");
				jq('.satirno' + id).remove();
			},
			error : function(xhr, textStatus, errorThrown) {
				alert(textStatus);
			}
		});
	}
}
function demirbassil(id) {
	if (confirm("Sileyim mi " + id)) {
		// jq('#satirno'+id).remove();
		jq.ajax({
			type : "POST",
			url : "./demirbassil",
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
}

function markaGetir(altTipId) {
	jq.ajax({
		type : "POST",
		url : "./markagetir",
		dataType : "JSON",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data : {
			altTipId : altTipId
		},
		success : function(gelen) {
			var select = jq('#slctMarka');
			if (select.prop) {
				var options = select.prop('options');
			} else {
				var options = select.attr('options');
			}
			jq('option', select).remove();
			options[options.length] = new Option("Seçiniz", 0);
			jq.each(gelen, function(id, adi) {
				options[options.length] = new Option(adi, id);
			});
		},
		error : function(xhr, textStatus, errorThrown) {
		}
	});
}

function markaGeti(altTipId) {

//	alert("merkaGeti çalıştı..");
	jq.ajax({
		type : "POST",
		url : "./markageti",
		dataType : "JSON",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data : {
			altTipId : altTipId
		},
		success : function(gelen) {
			var select = jq('#slctMarka');
			if (select.prop) {
				var options = select.prop('options');
			} else {
				var options = select.attr('options');
			}
			jq('option', select).remove();
			options[options.length] = new Option("Seçiniz", 0);
			jq.each(gelen, function(id, adi) {
				options[options.length] = new Option(adi, id);
			});
		},
		error : function(xhr, textStatus, errorThrown) {
		}
	});
}

function tipsekle(id) {

	var isim = jq("#isim");
	var katid = jq("#slctMarka");
	// alert("isim : "+isim.val()+", Kategori id : "+katid.val());
	jq.ajax({
		type : "POST",
		url : "./tipsekle",
		dataType : "JSON",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data : {
			isim : isim.val(),
			katid : katid.val()
		},
		success : function(gelen) {
			modelleriListele(gelen)
		},
		// alert("buraya kadar hata olacaK mı?");

		error : function(xhr, textStatus, errorThrown) {
			alert(textStatus + "<br>___" + xhr.responseText);
		}

	});
}

function modelGetir1(durum) {
	var altTipId = jq('#slctMarka')
	jq.ajax({
		type : "POST",
		url : "./modelgetir",
		dataType : "JSON",
		contentType : "application/x-www-form-urlencoded;charset=iso-8859-9",
		data : {
			altTipId : altTipId.val(),
			durum : durum
		},
		success : function(gelen) {
			modelleriListele(gelen)
		},
		error : function(xhr, textStatus, errorThrown) {
			alert(textStatus + "hata burdamı <br>___" + xhr.responseText);
		}
	});
}

function altTipleriListele(katid) {
	jq.ajax({
		type : "POST",
		url : "./alttiplerigetir",
		dataType : "JSON",
		contentType : "application/x-www-form-urlencoded;charset=iso-8859-9",
		data : {
			katid : katid
		},
		success : function(gelen) {
			// modelleriListele(gelen);
			var select = jq('#slctalttipid');
			if (select.prop) {
				var options = select.prop('options');
			} else {
				var options = select.attr('options');
			}
			jq('option', select).remove();
			options[options.length] = new Option("Seçiniz", 0);
			jq.each(gelen, function(id, adi) {

				options[options.length] = new Option(adi, id);
			});
		},
		error : function(xhr, textStatus, errorThrown) {
			alert(textStatus + "altTipleriListele hata burdamı <br>___"
					+ xhr.responseText);
		}

	});
}

function markalariListele(katid) {
	jq.ajax({
		type : "POST",
		url : "./alttiplerigetir",
		dataType : "JSON",
		contentType : "application/x-www-form-urlencoded;charset=iso-8859-9",
		data : {
			katid : katid
		},
		success : function(gelen) {
			// modelleriListele(gelen);
			var select = jq('#slctmarkaid');
			if (select.prop) {
				var options = select.prop('options');
			} else {
				var options = select.attr('options');
			}
			jq('option', select).remove();
			options[options.length] = new Option("Bilinmiyor", 0);
			jq.each(gelen, function(id, adi) {

				options[options.length] = new Option(adi, id);
			});
		},
		error : function(xhr, textStatus, errorThrown) {
			alert(textStatus + "<br>___" + xhr.responseText);
		}

	});
}

function modelleriListele1(katid) {
	jq.ajax({
		type : "POST",
		url : "./alttiplerigetir",
		dataType : "JSON",
		contentType : "application/x-www-form-urlencoded;charset=iso-8859-9",
		data : {
			katid : katid
		},
		success : function(gelen) {

			modelleriListele(gelen);
			var select = jq('#slctmodelid');
			if (select.prop) {
				var options = select.prop('options');
			} else {
				var options = select.attr('options');
			}
			jq('option', select).remove();
			options[options.length] = new Option("Bilinmiyor", 0);
			jq.each(gelen, function(id, adi) {

				options[options.length] = new Option(adi, id);
			});
		},
		error : function(xhr, textStatus, errorThrown) {
			alert(textStatus + "hata burdamı__***<br>___" + xhr.responseText);
		}

	});
}
function sayfadakiKayitlariGetir(sayfa) { // kayıt arama için yazıldı
	jq('#sayfaNo').val(sayfa);
	jq('#' + jq('#formId').val()).submit();

}
