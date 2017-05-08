/**
 * 
 */
package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.Yerler;

/**
 * @author Emrah Denizer
 *
 */
@Repository
public class YerEklemeDAOImpl implements YerEklemeDAO {
	@Autowired
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.YerEklemeDAO#ekle(forms.Yerler)
	 */
	@Override
	public void ekle(Yerler yerler) {
		sessionFactory.getCurrentSession().saveOrUpdate(yerler);
		// TODO Auto-generated method stub

	}

	@Override
	public List<Yerler> tipGetir() {
		Criteria criteriaSabittips = sessionFactory.getCurrentSession()
				.createCriteria(Yerler.class);
		// //System.out.println("*******************************************1");
		criteriaSabittips.addOrder(Order.asc("isim"));
		criteriaSabittips.add(Restrictions.isNull("tip"));
		@SuppressWarnings("unchecked")
		List<Yerler> sonuc = criteriaSabittips.list();
		return sonuc;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Yerler> altTipGetir(Long Id, Boolean durum) {
		List<Yerler> sonuc = null;
		if (Id == 0)
			sonuc = tipGetir();
		else {
			Criteria criteriaSabittips = sessionFactory.getCurrentSession()
					.createCriteria(Yerler.class);
			// //System.out.println("*******************************************1");
			criteriaSabittips.addOrder(Order.asc("isim"));
			criteriaSabittips.add(Restrictions.eq("tip.id", Id));
			criteriaSabittips.add(Restrictions.eq("durum", durum));
			sonuc = criteriaSabittips.list();
			// System.out.println("*******************************************2");
			// System.out.println("************"+sonuc.toArray().length);
		}
		return sonuc;
	}

	@Override
	public Yerler tipsGetir(Long id) {
		// System.out.println("******************************1");
		Yerler tips = (Yerler) sessionFactory.getCurrentSession().load(
				Yerler.class, id);
		// System.out.println("******************************"+
		tips.getId();
		return tips;
	}

	@Override
	public void tipsil(Long id) {
		sessionFactory.getCurrentSession().delete(tipsGetir(id));
	}

	@Override
	public void kaydet(Object nesne) {
		sessionFactory.getCurrentSession().saveOrUpdate(nesne);
	}

	@Override
	public Integer calistir(String sorgu) {
		return sessionFactory.getCurrentSession().createQuery(sorgu)
				.executeUpdate();
	}

	@Override
	public Object getir(Long id, @SuppressWarnings("rawtypes") Class sinif) {
		// load yerine get kullanildi getCurrentSession yeri be
		Object nesne = sessionFactory.getCurrentSession().get(sinif, id);
		return nesne;
	}

	@Override
	public Boolean isimVarMi(String isim) {
		Criteria criteriaSabittips = sessionFactory.getCurrentSession()
				.createCriteria(Yerler.class);
		criteriaSabittips.add(Restrictions.eq("isim", isim));
		@SuppressWarnings("rawtypes")
		List sonucList = criteriaSabittips.list();
		return (sonucList != null && sonucList.size() > 0);
	}

}
