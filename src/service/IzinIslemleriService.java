/**
 * 
 */
package service;

import java.util.List;

import forms.IzinIslemleri;

/**
 * @author Emrah Denizer
 *
 */
public interface IzinIslemleriService {
	public void izinEkle(IzinIslemleri izinIslemleri);

	public List<IzinIslemleri> izinListesi();

	public IzinIslemleri izinGetir(Long id);

	public void izinleriGuncelle(IzinIslemleri izinIslemleri);
}
