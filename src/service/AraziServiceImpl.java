package service;

import java.util.List;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AraziDAO;
import forms.AraziİslemHareketleri;

@Service
public class AraziServiceImpl implements AraziService {
	@Autowired
	AraziDAO araziDAO;

	@Override
	public void save(AraziİslemHareketleri islemHareketleri) {
		araziDAO.save(islemHareketleri);
	}

	@Override
	public List<AraziİslemHareketleri> islemHareketleriListesi() {
		return araziDAO.islemHareketleriListesi();
	}

	@Override
	public AraziİslemHareketleri araziİslemGetir(Long id) {
		return araziDAO.araziİslemGetir(id);
	}

	@Override
	public JSONArray islemTipineGöreListele(String islemTipi) {
		return araziDAO.islemTipineGöreListele(islemTipi);
	}

	@Override
	public Long sonIdGetir() {
		return araziDAO.sonIdGetir();
	}

	@Override
	public JSONArray ilceyeGöreListele(String ilce) {
		return araziDAO.ilceyeGöreListele(ilce);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * service.AraziService#islemTipineVePersoneleGöreListele(java.lang.String,
	 * java.lang.Long)
	 */
	@Override
	public JSONArray islemTipineVePersoneleGöreListele(String islemTipi, Long id) {
		return araziDAO.islemTipineVePersoneleGöreListele(islemTipi, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.AraziService#sil(java.lang.Long)
	 */
	@Override
	public void sil(Long id) {
		araziDAO.sil(id);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.AraziService#ilceyeGöreListele2(java.lang.String)
	 */
	@Override
	public List<AraziİslemHareketleri> ilceyeGöreListele2(String ilce, String ilkTarih, String sonTarih) {
		return araziDAO.ilceyeGöreListele2(ilce, ilkTarih, sonTarih);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.AraziService#ucAylikRapor(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public List<AraziİslemHareketleri> ucAylikRapor(String yil, String ilkAy, String sonAy) {
		return araziDAO.ucAylikRapor(yil, ilkAy, sonAy);
	}

	@Override
	public JSONArray islemTipineGoreUcAylikRapor(String islemTipi, String yil, String ilkAy,
			String sonAy) {
		return araziDAO.islemTipineGoreUcAylikRapor(islemTipi, yil, ilkAy, sonAy);
	}

}
