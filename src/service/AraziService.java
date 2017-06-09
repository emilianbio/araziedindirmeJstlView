package service;

import java.util.List;

import org.json.simple.JSONArray;

import forms.AraziİslemHareketleri;

public interface AraziService {

	public void save(AraziİslemHareketleri islemHareketleri);

	public void sil(Long id);

	public List<AraziİslemHareketleri> islemHareketleriListesi();

	public AraziİslemHareketleri araziİslemGetir(Long id);

	public JSONArray islemTipineGöreListele(String islemTipi);

	public JSONArray islemTipineVePersoneleGöreListele(String islemTipi, Long id);

	public Long sonIdGetir();

	public JSONArray ilceyeGöreListele(String ilce);

	public List<AraziİslemHareketleri> ilceyeGöreListele2(String ilce, String ilkTarih, String sonTarih);

	public List<AraziİslemHareketleri> ucAylikRapor(String yil, String ilkAy, String sonAy);
}
