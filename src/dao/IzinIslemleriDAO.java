/**
 * 
 */
package dao;

import java.util.List;

import forms.IzinIslemleri;

/**
 * @author Emrah Denizer
 *
 */
public interface IzinIslemleriDAO {
	public void izinEkle(IzinIslemleri izinIslemleri);

	public List<IzinIslemleri> izinListesi();

	public IzinIslemleri izinGetir(Long id);
	
	public void izinSureleriniGuncelle(IzinIslemleri izinIslemleri);
}
