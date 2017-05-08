package forms;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "izin_islemleri", schema = "public")
public class IzinIslemleri implements java.io.Serializable {
	private static final long serialVersionUID = 4418029727139184238L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "izin_islemleri_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	private long id;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "personel_id")
	private Kullanici personelId;

	@Column(name = "izin_turu ")
	private String izinTuru;

	// @Column(name = "toplam_izin_gun_sayisi")
	// private Long toplamIzinGunSayisi;

	@Column(name = "kalan_izin_gun_sayisi")
	private int kalanIzinGunSayisi;

	@Column(name = "izine_cikis_tarihi")
	private String izineCikisTarihi;

	@Column(name = "izinden_donus_tarhi")
	private String izindenDonusTarihi;

	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "yerine_bakacak_personel")
	private Kullanici yedekPersonel;

	@Column(name = "talep_edilen_izin_gun_sayisi")
	private int talepEdilenIzinGunSayisi;

	@Column(name = "devir_izin_gun_sayisi")
	private int devirIzinGunSayisi;

	@Column(name = "olur_sayisi")
	private String olurSayisi;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = " islem_zamani")
	private Date islemZamani;

	@Column(name = "mazeret_nedeni")
	private String mazeretNedeni;
	
	

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Kullanici getPersonelId() {
		if (personelId == null)
			personelId = new Kullanici();
		return personelId;
	}

	public void setPersonelId(Kullanici personelId) {
		this.personelId = personelId;
	}

	public String getIzinTuru() {
		return izinTuru;
	}

	public void setIzinTuru(String izinTuru) {
		this.izinTuru = izinTuru;
	}

	public int getKalanIzinGunSayisi() {
		return kalanIzinGunSayisi;
	}

	public void setKalanIzinGunSayisi(int kalanIzinGunSayisi) {
		this.kalanIzinGunSayisi = kalanIzinGunSayisi;
	}

	public String getIzineCikisTarihi() {
		return izineCikisTarihi;
	}

	public void setIzineCikisTarihi(String izineCikisTarihi) {
		this.izineCikisTarihi = izineCikisTarihi;
	}

	public String getIzindenDonusTarihi() {
		return izindenDonusTarihi;
	}

	public void setIzindenDonusTarihi(String izindenDonusTarihi) {
		this.izindenDonusTarihi = izindenDonusTarihi;
	}

	public Kullanici getYedekPersonel() {
		if (yedekPersonel == null)
			yedekPersonel = new Kullanici();
		return yedekPersonel;
	}

	public void setYedekPersonel(Kullanici yedekPersonel) {
		this.yedekPersonel = yedekPersonel;
	}

	public int getTalepEdilenIzinGunSayisi() {
		return talepEdilenIzinGunSayisi;
	}

	public void setTalepEdilenIzinGunSayisi(int talepEdilenIzinGunSayisi) {
		this.talepEdilenIzinGunSayisi = talepEdilenIzinGunSayisi;
	}

	public int getDevirIzinGunSayisi() {
		return devirIzinGunSayisi;
	}

	public void setDevirIzinGunSayisi(int devirIzinGunSayisi) {
		this.devirIzinGunSayisi = devirIzinGunSayisi;
	}

	public String getOlurSayisi() {
		return olurSayisi;
	}

	public void setOlurSayisi(String olurSayisi) {
		this.olurSayisi = olurSayisi;
	}

	public Date getIslemZamani() {
		return islemZamani;
	}

	public void setIslemZamani(Date islemZamani) {
		this.islemZamani = islemZamani;
	}

	public String getMazeretNedeni() {
		return mazeretNedeni;
	}

	public void setMazeretNedeni(String mazeretNedeni) {
		this.mazeretNedeni = mazeretNedeni;
	}

}
