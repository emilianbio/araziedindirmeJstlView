/**
 * 
 */
package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
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

	@RequestMapping(value = "/arac-islemleri")
	public String aracTakip(ModelMap model, @CookieValue(value = "id") Long id) {
		if (arac == null) {

			arac = new Arac();

		}
		if (dosyaDurumu != null) {

			model.put("dosyaDurumu", dosyaDurumu);
		}

		model.put("arac", arac);
		model.put("title", "Araç Takip ");
		model.put("kullaniciListesi", kullaniciService.aktifKullaniciListesi('1'));
		model.put("ilceListesi", yerEklemeService.altTipGetir(2l, true));
		model.put("download", download);
		download = "";
		dosyaDurumu = null;

		if (id == 1 || id == 9) {
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
	public String araziCikisEkle(@ModelAttribute("arac") Arac arac,
			@CookieValue(value = "id", required = true) Long id) {

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
			System.err.println("*********" + e.getMessage());
			System.err.println(e);
			return "redirect:/arazi-cikislari/arac-islemleri";
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
	@RequestMapping(value = "/raporAl")
	public String personelAraCikisRaporu(@RequestParam(value = "id", required = false) Long id,
			@CookieValue(value = "isim", required = true) String isim, HttpServletResponse response)
			throws ParseException, InvalidFormatException, IOException {
		List<Arac> cikisListesi = null;
		if (id == null) {
			cikisListesi = aracService.tumAracCikislari();
		} else {

			cikisListesi = aracService.kullaniciyaGoreCikisListesi(id);

		}

		String[] isimAyrac = isim.split("\\.");
		String ayrilanIsim = isimAyrac[0];
		String ayrilanSoyIsim = isimAyrac[1];
		System.out.println("çıkış listesi uzunluğu " + cikisListesi.size());
		String path = "D:\\evraklar\\";

		String filename = ayrilanIsim.toUpperCase() + " " + ayrilanSoyIsim.toUpperCase()
				+ ".docx"/* path to a file */;

		XWPFDocument document = new XWPFDocument();

		// ustbaslik logo
		// FileInputStream is = new FileInputStream(path + "bakanlik.png");
		// document.addPictureData(is, 0);

		// create table
		XWPFTable tableUst = document.createTable();
		tableUst.setCellMargins(10, 10, 10, 10);

		// üstbaşlık oluşturma
		XWPFTableRow tableUstBaslik = tableUst.getRow(0);
		tableUstBaslik.getCell(0).setText("GIDA TARIM VE HAYVANCILIK BAKANLIĞI");
		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.addBreak();
		run.addBreak();
		run.addBreak();

		// create table
		XWPFTable table = document.createTable();
		// table.setCellMargins(10, 10, 10, 10);

		CTTblPr tblPr = table.getCTTbl().getTblPr();
		CTString styleStr = tblPr.addNewTblStyle();
		styleStr.setVal("StyledTable");

		// create first row
		XWPFTableRow tableRowOne = table.getRow(0);
		tableRowOne.getCell(0).setText("Günler");
		tableRowOne.addNewTableCell().setText("Gidilen Yer");
		tableRowOne.addNewTableCell().setText("Gidiş Saati");
		tableRowOne.addNewTableCell().setText("Geliş Saati");
		tableRowOne.addNewTableCell().setText("Araç Plakası");
		tableRowOne.addNewTableCell().setText("Yapılan İşin Özeti");

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

		for (int i = 0; i < 22 - cikisListesi.size(); i++) {

			// create blank row
			XWPFTableRow tableRowTwo = table.createRow();

			tableRowTwo.getCell(0).setText("");
			tableRowTwo.getCell(1).setText("");
			tableRowTwo.getCell(2).setText("");
			tableRowTwo.getCell(3).setText("");
			tableRowTwo.getCell(4).setText("");
			tableRowTwo.getCell(5).setText("");

			System.out.println("boş satırlar eklendi...");
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println(isim);
		Date tarih = new Date();
		System.out.println(sdf.format(tarih));
		// path +
		try {
			FileOutputStream out = new FileOutputStream(
					path + ayrilanIsim.toUpperCase() + " " + ayrilanSoyIsim.toUpperCase() + ".docx");
			document.write(out);
			out.close();
			dosyaDurumu = "Dosya Başarıyla Oluşturuldu...";
			download = "DOLU";
			System.out.println("dosya oluşturuldu...");

			// oluşturulan dosyayı indirme linki
			File file = new File(path + filename);

			response.setContentType(new MimetypesFileTypeMap().getContentType(file));
			response.setContentLength((int) file.length());
			response.setHeader("content-disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));

			InputStream is = new FileInputStream(file);
			FileCopyUtils.copy(is, response.getOutputStream());

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

}
