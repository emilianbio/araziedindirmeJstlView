/**
 * 
 */
package service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.RaporlarDAO;
import forms.AraziİslemHareketleri;

/**
 * @author Emrah Denizer
 *
 */
@Service
public class RaporlarServiceImpl implements RaporlarService {
	@Autowired
	RaporlarDAO raporlarDAO;

	@Override
	public ArrayList<AraziİslemHareketleri> raporlarListesi() {
		return raporlarDAO.raporListesi();
	}
}
