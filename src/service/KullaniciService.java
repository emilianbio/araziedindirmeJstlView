package service;

import java.util.List;

import forms.Kullanici;

public interface KullaniciService {

	public Kullanici kullaniciGiris(String isim, String sifre);

	public List<Kullanici> kullanici();

	public List<Kullanici> kullanGetir(Long id);

	public Kullanici kullaniciGetirr(Long id);

	public void kullaniciEkle(Kullanici kullanici);
	
	public List<Kullanici> aktifKullaniciListesi(char durum);
}
