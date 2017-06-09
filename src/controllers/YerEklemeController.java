/**
 * 
 */
package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import forms.Kullanici;
import forms.Yerler;
import service.YerEklemeService;

/**
 * @author Emrah Denizer
 *
 */
@Controller
@RequestMapping(value = "/yonetim")
public class YerEklemeController {
	@Autowired
	YerEklemeService yerEklemeService;
	private Yerler tips;
	private String tusYazisi = "Ekle";

	@RequestMapping(value = "/sabitler")
	public String sabitlers(HttpSession session, ModelMap model, @CookieValue(value = "id", required = true) Long id) {
		model.put("modelListesi", yerEklemeService.tipGetir());
		if (tips == null) {
			tips = new Yerler();
		} else {
			if (tips.getTip() != null)
				if (tips.getTip().getTip() == null) // hi� bir �ey se�ili de�il
													// misal : bilgisayar
				{
					// System.out.println("bilgisayar se�ili");
					// model.put("altTipListesi",
					// sabitTipsService.altTipGetir(tips.getId(), true));
					tips.getTip().setTip(new Yerler(0));
					tips.getTip().getTip().setTip(new Yerler(tips.getTip().getId()));
					tips.getTip().setId(0);
					@SuppressWarnings("rawtypes")
					List sonuc = yerEklemeService.altTipGetir(tips.getTip().getTip().getTip().getId(), true);
					model.put("altTipListesi", sonuc);
					model.put("modelListesi", sonuc);
					// tips.getTip().getTip().getTip().setId(tips.getId());
				} else if (tips.getTip().getTip().getTip() == null)// bilgisayar
																	// se�ili
																	// diz�st�
																	// se�ili
																	// de�il
				{
					tips.getTip().getTip().setTip(new Yerler(tips.getTip().getTip().getId()));
					tips.getTip().getTip().setId(tips.getTip().getId());
					tips.getTip().setId(0);
					// Systgfvem.out.println("diz�st� se�ili");
					// System.out.println(sabitTipsService.tipsGetir(tips.getTip().getTip().getTip().getId()).getIsim());
					@SuppressWarnings("rawtypes")
					List sonuc = yerEklemeService.altTipGetir(tips.getTip().getTip().getId(), true);
					model.put("altTipListesi",
							yerEklemeService.altTipGetir(tips.getTip().getTip().getTip().getId(), true));
					model.put("markaListesi", sonuc);
					model.put("modelListesi", sonuc);
				} else {
					// System.out.println("acer se�ili");
					model.put("altTipListesi",
							yerEklemeService.altTipGetir(tips.getTip().getTip().getTip().getId(), true));
					model.put("markaListesi", yerEklemeService.altTipGetir(tips.getTip().getTip().getId(), true));
					model.put("modelListesi", yerEklemeService.altTipGetir(tips.getTip().getId(), true));
				}
		}
		model.put("title", "Yer Ekleme");
		model.put("tips", tips);

		model.put("tipListesi", yerEklemeService.tipGetir());// MOB�LYA VEYA
																// B�LG�SAYAR
		model.put("tusYazisi", tusYazisi);
		return "Yerler/sabitler";
	}

	@RequestMapping(value = "/sabitonay", method = RequestMethod.GET)
	public String ekleme(HttpSession session, HttpServletResponse response, @ModelAttribute("tips") Yerler yer,
			@ModelAttribute("kullanici") Kullanici kullanici, @CookieValue(value = "id", required = true) Long id) {

		if (yer.getTip().getTip().getTip().getId() == 0)

		{
			yer.setTip(null);
		} else if (yer.getTip().getTip().getId() == 0) {
			yer.setTip(new Yerler(yer.getTip().getTip().getTip().getId()));
		} else if (yer.getTip().getId() == 0) {
			yer.setTip(new Yerler(yer.getTip().getTip().getId()));
		}
		yer.setEklemezamani(new Date());
		yerEklemeService.ekle(yer);
		tips = yer;
		tips.setId(0);
		tusYazisi = "Ekle";
		return "redirect:/yonetim/sabitler";
	}

	@RequestMapping(value = "/edit/{id}")
	public String sabitEdit(@PathVariable("id") Long Id, @CookieValue(value = "id", required = true) Long id) {
		tips = yerEklemeService.tipsGetir(Id);
		tusYazisi = "Güncelle";
		return "redirect:/yonetim/sabitler";
	}

	@RequestMapping(value = "/vazgec")
	public String vazgec(HttpSession session) {
		// sabitlerService.ekle(sabit1);
		// sabit = sabit1;
		tips.setId(0);
		tips.setIsim(null);
		tips.setTip(null);
		tips.setDurum(null);
		// tips=new Sabittips(); //bo�alt�r
		tusYazisi = "Ekle";
		return "redirect:/yonetim/sabitler";
	}

	@RequestMapping(value = "/tipsil", method = RequestMethod.POST)
	public @ResponseBody String tipsil(@RequestParam(value = "id", required = true) Long id,
			@CookieValue(value = "hitCounter", defaultValue = "0") Long hitCounter, HttpServletResponse response,
			@CookieValue(value = "id", required = true) Long cookieId) {
		yerEklemeService.tipsil(id);
		response.setCharacterEncoding("UTF-8");

		hitCounter++;

		// create cookie and set it in response
		Cookie cookie = new Cookie("hitCounter", hitCounter.toString());
		response.addCookie(cookie);

		return "{}";
	}

	@RequestMapping(value = "/tipsekle", method = RequestMethod.POST)
	public @ResponseBody byte[] tipsekle(@RequestParam(value = "isim", required = true) String isim,
			@RequestParam(value = "katid", required = true) Long katid,
			@RequestParam(value = "durum", required = true) Boolean durum, HttpServletResponse response,
			@CookieValue(value = "id", required = true) Long id) throws Exception {
		Yerler tipsi = new Yerler();
		tipsi.setIsim(isim);
		tipsi.setTip(new Yerler(katid));
		yerEklemeService.kaydet(tipsi);
		return modellerListesi(katid, durum).toJSONString().getBytes("UTF-8");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/markagetir", method = RequestMethod.POST)
	@ResponseBody
	public byte[] markaGetir(@RequestParam(value = "altTipId", required = true) Long altTipId,
			HttpServletResponse response, @CookieValue(value = "id", required = true) Long id) throws Exception {
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

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/alttiplerigetir", method = RequestMethod.POST)
	@ResponseBody
	public byte[] altTipleriGetir(@RequestParam(value = "katid", required = true) Long katid,
			HttpServletResponse response) throws Exception {
		// System.out.println(ustId+"****");
		JSONObject jsonObject = new JSONObject();
		List<Yerler> altTipListesi = new ArrayList<Yerler>();
		altTipListesi = yerEklemeService.altTipGetir(katid, true);
		Iterator<Yerler> iterator = altTipListesi.iterator();
		while (iterator.hasNext()) {
			Yerler tip = iterator.next();
			jsonObject.put(tip.getId(), tip.getIsim());
		}
		return jsonObject.toJSONString().getBytes("UTF-8");
	}

	@RequestMapping(value = "/modelgetir", method = RequestMethod.POST)
	public @ResponseBody byte[] modelGetir(@RequestParam(value = "altTipId", required = true) Long altTipId,
			@RequestParam(value = "durum", required = true) Boolean durum) throws Exception {

		return modellerListesi(altTipId, durum).toJSONString().getBytes("UTF-8");
	}

	@SuppressWarnings("unchecked")
	private JSONArray modellerListesi(Long altTipId, Boolean durum) {
		JSONArray donecek = new JSONArray();
		List<Yerler> altTipListesi = new ArrayList<Yerler>();
		altTipListesi = yerEklemeService.altTipGetir(altTipId, durum);
		Iterator<Yerler> iterator = altTipListesi.iterator();
		while (iterator.hasNext()) {
			JSONObject jsonObject = new JSONObject();
			Yerler tip = iterator.next();
			jsonObject.put("id", tip.getId());
			jsonObject.put("isim", tip.getIsim());
			// jsonObject.put("kaydeden", tip.getMemurs().getIsim());

			String drm = "Aktif";
			if (tip.getDurum() == false)
				drm = "Pasif";

			jsonObject.put("durum", drm);

			String date = "Bilinmiyor";
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			if (tip.getEklemezamani() != null)
				date = DATE_FORMAT.format(tip.getEklemezamani());
			jsonObject.put("ekleme", date);
			donecek.add(jsonObject);
		}
		return donecek;
	}
}
