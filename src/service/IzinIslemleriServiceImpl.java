/**
 * 
 */
package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IzinIslemleriDAO;
import forms.IzinIslemleri;

/**
 * @author Emrah Denizer
 *
 */
@Service
public class IzinIslemleriServiceImpl implements IzinIslemleriService {

	@Autowired
	IzinIslemleriDAO izinDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IzinIslemleriService#izinEkle(forms.IzinIslemleri)
	 */
	@Override
	public void izinEkle(IzinIslemleri izinIslemleri) {
		izinDao.izinEkle(izinIslemleri);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IzinIslemleriService#izinListesi()
	 */
	@Override
	public List<IzinIslemleri> izinListesi() {
		// TODO Auto-generated method stub
		return izinDao.izinListesi();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IzinIslemleriService#izinGetir(java.lang.Long)
	 */
	@Override
	public IzinIslemleri izinGetir(Long id) {
		// TODO Auto-generated method stub
		return izinDao.izinGetir(id);
	}

	/* (non-Javadoc)
	 * @see service.IzinIslemleriService#izinleriGuncelle(forms.IzinIslemleri)
	 */
	@Override
	public void izinleriGuncelle(IzinIslemleri izinIslemleri) {
		izinDao.izinSureleriniGuncelle(izinIslemleri);
		
	}

}
