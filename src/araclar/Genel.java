package araclar;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import forms.Kullanici;

public class Genel {

	public static Kullanici kullaniciBean = null;

	public static Kullanici getKullaniciBean() {
		return kullaniciBean;
	}

	public static void setKullaniciBean(Kullanici kullaniciBean) {
		Genel.kullaniciBean = kullaniciBean;
	}

	private static Integer a = 0;

	public static Integer getA() {
		return a;
	}

	public static void setA(Integer a) {
		Genel.a = a;
	}

	private static Integer kayitSayisi = 15;

	public static Integer TamSayi(String sSayi, Integer sira) {
		Integer sayi = 0;
		try {
			sayi = Integer.parseInt(sSayi.substring(sira - 1, sira));
		} catch (Exception e) {
			sayi = 0;
		}
		return sayi;
	}

	public static Boolean TCKimlikDogrumu(String TCKimlik) {
		Boolean sonuc = false;
		if (TCKimlik.length() > 0) {
			Integer carpim = 0;
			try {
				if (Long.parseLong(TCKimlik) > 9999999999L) {
					carpim = (TamSayi(TCKimlik, 1) + TamSayi(TCKimlik, 3)
							+ TamSayi(TCKimlik, 5) + TamSayi(TCKimlik, 7) + TamSayi(
							TCKimlik, 9)) * 7;
					carpim -= (TamSayi(TCKimlik, 2) + TamSayi(TCKimlik, 4)
							+ TamSayi(TCKimlik, 6) + TamSayi(TCKimlik, 8));
					sonuc = (carpim % 10 == TamSayi(TCKimlik, 10));
				}
			} catch (Exception e) {
			}
		}
		return sonuc;
	}

	@SuppressWarnings("finally")
	public static String tarihStringe(Date tarih) {
		String sonuc = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			if (tarih != null)
				sonuc = dateFormat.format(tarih);
		} finally {
			return sonuc;
		}
	}

	@SuppressWarnings("finally")
	public static String tarihGunAyStringe(Date tarih) {
		String sonuc = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM");
			if (tarih != null)
				sonuc = dateFormat.format(tarih);
		} finally {
			return sonuc;
		}
	}

	@SuppressWarnings("finally")
	public static String tarihZamanStringe(Date tarih) {
		String sonuc = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"dd.MM.yyyy HH:mm:ss");
			if (tarih != null)
				sonuc = dateFormat.format(tarih);
		} finally {
			return sonuc;
		}
	}

	public static String zamanYaz(Date tarih, Boolean gun) {
		if (tarih == null)
			return "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		String sonuc = dateFormat.format(tarih);
		if (gun) {
			Calendar cal = Calendar.getInstance();
			Calendar bugun = Calendar.getInstance();
			cal.setTime(tariheCevir(tarihStringe(tarih)));
			bugun.setTime(tariheCevir(tarihStringe(new Date())));
			if (bugun.compareTo(cal) == 0) {
				dateFormat = new SimpleDateFormat("HH:mm");
				sonuc = dateFormat.format(tarih);
			}
			;
		} else {
			dateFormat = new SimpleDateFormat("HH:mm");
			sonuc = dateFormat.format(tarih);
		}
		;
		return sonuc;
	}

	public static Date tariheCevir(String tarih) {
		Date sonuc = null;
		if (tarih == null)
			tarih = tarihStringe(null);
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		try {
			sonuc = format.parse(tarih);
		} catch (Exception e) {
		} finally {
		}
		return sonuc;
	}

	public static String kisalt(String cumle, Integer uzunluk) {
		if (uzunluk == null)
			uzunluk = 30;
		String sonuc = "";
		if (cumle != null) {
			sonuc = cumle;
			if (cumle.length() > uzunluk)
				sonuc = cumle.substring(0, uzunluk) + "...";
		}
		return sonuc;
	}

	@SuppressWarnings("rawtypes")
	public static Map kanGruplari() {
		Map<Short, String> kanGruplari = new HashMap<Short, String>();
		kanGruplari.put((short) 0, " ");
		kanGruplari.put((short) 1, "Arh+");
		kanGruplari.put((short) 2, "Arh-");
		kanGruplari.put((short) 3, "Brh+");
		kanGruplari.put((short) 4, "Brh-");
		kanGruplari.put((short) 5, "0rh+");
		kanGruplari.put((short) 6, "0rh-");
		kanGruplari.put((short) 7, "ABrh+");
		kanGruplari.put((short) 8, "ABrh-");
		return kanGruplari;
	}

	@SuppressWarnings("rawtypes")
	public static Map aylar() {
		Map<String, String> aylar = new HashMap<String, String>();
		aylar.put("11", "Kasım");
		aylar.put("01", "Ocak");
		aylar.put("02", "Şubat");
		aylar.put("03", "Mart");
		aylar.put("04", "Nisan");
		aylar.put("05", "Mayıs");
		aylar.put("06", "Haziran");
		aylar.put("07", "Temmuz");
		aylar.put("08", "Ağustos");
		aylar.put("09", "Eylül");
		aylar.put("10", "Ekim");
		
		aylar.put("12", "Aralık");
		return aylar;
	}

	public static Integer yil() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return c.get(Calendar.YEAR);
	}

	public static Integer ay() {
		Calendar c = new GregorianCalendar();
		return c.get(Calendar.MONTH) + 1;
	}

	@SuppressWarnings("unused")
	private static String smskullaniciParametre() {
		String sonuc = "";
		try {
			String userName = URLEncoder.encode("HermesTest", "UTF-8"), userPass = URLEncoder
					.encode("test017", "UTF-8"), customerCode = URLEncoder
					.encode("01001201", "UTF-8"), apiKey = URLEncoder.encode(
					"256c51b89746fffa807d14241c9", "UTF-8"), vendorCode = URLEncoder
					.encode("2", "UTF-8");
			sonuc = "userName=" + userName + "&userPass=" + userPass
					+ "&customerCode=" + customerCode + "&apiKey=" + apiKey
					+ "&vendorCode=" + vendorCode;
		} catch (Exception e) {

		}
		return sonuc;
	}

	public static Document loadXMLFromString(String xml) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		return builder.parse(is);
	}

	@SuppressWarnings("unused")
	private static Document smsIstek(String requestUrl, String param) {
		Document xmlDocument = null;
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length",
					"" + Integer.toString(param.getBytes().length));
			// connection.setRequestProperty("Content-Language", "en-US");
			connection.setUseCaches(false);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			// Send request
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(param);
			wr.flush();
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			System.out.println(response.toString());
			xmlDocument = loadXMLFromString(response.toString());
		} catch (Exception e) {
		}
		return xmlDocument;
	}

	// public static String smsGonder(List memurListesi,Sms sms,Sube sube){
	// String sonuc="";
	// try {
	// URL u = new URL("http://api.netgsm.com.tr/xmlbulkhttppost.asp");
	// HttpURLConnection uc = (HttpURLConnection)u.openConnection();
	// HttpURLConnection connection = (HttpURLConnection) uc;
	// connection.setDoOutput(true);
	// connection.setDoInput(true);
	// connection.setRequestMethod("POST");
	// OutputStream out = connection.getOutputStream();
	// OutputStreamWriter wout = new OutputStreamWriter(out, "UTF-8");
	//
	// wout.write("<?xml version='1.0' encoding='UTF-8'?>"+
	// " <mainbody>"+
	// " <header>"+
	// "<company dil='TR'>NETGSM</company>"+
	// "<usercode>"+sube.getSmsKullaniciAdi()+"</usercode>"+
	// " <password>"+sube.getSmsSifre()+"</password>"+
	// " <startdate></startdate>"+
	// " <stopdate></stopdate>"+
	// " <type>1:n</type>"+
	// " <msgheader>"+sube.getSmsGonderen()+"</msgheader>"+
	// " </header>"+
	// " <body>");
	// wout.write(" <msg><![CDATA["+sms.getMetin()+"]]></msg>");
	// String[] telefonlar=sms.getTelefonlar().split(";");
	// for (String telefon : telefonlar) {
	// if(telefonUygunmu(telefon)) wout.write(" <no>"+telefon+"</no>");
	// }
	// if(sms.getMemuraGonder()!=null && sms.getMemuraGonder() ){
	// Iterator<MemurlarExtends> iter=memurListesi.iterator();
	// StringBuffer stringBufferTelefonlar=new StringBuffer();
	// while(iter.hasNext()){
	// MemurlarExtends memurlarExtends=iter.next();
	// if(memurlarExtends.getSecili()){
	// wout.write(" <no>"+memurlarExtends.getTelefon()+"</no>");
	// stringBufferTelefonlar.append(memurlarExtends.getId()+";");
	// }
	// }
	// sms.setMemurlar(stringBufferTelefonlar.toString());
	// }
	// wout.write(" </body> </mainbody>");
	// wout.flush();
	// out.close();
	//
	// InputStream is = connection.getInputStream();
	// BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	// String line;
	// StringBuffer response = new StringBuffer();
	// while((line = rd.readLine()) != null) {
	// response.append(line);
	// response.append('\r');
	// }
	// rd.close();
	// System.out.println("****"+response.toString()+"****");
	//
	// if(response.toString().startsWith("00"))
	// sonuc="SMS BaÅŸarÄ±yla GÃ¶nderildi";
	// if(response.toString().startsWith("10"))
	// sonuc="Telefon NumarasÄ± HatalÄ±";
	// if(response.toString().startsWith("20"))
	// sonuc="SMS Metni HatalÄ± veya Ã‡ok Uzun";
	// if(response.toString().startsWith("30"))
	// sonuc="KullanÄ±cÄ± bilgisi bulunamadÄ±";
	// if(response.toString().startsWith("40"))
	// sonuc="GÃ¶nderen Bilgisi Sisteme TanÄ±tÄ±lmamÄ±ÅŸ";
	// if(response.toString().startsWith("50")) sonuc="Kredi Yok";
	// if(response.toString().startsWith("60")) sonuc="Telefon No Yok";
	// if(response.toString().startsWith("70")) sonuc="SMS BaÅŸlÄ±ÄŸÄ± hatalÄ±";
	// /*
	//
	// InputStream in = connection.getInputStream();
	// int c;
	// while ((c = in.read()) != -1) System.out.write(c);
	// System.out.println();
	// in.close();
	// out.close();*/
	// is.close();
	// connection.disconnect();
	// } catch (IOException e) {
	// sonuc=e.getMessage();
	// System.err.println(e);
	// e.printStackTrace();
	// }
	// return sonuc;
	// }

	public static Boolean telefonUygunmu(String telefon) {
		Boolean sonuc = false;
		sonuc = (telefon != null && telefon.length() > 9); // &&
															// telefon.startsWith("5")
		return sonuc;
	}

	public static String enterlerBrye(String yazi) {
		if (yazi == null)
			return "";
		return yazi.replace("\n", "<br/>");
	}

	public static Integer getKayitSayisi() {
		return kayitSayisi;
	}

	public static void setKayitSayisi(Integer kayitSayisi) {
		Genel.kayitSayisi = kayitSayisi;
	}

	public static String sayfala(Integer sayfa, Long kSayisi) {
		final int sayfalamaSayi = 10;
		String stilClick = "cursor:pointer;border:solid 0.1em #89ACD6;float:left;width:1.3em;-moz-border-radius: 0.2em;-webkit-border-radius: 0.2em;border-radius: 0.2em;";
		String stil = "background-color:gray;border:solid 0.1em #89ACD6;float:left;width:1.3em;-moz-border-radius: 0.2em;-webkit-border-radius: 0.2em;border-radius: 0.2em;";
		String bosluk = "<div style='float:left;'>&nbsp;</div>";
		int sayfaSayisi = (int) (Math.ceil((double) kSayisi / kayitSayisi));
		int baslangicSayfa = sayfa - sayfalamaSayi / 2;
		if ((sayfaSayisi - sayfa) < sayfalamaSayi / 2)
			baslangicSayfa = sayfaSayisi - sayfalamaSayi;
		if (baslangicSayfa < 2)
			baslangicSayfa = 2;
		String sonuc = "";
		if (sayfaSayisi > 1) {
			sonuc = "<div style='float:left;'>Sayfa : <img src='/uygulama/resources/resim/sola_trans.png'"
					+ ((sayfa > 1) ? " onclick='sayfadakiKayitlariGetir("
							+ (sayfa - 1) + ")' style='cursor:pointer;'"
							: "style='opacity:0.20;'") + " /></div>";
			if (1 != sayfa)
				sonuc += bosluk
						+ "<div onclick='sayfadakiKayitlariGetir(1)' style='"
						+ stilClick + "'>1 </div>";
			else
				sonuc += bosluk + "<div style='" + stil + "'>1 </div>";
			if (baslangicSayfa > 2)
				sonuc += bosluk + "<div style='float:left;'>....</div>";
			for (int i = baslangicSayfa; i <= baslangicSayfa + sayfalamaSayi
					&& i <= sayfaSayisi; i++) {
				if (i != sayfa)
					sonuc += bosluk + "<div onclick='sayfadakiKayitlariGetir("
							+ i + ")' style='" + stilClick + "'> " + i
							+ " </div> ";
				else
					sonuc += bosluk + "<div style='" + stil + "'> " + i
							+ " </div>";
			}
			if (baslangicSayfa + sayfalamaSayi + 1 < sayfaSayisi)
				sonuc += bosluk + "<div style='float:left;'>....</div>";
			if (baslangicSayfa + sayfalamaSayi < sayfaSayisi)
				sonuc += bosluk + " <div onclick='sayfadakiKayitlariGetir("
						+ sayfaSayisi + ")' style='" + stilClick + "'> "
						+ sayfaSayisi + " </div> ";
			sonuc += bosluk
					+ "<div style='float:left;'><img src='/uygulama/resources/resim/saga_trans.png'"
					+ ((sayfa < sayfaSayisi) ? " onclick='sayfadakiKayitlariGetir("
							+ (sayfa + 1) + ")' style='cursor:pointer;'"
							: "style='opacity:0.20;'") + " /></div>";
		}
		return sonuc;
	}

	public static long gunFarki(Date eksilenTarih, Date cikanTarih) {
		Long eksilen = sadeceTarih(eksilenTarih).getTime();
		Long cikan = sadeceTarih(cikanTarih).getTime();
		if (eksilenTarih.after(cikanTarih)) {
			eksilen += 1000 * 60 * 60 * 10;
			cikan += 1000 * 60 * 60 * 5;
		} else {
			cikan += 1000 * 60 * 60 * 10;
			eksilen += 1000 * 60 * 60 * 5;
		}
		return ((eksilen - cikan) / (1000 * 60 * 60 * 24));

	}

	public static Date sadeceTarih(Date gun) {
		if (gun == null)
			gun = new Date();
		return tariheCevir(tarihStringe(gun));
	}

	public static Date gunEkle(Date date, Integer gunSayisi) {
		if (gunSayisi == null)
			gunSayisi = 1;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, gunSayisi);
		return c.getTime();
	}

	public static Date tarihmurakabe(String tarih) {
		Date sonuc = null;

		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		try {
			sonuc = format.parse(tarih);
		} catch (Exception e) {
		} finally {
		}
		return sonuc;
	}

	public static String sayfalar(Integer sayfa, Long kSayisi, String url) {
		final int sayfalamaSayi = 10;
		String stilClick = "cursor:pointer;border:solid 0.1em #89ACD6;float:left;width:1.3em;-moz-border-radius: 0.2em;-webkit-border-radius: 0.2em;border-radius: 0.2em;";
		String stil = "background-color:gray;border:solid 0.1em #89ACD6;float:left;width:1.3em;-moz-border-radius: 0.2em;-webkit-border-radius: 0.2em;border-radius: 0.2em;";
		String bosluk = "<div style='float:left;'>&nbsp;</div>";
		int sayfaSayisi = (int) (Math.ceil((double) kSayisi / kayitSayisi));
		// System.out.println("sayfa Sayisi "+sayfaSayisi
		// +" kayi"+kayitSayisi+"   ksayisi"+kSayisi);
		int baslangicSayfa = sayfa - sayfalamaSayi / 2;
		if ((sayfaSayisi - sayfa) < sayfalamaSayi / 2)
			baslangicSayfa = sayfaSayisi - sayfalamaSayi;
		if (baslangicSayfa < 2)
			baslangicSayfa = 2;
		String sonuc = "";
		if (sayfaSayisi > 1) {
			// sonuc="<div style='float:left;'>Sayfa : <a href='"+url+"?sayfano="+(sayfa-1)+"'>'<img src='/uygulama/resources/resim/sola_trans.png' style='opacity:0.20;'/></a></div>";
			if (1 != sayfa)
				sonuc += bosluk + "<div style='" + stilClick + "'><a href='"
						+ url + "?sayfano=1'>1</a> </div>";
			else
				sonuc += bosluk + "<div style='" + stil + "'>1 </div>";
			if (baslangicSayfa > 2)
				sonuc += bosluk + "<div style='float:left;'>....</div>";
			for (int i = baslangicSayfa; i <= baslangicSayfa + sayfalamaSayi
					&& i <= sayfaSayisi; i++) {
				if (i != sayfa)
					sonuc += bosluk + "<div style='" + stilClick
							+ "'> <a href='" + url + "?sayfano=" + i + "'>" + i
							+ "</a> </div> ";
				else
					sonuc += bosluk + "<div style='" + stil + "'> " + i
							+ " </div>";
			}
			if (baslangicSayfa + sayfalamaSayi + 1 < sayfaSayisi)
				sonuc += bosluk + "<div style='float:left;'>....</div>";
			if (baslangicSayfa + sayfalamaSayi < sayfaSayisi)
				sonuc += bosluk + " <div style='" + stilClick + "'> <a href='"
						+ url + "?sayfano=" + sayfaSayisi + "'>" + sayfaSayisi
						+ "</a> </div> ";
			// sonuc+=bosluk+"<div style='float:left;'><img src='/uygulama/resources/resim/saga_trans.png'"+((sayfa<sayfaSayisi)?" onclick='sayfadakiKayitlariGetir("+(sayfa+1)+")' style='cursor:pointer;'":"style='opacity:0.20;'")
			// +" /></div>";
		}
		return sonuc;
	}

	public static Map<String, String> ilcelers() {
		Map<String, String> ilceler = new LinkedHashMap<String, String>();
		ilceler.put("Yüreğir", "Yüreğir");
		ilceler.put("Çukurova", "Çukurova");
		ilceler.put("Sarıçam", "Sarıçam");
		ilceler.put("Tufanbeyli", "Tufanbeyli");
		ilceler.put("Saimbeyli", "Saimbeyli");
		ilceler.put("Feke", "Feke");
		ilceler.put("Aladağ", "Aladağ");
		ilceler.put("Kozan", "Kozan");
		ilceler.put("İmamoğlu", "İmamoğlu");
		ilceler.put("Karaisalı", "Karaisalı");
		ilceler.put("Pozantı", "Pozantı");
		ilceler.put("Karataş", "Karataş");
		ilceler.put("Ceyhan", "Ceyhan");
		ilceler.put("Yumurtalık", "Yumurtalık");
		ilceler.put("Seyhan", "Seyhan");

		return ilceler;

	}

}
