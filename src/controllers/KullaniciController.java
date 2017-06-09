/**
 * 
 */
package controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import araclar.Genel;
import forms.Kullanici;
import service.KullaniciService;
import service.UserRolesService;

/**
 * @author Emrah Denizer
 *
 */
@Controller
@RequestMapping(value = "/kullanici-islemleri")
public class KullaniciController {
	private static final Logger log = LoggerFactory.getLogger(KullaniciController.class);
	private static String UPLOADED_FOLDER = "C:\\Users\\Emrah Denizer\\Web Projelerim\\araziedindirmeIsyeri\\WebContent\\WEB-INF\\assets\\images\\personelimage\\";
	@Autowired
	KullaniciService kullaniciService;

	@Autowired
	UserRolesService userRolesService;
	public String tusYazisi = "Ekle";

	@RequestMapping(value = "/kullanici")
	public ModelAndView kullanici(@CookieValue(value = "id", required = false) Long id) {
		if (Genel.kullaniciBean == null) {
			Genel.kullaniciBean = new Kullanici();
			log.debug("yeni bean oluşturuldu...");
		}
		String newFilenameBase = UUID.randomUUID().toString();

		File curDir = new File(UPLOADED_FOLDER);

		File[] filesList = curDir.listFiles();
		for (File f : filesList) {
			if (f.isDirectory())
				System.out.println(f.getName());
			if (f.isFile()) {
				System.out.println(f.getName());
			}
		}

		System.out.println("random file name : " + newFilenameBase);
		ModelAndView modelAndView = new ModelAndView("KullaniciIslemleri/KullaniciDuzenle");
		modelAndView.addObject("kullanici", Genel.kullaniciBean);
		modelAndView.addObject("kullaniciListesi", kullaniciService.kullanici());
		modelAndView.addObject("title", "Kullanici Bilgileri");
		modelAndView.addObject("roller", userRolesService.roleList());
		// Genel.kullaniciBean =null;
		return modelAndView;
	}

	@RequestMapping(value = "/kullaniciEkle", method = RequestMethod.POST)
	public String kullaniciEkle(@ModelAttribute("kullanici") Kullanici kullanici, BindingResult result,
			@CookieValue(value = "id", required = false) Long id,
			@CookieValue(value = "isim", required = false) String isim, RedirectAttributes redirectAttributes,
			@RequestParam("file") MultipartFile file) throws IOException {// @RequestParam("userPic")
																			// MultipartFile
																			// file,
		if (result.hasErrors()) {

			System.err.println("hatalı sütun: " + result.getFieldError());

			// Sayfa redirect olduğunda mesaj görüntülenmesini istiyorsak
			// redirectAttributes.addFlashAttribute("message", "Please select a
			// file to upload");
			return "redirect:/kullanici-islemleri/kullanici";
		}
		System.out.println("kullanici : " + kullanici);
		if (Genel.kullaniciBean != null) {
			System.out.println(Genel.kullaniciBean.getId());
			kullanici.setId(Genel.kullaniciBean.getId());

		} else {
			kullanici.setId(0l);
		}

		/*
		 * if (file.isEmpty()) { redirectAttributes.addFlashAttribute("message",
		 * "Please select a file to upload"); return
		 * "redirect:/kullanici-islemleri/uploadJsp"; }
		 * 
		 * try {
		 * 
		 * // Get the file and save it somewhere byte[] bytes = file.getBytes();
		 * Path path = Paths.get(UPLOADED_FOLDER + isim + "_" + id);
		 * Files.write(path, bytes);
		 * 
		 * redirectAttributes.addFlashAttribute("message",
		 * "You successfully uploaded '" + file.getOriginalFilename() + "'" +
		 * "<br>" + "Dosya Yolu " + path.toString());
		 * 
		 * } catch (IOException e) { e.printStackTrace(); }
		 */
		byte[] img = file.getBytes();
		kullanici.setPic(img);
		System.out.println("kullanici pic: " + kullanici.getPic() + " /// " + img);
		kullaniciService.kullaniciEkle(kullanici);
		Genel.kullaniciBean = null;
		return "redirect:/kullanici-islemleri/kullanici";
	}

	@RequestMapping(value = "/kullaniciGuncelle/{id}")
	public String kullaniciGuncelle(@PathVariable(value = "id") Long id) {
		Genel.kullaniciBean = kullaniciService.kullaniciGetirr(id);
		return "redirect:/kullanici-islemleri/kullanici";
	}

	@RequestMapping(value = "/kullaniciVazgec")
	public String kullaniciVazgec(HttpServletRequest request) {
		// if (Genel.kullaniciBean != null) {
		String referrer = request.getHeader("referer");
		System.out.println("kullaniciVazgeç e basıldı..." + referrer);

		Genel.kullaniciBean = null;
		return "redirect:/kullanici-islemleri/kullanici";
	}

	@RequestMapping(value = "/productImage", method = RequestMethod.GET)
	public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam("id") Long id) throws IOException {
		Kullanici kullanici = null;
		if (id != null) {
			kullanici = kullaniciService.kullaniciGetirr(id);
		}

			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");// ,
																					// image/jpg,
																					// image/png,
																					// image/gif
			// response.setContentLength(kullanici.getUserPic());
			// response.getOutputStream().write(kullanici.getUserPic());
			// OutputStream out = response.getOutputStream();
			// IOUtils.copy(new ByteArrayInputStream(kullanici.getUserPic()),
			// out);

			// out.flush();
			// out.close();

			// InputStream in1 = new ByteArrayInputStream(bAvatar);
			// IOUtils.copy(in1, response.getOutputStream());
		response.getOutputStream().flush();
	}

	//veritabanına kayıtlı resim dosyasını göstermek için kod
	@RequestMapping(value = "/photoGoster2", method = RequestMethod.GET)
	public void testphoto2(@RequestParam(value = "id", required = false) Long id, HttpServletResponse response,
			ModelMap model) throws IOException {
		Kullanici kullanici = kullaniciService.kullaniciGetirr(id);

		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		if (id == 0) {

			response.getOutputStream().write(null);
		}
		if (kullanici.getPic() != null && kullanici.getPic().length > 0) {
			response.getOutputStream().write(kullanici.getPic());
		}
	}

	
	//harici klasörde kayıtlı olan resimeleri gösterme için kullanılan kod
	@RequestMapping(value = "/photoGoster", method = RequestMethod.GET)
	public void testphoto(@RequestParam(value = "id", required = false) Long id, HttpServletResponse response,
			ModelMap model) throws IOException {
		Kullanici kullanici = kullaniciService.kullaniciGetirr(id);

		if (id == 8) {

			System.out.println("id 8 pic : " + kullanici.getPic());

		}
		FileInputStream fis = null;
		
		//veritabanında resim dosyasının kayıtlı olduğu dosya yolunu gösterir
		
		//if (kullanici.getUserPicURL() == null) {
			fis = new FileInputStream(new File(
					"C:/Users/Emrah Denizer/Web Projelerim/araziedindirmeIsyeri/WebContent/WEB-INF/assets/images/personelimage/avatar"));
			System.out.println("avatar yüklendi....");
	//	} else {
		//	fis = new FileInputStream(new File(kullanici.getPic().toString()));
		//}
		@SuppressWarnings("resource")
		BufferedInputStream bis = new BufferedInputStream(fis);
		response.setContentType("image/jpeg, image/jpg, image/png,	 image/gif");
		BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream());
		for (int data; (data = bis.read()) > -1;) {
			output.write(data);
		}

		

	}

	@RequestMapping("/uploadJsp")
	public String imgUpload(ModelMap model) {

		model.put("kullaniciListesi", kullaniciService.kullanici());
		return "imgUpload";
	}

	@RequestMapping("/kullanici-profile")
	public String imgUpload1(ModelMap model) {

		model.put("kullaniciListesi", kullaniciService.kullanici());

		return "KullaniciIslemleri/KullaniciProfile";
	}

	
	
	//resim dosyalarını harici klasöre ve dosya yolunu da veritabanına kaydeetmek için kod
	@RequestMapping("/upload") // //new annotation since 4.3
	public String singleFileUpload(Kullanici kullanici, @RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "isim", required = false) String isim, @RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:/kullanici-islemleri/uploadJsp";
		}

		try {
			Kullanici kullanici2 = kullaniciService.kullaniciGetirr(id);

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + kullanici2.getIsimSoyisim() + "_" + id);
			Files.write(path, bytes);

			//kullanici2.setUserPicURL(UPLOADED_FOLDER + kullanici2.getIsimSoyisim() + "_" + id);
			System.out.println(kullanici2.getIsimSoyisim() + "_" + id);
			kullaniciService.kullaniciEkle(kullanici2);
			redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename()
					+ "'" + "<br>" + "Dosya Yolu " + path.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/kullanici-islemleri/uploadJsp";
	}
}
