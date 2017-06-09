/**
 * 
 */
package controllers;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import araclar.Genel;
import forms.AraziİslemHareketleri;
import forms.Kullanici;
import service.AraziService;
import service.KullaniciService;

/**
 * @author Emrah Denizer
 *
 */

@Controller
@RequestMapping(value = "/satis-cesitleri")
public class AraziİslemHareketleriController {
	@Autowired
	AraziService araziService;
	@Autowired
	KullaniciService kullaniciService;
	public AraziİslemHareketleri arazi;
	public String tusYazisi = "Kaydet";
	public List<AraziİslemHareketleri> islemTipineGöreListe;
	public Long id = null;
	public String hata = "";

	@RequestMapping(value = "/satis",method= RequestMethod.GET)
	public String Satis(ModelMap model, @CookieValue(value = "id", required = false) Long id,
			HttpServletRequest request, @ModelAttribute("araziIslem") AraziİslemHareketleri islemHareketleri,
			BindingResult result) {

		Genel.setKullaniciBean(null);
		if (arazi == null) {
			arazi = new AraziİslemHareketleri();
		}

		if (id != null) {
			// ModelAndView modelAndView = new
			// ModelAndView("SatisCesitleri/Satis");
			model.put("tusYazisi", tusYazisi);
			model.put("ilceler", araclar.Genel.ilcelers());
			model.put("title", "Satış Yoluyla Devir");
			model.put("islemListesi", araziService.islemHareketleriListesi());
			model.put("araziIslem", arazi);
			model.put("islemTipineGöreListe", islemTipineGöreListe);
			model.put("id", araziService.sonIdGetir());
			tusYazisi = "Kaydet";
			islemHareketleri.setId(0);
			arazi = null;

			if (hata == "error") {

				model.put("errorMessage", hata);

			}
			hata = "";
			return "SatisCesitleri/Satis";

		} else {
			System.out.println("/--*/- araziişlemleri ID si null...");
			System.out.println("satiş çeşitlerinde hata ....");
			return "redirect:/anasayfa";

		}
	}

	@RequestMapping(value = "/ekle")
	public ModelAndView Satis2(@CookieValue(value = "id") Long id, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("araziIslem") AraziİslemHareketleri islemHareketleri,
			BindingResult result) throws ParseException {

		Kullanici kullanici = new Kullanici();
		kullanici.setId(id);

		System.out.println(islemHareketleri.getTarih());
		islemHareketleri.setKullanici(kullanici);
		islemHareketleri.setIslemZamani(new Date());

		if (!result.hasFieldErrors()) {
			araziService.save(islemHareketleri);
		} else {
			hata = "error";
			System.out.println(result.getAllErrors());
			ModelAndView modelAndView = new ModelAndView("redirect:/satis-cesitleri/satis");
			tusYazisi = "Kaydet";
			modelAndView.addObject("tusYazisi", tusYazisi);

			return modelAndView;
		}
		islemHareketleri.setId(0);
		ModelAndView modelAndView = new ModelAndView("redirect:/satis-cesitleri/satis");

		islemHareketleri.setDevriIstenenParselAlani(null);
		islemHareketleri.setDevriIstenenParselSayisi(0);
		islemHareketleri.setEvrakNo(0);
		islemHareketleri.setIlce(null);
		islemHareketleri.setIslemZamani(null);
		islemHareketleri.setIzinVerilenParselAlani(null);
		islemHareketleri.setIzinVerilenParselSayisi(0);
		islemHareketleri.setIzinVerilmeyenParselAlani(null);
		islemHareketleri.setIzinVerilmeyenParselSayisi(0);
		islemHareketleri.setKullanici(null);
		islemHareketleri.setMahalle(null);
		islemHareketleri.setNitelik(null);
		islemHareketleri.setTarih("");
		arazi = null;

		System.out.println(arazi + "güncellendi");
		modelAndView.addObject("araziIslem", arazi);
		tusYazisi = "Kaydet";
		modelAndView.addObject("tusYazisi", tusYazisi);
		return modelAndView;
	}

	@RequestMapping(value = "/araziIslemDuzelt/{id}")
	public String düzenle(@PathVariable("id") Long id) {
		arazi = araziService.araziİslemGetir(id);
		tusYazisi = "Güncelle";
		System.out.println(arazi.getIlce());

		return "redirect:/satis-cesitleri/satis";
	}

	@RequestMapping(value = "/vazgec")
	public String vazgec(@ModelAttribute("araziIslem") AraziİslemHareketleri islemHareketleri) {

		tusYazisi = "Kaydet";

		// islemHareketleri.setEvrakNo((Integer) null);
		// islemHareketleri.setDevriIstenenParselSayisi((Integer) null);
		// islemHareketleri.setIzinVerilenParselSayisi((Integer) null);
		// islemHareketleri.setIzinVerilmeyenParselSayisi((Integer) null);
		islemHareketleri.setId(0);
		arazi = islemHareketleri;
		System.out.println(islemHareketleri.getIlce());

		return "redirect:/satis-cesitleri/satis";
	}

	@RequestMapping(value = "/islemTipineGöreListeGetir", method = RequestMethod.GET)
	public @ResponseBody String islemTipineGöreListeGetir(
			@RequestParam(value = "islemTipi", required = true) String islemTipi,
			@CookieValue(value = "id", required = false) Long id) {
		Gson gson = new Gson();

		return gson.toJson(araziService.islemTipineGöreListele(islemTipi));
	}

	@RequestMapping(value = "/id", method = RequestMethod.GET)
	public @ResponseBody Long sonIdNo() {
		System.out.println("id çalıştı");

		return araziService.sonIdGetir().longValue();
	}

	@RequestMapping(value = "/araziIslemSil")
	public String sil(@RequestParam(value = "id") Long id) {

		araziService.sil(id);

		return "redirect:/satis-cesitleri/satis";
	}

}
