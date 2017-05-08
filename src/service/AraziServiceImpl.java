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
		// TODO Auto-generated method stub
		return araziDAO.araziİslemGetir(id);
	}

	@Override
	public JSONArray islemTipineGöreListele(String islemTipi) {
		return araziDAO.islemTipineGöreListele(islemTipi);
	}

	@Override
	public Long sonIdGetir() {
		// TODO Auto-generated method stub
		return araziDAO.sonIdGetir();
	}

	@Override
	public JSONArray ilceyeGöreListele(String ilce) {
		// TODO Auto-generated method stub
		return araziDAO.ilceyeGöreListele(ilce);
	}

}
