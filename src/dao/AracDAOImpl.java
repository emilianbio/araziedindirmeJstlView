/**
 * 
 */
package dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.Arac;

/**
 * @author lenovo
 *
 */
@Repository
public class AracDAOImpl implements AracDAO {

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.AracDAO#save(forms.Arac)
	 */
	@Override
	@Transactional
	public void save(Arac arac) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(arac);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.AracDAO#delete(java.lang.Long)
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(aracCikisGetir(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.AracDAO#aracCikisGetir(java.lang.Long)
	 */
	@Override
	@Transactional
	public Arac aracCikisGetir(Long id) {
		// TODO Auto-generated method stub
		Arac arac = (Arac) sessionFactory.getCurrentSession().get(Arac.class, id);

		arac.getId();

		return arac;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.AracDAO#tumAracCikislari()
	 */
	@Override
	@Transactional
	public List<Arac> tumAracCikislari() {
		Criteria criteriaArac = sessionFactory.getCurrentSession().createCriteria(Arac.class);

		List<Arac> aracCikisListesi = criteriaArac.list();

		return aracCikisListesi;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.AracDAO#kullaniciyaGoreCikisListesi(java.lang.Long)
	 */
	@Override
	@Transactional
	public List<Arac> kullaniciyaGoreCikisListesi(Long kullaniciID) {
		Criteria criteriaArac = sessionFactory.getCurrentSession().createCriteria(Arac.class);
		criteriaArac.add(Restrictions.eq("kullanici.id", kullaniciID));
		List<Arac> aracCikisListesi = criteriaArac.list();

		return aracCikisListesi;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.AracDAO#plakayaGoreCikisListesi(java.lang.String)
	 */
	@Override
	@Transactional
	public List<Arac> resmiPlakayaGoreCikisListesi(String plaka) {
		Criteria criteriaArac = sessionFactory.getCurrentSession().createCriteria(Arac.class);
		criteriaArac.add(Restrictions.eq("resmiPlaka", plaka));
		List<Arac> aracCikisListesi = criteriaArac.list();

		return aracCikisListesi;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.AracDAO#ozelPlakayaGoreCikisListesi(java.lang.String)
	 */
	@Override
	@Transactional
	public List<Arac> ozelPlakayaGoreCikisListesi(String plaka) {
		Criteria criteriaArac = sessionFactory.getCurrentSession().createCriteria(Arac.class);
		criteriaArac.add(Restrictions.eq("ozelPlaka", plaka));
		List<Arac> aracCikisListesi = criteriaArac.list();

		return aracCikisListesi;
	}

}
