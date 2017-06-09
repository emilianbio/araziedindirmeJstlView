package service;

import java.util.List;

import forms.Kullanici;
import forms.KullaniciPicture;

public interface KullaniciService {

	public Kullanici kullaniciGiris(String isim, String sifre);

	public List<Kullanici> kullanici();

	public List<Kullanici> kullanGetir(Long id);

	public Kullanici kullaniciGetirr(Long id);

	public void kullaniciEkle(Kullanici kullanici);
	public void kullaniciEkle2(KullaniciPicture kullaniciPicture);
	
	public List<Kullanici> aktifKullaniciListesi(char durum);
}
