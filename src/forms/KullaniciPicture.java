/**
 * 
 */
package forms;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author Emrah Denizer
 *
 */
public class KullaniciPicture {

	private String isimSoyisim;

	private String sifre;

	private String adi;

	private String unvan;

	private String birim;

	private String sicilNo;

	private long cepTelefonu;

	private String ePosta;

	private char durum;

	private Integer izinHakki;

	private CommonsMultipartFile fileData;

	/**
	 * @return the isimSoyisim
	 */
	public String getIsimSoyisim() {
		return isimSoyisim;
	}

	/**
	 * @param isimSoyisim the isimSoyisim to set
	 */
	public void setIsimSoyisim(String isimSoyisim) {
		this.isimSoyisim = isimSoyisim;
	}

	/**
	 * @return the sifre
	 */
	public String getSifre() {
		return sifre;
	}

	/**
	 * @param sifre the sifre to set
	 */
	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	/**
	 * @return the adi
	 */
	public String getAdi() {
		return adi;
	}

	/**
	 * @param adi the adi to set
	 */
	public void setAdi(String adi) {
		this.adi = adi;
	}

	/**
	 * @return the unvan
	 */
	public String getUnvan() {
		return unvan;
	}

	/**
	 * @param unvan the unvan to set
	 */
	public void setUnvan(String unvan) {
		this.unvan = unvan;
	}

	/**
	 * @return the birim
	 */
	public String getBirim() {
		return birim;
	}

	/**
	 * @param birim the birim to set
	 */
	public void setBirim(String birim) {
		this.birim = birim;
	}

	/**
	 * @return the sicilNo
	 */
	public String getSicilNo() {
		return sicilNo;
	}

	/**
	 * @param sicilNo the sicilNo to set
	 */
	public void setSicilNo(String sicilNo) {
		this.sicilNo = sicilNo;
	}

	/**
	 * @return the cepTelefonu
	 */
	public long getCepTelefonu() {
		return cepTelefonu;
	}

	/**
	 * @param cepTelefonu the cepTelefonu to set
	 */
	public void setCepTelefonu(long cepTelefonu) {
		this.cepTelefonu = cepTelefonu;
	}

	/**
	 * @return the ePosta
	 */
	public String getePosta() {
		return ePosta;
	}

	/**
	 * @param ePosta the ePosta to set
	 */
	public void setePosta(String ePosta) {
		this.ePosta = ePosta;
	}

	/**
	 * @return the durum
	 */
	public char getDurum() {
		return durum;
	}

	/**
	 * @param durum the durum to set
	 */
	public void setDurum(char durum) {
		this.durum = durum;
	}

	/**
	 * @return the izinHakki
	 */
	public Integer getIzinHakki() {
		return izinHakki;
	}

	/**
	 * @param izinHakki the izinHakki to set
	 */
	public void setIzinHakki(Integer izinHakki) {
		this.izinHakki = izinHakki;
	}

	/**
	 * @return the fileData
	 */
	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	/**
	 * @param fileData the fileData to set
	 */
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}

}
