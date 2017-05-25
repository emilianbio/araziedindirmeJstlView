/**
 * 
 */
package dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.AracDAO#save(forms.Arac)
	 */
	@Override
	@Transactional
	public void save(Arac arac) {
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
		Arac arac = (Arac) sessionFactory.getCurrentSession().get(Arac.class, id);

		arac.getId();

		return arac;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.AracDAO#tumAracCikislari()
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Arac> tumAracCikislari() {
		Criteria criteriaArac = sessionFactory.getCurrentSession().createCriteria(Arac.class);
		criteriaArac.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		List<Arac> aracCikisListesi = criteriaArac.list();

		return aracCikisListesi;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Arac> kullaniciyaGoreCikisListesi(Long kullaniciID, Integer donemAy, Integer donemYil) {
		Criteria criteriaArac = sessionFactory.getCurrentSession().createCriteria(Arac.class);
		criteriaArac.createAlias("kullaniciList", "kullanici");
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
		System.out.println("kullaniciID: " + kullaniciID);
		Criteria criteriaArac = sessionFactory.getCurrentSession().createCriteria(Arac.class);
		criteriaArac.createAlias("kullaniciList", "kullanici");
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
	public List<Arac> donemYilGetir() {
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
		criteriaPersonel.createAlias("kullaniciList", "kullanici");
		
		ProjectionList list= Projections.projectionList();
		list.add((Projections.property("kullanici.id")));
		list.add((Projections.property("kullanici.adi")));
		criteriaPersonel.setProjection(Projections.distinct(list));
		
		criteriaPersonel.addOrder(Order.asc("kullanici.adi"));
		System.out.println("aracdao: "+criteriaPersonel.list().toString() );
		return criteriaPersonel.list();
	}

}
