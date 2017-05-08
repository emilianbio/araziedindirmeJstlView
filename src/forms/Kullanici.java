/**
 * 
 */
package forms;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Emrah Denizer
 *
 */
@Entity
@Table(name = "kullanici", schema = "public")
public class Kullanici implements Serializable {
	private static final long serialVersionUID = 4418029727139184238L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "kullanici_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	private long id;

	@Column(name = "isim_soyisim")
	private String isimSoyisim;

	@Column(name = "sifre")
	private String sifre;

	@Column(name = "adi")
	private String adi;


	@Column(name = "unvan")
	private String unvan;

	@Column(name = "birim")
	private String birim;

	public String getUnvan() {
		return unvan;
	}

	public void setUnvan(String unvan) {
		this.unvan = unvan;
	}

	public String getBirim() {
		return birim;
	}

	public void setBirim(String birim) {
		this.birim = birim;
	}

	@Column(name = "sicil_no")
	private String sicilNo;

	@Column(name = "cep_telefonu")
	private long cepTelefonu;

	@Column(name = "e_mail")
	private String ePosta;

	@Column(name = "durum")
	private char durum;

	@Column(name = "izin_hakki")
	private Integer izinHakki;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIsimSoyisim() {
		return isimSoyisim;
	}

	public void setIsimSoyisim(String isimSoyisim) {
		this.isimSoyisim = isimSoyisim;
	}

	public String getSifre() {
		return sifre;
	}

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
	 * @param adi
	 *            the adi to set
	 */
	public void setAdi(String adi) {
		this.adi = adi;
	}
	// public Unvanlar getUnvan() {
	// return unvan;
	// }
	//
	// public void setUnvan(Unvanlar unvan) {
	// this.unvan = unvan;
	// }
	//
	// public Birimler getBirim() {
	// return birim;
	// }
	//
	// public void setBirim(Birimler birim) {
	// this.birim = birim;
	// }

	public String getSicilNo() {
		return sicilNo;
	}

	public void setSicilNo(String sicilNo) {
		this.sicilNo = sicilNo;
	}

	public long getCepTelefonu() {
		return cepTelefonu;
	}

	public void setCepTelefonu(long cepTelefonu) {
		this.cepTelefonu = cepTelefonu;
	}

	public String getePosta() {
		return ePosta;
	}

	public void setePosta(String ePosta) {
		this.ePosta = ePosta;
	}

	public char getDurum() {
		return durum;
	}

	public void setDurum(char durum) {
		this.durum = durum;
	}

	public Integer getIzinHakki() {
		return izinHakki;
	}

	public void setIzinHakki(Integer izinHakki) {
		this.izinHakki = izinHakki;
	}

}
