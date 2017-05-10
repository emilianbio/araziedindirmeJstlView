package forms;

import java.util.Date;

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

@Entity
@Table(name = "arazi_islem_hareketleri", schema = "public")
public class AraziİslemHareketleri implements java.io.Serializable {
	private static final long serialVersionUID = 4418029727139184238L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "arazi_islem_hareketleri_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	private long id;

	@ManyToOne
	@JoinColumn(name = "islem_yapan")
	private Kullanici kullanici;

	@Column(name = "evrak_tarihi")
	private String tarih;

	@Column(name = "ilce")
	private String ilce;

	@Column(name = "evrak_no")
	private long evrakNo;

	@Column(name = "mahalle")
	private String mahalle;

	@Column(name = "devri_istenen_parsel_sayisi")
	private int devriIstenenParselSayisi;

	@Column(name = "devri_istenen_parsel_alani")
	private Long devriIstenenParselAlani;

	@Column(name = "izin_verilen_parsel_sayisi")
	private int izinVerilenParselSayisi;

	@Column(name = "izin_verilen_parsel_alani")
	private Long izinVerilenParselAlani;

	@Column(name = "izin_verilmeyen_parsel_sayisi")
	private int izinVerilmeyenParselSayisi;

	@Column(name = "izin_verilmeyen_parsel_alani")
	private Long izinVerilmeyenParselAlani;

	@Column(name = "nitelik")
	private String nitelik;

	@Column(name = "islem_tipi")
	private String islemTipi;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "islem_zamani")
	private Date islemZamani;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Kullanici getKullanici() {
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

	public Date getIslemZamani() {
		return islemZamani;
	}

	public void setIslemZamani(Date islemZamani) {
		this.islemZamani = islemZamani;
	}

	public String getIlce() {
		return ilce;
	}

	public void setIlce(String ilce) {
		this.ilce = ilce;
	}

	public long getEvrakNo() {

		return evrakNo;
	}

	public void setEvrakNo(long evrakNo) {
		this.evrakNo = evrakNo;
	}

	public String getMahalle() {
		return mahalle;
	}

	public void setMahalle(String mahalle) {
		this.mahalle = mahalle;
	}

	public int getDevriIstenenParselSayisi() {
		return devriIstenenParselSayisi;
	}

	public void setDevriIstenenParselSayisi(int devriIstenenParselSayisi) {
		this.devriIstenenParselSayisi = devriIstenenParselSayisi;
	}

	public Long getDevriIstenenParselAlani() {
		return devriIstenenParselAlani;
	}

	public void setDevriIstenenParselAlani(Long devriIstenenParselAlani) {
		this.devriIstenenParselAlani = devriIstenenParselAlani;
	}

	public int getIzinVerilenParselSayisi() {
		return izinVerilenParselSayisi;
	}

	public void setIzinVerilenParselSayisi(int izinVerilenParselSayisi) {
		this.izinVerilenParselSayisi = izinVerilenParselSayisi;
	}

	public Long getIzinVerilenParselAlani() {
		return izinVerilenParselAlani;
	}

	public void setIzinVerilenParselAlani(Long izinVerilenParselAlani) {
		this.izinVerilenParselAlani = izinVerilenParselAlani;
	}

	public int getIzinVerilmeyenParselSayisi() {
		return izinVerilmeyenParselSayisi;
	}

	public void setIzinVerilmeyenParselSayisi(int izinVerilmeyenParselSayisi) {
		this.izinVerilmeyenParselSayisi = izinVerilmeyenParselSayisi;
	}

	public Long getIzinVerilmeyenParselAlani() {
		return izinVerilmeyenParselAlani;
	}

	public void setIzinVerilmeyenParselAlani(Long izinVerilmeyenParselAlani) {
		this.izinVerilmeyenParselAlani = izinVerilmeyenParselAlani;
	}

	public String getNitelik() {
		return nitelik;
	}

	public void setNitelik(String nitelik) {
		this.nitelik = nitelik;
	}

	public String getIslemTipi() {
		return islemTipi;
	}

	public void setIslemTipi(String islemTipi) {
		this.islemTipi = islemTipi;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AraziİslemHareketleri [id=" + id + ", kullanici=" + kullanici + ", tarih=" + tarih + ", ilce=" + ilce
				+ ", evrakNo=" + evrakNo + ", mahalle=" + mahalle + ", devriIstenenParselSayisi="
				+ devriIstenenParselSayisi + ", devriIstenenParselAlani=" + devriIstenenParselAlani
				+ ", izinVerilenParselSayisi=" + izinVerilenParselSayisi + ", izinVerilenParselAlani="
				+ izinVerilenParselAlani + ", izinVerilmeyenParselSayisi=" + izinVerilmeyenParselSayisi
				+ ", izinVerilmeyenParselAlani=" + izinVerilmeyenParselAlani + ", nitelik=" + nitelik + ", islemTipi="
				+ islemTipi + ", islemZamani=" + islemZamani + "]";
	}

	

	
}
