/**
 * 
 */
package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AracDAO;
import forms.Arac;

/**
 * @author lenovo
 *
 */
@Service
public class AracServiceImpl implements AracService {

	@Autowired
	AracDAO aracDao;

	@Override
	public void save(Arac arac) {
		aracDao.save(arac);

	}

	@Override
	public void delete(Long id) {
		aracDao.delete(id);

	}

	@Override
	public Arac aracCikisGetir(Long id) {

		return aracDao.aracCikisGetir(id);
	}

	@Override
	public List<Arac> tumAracCikislari() {
		return aracDao.tumAracCikislari();
	}

	@Override
	public List<Arac> kullaniciyaGoreCikisListesi(Long kullaniciID, Integer donemAy, Integer donemYil) {
		return aracDao.kullaniciyaGoreCikisListesi(kullaniciID, donemAy, donemYil);
	}

	@Override
	public List<Arac> kullaniciyaGoreCikisListesi(Long kullaniciID) {
		return aracDao.kullaniciyaGoreCikisListesi(kullaniciID);
	}

	@Override
	public List<Arac> ozelPlakayaGoreCikisListesi(String plaka) {
		return aracDao.ozelPlakayaGoreCikisListesi(plaka);
	}

	@Override
	public List<Arac> resmiPlakayaGoreCikisListesi(String plaka) {
		return aracDao.resmiPlakayaGoreCikisListesi(plaka);
	}

	@Override
	public List<Arac> donemAyGetir() {
		return aracDao.donemAyGetir();
	}

	@Override
	public List<Arac> donemYilGetir() {
		return aracDao.donemYilGetir();
	}

	@Override
	public List<Arac> cikisYapanPersonelListesi() {

		return aracDao.cikisYapanPersonelListesi();
	}

	@Override
	public boolean ayniGorevdenVarMi(long mahalleID, String tarih, String cikisSaati, String girisSaati) {
		return aracDao.ayniGorevdenVarMi(mahalleID, tarih, cikisSaati, girisSaati);
	}

	@Override
	public List<Arac> gorevBul(String plaka, String tarih) {
		return aracDao.gorevBul(plaka, tarih);
	}
}
