/**
 * 
 */
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import araclar.Genel;
import forms.Kullanici;
import service.KullaniciService;

/**
 * @author Emrah Denizer
 *
 */
@Controller
@RequestMapping(value = "/kullanici-islemleri")
public class KullaniciController {
	@Autowired
	KullaniciService kullaniciService;
	public String tusYazisi = "Ekle";

	@RequestMapping(value = "/kullanici")
	public ModelAndView kullanici() {
		if (Genel.kullaniciBean == null) {
			Genel.kullaniciBean = new Kullanici();
		}
		ModelAndView modelAndView = new ModelAndView(
				"KullaniciIslemleri/KullaniciDuzenle");
		modelAndView.addObject("kullanici", Genel.kullaniciBean);
		modelAndView
				.addObject("kullaniciListesi", kullaniciService.kullanici());
		modelAndView.addObject("title", "Kullanici Bilgileri");

		return modelAndView;
	}

	@SuppressWarnings("null")
	@RequestMapping(value = "/kullaniciEkle", method = RequestMethod.GET)
	public String kullaniciEkle(Kullanici kullanici) {

		if (Genel.kullaniciBean != null) {

			System.out.println(Genel.kullaniciBean.getId());
			kullanici.setId(Genel.kullaniciBean.getId());
		} else {
			kullanici.setId((Long) null);
		}
		kullaniciService.kullaniciEkle(kullanici);
		Genel.kullaniciBean = null;
		return "redirect:/kullanici-islemleri/kullanici";
	}

	@RequestMapping(value = "/kullaniciGuncelle/{id}")
	public String kullaniciGuncelle(@PathVariable(value = "id") Long id) {
		Genel.kullaniciBean = kullaniciService.kullaniciGetirr(id);
		return "redirect:/kullanici-islemleri/kullanici";
	}

	@SuppressWarnings("null")
	@RequestMapping(value = "/kullaniciVazgec")
	public String kullaniciVazgec() {
		if (Genel.kullaniciBean != null) {

			Genel.kullaniciBean.setId(0);
			// Genel.kullaniciBean.setBirim(null);

			Genel.kullaniciBean.setCepTelefonu(0);
			Genel.kullaniciBean.setDurum((Character) null);
			Genel.kullaniciBean.setePosta(null);
			Genel.kullaniciBean.setIsimSoyisim(null);
			Genel.kullaniciBean.setSicilNo(null);
			Genel.kullaniciBean.setSifre(null);
			// Genel.kullaniciBean.setUnvan(null);
		}
		return "redirect:/kullanici-islemleri/kullanici";
	}

}
