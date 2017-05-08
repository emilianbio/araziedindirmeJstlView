/**
 * 
 */
package dao;

import java.util.List;

import forms.Yerler;

/**
 * @author Emrah Denizer
 *
 */
public interface YerEklemeDAO {
	public void ekle(Yerler yerler);

	public List<Yerler> tipGetir();

	public List<Yerler> altTipGetir(Long Id, Boolean durum);

	public void tipsil(Long id);

	public Yerler tipsGetir(Long id);

	public void kaydet(Object nesne);

	public Integer calistir(String sorgu);

	public Object getir(Long id, @SuppressWarnings("rawtypes") Class sinif);

	// public Boolean katidVarMi(Long katId);
	public Boolean isimVarMi(String isim);

}
