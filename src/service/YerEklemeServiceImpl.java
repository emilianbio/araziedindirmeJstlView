/**
 * 
 */
package service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.YerEklemeDAO;
import forms.Yerler;

/**
 * @author Emrah Denizer
 *
 */
@Service
public class YerEklemeServiceImpl implements YerEklemeService {
	@Autowired
	YerEklemeDAO yerEklemeDao;

	@Override
	@Transactional
	public void ekle(Yerler yerler) {
		yerEklemeDao.ekle(yerler);
	}

	@Override
	@Transactional
	public List<Yerler> tipGetir() {
		// TODO Auto-generated method stub
		return yerEklemeDao.tipGetir();
	}

	@Override
	@Transactional
	public List<Yerler> altTipGetir(Long Id, Boolean durum) {
		// TODO Auto-generated method stub
		return yerEklemeDao.altTipGetir(Id, durum);
	}

	@Override
	@Transactional
	public Yerler tipsGetir(Long id) {
		// TODO Auto-generated method stub
		return yerEklemeDao.tipsGetir(id);
	}

	@Override
	@Transactional
	public void tipsil(Long id) {
		yerEklemeDao.tipsil(id);
	}

	@Transactional
	public void kaydet(Object nesne) {
		yerEklemeDao.kaydet(nesne);
	}

	@Transactional
	public Integer calistir(String sorgu) {
		return yerEklemeDao.calistir(sorgu);
	}

	@Transactional
	public Object getir(Long id, @SuppressWarnings("rawtypes") Class sinif) {
		return yerEklemeDao.getir(id, sinif);
	}

	// @Transactional
	// public boolean katidVarMi(Long katId) {
	// return sabitTipsDAO.katidVarMi( katId);
	// }

	@Transactional
	public boolean isimVarMi(String isim) {
		return yerEklemeDao.isimVarMi(isim);
	}

}
