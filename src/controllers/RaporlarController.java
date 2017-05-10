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

import com.google.gson.Gson;

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

				System.out.println(i+". Hata "+"==================VERİTABANI HATALI VERİLER==================");

				System.out.println("İşlemi Yapan: " + araziList.get(i).getKullanici().getAdi());
				System.out.println("İşlemi Tarihi: " + araziList.get(i).getIslemZamani());
				System.out.println("İşlem Detayları: ");

				System.out.print("ID: " + araziList.get(i).getId() + "---");

				System.out.println(araziList.get(i).getTarih() + "-----");
				System.out.print(
						"Devri istenen parsel sayısı: " + araziList.get(i).getDevriIstenenParselSayisi() + "----");
				System.out.println("Devri istenen parsel alanı: " + araziList.get(i).getDevriIstenenParselAlani());

				System.out.print("İzin verilen parsel sayısı: " + araziList.get(i).getIzinVerilenParselSayisi() + "---");
				System.out.println("İzin verilen parsel alanı: " + araziList.get(i).getIzinVerilenParselAlani());

				System.out.print(
						"İzin verilmeyen parsel sayısı: " + araziList.get(i).getIzinVerilmeyenParselSayisi() + "---");
				System.out.println(
						"İzin verilmeyen parsel alanı: " + araziList.get(i).getIzinVerilmeyenParselAlani() + "---");
				System.out.println("Fark: ");
				System.out.print("Sayı: ");
				System.out.println((int) araziList.get(i).getDevriIstenenParselSayisi()
						- (int) araziList.get(i).getIzinVerilenParselSayisi()
						+ (int) araziList.get(i).getIzinVerilmeyenParselSayisi());
				System.out.print("Alan: ");
				System.out.println(
						araziList.get(i).getDevriIstenenParselAlani() - araziList.get(i).getIzinVerilenParselAlani()
								+ araziList.get(i).getIzinVerilmeyenParselAlani());
			
				System.out.println(i+". Hata "+"==================VERİTABANI HATALI VERİLER SON==================");
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

	@RequestMapping(value = "/ilceyeGöreListeGetir", method = RequestMethod.GET)
	public @ResponseBody String islemTipineGöreListeGetir(@RequestParam(value = "ilce", required = true) String ilce) {
		Gson gson = new Gson();

		return gson.toJson(araziService.ilceyeGöreListele(ilce));
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

			// System.out.println("rarporlar TOPLAM sys :" +
			// arazi.get(i).getTarih());

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
			@RequestParam(value = "birinciAy") String birinciAy, @RequestParam(value = "ikinciAy") String ikinciAy,
			@RequestParam(value = "ucuncuAy") String ucuncuAy) {
		List<AraziİslemHareketleri> arazi = new ArrayList<AraziİslemHareketleri>();
		arazi = araziService.islemHareketleriListesi();
		int devriIstenenParselSayisiToplami = 0, izinVerilenParselSayisiToplami = 0,
				izinVerilmeyenParselSayisiToplami = 0;
		int devriIstenenParselSayisiToplami2 = 0, izinVerilenParselSayisiToplami2 = 0,
				izinVerilmeyenParselSayisiToplami2 = 0;
		int devriIstenenParselSayisiToplami3 = 0, izinVerilenParselSayisiToplami3 = 0,
				izinVerilmeyenParselSayisiToplami3 = 0;
		float devriIstenenParselAlaniToplami = 0, izinVerilenParselAlaniToplami = 0,
				izinVerilmeyenParselAlaniToplami = 0;
		float devriIstenenParselAlaniToplami2 = 0, izinVerilenParselAlaniToplami2 = 0,
				izinVerilmeyenParselAlaniToplami2 = 0;
		float devriIstenenParselAlaniToplami3 = 0, izinVerilenParselAlaniToplami3 = 0,
				izinVerilmeyenParselAlaniToplami3 = 0;
		System.out.println("seçilen yıl : " + yil);
		for (int i = 0; i < arazi.size(); i++) {

			String[] yilKismi = arazi.get(i).getTarih().split("-");
			String year = yilKismi[0];

			String[] ayKismi = arazi.get(i).getTarih().split("-");

			String firstM = ayKismi[1];
			String secondM = ayKismi[1];
			String thirdM = ayKismi[1];
			System.out.println("rarporlar TOPLAM sys :" + year + "/" + firstM + "/" + secondM + "/" + thirdM);
			System.out.println("true-false :" + year.equals("2016"));
			System.out.println("true-false :" + firstM.equals("10"));

			if (year.equals(yil)) {
				if (firstM.equals(birinciAy)) {
					devriIstenenParselSayisiToplami += arazi.get(i).getDevriIstenenParselSayisi();
					devriIstenenParselAlaniToplami += arazi.get(i).getDevriIstenenParselAlani();

					izinVerilenParselSayisiToplami += arazi.get(i).getIzinVerilenParselSayisi();

					izinVerilenParselAlaniToplami += arazi.get(i).getIzinVerilenParselAlani();

					izinVerilmeyenParselSayisiToplami += arazi.get(i).getIzinVerilmeyenParselSayisi();

					izinVerilmeyenParselAlaniToplami += arazi.get(i).getIzinVerilmeyenParselAlani();

				}

				if (firstM.equals(ikinciAy)) {
					devriIstenenParselSayisiToplami2 += arazi.get(i).getDevriIstenenParselSayisi();
					devriIstenenParselAlaniToplami2 += arazi.get(i).getDevriIstenenParselAlani();

					izinVerilenParselSayisiToplami2 += arazi.get(i).getIzinVerilenParselSayisi();

					izinVerilenParselAlaniToplami2 += arazi.get(i).getIzinVerilenParselAlani();

					izinVerilmeyenParselSayisiToplami2 += arazi.get(i).getIzinVerilmeyenParselSayisi();

					izinVerilmeyenParselAlaniToplami2 += arazi.get(i).getIzinVerilmeyenParselAlani();

				}
				if (firstM.equals(ucuncuAy)) {
					devriIstenenParselSayisiToplami3 += arazi.get(i).getDevriIstenenParselSayisi();
					devriIstenenParselAlaniToplami3 += arazi.get(i).getDevriIstenenParselAlani();

					izinVerilenParselSayisiToplami3 += arazi.get(i).getIzinVerilenParselSayisi();

					izinVerilenParselAlaniToplami3 += arazi.get(i).getIzinVerilenParselAlani();

					izinVerilmeyenParselSayisiToplami3 += arazi.get(i).getIzinVerilmeyenParselSayisi();

					izinVerilmeyenParselAlaniToplami3 += arazi.get(i).getIzinVerilmeyenParselAlani();

				}

			}
		}
		Map<String, Long> toplam = new JSONObject();

		toplam.put("devriIstenenParselSayisiToplami", (long) devriIstenenParselSayisiToplami
				+ devriIstenenParselSayisiToplami2 + devriIstenenParselSayisiToplami3);
		toplam.put("devriIstenenParselAlaniToplami", (long) (devriIstenenParselAlaniToplami
				+ devriIstenenParselAlaniToplami2 + devriIstenenParselAlaniToplami3));
		toplam.put("izinVerilenParselSayisiToplami", (long) izinVerilenParselSayisiToplami
				+ izinVerilenParselSayisiToplami2 + izinVerilenParselSayisiToplami3);
		toplam.put("izinVerilenParselAlaniToplami", (long) (izinVerilenParselAlaniToplami
				+ izinVerilenParselAlaniToplami2 + izinVerilenParselAlaniToplami3));
		toplam.put("izinVerilmeyenParselSayisiToplami", (long) izinVerilmeyenParselSayisiToplami
				+ izinVerilmeyenParselSayisiToplami2 + izinVerilmeyenParselSayisiToplami3);
		toplam.put("izinVerilmeyenParselAlaniToplami", (long) (izinVerilmeyenParselAlaniToplami
				+ izinVerilmeyenParselAlaniToplami2 + izinVerilmeyenParselAlaniToplami3));

		return toplam;
	}

}
