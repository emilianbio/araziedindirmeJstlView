package service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.KullaniciDAO;
import forms.Kullanici;
import forms.KullaniciPicture;

@Service
public class KullaniciServiceImpl implements KullaniciService {
	@Autowired
	private KullaniciDAO kullaniciDAO;

	@Override
	@Transactional
	public Kullanici kullaniciGiris(String isim, String sifre) {
		return kullaniciDAO.kullaniciGiris(isim, sifre);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.KullaniciService#kullanici()
	 */
	@Override
	public List<Kullanici> kullanici() {
		return kullaniciDAO.kullanici();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.KullaniciService#kullanGetir(java.lang.Long)
	 */
	@Override
	public List<Kullanici> kullanGetir(Long id) {
		return kullaniciDAO.kullaniciGetir(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.KullaniciService#kullanici(java.lang.Long)
	 */
	@Override
	public Kullanici kullaniciGetirr(Long id) {
		return kullaniciDAO.kullaniciGetirr(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.KullaniciService#kullaniciEkle(forms.Kullanici)
	 */
	@Override
	public void kullaniciEkle(Kullanici kullanici) {
		kullaniciDAO.kullaniciEkle(kullanici);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.KullaniciService#aktifKullaniciListesi(java.lang.Boolean)
	 */
	@Override
	public List<Kullanici> aktifKullaniciListesi(char durum) {
		return kullaniciDAO.aktifKullaniciListesi(durum);
	}

	/* (non-Javadoc)
	 * @see service.KullaniciService#kullaniciEkle2(forms.KullaniciPicture)
	 */
	@Override
	public void kullaniciEkle2(KullaniciPicture kullaniciPicture) {
		kullaniciDAO.kullaniciEkle2(kullaniciPicture);
		
	}

}
