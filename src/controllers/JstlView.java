package controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.InternalResourceView;

public class JstlView extends InternalResourceView {
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// Determine the path for the request dispatcher.
		String dispatcherPath = prepareForRendering(request, response);

		// set original view being asked for as a request parameter
		request.setAttribute("partial", dispatcherPath);
		request.setAttribute("title", model.get("title"));

		request.setAttribute("islemListesi", model.get("islemListesi"));
		request.setAttribute("ilceListesi", model.get("ilceListesi"));
		request.setAttribute("araziIslem", model.get("araziIslem"));
		request.setAttribute("ilceler", model.get("ilceler"));
		request.setAttribute("kullanici", model.get("kullanici"));
		request.setAttribute("tusYazisi", model.get("tusYazisi"));
		request.setAttribute("islemTipineGöreListe", model.get("islemTipineGöreListe"));
		request.setAttribute("raporlarListesi", model.get("raporlarListesi"));
		request.setAttribute("id", model.get("id"));
		request.setAttribute("devriIstenenParselSayisi", model.get("devriIstenenParselSayisi"));
		request.setAttribute("devriIstenenParselAlani", model.get("devriIstenenParselAlani"));
		request.setAttribute("izinVerilenParselSayisi", model.get("izinVerilenParselSayisi"));
		request.setAttribute("izinVerilenParselAlani", model.get("izinVerilenParselAlani"));
		request.setAttribute("izinVerilmeyenParselSayisi", model.get("izinVerilmeyenParselSayisi"));
		request.setAttribute("izinVerilmeyenParselAlani", model.get("izinVerilmeyenParselAlani"));

		request.setAttribute("izinFormu", model.get("izinFormu"));

		request.setAttribute("kullaniciListesi", model.get("kullaniciListesi"));

		request.setAttribute("izinListesi", model.get("izinListesi"));
		request.setAttribute("tips", model.get("tips"));
		request.setAttribute("tipListesi", model.get("tipListesi"));
		request.setAttribute("altTipListesi", model.get("altTipListesi"));
		request.setAttribute("markaListesi", model.get("markaListesi"));
		request.setAttribute("modelListesi", model.get("modelListesi"));

		request.setAttribute("aylar", model.get("aylar"));

		request.setAttribute("arac", model.get("arac"));

		request.setAttribute("aracCikisListesi", model.get("aracCikisListesi"));
		request.setAttribute("girisYapanKullanici", model.get("girisYapanKullanici"));
		request.setAttribute("download", model.get("download"));
		request.setAttribute("dosyaDurumu", model.get("dosyaDurumu"));
		request.setAttribute("url", model.get("url"));
		request.setAttribute("errorMessage", model.get("errorMessage"));
		request.setAttribute("dataPoints", model.get("dataPoints"));
		request.setAttribute("dataPoints1", model.get("dataPoints1"));
		request.setAttribute("dataPoints2", model.get("dataPoints2"));
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");

		// force everything to be template.jsp
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/template.jsp");
		requestDispatcher.include(request, response);

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			chain.doFilter(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}