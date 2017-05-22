package controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import araclar.Genel;
import forms.Kullanici;
import service.KullaniciService;

@Controller
public class HomeController {
	@Autowired
	private KullaniciService kullaniciService;
	private Kullanici kullanici;
	public String giris = "0";

	@RequestMapping(value = "/")
	public ModelAndView home(ModelMap model, HttpServletResponse response, HttpServletRequest request)
			throws UnsupportedEncodingException {
		if (kullanici == null) {
			kullanici = new Kullanici();

		}

		Cookie cookie1 = new Cookie("id", "");
		Cookie cookie2 = new Cookie("isim", "");
		// cookie.setValue("");
		// cookie1.setValue("");
		// cookie2.setValue("");
		cookie1.setMaxAge(0);
		cookie2.setMaxAge(0);
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		request.setCharacterEncoding("utf-8");
		ModelAndView modelAndView = new ModelAndView("giris");
		modelAndView.addObject("girisBasarili", giris);
		modelAndView.addObject("title", "GİRİŞ ");
		modelAndView.addObject("kullanici", kullanici);

		Genel.kullaniciLoginInfo = null;
		return modelAndView;
	}

	@RequestMapping(value = "/cikis")
	public ModelAndView cikis(ModelMap model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) throws UnsupportedEncodingException {
		if (kullanici == null) {
			kullanici = new Kullanici();

		}

		// session = request.getSession();
		// session.invalidate();
		// Genel.kullaniciLoginInfo = null;
		Cookie cookie1 = new Cookie("id", "");
		Cookie cookie2 = new Cookie("isim", "");
		// cookie.setValue("");
		// cookie1.setValue("");
		// cookie2.setValue("");
		cookie1.setMaxAge(0);
		cookie2.setMaxAge(0);
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		request.setCharacterEncoding("utf-8");
		ModelAndView modelAndView = new ModelAndView("redirect://");
		modelAndView.addObject("girisBasarili", giris);
		modelAndView.addObject("title", "GİRİŞ ");
		modelAndView.addObject("kullanici", kullanici);
		return new ModelAndView("redirect:/anasayfa");
	}

	@RequestMapping(value = "/anasayfa")
	public ModelAndView giris(ModelMap model, HttpServletResponse response, HttpServletRequest request)
			throws UnsupportedEncodingException {

		if (kullanici == null) {
			kullanici = new Kullanici();

		}
		ModelAndView modelAndView = new ModelAndView("giris");
		modelAndView.addObject("kullanici", kullanici);
		modelAndView.addObject("girisBasarili", giris);
		modelAndView.addObject("title", "Anasayfa ");
		return modelAndView;
	}

	@RequestMapping(value = "/gonder")
	public ModelAndView gonder(@RequestParam(value = "giris") String giris2, ModelMap model,
			HttpServletResponse response) throws IOException {
		ModelAndView modelAndView = new ModelAndView("redirect:/anasayfa");

		String giris3 = "C:\\Users\\Emrah Denizer\\Web Projelerim\\araziedindirme\\" + giris2;

		int some = 0;
		for (int i = 0; i <= 20; i++) {
			some += i;
			System.out.println(some);

			PrintWriter out = new PrintWriter(new FileWriter(giris3 + ".docx", true), true);
			out.write((Integer.toString(i) + " " + Integer.toString(some)));
			out.write(System.getProperty("line.separator"));
			out.close();

		}

		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView kullaniciOnay(@ModelAttribute("kullanici") Kullanici kullanici,
			@RequestParam(value = "isimSoyisim") String isim, @RequestParam(value = "sifre") String sifre,
			HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws UnsupportedEncodingException {
		// kullanici.toString();
		response.setCharacterEncoding("UTF-8");
		kullanici.setBirim(null);
		kullanici.setCepTelefonu(0);
		kullanici.setePosta(null);
		kullanici.setSicilNo(null);
		kullanici.setUnvan(null);

		Kullanici kayitliKullanici = kullaniciService.kullaniciGiris(isim, sifre);
		System.out.println(kullanici.getIsimSoyisim());
		System.out.println("logine basıldı" + " " + new Date());
		if (kayitliKullanici == null) {
			response.setCharacterEncoding("UTF-8");
			// JOptionPane panel = new JOptionPane();
			// JOptionPane.showMessageDialog(panel, "Yanlış Bilgi Girdiniz....",
			// "Hatalı Giriş",
			// JOptionPane.ERROR_MESSAGE);
			System.out.println("Giriş Yapılamadı" + " " + new Date());
			return new ModelAndView("redirect:/");
		} else {
			Genel.kullaniciLoginInfo = kayitliKullanici;
			Genel.kullaniciLoginInfo.setId(kayitliKullanici.getId());
			Genel.kullaniciLoginInfo.setIsimSoyisim(kayitliKullanici.getIsimSoyisim());

			Cookie cookieId = new Cookie("id", Long.toString(kayitliKullanici.getId()));
			Cookie cookieIsim = new Cookie("isim", kayitliKullanici.getIsimSoyisim());
			// response.setCharacterEncoding("UTF-8");
			String valueId = URLDecoder.decode(cookieId.getValue(), "UTF-8");
			String valueIsim = URLDecoder.decode(cookieIsim.getValue(), "UTF-8");
			System.out.println("cookie isim / " + cookieIsim.getValue());
			System.out.println("cookie id / " + cookieId.getValue());
			System.out.println("value isim / " + valueIsim);
			System.out.println("value id / " + valueId);

			response.addCookie(cookieIsim);
			response.addCookie(new Cookie("isim", valueIsim));
			response.addCookie(new Cookie("id", valueId));

			System.out.println("Giriş Başarılı.." + " " + new Date());
			return new ModelAndView("redirect:/anasayfa");
		}
	}

	@RequestMapping("/error")
	public String errorPage(ModelMap model) {
		String message = araclar.Genel.getErrorMessage();
		if (message != null)
			model.put("errorMessage", message);
		return "error";
	}
}
