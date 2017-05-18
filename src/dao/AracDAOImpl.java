/**
 * 
 */
package dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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

	@Override
	@Transactional
	public void save(Arac arac) {
		sessionFactory.getCurrentSession().saveOrUpdate(arac);

	}

	@Override
	@Transactional
	public void delete(Long id) {
		sessionFactory.getCurrentSession().delete(aracCikisGetir(id));
	}

	@Override
	@Transactional
	public Arac aracCikisGetir(Long id) {
		Arac arac = (Arac) sessionFactory.getCurrentSession().get(Arac.class, id);

		arac.getId();

		return arac;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Arac> tumAracCikislari() {
		Criteria criteriaArac = sessionFactory.getCurrentSession().createCriteria(Arac.class);

		List<Arac> aracCikisListesi = criteriaArac.list();

		return aracCikisListesi;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Arac> kullaniciyaGoreCikisListesi(Long kullaniciID, Integer donemAy, Integer donemYil) {
		Criteria criteriaArac = sessionFactory.getCurrentSession().createCriteria(Arac.class);
		criteriaArac.add(Restrictions.eq("kullanici.id", kullaniciID));
		criteriaArac.add(Restrictions.eq("donemAy", donemAy));
		criteriaArac.add(Restrictions.eq("donemYil", donemYil));
		criteriaArac.addOrder(Order.asc("tarih"));
		List<Arac> aracCikisListesi = criteriaArac.list();

		return aracCikisListesi;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Arac> kullaniciyaGoreCikisListesi(Long kullaniciID) {
		Criteria criteriaArac = sessionFactory.getCurrentSession().createCriteria(Arac.class);
		criteriaArac.add(Restrictions.eq("kullanici.id", kullaniciID));

		List<Arac> aracCikisListesi = criteriaArac.list();

		return aracCikisListesi;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Arac> resmiPlakayaGoreCikisListesi(String plaka) {
		Criteria criteriaArac = sessionFactory.getCurrentSession().createCriteria(Arac.class);
		criteriaArac.add(Restrictions.eq("resmiPlaka", plaka));
		List<Arac> aracCikisListesi = criteriaArac.list();

		return aracCikisListesi;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Arac> ozelPlakayaGoreCikisListesi(String plaka) {
		Criteria criteriaArac = sessionFactory.getCurrentSession().createCriteria(Arac.class);
		criteriaArac.add(Restrictions.eq("ozelPlaka", plaka));
		List<Arac> aracCikisListesi = criteriaArac.list();

		return aracCikisListesi;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Arac> donemAyGetir() {

		Criteria criteriaAy = sessionFactory.getCurrentSession().createCriteria(Arac.class);
		criteriaAy.setProjection(Projections.distinct(Projections.property("donemAy")));
		return criteriaAy.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Arac> donemYilgetir() {
		Criteria criteriaYil = sessionFactory.getCurrentSession().createCriteria(Arac.class);
		criteriaYil.setProjection(Projections.distinct(Projections.property("donemYil")));
		return criteriaYil.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.AracDAO#cikisYapanPersonelListesi()
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Arac> cikisYapanPersonelListesi() {
		Criteria criteriaPersonel = sessionFactory.getCurrentSession().createCriteria(Arac.class);

		criteriaPersonel.setProjection(Projections.distinct(Projections.property("kullanici")));

		return criteriaPersonel.list();
	}

}
