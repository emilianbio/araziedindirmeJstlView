/**
 * 
 */
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

/**
 * @author Emrah Denizer
 *
 */
@Entity
@Table(name = "tum_turkiye", schema = "public")
// Veri tabanındaki tablo ismi sabitler mi evet sequence ismi on
public class Yerler implements java.io.Serializable {
	private static final long serialVersionUID = 4418029727139184238L;
	@Id
	@Column(name = "id", nullable = false)
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "tum_turkiye_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	private long id;

	@Column(name = "isim")
	private String isim;

	@Column(name = "durum")
	private Boolean durum;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "islemzamani")
	private Date eklemezamani;

	@ManyToOne
	@JoinColumn(name = "katid")
	private Yerler tip;

	public Yerler getTip() {

		if (tip == null) {
			tip = new Yerler();
		}
		return tip;
	}

	public void setTip(Yerler tip) {
		this.tip = tip;
	}

	public Yerler() {

	}

	public Yerler(long katId) {
		this.id = katId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}

	public Date getEklemezamani() {
		return eklemezamani;
	}

	public void setEklemezamani(Date eklemezamani) {
		this.eklemezamani = eklemezamani;
	}

	public Boolean getDurum() {
		return durum;
	}

	public void setDurum(Boolean durum) {
		this.durum = durum;
	}

}
