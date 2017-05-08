package controllers;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import forms.OpenLayer;

@Controller
public class OpenLayerController {
	@RequestMapping(value = "ol", method = RequestMethod.GET)
	public String openlayer(ModelMap modelMap) {

		modelMap.put("title", "OpenLayer");

		return "openlayer";
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@RequestMapping(value = "/jsonKaydet")
	public String jsonKaydet(@RequestParam("data") String str,
			OpenLayer openLayer) throws ParseException {

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(str);

		Gson gson1 = new Gson();
		String json = gson1.toJson(str);

		JSONArray array = new JSONArray();
		array.add(obj);

		// JsonObject o = new
		// JsonParser().parse(array.toJSONString()).getAsJsonObject();

		System.out.println("1" + json);

		System.out.println("2" + obj.toString());

		System.out.println("3" + array);

		// String obj2= array.toJSONString();
		Gson gson = new GsonBuilder().create();

		// JsonObject job = gson.fromJson(o, JsonObject.class);
		// JsonElement entry = job.getAsJsonObject("features")
		// .getAsJsonObject("geometry").getAsJsonArray("coordinates");

		// String str2 = entry.toString();
		//
		// System.out.println(str2+"----");

		//
		// String[] data = str.split(",");
		// Point[] pointArray = new Point[data.length];
		// for (int i = 0; i < data.length; i++)
		//
		// {
		// // pointArray = pointArray[i];
		// }
		// openLayer.setCoord(pointArray);
		// openLayerService.ekle(openLayer);

		return "redirect:/ol";
	}
}
