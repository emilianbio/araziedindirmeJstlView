///**
// * 
// */
//package forms;
//
//import java.io.Serializable;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//
///**
// * @author Emrah Denizer
// *
// */
//@Entity
//@Table(name = "birimler", schema = "public")
//public class Birimler implements Serializable {
//	private static final long serialVersionUID = 4418029727139184238L;
//	@Id
//	@Column(name = "id")
//	@SequenceGenerator(name = "tabloSequnce", sequenceName = "birimler_id_seq")
//	@GeneratedValue(generator = "tabloSequnce")
//	private long id;
//
//	@Column(name = "birim_adi")
//	private String birimAdi;
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public String getBirimAdi() {
//		return birimAdi;
//	}
//
//	public void setBirimAdi(String birimAdi) {
//		this.birimAdi = birimAdi;
//	}
// }
