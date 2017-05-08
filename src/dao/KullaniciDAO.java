package dao;

import java.util.List;

import forms.Kullanici;

public interface KullaniciDAO {

	public Kullanici kullaniciGiris(String isim, String sifre);

	public List<Kullanici> kullanici();

	public List<Kullanici> kullaniciGetir(Long id);

	public Kullanici kullaniciGetirr(Long id);

	public void kullaniciEkle(Kullanici kullanici);

	public List<Kullanici> aktifKullaniciListesi(char durum);

}
