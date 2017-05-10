/**
 * 
 */
package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.json.simple.JSONObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import araclar.Genel;
import forms.Arac;
import forms.Kullanici;
import forms.Yerler;
import service.AracService;
import service.KullaniciService;
import service.YerEklemeService;

/**
 * @author Emrah Denizer
 *
 */
@Controller
@RequestMapping(value = "/arazi-cikislari")
public class AracController {
	@Autowired
	YerEklemeService yerEklemeService;
	@Autowired
	KullaniciService kullaniciService;
	@Autowired
	private AracService aracService;
	private Arac arac;
	public String dosyaDurumu = null;
	public String download = "";

	public List<Arac> cikisListesi1 = null;

	@RequestMapping(value = "/arac-islemleri")
	public String aracTakip(ModelMap model, @CookieValue(value = "id") Long id, HttpServletRequest request) {
		String url = request.getRequestURI().toString();
		System.out.println("adres satırı :" + url);
		if (arac == null) {

			arac = new Arac();

		}
		if (dosyaDurumu != null) {

			model.put("dosyaDurumu", dosyaDurumu);
		}

		model.put("url", url);
		model.put("arac", arac);
		model.put("title", Genel.getKullaniciLoginInfo().getAdi());
		model.put("kullaniciListesi", kullaniciService.aktifKullaniciListesi('1'));
		model.put("ilceListesi", yerEklemeService.altTipGetir(2l, true));
		model.put("download", download);
		download = "";
		dosyaDurumu = null;

		if (id == 1 || id == 9 || id == 7) {
			model.put("aracCikisListesi", aracService.tumAracCikislari());
			model.put("girisYapanKullanici", kullaniciService.kullanici());
		} else {
			model.put("girisYapanKullanici", kullaniciService.kullanGetir(id));
			model.put("aracCikisListesi", aracService.kullaniciyaGoreCikisListesi(id));
		}
		arac = null;

		return "AraziCikis/AracTakip";
	}

	@RequestMapping(value = "/araziCikisEkle")
	public String araziCikisEkle(@ModelAttribute("arac") Arac arac, @CookieValue(value = "id", required = true) Long id,
			HttpServletRequest request) {

		Kullanici islemyapan = new Kullanici();
		islemyapan.setId(id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		// String girilenTarih = (arac.getTarih());
		System.out.println("girilen tarih : " + sdf);
		// arac.getKullanici().getAdi();
		// arac.getOzelPlaka();
		// arac.getResmiPlaka();
		// arac.getIlce();
		// arac.getMahalle();
		// arac.getCikisSaati();
		// arac.getGirisSaati();
		// arac.getAciklama();
		// arac.setIslemZamani(new Date());
		// System.out.println("arayüzden girilen bilgiler: " + arac);
		try {

			if (!arac.getOzelPlaka().equals("")) {

				arac.setResmiPlaka(null);
			}
			System.out.println("ekleme başlangıç");
			System.out.println("işlem yapan : " + islemyapan.getAdi());
			arac.setIslemyapan(islemyapan);
			arac.setIslemZamani(new Date());
			// arac.setOzelPlaka("01R9669");
			// arac.setResmiPlaka("01R1234");
			// arac.setIlce("Saricam");
			// arac.setMahalle("Mustafalar");
			// arac.setGirisSaati("15.00");
			// arac.setCikisSaati("08.00");
			// arac.setTarih(girilenTarih);
			// arac.setAciklama("Deneme");
			System.out.println("girilen bilgiler = " + arac);

			aracService.save(arac);

			return "redirect:/arazi-cikislari/arac-islemleri";
		} catch (Exception e) {

			String url1 = request.getRequestURI().toString();

			URL url = null;
			HttpURLConnection connection = null;
			try {
				url = new URL(url1);
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.connect();
				int code = connection.getResponseCode();
				System.out.println("hata kodu : " + code);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			System.err.println("*********" + e.getMessage());
			System.err.println(e);

			araclar.Genel.setErrorMessage(e.getMessage());
			return "redirect:/error";
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/markageti", method = RequestMethod.POST)
	@ResponseBody
	public byte[] markaGetir(@RequestParam(value = "altTipId", required = true) Long altTipId,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<Yerler> altTipListesi = new ArrayList<Yerler>();
		altTipListesi = yerEklemeService.altTipGetir(altTipId, true);
		Iterator<Yerler> iterator = altTipListesi.iterator();
		while (iterator.hasNext()) {
			Yerler tip = iterator.next();
			jsonObject.put(tip.getId(), tip.getIsim());
		}
		return jsonObject.toJSONString().getBytes("UTF-8");
	}

	@RequestMapping(value = "/duzenle/{id}")
	public String duzenle(@PathVariable("id") Long id) {
		arac = aracService.aracCikisGetir(id);
		System.out.println(arac.getMahalle().getIsim());
		return "redirect:/arazi-cikislari/arac-islemleri";
	}

	@SuppressWarnings("resource")
	@RequestMapping(value = "/raporAl", method = RequestMethod.GET)
	public String personelAraCikisRaporu(@RequestParam(value = "id", required = false) Long id,
			@CookieValue(value = "isim", required = true) String isim, HttpServletResponse response)
			throws ParseException, InvalidFormatException, IOException {
		System.out.println("ID = " + id);
		System.out.println("bilinmeyen karakter" + "\0 dsfgdfgdfg");
		List<Arac> cikisListesi = null;
		if (id == null) {
			cikisListesi = aracService.tumAracCikislari();

		} else {

			cikisListesi1 = aracService.kullaniciyaGoreCikisListesi(id);
			return "redirect:/arazi-cikislari/araziCikislari";
		}

		String[] isimAyrac = isim.split("\\.");
		String ayrilanIsim = isimAyrac[0];
		String ayrilanSoyIsim = isimAyrac[1];
		System.out.println("çıkış listesi uzunluğu " + cikisListesi.size());
		String path = "D:\\evraklar\\";

		String filename = ayrilanIsim.toUpperCase() + " " + ayrilanSoyIsim.toUpperCase()
				+ ".docx"/* path to a file */;
		String baslikIsmi = ayrilanIsim.toUpperCase() + " " + ayrilanSoyIsim.toUpperCase();

		XWPFDocument document = new XWPFDocument();

		// ustbaslik logo
		// FileInputStream is = new FileInputStream(path + "bakanlik.png");
		// document.addPictureData(is, 0);

		// create table
		XWPFTable tableUst = document.createTable();
		tableUst.setCellMargins(10, 10, 10, 10);

		// üstbaşlık oluşturma
		XWPFTableRow tableUstBaslik = tableUst.getRow(0);
		tableUstBaslik.getCell(0)
				.setText("     GIDA TARIM VE HAYVANCILIK BAKANLIĞI                                            ");
		tableUstBaslik.addNewTableCell().setText("                                   ");
		tableUstBaslik.addNewTableCell().setText("                              " + baslikIsmi);
		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.addBreak();
		run.addBreak();

		// tablo çizgilerini siler
		tableUst.getCTTbl().getTblPr().unsetTblBorders();
		// create table
		XWPFTable table = document.createTable();
		// table.setCellMargins(10, 10, 10, 10);

		CTTblPr tblPr = table.getCTTbl().getTblPr();
		CTString styleStr = tblPr.addNewTblStyle();
		styleStr.setVal("StyledTable");

		// create first row
		XWPFTableRow tableRowOne = table.getRow(0);
		tableRowOne.getCell(0).setText("Günler");
		tableRowOne.addNewTableCell().setText("   Gidilen Yer   ");
		tableRowOne.addNewTableCell().setText("Gidiş Saati");
		tableRowOne.addNewTableCell().setText("Geliş Saati");
		tableRowOne.addNewTableCell().setText("Araç Plakası  ");
		tableRowOne.addNewTableCell().setText("     Yapılan İşin Özeti    ");
		tableRowOne.setHeight(10);

		for (int i = 0; i < cikisListesi.size(); i++) {
			String tamTarih = cikisListesi.get(i).getTarih();
			String[] gunAyraci = tamTarih.split("-");
			String gun = gunAyraci[2];

			// create second row
			XWPFTableRow tableRowTwo = table.createRow();

			tableRowTwo.getCell(0).setText(gun);
			tableRowTwo.getCell(1).setText(
					cikisListesi.get(i).getIlce().getIsim() + "-" + cikisListesi.get(i).getMahalle().getIsim());
			tableRowTwo.getCell(2).setText(cikisListesi.get(i).getCikisSaati());
			tableRowTwo.getCell(3).setText(cikisListesi.get(i).getGirisSaati());

			if (cikisListesi.get(i).getResmiPlaka() == null) {
				tableRowTwo.getCell(4).setText(cikisListesi.get(i).getOzelPlaka());
			} else {
				tableRowTwo.getCell(4).setText(cikisListesi.get(i).getResmiPlaka());
			}

			tableRowTwo.getCell(5).setText(cikisListesi.get(i).getAciklama());

			System.out.println(i + ". kayıt girildi");
			System.out.println(cikisListesi.get(i).getTarih());
		}

		for (int i = 0; i < 18 - cikisListesi.size(); i++) {

			// create blank row
			XWPFTableRow tableRowTwo = table.createRow();

			tableRowTwo.getCell(0).setText("");
			tableRowTwo.getCell(1).setText("");
			tableRowTwo.getCell(2).setText("");
			tableRowTwo.getCell(3).setText("");
			tableRowTwo.getCell(4).setText("");
			tableRowTwo.getCell(5).setText("");
			tableRowTwo.setHeight(5);
			System.out.println("boş satırlar eklendi...");
		}
		XWPFParagraph paragraph2 = document.createParagraph();
		XWPFRun run2 = paragraph2.createRun();

		// create table
		XWPFTable tableAlt = document.createTable();
		// table.setCellMargins(10, 10, 10, 10);

		// XWPFTableRow altTableRow1 = tableAlt.getRow(0);
		// altTableRow1.getCell(0).setText("GIDA TARIM VE HAYVANCILIK
		// BAKANLIĞI");
		// altTableRow1.addNewTableCell().setText("GIDA TARIM VE HAYVANCILIK
		// BAKANLIĞI");
		// altTableRow1.addNewTableCell().setText("GIDA TARIM VE HAYVANCILIK
		// BAKANLIĞI");

		XWPFTableRow altTableRow1 = tableAlt.createRow();
		altTableRow1.getCell(0).setText("Arazi Gün Sayısı " + cikisListesi.size() + " gün" + "");
		altTableRow1.addNewTableCell().setText("Tasdik Olunur");

		XWPFTableRow altTableRow2 = tableAlt.createRow();
		altTableRow2.getCell(0).setText("2017/4 Döneminde Tarafımdan Yapılan Arazi Çalışmalarına Ait Rapordur");
		altTableRow2.addNewTableCell().setText(" .../.../2017");

		XWPFTableRow altTableRow3 = tableAlt.createRow();
		altTableRow3.getCell(0).setText("");
		altTableRow3.addNewTableCell().setText("");

		XWPFTableRow altTableRow4 = tableAlt.createRow();
		altTableRow4.getCell(0).setText(baslikIsmi);
		altTableRow4.addNewTableCell().setText("Memmet Oğultekin");

		XWPFTableRow altTableRow5 = tableAlt.createRow();
		altTableRow5.getCell(0).setText("Tekniker");
		altTableRow5.addNewTableCell().setText("Şube Müdürü");

		// tablo çizgilerini siler
		tableAlt.getCTTbl().getTblPr().unsetTblBorders();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println(isim);
		Date tarih = new Date();
		System.out.println(sdf.format(tarih));
		// path +
		try {
			FileOutputStream out = new FileOutputStream(filename);
			document.write(out);
			out.close();
			dosyaDurumu = "Dosya Başarıyla Oluşturuldu...";
			download = "DOLU";
			System.out.println("dosya oluşturuldu...");

			// oluşturulan dosyayı indirme linki
			File file = new File(filename);

			response.setContentType(new MimetypesFileTypeMap().getContentType(file));
			response.setContentLength((int) file.length());
			response.setHeader("content-disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
			try {

				InputStream is = new FileInputStream(file);
				FileCopyUtils.copy(is, response.getOutputStream());
				System.out.println("dosya indirildi");
				try {
					file.delete();
					System.out.println("dosya silindi");
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("dosya silinemedi.. " + e);
				}

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("dosya indirilemedi.. " + e);
			}

			return null;
		} catch (Exception e) {
			dosyaDurumu = "Dosya Oluşturma Başarısız.Lütfen Sitem Yöneticinizle Görüşün..." + e;
			e.printStackTrace();
			return "redirect:/arazi-cikislari/arac-islemleri";
		}

	}

	@RequestMapping(value = "/download")
	public String downloadPOIDoc(HttpServletResponse response, @CookieValue(value = "isim") String isim)
			throws IOException {
		String[] isimAyrac = isim.split("\\.");
		String ayrilanIsim = isimAyrac[0];
		String ayrilanSoyIsim = isimAyrac[1];
		String filename = ayrilanIsim.toUpperCase() + " " + ayrilanSoyIsim.toUpperCase()
				+ ".docx"/* path to a file */;
		String path = "D:\\evraklar\\";
		File file = new File(path + filename);

		response.setContentType(new MimetypesFileTypeMap().getContentType(file));
		response.setContentLength((int) file.length());
		response.setHeader("content-disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));

		InputStream is = new FileInputStream(file);
		FileCopyUtils.copy(is, response.getOutputStream());

		return null;

	}

	@RequestMapping(value = "/araziCikislari")
	public String raporAlmaSayasi(ModelMap model) {
System.out.println(cikisListesi1);
		model.put("aracCikisListesi", cikisListesi1);

		return "Raporlar/AraziCikis";
	}

}
