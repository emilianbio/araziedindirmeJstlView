/**
 * 
 */
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import araclar.RolesEnum;
import forms.UserRoles;
import service.KullaniciService;
import service.UserRolesService;

/**
 * @author lenovo
 *
 */

@Controller
@RequestMapping(value = "/roller")
public class UserRolesController {
	@Autowired
	KullaniciService kullaniciService;
	@Autowired
	UserRolesService userRolesService;
	public UserRoles roll;

	@RequestMapping(value = "/rol-yonetimi")
	public String rolPanel(ModelMap model) {
		if (roll == null) {

			roll = new UserRoles();
		}
		model.put("kullaniciListesi", kullaniciService.kullanici());
		model.put("roll", roll);
		model.put("title", "Rol Yönetimi");
		model.put("roller", RolesEnum.values());
		return "KullaniciRolleri/Rol";

	}

	@RequestMapping(value = "/rolEkle")
	public String ekle(@ModelAttribute("roll") UserRoles roll, ModelMap model) {

		try {
			userRolesService.ekle(roll);
			return "redirect:/roller/rol-yonetimi";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			model.put("errorMessage", e.getMessage());

			return null;
		}

	}

}
