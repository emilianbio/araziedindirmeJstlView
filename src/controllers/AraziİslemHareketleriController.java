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

	@RequestMapping(value = "/satis")
	public ModelAndView Satis(ModelMap model, @ModelAttribute("araziIslem") AraziİslemHareketleri islemHareketleri,
			@CookieValue(value = "id") Long id) {

		Genel.setKullaniciBean(null);
		if (arazi == null) {
			arazi = new AraziİslemHareketleri();
		}

		ModelAndView modelAndView = new ModelAndView("SatisCesitleri/Satis");

		modelAndView.addObject("tusYazisi", tusYazisi);
		modelAndView.addObject("ilceler", araclar.Genel.ilcelers());
		modelAndView.addObject("title", "Satış Yoluyla Devir");
		modelAndView.addObject("islemListesi", araziService.islemHareketleriListesi());
		modelAndView.addObject("araziIslem", arazi);
		modelAndView.addObject("islemTipineGöreListe", islemTipineGöreListe);
		modelAndView.addObject("id", araziService.sonIdGetir());
		tusYazisi = "Kaydet";
		islemHareketleri.setId(0);
		arazi = null;
		return modelAndView;
	}

	@RequestMapping(value = "/ekle")
	public ModelAndView Satis2(@CookieValue(value = "id") Long id,
			@ModelAttribute("araziIslem") AraziİslemHareketleri islemHareketleri, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws ParseException {

		Kullanici kullanici = new Kullanici();
		kullanici.setId(id);

		System.out.println(islemHareketleri.getTarih());
		islemHareketleri.setKullanici(kullanici);
		islemHareketleri.setIslemZamani(new Date());
		araziService.save(islemHareketleri);

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
		islemHareketleri.setTarih(null);
		arazi = null;

		System.out.println(arazi + "güncellendi");
		modelAndView.addObject("araziIslem", arazi);
		tusYazisi = "Kaydet";
		modelAndView.addObject("tusYazisi", tusYazisi);
		return new ModelAndView("redirect:/satis-cesitleri/satis");
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

		Kullanici kullanici = kullaniciService.kullaniciGetirr(id);

		if (kullanici.getRoles().getRollAdi().equals(araclar.RolesEnum.ROLE_SUPER_ADMIN.toString())
				|| kullanici.getRoles().getRollAdi().equals(araclar.RolesEnum.ROLE_AUTHORIZED_USER.toString())) {
			return gson.toJson(araziService.islemTipineGöreListele(islemTipi));

		} else {

			return gson.toJson(araziService.islemTipineVePersoneleGöreListele(islemTipi, id));
		}
	}

	@RequestMapping(value = "/id", method = RequestMethod.GET)
	public @ResponseBody Long sonIdNo() {
		System.out.println("id çalıştı");

		return araziService.sonIdGetir().longValue();
	}

}
