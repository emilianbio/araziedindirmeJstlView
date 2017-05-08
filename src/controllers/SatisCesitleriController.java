package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import forms.AraziİslemHareketleri;
import service.AraziService;

@Controller
@RequestMapping(value = "/satis-cesitleri")
public class SatisCesitleriController {
	@Autowired
	AraziService araziService;

	@RequestMapping(value = "/miras", method = RequestMethod.GET)
	public ModelAndView Line() {
		ModelAndView modelAndView = new ModelAndView("SatisCesitleri/Miras");
		modelAndView.addObject("title", "Miras Yoluyla Devir");
		return modelAndView;
	}

	@RequestMapping(value = "/3083", method = RequestMethod.GET)
	public ModelAndView Spline() {
		ModelAndView modelAndView = new ModelAndView("SatisCesitleri/3083");
		modelAndView.addObject("title", "3083");
		return modelAndView;
	}

	@RequestMapping(value = "/vasif", method = RequestMethod.GET)
	public ModelAndView StepLine(
			@ModelAttribute("kaydet2") AraziİslemHareketleri kaydet,
			ModelMap modelMap) {
		if (kaydet == null) {
			kaydet = new AraziİslemHareketleri();
		}

		ModelAndView modelAndView = new ModelAndView("SatisCesitleri/Vasif");
		modelAndView.addObject("title", "Vasıf");
		modelAndView.addObject(modelMap.put("kaydet2", kaydet));
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/kaydet", method = RequestMethod.POST)
	public String kaydet() {
		System.out.println("www");
		return "";
	}

}