/**
 * 
 */
package dao;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.IzinIslemleri;

/**
 * @author Emrah Denizer
 *
 */
@Repository
public class IzinIslemleriDAOImpl implements IzinIslemleriDAO {
	@Autowired
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.IzinIslemleriDAO#izinEkle(forms.IzinIslemleri)
	 */
	@Override
	@Transactional
	public void izinEkle(IzinIslemleri izinIslemleri) {
		sessionFactory.getCurrentSession().saveOrUpdate(izinIslemleri);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.IzinIslemleriDAO#izinListesi()
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<IzinIslemleri> izinListesi() {
		Criteria criteriaİzinListesi = sessionFactory.getCurrentSession()
				.createCriteria(IzinIslemleri.class);
		criteriaİzinListesi.addOrder(Order.desc("islemZamani"));

		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from IzinIslemleri where personelId.isimSoyisim like '%rah%' ");

		System.out.println("İzinDAO " + query.list());
		return criteriaİzinListesi.list();
	}

	@SuppressWarnings("unused")
	@Override
	@Transactional
	public IzinIslemleri izinGetir(Long id) {
		Session session = sessionFactory.openSession();
		IzinIslemleri izin = (IzinIslemleri) sessionFactory.getCurrentSession()
				.get(IzinIslemleri.class, id);

		izin.getId();
		return izin;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.IzinIslemleriDAO#izinSureleriniGuncelle(forms.IzinIslemleri)
	 */

	@SuppressWarnings("unused")
	@Override
	public void izinSureleriniGuncelle(IzinIslemleri izin2) {

		Session session = sessionFactory.openSession();
		IzinIslemleri izin = (IzinIslemleri) sessionFactory.getCurrentSession()
				.get(IzinIslemleri.class, izin2.getId());

		int suAnkiYil = Calendar.getInstance().getWeekYear();
		int toplamIzin = izin.getDevirIzinGunSayisi()
				+ izin.getKalanIzinGunSayisi();
		Calendar islemZamani = Calendar.getInstance();
		islemZamani.setTime(izin.getIslemZamani());
		int sonIzinYili = islemZamani.get(Calendar.YEAR);
		System.out.println(izin.getPersonelId().getIsimSoyisim() + " "
				+ izin.getKalanIzinGunSayisi() + " xxxxx");
		System.out.println(suAnkiYil - sonIzinYili);

		if (suAnkiYil - sonIzinYili > 2) // en son, en az 3 yıl önce izin
											// kullanmışsa
			if (izin.getPersonelId().getIzinHakki() == 2) { // izin hakkı 20 gün
															// ise
				izin.setDevirIzinGunSayisi(20); // öceki yıldan yeni yıla 20 gün
												// aktar
				izin.setKalanIzinGunSayisi(20); // yeni yıl için 20 günlük yeni
												// hak ver
			} else if (izin.getPersonelId().getIzinHakki() == 3) { // izin hakkı
																	// 30 gün
																	// ise
				izin.setDevirIzinGunSayisi(30); // öceki yıldan yeni yıla 30 gün
												// aktar
				izin.setKalanIzinGunSayisi(30); // yeni yıl için 30 günlük yeni
												// hak ver

			}

		if (suAnkiYil - sonIzinYili != 0)
			if (izin.getPersonelId().getIzinHakki() == 2)// izin hakkı 20 gün
															// ise
				if (toplamIzin <= 20) // yeni yılda hak ettiği izin hariç önceki
										// yıllardan gelen toplam izin gün
										// sayısı 20den "AZ" ise

				{
					izin.setDevirIzinGunSayisi(izin.getKalanIzinGunSayisi()); // önceki
																				// yıldan
																				// yeni
																				// yıla
																				// kalan
																				// izinleri
																				// aktar
					izin.setKalanIzinGunSayisi(20);// yeni yıl için 20 günlük
													// yeni hak ver
				}

				else { // yeni yılda hak ettiği izin hariç toplam izin gün
						// sayısı 20den "FAZLA" ise
					izin.setDevirIzinGunSayisi(20);// öceki yıldan yeni yıla 20
													// gün aktar
					izin.setKalanIzinGunSayisi(20);// yeni yıl için 20 günlük
													// yeni hak ver
				}

			else if (izin.getPersonelId().getIzinHakki() == 3)// izin hakkı 30
																// gün ise
				if (toplamIzin <= 30) {// yeni yılda hak ettiği izin hariç
										// önceki yıllardan gelen toplam izin
										// gün sayısı 30dan "AZ" ise
					izin.setDevirIzinGunSayisi(izin.getKalanIzinGunSayisi());// önceki
																				// yıldan
																				// yeni
																				// yıla
																				// kalan
																				// izinleri
																				// aktar
					izin.setKalanIzinGunSayisi(30); // yeni yıl için 30 günlük
													// yeni hak ver
				} else { // yeni yılda hak ettiği izin hariç önceki yıllardan
							// gelen toplam izin gün sayısı 30dan "FAZLA" ise
					izin.setDevirIzinGunSayisi(30);// öceki yıldan yeni yıla 30
													// gün aktar
					izin.setKalanIzinGunSayisi(30);// yeni yıl için 30 günlük
													// yeni hak ver
				}

	}

}
