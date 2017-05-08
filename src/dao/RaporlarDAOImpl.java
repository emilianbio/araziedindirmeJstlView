package dao;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.AraziİslemHareketleri;

@Repository
public class RaporlarDAOImpl implements RaporlarDAO {
	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<AraziİslemHareketleri> raporListesi() {
		Criteria criteriaDemirbas = sessionFactory.getCurrentSession()
				.createCriteria(AraziİslemHareketleri.class);
		criteriaDemirbas.addOrder(Order.desc("islemZamani"));

		ArrayList<AraziİslemHareketleri> liste = new ArrayList<AraziİslemHareketleri>(
				criteriaDemirbas.list());

		// List<AraziİslemHareketleri> araziIslemListesi = new
		// ArrayList<AraziİslemHareketleri>();
		// araziIslemListesi = criteriaDemirbas.list();
		// Iterator<AraziİslemHareketleri> iterator =
		// araziIslemListesi.iterator();
		// AraziİslemHareketleri tip = null;
		// ArrayList list = new ArrayList();
		// while (iterator.hasNext()) {
		// list.add(iterator.next().getIlce());
		// list.add(iterator.next().getIslemTipi());
		// }

		return liste;

	}
}
