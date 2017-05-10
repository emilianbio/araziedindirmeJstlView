package dao;

import java.util.List;

import org.json.simple.JSONArray;

import forms.AraziİslemHareketleri;

public interface AraziDAO {

	public void save(AraziİslemHareketleri islemHareketleri);

	public List<AraziİslemHareketleri> islemHareketleriListesi();

	public AraziİslemHareketleri araziİslemGetir(Long id);

	public JSONArray islemTipineGöreListele(String islemTipi);
	public JSONArray islemTipineVePersoneleGöreListele(String islemTipi,Long id);

	public Long sonIdGetir();

	public JSONArray ilceyeGöreListele(String ilce);

	public JSONArray ayalaraGoreToplamGetir(String yil, String birinciAy,
			String ikinciAy, String ucuncuAy);
}
