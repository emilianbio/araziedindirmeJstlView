package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.AraziİslemHareketleri;

@Repository
public class AraziDAOImpl implements AraziDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void save(AraziİslemHareketleri islemHareketleri) {
		sessionFactory.getCurrentSession().saveOrUpdate(islemHareketleri);

	}

	@Override
	@Transactional
	public List<AraziİslemHareketleri> islemHareketleriListesi() {

		Criteria criteriaDemirbas = sessionFactory.getCurrentSession().createCriteria(AraziİslemHareketleri.class);
		criteriaDemirbas.addOrder(Order.desc("islemZamani"));
		@SuppressWarnings("unchecked")
		List<AraziİslemHareketleri> sonuc = criteriaDemirbas.list();

		return sonuc;
	}

	@Override
	@Transactional
	public AraziİslemHareketleri araziİslemGetir(Long id) {
		@SuppressWarnings("unused")
		org.hibernate.Session session = sessionFactory.openSession();
		AraziİslemHareketleri arazi = (AraziİslemHareketleri) sessionFactory.getCurrentSession()
				.get(AraziİslemHareketleri.class, id);

		arazi.getId();
		return arazi;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public JSONArray islemTipineGöreListele(String islemTipi) {
		Criteria criteriaDemirbas = sessionFactory.getCurrentSession().createCriteria(AraziİslemHareketleri.class);
		criteriaDemirbas.addOrder(Order.desc("islemZamani"));
		criteriaDemirbas.add(Restrictions.eq("islemTipi", islemTipi));

		// criteriaDemirbas.setMaxResults(5);
		JSONArray donecek = new JSONArray();
		List<AraziİslemHareketleri> araziIslemListesi = new ArrayList<AraziİslemHareketleri>();
		araziIslemListesi = criteriaDemirbas.list();
		Iterator<AraziİslemHareketleri> iterator = araziIslemListesi.iterator();
		while (iterator.hasNext()) {
			JSONObject jsonObject = new JSONObject();
			AraziİslemHareketleri tip = iterator.next();
			jsonObject.put("id", tip.getId());
			jsonObject.put("islemYapan", tip.getKullanici().getAdi());
			jsonObject.put("islemYapanID", tip.getKullanici().getId());
			jsonObject.put("tarih", tip.getTarih());
			jsonObject.put("ilce", tip.getIlce());
			jsonObject.put("evrakNo", tip.getEvrakNo());
			jsonObject.put("mahalle", tip.getMahalle());
			jsonObject.put("devriIstenenParselSayisi", tip.getDevriIstenenParselSayisi());
			jsonObject.put("devriIstenenParselAlani", tip.getDevriIstenenParselAlani());
			jsonObject.put("izinVerilenParselSayisi", tip.getIzinVerilenParselSayisi());
			jsonObject.put("izinVerilenParselAlani", tip.getIzinVerilenParselAlani());
			jsonObject.put("izinVerilmeyenParselSayisi", tip.getIzinVerilmeyenParselSayisi());
			jsonObject.put("izinVerilmeyenParselAlani", tip.getIzinVerilmeyenParselAlani());
			jsonObject.put("nitelik", tip.getNitelik());
			if (tip.getIslemTipi() == "SATIŞ") {
				jsonObject.put("islemTipi", tip.getIslemTipi() + " (5403)");
			} else {
				jsonObject.put("islemTipi", tip.getIslemTipi());
			}

			// donecek.add(tip.getAlisFiyati());
			// donecek.add(tip.getHisseFiyati());
			// donecek.add(tip.getSatisFiyati());
			// donecek.add(tip.getHayvanNo());
			donecek.add(jsonObject);
		}

		return (donecek);

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public JSONArray ilceyeGöreListele(String ilce) {
		Criteria criteriaDemirbas = sessionFactory.getCurrentSession().createCriteria(AraziİslemHareketleri.class);
		criteriaDemirbas.addOrder(Order.desc("islemZamani"));
		criteriaDemirbas.add(Restrictions.eq("ilce", ilce));
		// criteriaDemirbas.setMaxResults(5);
		JSONArray donecek = new JSONArray();
		List<AraziİslemHareketleri> araziIslemListesi = new ArrayList<AraziİslemHareketleri>();
		araziIslemListesi = criteriaDemirbas.list();
		Iterator<AraziİslemHareketleri> iterator = araziIslemListesi.iterator();
		while (iterator.hasNext()) {
			JSONObject jsonObject = new JSONObject();
			AraziİslemHareketleri tip = iterator.next();
			jsonObject.put("id", tip.getId());
			jsonObject.put("tarih", tip.getTarih());
			jsonObject.put("ilce", tip.getIlce());
			jsonObject.put("evrakNo", tip.getEvrakNo());
			jsonObject.put("mahalle", tip.getMahalle());
			jsonObject.put("devriIstenenParselSayisi", tip.getDevriIstenenParselSayisi());
			jsonObject.put("devriIstenenParselAlani", tip.getDevriIstenenParselAlani());
			jsonObject.put("izinVerilenParselSayisi", tip.getIzinVerilenParselSayisi());
			jsonObject.put("izinVerilenParselAlani", tip.getIzinVerilenParselAlani());
			jsonObject.put("izinVerilmeyenParselSayisi", tip.getIzinVerilmeyenParselSayisi());
			jsonObject.put("izinVerilmeyenParselAlani", tip.getIzinVerilmeyenParselAlani());
			jsonObject.put("nitelik", tip.getNitelik());
			jsonObject.put("evrakTarihi", tip.getTarih());
			if (tip.getIslemTipi() == "SATIŞ") {
				jsonObject.put("islemTipi", tip.getIslemTipi() + " (5403)");
			} else {
				jsonObject.put("islemTipi", tip.getIslemTipi());
			}

			// donecek.add(tip.getAlisFiyati());
			// donecek.add(tip.getHisseFiyati());
			// donecek.add(tip.getSatisFiyati());
			// donecek.add(tip.getHayvanNo());
			donecek.add(jsonObject);
		}
		return (donecek);
	}

	@Override
	@Transactional
	public Long sonIdGetir() {

		Criteria crt = sessionFactory.getCurrentSession().createCriteria(AraziİslemHareketleri.class);
		crt.setProjection(Projections.max("id"));
		Long id = (Long) crt.uniqueResult();
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public JSONArray ayalaraGoreToplamGetir(String yil, String birinciAy, String ikinciAy, String ucuncuAy) {

		String[] yilKismi = yil.split("-");
		String year = yilKismi[0];

		Criteria criteriaDemirbas = sessionFactory.getCurrentSession().createCriteria(AraziİslemHareketleri.class);

		criteriaDemirbas.add(Restrictions.ilike("tarih", "%" + year + "%"));
		criteriaDemirbas.add((Restrictions.disjunction()
				.add(Restrictions.or(Restrictions.ge("tarih", birinciAy)).add(Restrictions.le("tarih", ikinciAy)))));

		JSONArray donecek = new JSONArray();
		List<AraziİslemHareketleri> araziIslemListesi = new ArrayList<AraziİslemHareketleri>();
		araziIslemListesi = criteriaDemirbas.list();
		Iterator<AraziİslemHareketleri> iterator = araziIslemListesi.iterator();
		while (iterator.hasNext()) {
			JSONObject jsonObject = new JSONObject();
			AraziİslemHareketleri tip = iterator.next();

			jsonObject.put("devriIstenenParselSayisi", tip.getDevriIstenenParselSayisi());
			jsonObject.put("devriIstenenParselAlani", tip.getDevriIstenenParselAlani());
			jsonObject.put("izinVerilenParselSayisi", tip.getIzinVerilenParselSayisi());
			jsonObject.put("izinVerilenParselAlani", tip.getIzinVerilenParselAlani());
			jsonObject.put("izinVerilmeyenParselSayisi", tip.getIzinVerilmeyenParselSayisi());
			jsonObject.put("izinVerilmeyenParselAlani", tip.getIzinVerilmeyenParselAlani());
			jsonObject.put("nitelik", tip.getNitelik());

			// donecek.add(tip.getAlisFiyati());
			// donecek.add(tip.getHisseFiyati());
			// donecek.add(tip.getSatisFiyati());
			// donecek.add(tip.getHayvanNo());
			donecek.add(jsonObject);
		}

		return (donecek);

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public JSONArray islemTipineVePersoneleGöreListele(String islemTipi, Long id) {
		Criteria criteriaDemirbas = sessionFactory.getCurrentSession().createCriteria(AraziİslemHareketleri.class);
		criteriaDemirbas.addOrder(Order.desc("islemZamani"));
		criteriaDemirbas.add(Restrictions.eq("islemTipi", islemTipi));
		criteriaDemirbas.add(Restrictions.eq("kullanici.id", id));
		// criteriaDemirbas.setMaxResults(5);
		JSONArray donecek = new JSONArray();
		List<AraziİslemHareketleri> araziIslemListesi = new ArrayList<AraziİslemHareketleri>();
		araziIslemListesi = criteriaDemirbas.list();
		Iterator<AraziİslemHareketleri> iterator = araziIslemListesi.iterator();
		while (iterator.hasNext()) {
			JSONObject jsonObject = new JSONObject();
			AraziİslemHareketleri tip = iterator.next();
			jsonObject.put("id", tip.getId());
			jsonObject.put("tarih", tip.getTarih());
			jsonObject.put("ilce", tip.getIlce());
			jsonObject.put("evrakNo", tip.getEvrakNo());
			jsonObject.put("mahalle", tip.getMahalle());
			jsonObject.put("devriIstenenParselSayisi", tip.getDevriIstenenParselSayisi());
			jsonObject.put("devriIstenenParselAlani", tip.getDevriIstenenParselAlani());
			jsonObject.put("izinVerilenParselSayisi", tip.getIzinVerilenParselSayisi());
			jsonObject.put("izinVerilenParselAlani", tip.getIzinVerilenParselAlani());
			jsonObject.put("izinVerilmeyenParselSayisi", tip.getIzinVerilmeyenParselSayisi());
			jsonObject.put("izinVerilmeyenParselAlani", tip.getIzinVerilmeyenParselAlani());
			jsonObject.put("nitelik", tip.getNitelik());
			if (tip.getIslemTipi() == "SATIŞ") {
				jsonObject.put("islemTipi", tip.getIslemTipi() + " (5403)");
			} else {
				jsonObject.put("islemTipi", tip.getIslemTipi());
			}

			// donecek.add(tip.getAlisFiyati());
			// donecek.add(tip.getHisseFiyati());
			// donecek.add(tip.getSatisFiyati());
			// donecek.add(tip.getHayvanNo());
			donecek.add(jsonObject);
		}

		return (donecek);

	}

	@Override
	@Transactional
	public void sil(Long id) {
		sessionFactory.getCurrentSession().delete(araziİslemGetir(id));

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<AraziİslemHareketleri> ilceyeGöreListele2(String ilce, String ilkTarih, String sonTarih) {
		Criteria criteriaDemirbas = sessionFactory.getCurrentSession().createCriteria(AraziİslemHareketleri.class);
		// criteriaDemirbas.addOrder(Order.desc("islemZamani"));
		criteriaDemirbas.add(Restrictions.eq("ilce", ilce));
		criteriaDemirbas.add(Restrictions.ge("tarih", ilkTarih));
		criteriaDemirbas.add(Restrictions.le("tarih", sonTarih));

		// criteriaDemirbas.add((Restrictions.disjunction()
		// .add(Restrictions.and(Restrictions.ilike("tarih", "%" + ilceYil +
		// "%"))
		// .add(Restrictions.ilike("tarih", "%" + ilceBirinciAy + "%"))
		// .add(Restrictions.ilike("tarih", "%" + ilceIkinciAy + "%"))
		// .add(Restrictions.ilike("tarih", "%" + ilceUcuncuAy + "%")))
		//
		// ));
		// ProjectionList p1 = Projections.projectionList();
		//
		// p1.add(Projections.sum("devriIstenenParselAlani"));
		// p1.add(Projections.sum("devriIstenenParselSayisi"));
		// p1.add(Projections.sum("izinVerilenParselAlani"));
		// p1.add(Projections.sum("izinVerilenParselSayisi"));
		// p1.add(Projections.sum("izinVerilmeyenParselAlani"));
		// p1.add(Projections.sum("izinVerilmeyenParselSayisi"));
		// criteriaDemirbas.setProjection(p1);
		// criteriaDemirbas.setProjection(Projections.groupProperty("islemZamani"));

		return criteriaDemirbas.list();
	}

	@Override
	@Transactional
	public List<AraziİslemHareketleri> ucAylikRapor(String yil, String ilkAy, String sonAy) {
		Criteria criteriaDemirbas = sessionFactory.getCurrentSession().createCriteria(AraziİslemHareketleri.class);
		// criteriaDemirbas.addOrder(Order.desc("islemZamani"));

		// System.out.println("seçilen yıl: " + yil);
		// System.out.println("ilk dönem: " + ilkAy);
		// System.out.println("son dönem: " + sonAy);
		criteriaDemirbas.add(Restrictions.like("tarih", "%" + yil + "%"));
		criteriaDemirbas.add(Restrictions.ge("tarih", ilkAy));
		criteriaDemirbas.add(Restrictions.le("tarih", sonAy));

		// criteriaDemirbas.add((Restrictions.disjunction()
		// .add(Restrictions.and(Restrictions.ilike("tarih", "%" + ilceYil +
		// "%"))
		// .add(Restrictions.ilike("tarih", "%" + ilceBirinciAy + "%"))
		// .add(Restrictions.ilike("tarih", "%" + ilceIkinciAy + "%"))
		// .add(Restrictions.ilike("tarih", "%" + ilceUcuncuAy + "%")))
		//
		// ));
		// ProjectionList p1 = Projections.projectionList();
		//
		// p1.add(Projections.sum("devriIstenenParselAlani"));
		// p1.add(Projections.sum("devriIstenenParselSayisi"));
		// p1.add(Projections.sum("izinVerilenParselAlani"));
		// p1.add(Projections.sum("izinVerilenParselSayisi"));
		// p1.add(Projections.sum("izinVerilmeyenParselAlani"));
		// p1.add(Projections.sum("izinVerilmeyenParselSayisi"));
		// criteriaDemirbas.setProjection(p1);
		// criteriaDemirbas.setProjection(Projections.groupProperty("islemZamani"));

		return criteriaDemirbas.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public JSONArray islemTipineGoreUcAylikRapor(String islemTipi, String yil, String ilkAy, String sonAy) {
		Criteria criteriaDemirbas = sessionFactory.getCurrentSession().createCriteria(AraziİslemHareketleri.class);
		// criteriaDemirbas.addOrder(Order.desc("islemZamani"));

		// System.out.println("seçilen yıl: " + yil);
		// System.out.println("ilk dönem: " + ilkAy);
		// System.out.println("son dönem: " + sonAy);
		criteriaDemirbas.add(Restrictions.eq("islemTipi", islemTipi));
		criteriaDemirbas.add(Restrictions.like("tarih", "%" + yil + "%"));
		criteriaDemirbas.add(Restrictions.ge("tarih", ilkAy));
		criteriaDemirbas.add(Restrictions.le("tarih", sonAy));

		// criteriaDemirbas.setMaxResults(5);
		JSONArray donecek = new JSONArray();
		List<AraziİslemHareketleri> araziIslemListesi = new ArrayList<AraziİslemHareketleri>();
		araziIslemListesi = criteriaDemirbas.list();
		Iterator<AraziİslemHareketleri> iterator = araziIslemListesi.iterator();
		while (iterator.hasNext()) {
			JSONObject jsonObject = new JSONObject();
			AraziİslemHareketleri tip = iterator.next();
			jsonObject.put("id", tip.getId());
			jsonObject.put("islemYapan", tip.getKullanici().getAdi());
			jsonObject.put("islemYapanID", tip.getKullanici().getId());
			jsonObject.put("tarih", tip.getTarih());
			jsonObject.put("ilce", tip.getIlce());
			jsonObject.put("evrakNo", tip.getEvrakNo());
			jsonObject.put("mahalle", tip.getMahalle());
			jsonObject.put("devriIstenenParselSayisi", tip.getDevriIstenenParselSayisi());
			jsonObject.put("devriIstenenParselAlani", tip.getDevriIstenenParselAlani());
			jsonObject.put("izinVerilenParselSayisi", tip.getIzinVerilenParselSayisi());
			jsonObject.put("izinVerilenParselAlani", tip.getIzinVerilenParselAlani());
			jsonObject.put("izinVerilmeyenParselSayisi", tip.getIzinVerilmeyenParselSayisi());
			jsonObject.put("izinVerilmeyenParselAlani", tip.getIzinVerilmeyenParselAlani());
			jsonObject.put("nitelik", tip.getNitelik());
			if (tip.getIslemTipi() == "SATIŞ") {
				jsonObject.put("islemTipi", tip.getIslemTipi() + " (5403)");
			} else {
				jsonObject.put("islemTipi", tip.getIslemTipi());
			}

			// donecek.add(tip.getAlisFiyati());
			// donecek.add(tip.getHisseFiyati());
			// donecek.add(tip.getSatisFiyati());
			// donecek.add(tip.getHayvanNo());
			donecek.add(jsonObject);
		}

		return (donecek);
	}
}
