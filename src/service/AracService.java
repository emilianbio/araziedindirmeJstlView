/**
 * 
 */
package service;

import java.util.List;

import forms.Arac;

/**
 * @author lenovo
 *
 */
public interface AracService {
	public void save(Arac arac);

	public void delete(Long id);

	public Arac aracCikisGetir(Long id);

	public List<Arac> tumAracCikislari();

	public List<Arac> kullaniciyaGoreCikisListesi(Long kullaniciID);

	public List<Arac> ozelPlakayaGoreCikisListesi(String plaka);

	public List<Arac> resmiPlakayaGoreCikisListesi(String plaka);
}
