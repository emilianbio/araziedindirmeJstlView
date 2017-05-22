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

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.AracService#save(forms.Arac)
	 */
	@Override
	public void save(Arac arac) {
		// TODO Auto-generated method stub
		aracDao.save(arac);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.AracService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		aracDao.delete(id);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.AracService#aracCikisGetir(java.lang.Long)
	 */
	@Override
	public Arac aracCikisGetir(Long id) {
		// TODO Auto-generated method stub

		return aracDao.aracCikisGetir(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.AracService#tumAracCikislari()
	 */
	@Override
	public List<Arac> tumAracCikislari() {
		// TODO Auto-generated method stub
		return aracDao.tumAracCikislari();
	}

	@Override
	public List<Arac> kullaniciyaGoreCikisListesi(Long kullaniciID, Integer donemAy, Integer donemYil) {
		// TODO Auto-generated method stub
		return aracDao.kullaniciyaGoreCikisListesi(kullaniciID, donemAy, donemYil);
	}

	@Override
	public List<Arac> kullaniciyaGoreCikisListesi(Long kullaniciID) {
		// TODO Auto-generated method stub
		return aracDao.kullaniciyaGoreCikisListesi(kullaniciID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.AracService#ozelPlakayaGoreCikisListesi(java.lang.String)
	 */
	@Override
	public List<Arac> ozelPlakayaGoreCikisListesi(String plaka) {
		// TODO Auto-generated method stub
		return aracDao.ozelPlakayaGoreCikisListesi(plaka);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.AracService#resmiPlakayaGoreCikisListesi(java.lang.String)
	 */
	@Override
	public List<Arac> resmiPlakayaGoreCikisListesi(String plaka) {
		// TODO Auto-generated method stub
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
}
