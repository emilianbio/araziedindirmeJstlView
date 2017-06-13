/**
 * 
 */
package forms;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	@ManyToOne
	@JoinColumn(name = "role")
	private UserRoles roles;

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

	@Column(name = "pic")
	private byte[] pic;

	@OneToMany(targetEntity = Kullanici.class)
	public List<byte[]> pictureList;

	public UserRoles getRoles() {

		if (roles == null) {
			roles = new UserRoles();
		}
		return roles;
	}

	public void setRoles(UserRoles roles) {
		this.roles = roles;
	}

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

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

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

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public Kullanici(String isimSoyisim, String sifre, String adi, String unvan, String birim, UserRoles roles,
			String sicilNo, long cepTelefonu, String ePosta, char durum, Integer izinHakki) {
		this.isimSoyisim = isimSoyisim;
		this.sifre = sifre;
		this.adi = adi;
		this.unvan = unvan;
		this.birim = birim;
		this.roles = roles;
		this.sicilNo = sicilNo;
		this.cepTelefonu = cepTelefonu;
		this.ePosta = ePosta;
		this.durum = durum;
		this.izinHakki = izinHakki;
	}

	public Kullanici() {
	}

	public List<byte[]> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<byte[]> pictureList) {
		this.pictureList = pictureList;
	}

}
