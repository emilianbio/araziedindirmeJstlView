package dao;

import java.util.List;

import forms.Kullanici;
import forms.KullaniciPicture;

public interface KullaniciDAO {

	public Kullanici kullaniciGiris(String isim, String sifre);

	public List<Kullanici> kullanici();

	public List<Kullanici> kullaniciGetir(Long id);

	public Kullanici kullaniciGetirr(Long id);

	public void kullaniciEkle(Kullanici kullanici);
	
	public void kullaniciEkle2(KullaniciPicture kullaniciPicture);

	public List<Kullanici> aktifKullaniciListesi(char durum);
	
	public Kullanici kullaniciBul(Long id);

}
