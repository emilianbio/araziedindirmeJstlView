package service;

import java.util.List;

import org.json.simple.JSONArray;

import forms.AraziİslemHareketleri;

public interface AraziService {

	public void save(AraziİslemHareketleri islemHareketleri);

	public List<AraziİslemHareketleri> islemHareketleriListesi();

	public AraziİslemHareketleri araziİslemGetir(Long id);

	public JSONArray islemTipineGöreListele(String islemTipi);

	public Long sonIdGetir();

	public JSONArray ilceyeGöreListele(String ilce);
}
