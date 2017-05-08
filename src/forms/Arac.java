package forms;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "arazi_cikislari3", schema = "public")
public class Arac implements java.io.Serializable {
	private static final long serialVersionUID = 4418029727139184238L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "arazi_cikislari3_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	private long id;

	@ManyToOne()
	@JoinColumn(name = "personel")
	private Kullanici kullanici;

	@ManyToOne()
	@JoinColumn(name = "islemyapan")
	private Kullanici islemyapan;

	@Column(name = "tarih")
	private String tarih;

	@Column(name = "resmi_plaka")
	private String resmiPlaka;

	@Column(name = "ozel_plaka")
	private String ozelPlaka;

	@ManyToOne()
	@JoinColumn(name = "ilce")
	private Yerler ilce;

	@ManyToOne()
	@JoinColumn(name = "mahalle")
	private Yerler mahalle;

	// @Column(name = "ilce")
	// private String ilce;
	//
	// @Column(name = "mahalle")
	// private String mahalle;

	@Column(name = "cikis_saati")
	private String cikisSaati;

	@Column(name = "giris_saati")
	private String girisSaati;

	@Column(name = "aciklama")
	private String aciklama;

	@Temporal(TemporalType.TIMESTAMP)

	@Column(name = "islemzamani")
	private Date islemZamani;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Kullanici getKullanici() {

		if (kullanici == null) {

			kullanici = new Kullanici();
		}

		return kullanici;
	}

	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

	public String getResmiPlaka() {
		return resmiPlaka;
	}

	public void setResmiPlaka(String resmiPlaka) {
		this.resmiPlaka = resmiPlaka;
	}

	public String getOzelPlaka() {
		return ozelPlaka;
	}

	public void setOzelPlaka(String ozelPlaka) {
		this.ozelPlaka = ozelPlaka;
	}

	public Yerler getIlce() {
		if (ilce == null) {
			ilce = new Yerler();
		}

		return ilce;
	}

	public void setIlce(Yerler ilce) {
		this.ilce = ilce;
	}

	public Yerler getMahalle() {

		if (mahalle == null) {
			mahalle = new Yerler();
		}
		return mahalle;
	}

	public void setMahalle(Yerler mahalle) {
		this.mahalle = mahalle;
	}

	public String getCikisSaati() {
		return cikisSaati;
	}

	public void setCikisSaati(String cikisSaati) {
		this.cikisSaati = cikisSaati;
	}

	public String getGirisSaati() {
		return girisSaati;
	}

	public void setGirisSaati(String girisSaati) {
		this.girisSaati = girisSaati;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public Date getIslemZamani() {
		return islemZamani;
	}

	public void setIslemZamani(Date islemZamani) {
		this.islemZamani = islemZamani;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	
	
	public Kullanici getIslemyapan() {
		return islemyapan;
	}

	public void setIslemyapan(Kullanici islemyapan) {
		this.islemyapan = islemyapan;
	}

	@Override
	public String toString() {
		return "Arac [id=" + id + ", kullanici=" + kullanici + ", tarih=" + tarih + ", resmiPlaka=" + resmiPlaka
				+ ", ozelPlaka=" + ozelPlaka + ", ilce=" + ilce + ", mahalle=" + mahalle + ", cikisSaati=" + cikisSaati
				+ ", girisSaati=" + girisSaati + ", aciklama=" + aciklama + ", islemZamani=" + islemZamani + "]";
	}

}
