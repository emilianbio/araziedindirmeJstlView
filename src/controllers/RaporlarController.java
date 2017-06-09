package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import forms.AraziİslemHareketleri;
import service.AracService;
import service.AraziService;
import service.RaporlarService;

@Controller
@RequestMapping(value = "/raporlar", method = RequestMethod.GET)
public class RaporlarController {
	@Autowired
	AraziService araziService;
	@Autowired
	RaporlarService raporlarService;
	@Autowired
	AracService aracService;
	public String islemTipi = "";
	public AraziİslemHareketleri arazi;

	@RequestMapping(value = "/satisrapor")
	public ModelAndView giris(@ModelAttribute("araziIslem") AraziİslemHareketleri araziİslemHareketleri, ModelMap model,
			HttpServletRequest request, @CookieValue(value = "id", required = true) Long id) {
		ModelAndView modelAndView = new ModelAndView("Raporlar/SatisRaporlari");
		modelAndView.addObject("title", "Raporlar ");
		modelAndView.addObject("islemListesi", araziService.islemHareketleriListesi());

		if (arazi == null) {

			arazi = new AraziİslemHareketleri();
		}
		modelAndView.addObject("araziIslem", arazi);
		/* yönetici değilse geldiği sayfaya geri döner -BAŞLANGIÇ */
		// if (id != 1) {
		// String referer = request.getHeader("Referer");
		// return new ModelAndView("redirect:" + referer);

		/* yönetici değilse geldiği sayfaya geri döner -SON */
		// } else if (id == 3) {
		// System.out.println(id + " is null");
		// return new ModelAndView("redirect:/");
		// }
		// else

		List<AraziİslemHareketleri> araziList = new ArrayList<AraziİslemHareketleri>();
		araziList = araziService.islemHareketleriListesi();
		int devriIstenenParselSayisiToplami = 0, izinVerilenParselSayisiToplami = 0,
				izinVerilmeyenParselSayisiToplami = 0;
		float devriIstenenParselAlaniToplami = 0, izinVerilenParselAlaniToplami = 0,
				izinVerilmeyenParselAlaniToplami = 0;

		for (int i = 0; i < araziList.size(); i++) {

			devriIstenenParselSayisiToplami += araziList.get(i).getDevriIstenenParselSayisi();
			devriIstenenParselAlaniToplami += araziList.get(i).getDevriIstenenParselAlani();

			izinVerilenParselSayisiToplami += araziList.get(i).getIzinVerilenParselSayisi();

			izinVerilenParselAlaniToplami += araziList.get(i).getIzinVerilenParselAlani();

			izinVerilmeyenParselSayisiToplami += araziList.get(i).getIzinVerilmeyenParselSayisi();

			izinVerilmeyenParselAlaniToplami += araziList.get(i).getIzinVerilmeyenParselAlani();

			if (!(araziList.get(i).getDevriIstenenParselAlani() == araziList.get(i).getIzinVerilenParselAlani()
					+ araziList.get(i).getIzinVerilmeyenParselAlani())) {
				System.err.println(i + ". Hata " + "==================VERİTABANI HATALI VERİLER==================");

				System.err.println("İşlemi Yapan: " + araziList.get(i).getKullanici().getAdi());
				System.err.println("İşlemi Tarihi: " + araziList.get(i).getIslemZamani());
				System.err.println("İşlem Detayları: ");

				System.err.print("ID: " + araziList.get(i).getId() + "---");

				System.err.println(araziList.get(i).getTarih() + "-----");
				System.err.print(
						"Devri istenen parsel sayısı: " + araziList.get(i).getDevriIstenenParselSayisi() + "----");
				System.err.println("Devri istenen parsel alanı: " + araziList.get(i).getDevriIstenenParselAlani());

				System.err
						.print("İzin verilen parsel sayısı: " + araziList.get(i).getIzinVerilenParselSayisi() + "---");
				System.err.println("İzin verilen parsel alanı: " + araziList.get(i).getIzinVerilenParselAlani());

				System.err.print(
						"İzin verilmeyen parsel sayısı: " + araziList.get(i).getIzinVerilmeyenParselSayisi() + "---");
				System.err.println(
						"İzin verilmeyen parsel alanı: " + araziList.get(i).getIzinVerilmeyenParselAlani() + "---");
				System.err.println("Fark: ");
				System.err.print("Sayı: ");
				System.err.println((int) araziList.get(i).getDevriIstenenParselSayisi()
						- (int) araziList.get(i).getIzinVerilenParselSayisi()
						+ (int) araziList.get(i).getIzinVerilmeyenParselSayisi());
				System.err.print("Alan: ");
				System.err.println(
						araziList.get(i).getDevriIstenenParselAlani() - araziList.get(i).getIzinVerilenParselAlani()
								+ araziList.get(i).getIzinVerilmeyenParselAlani());

				System.err.println(i + ". Hata " + "==================VERİTABANI HATALI VERİLER SON==================");
			}

		}

		modelAndView.addObject("devriIstenenParselSayisi", devriIstenenParselSayisiToplami);
		modelAndView.addObject("devriIstenenParselAlani", devriIstenenParselAlaniToplami);
		modelAndView.addObject("izinVerilenParselSayisi", izinVerilenParselSayisiToplami);
		modelAndView.addObject("izinVerilenParselAlani", izinVerilenParselAlaniToplami);
		modelAndView.addObject("izinVerilmeyenParselSayisi", izinVerilmeyenParselSayisiToplami);
		modelAndView.addObject("izinVerilmeyenParselAlani", izinVerilmeyenParselAlaniToplami);
		modelAndView.addObject("ilceler", araclar.Genel.ilcelers());
		modelAndView.addObject("aylar", araclar.Genel.aylar());

		return modelAndView;
	}

	@RequestMapping(value = "/rapor", method = RequestMethod.GET)
	public @ResponseBody ArrayList<AraziİslemHareketleri> raporlarListesi() {
		// System.out.println(raporlarService.raporlarListesi());

		for (int i = 0; i < raporlarService.raporlarListesi().size(); i++) {

			System.out.println(raporlarService.raporlarListesi().get(i).getIlce().toString());
		}
		return raporlarService.raporlarListesi();
	}

	public ModelAndView toplamAraziSatislari() {
		ModelAndView modelAndView = new ModelAndView("redirect:/raporlar/satisrapor");

		return modelAndView;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/toplam", method = RequestMethod.GET)
	public @ResponseBody Map<String, Long> list() {

		List<AraziİslemHareketleri> arazi = new ArrayList<AraziİslemHareketleri>();
		arazi = araziService.islemHareketleriListesi();
		int devriIstenenParselSayisiToplami = 0, izinVerilenParselSayisiToplami = 0,
				izinVerilmeyenParselSayisiToplami = 0;
		float devriIstenenParselAlaniToplami = 0, izinVerilenParselAlaniToplami = 0,
				izinVerilmeyenParselAlaniToplami = 0;

		for (int i = 0; i < arazi.size(); i++) {

			devriIstenenParselSayisiToplami += arazi.get(i).getDevriIstenenParselSayisi();
			devriIstenenParselAlaniToplami += arazi.get(i).getDevriIstenenParselAlani();

			izinVerilenParselSayisiToplami += arazi.get(i).getIzinVerilenParselSayisi();

			izinVerilenParselAlaniToplami += arazi.get(i).getIzinVerilenParselAlani();

			izinVerilmeyenParselSayisiToplami += arazi.get(i).getIzinVerilmeyenParselSayisi();

			izinVerilmeyenParselAlaniToplami += arazi.get(i).getIzinVerilmeyenParselAlani();

		}
		Map<String, Long> toplam = new JSONObject();

		toplam.put("devriIstenenParselSayisiToplami", (long) devriIstenenParselSayisiToplami);
		toplam.put("devriIstenenParselAlaniToplami", (long) devriIstenenParselAlaniToplami);
		toplam.put("izinVerilenParselSayisiToplami", (long) izinVerilenParselSayisiToplami);
		toplam.put("izinVerilenParselAlaniToplami", (long) izinVerilenParselAlaniToplami);
		toplam.put("izinVerilmeyenParselSayisiToplami", (long) izinVerilmeyenParselSayisiToplami);
		toplam.put("izinVerilmeyenParselAlaniToplami", (long) izinVerilmeyenParselAlaniToplami);

		return toplam;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/ucayliktoplamgetir", method = RequestMethod.GET)
	public @ResponseBody Map<String, Long> ucAylikToplam(@RequestParam(value = "yil") String yil,
			@RequestParam(value = "birinciAy") String birinciAy, @RequestParam(value = "ikinciAy") String ikinciAy) {
		List<AraziİslemHareketleri> arazi = new ArrayList<AraziİslemHareketleri>();
		arazi = araziService.ucAylikRapor(yil, birinciAy, ikinciAy);
		int devriIstenenParselSayisiToplami = 0, izinVerilenParselSayisiToplami = 0,
				izinVerilmeyenParselSayisiToplami = 0;
		float devriIstenenParselAlaniToplami = 0, izinVerilenParselAlaniToplami = 0,
				izinVerilmeyenParselAlaniToplami = 0;
		for (int i = 0; i < arazi.size(); i++) {

			devriIstenenParselSayisiToplami += arazi.get(i).getDevriIstenenParselSayisi();
			devriIstenenParselAlaniToplami += arazi.get(i).getDevriIstenenParselAlani();

			izinVerilenParselSayisiToplami += arazi.get(i).getIzinVerilenParselSayisi();

			izinVerilenParselAlaniToplami += arazi.get(i).getIzinVerilenParselAlani();

			izinVerilmeyenParselSayisiToplami += arazi.get(i).getIzinVerilmeyenParselSayisi();

			izinVerilmeyenParselAlaniToplami += arazi.get(i).getIzinVerilmeyenParselAlani();

		}
		Map<String, Long> toplam = new JSONObject();

		toplam.put("devriIstenenParselSayisiToplami", (long) devriIstenenParselSayisiToplami);
		toplam.put("devriIstenenParselAlaniToplami", (long) devriIstenenParselAlaniToplami);
		toplam.put("izinVerilenParselSayisiToplami", (long) izinVerilenParselSayisiToplami);
		toplam.put("izinVerilenParselAlaniToplami", (long) izinVerilenParselAlaniToplami);
		toplam.put("izinVerilmeyenParselSayisiToplami", (long) izinVerilmeyenParselSayisiToplami);
		toplam.put("izinVerilmeyenParselAlaniToplami", (long) izinVerilmeyenParselAlaniToplami);

		return toplam;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/ilceyeGöreListeGetir", method = RequestMethod.GET)
	public @ResponseBody Map<String, Long> ilceyeGöreListeGetir(
			@RequestParam(value = "ilceID", required = true) String ilce,
			@RequestParam(value = "ilceBirinciAy", required = true) String ilkTarih,
			@RequestParam(value = "ilceIkinciAy", required = true) String sonTarih

	) {
		List<AraziİslemHareketleri> arazi = new ArrayList<AraziİslemHareketleri>();

		int devriIstenenParselSayisiToplami = 0, izinVerilenParselSayisiToplami = 0,
				izinVerilmeyenParselSayisiToplami = 0;

		float devriIstenenParselAlaniToplami = 0, izinVerilenParselAlaniToplami = 0,
				izinVerilmeyenParselAlaniToplami = 0;
		arazi = araziService.ilceyeGöreListele2(ilce, ilkTarih, sonTarih);

		for (int i = 0; i < arazi.size(); i++) {

			devriIstenenParselSayisiToplami += arazi.get(i).getDevriIstenenParselSayisi();
			devriIstenenParselAlaniToplami += arazi.get(i).getDevriIstenenParselAlani();

			izinVerilenParselSayisiToplami += arazi.get(i).getIzinVerilenParselSayisi();

			izinVerilenParselAlaniToplami += arazi.get(i).getIzinVerilenParselAlani();

			izinVerilmeyenParselSayisiToplami += arazi.get(i).getIzinVerilmeyenParselSayisi();

			izinVerilmeyenParselAlaniToplami += arazi.get(i).getIzinVerilmeyenParselAlani();

		}

		Map<String, Long> toplam = new JSONObject();

		toplam.put("devriIstenenParselSayisiToplami", (long) devriIstenenParselSayisiToplami);
		toplam.put("devriIstenenParselAlaniToplami", (long) (devriIstenenParselAlaniToplami));
		toplam.put("izinVerilenParselSayisiToplami", (long) izinVerilenParselSayisiToplami);
		toplam.put("izinVerilenParselAlaniToplami", (long) (izinVerilenParselAlaniToplami));
		toplam.put("izinVerilmeyenParselSayisiToplami", (long) izinVerilmeyenParselSayisiToplami);
		toplam.put("izinVerilmeyenParselAlaniToplami", (long) izinVerilmeyenParselAlaniToplami);

		return toplam;
	}
}
