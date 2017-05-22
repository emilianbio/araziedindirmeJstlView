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
 * @author lenovo
 *
 */
@Entity
@Table(name = "user_roles", schema = "public")
public class UserRoles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "user_roles_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	private long id;

	@Column(name = "isim")
	private String rollAdi;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRollAdi() {
		
		
		return rollAdi;
	}

	public void setRollAdi(String rollAdi) {
		this.rollAdi = rollAdi;
	}

	@Override
	public String toString() {
		return "UserRoles [id=" + id + ", rollAdi=" + rollAdi + "]";
	}

	/**
	 * @param rollAdi
	 * @param kullanici
	 */
	public UserRoles(String rollAdi) {
		super();
		this.rollAdi = rollAdi;
	}

	/**
	 * 
	 */
	public UserRoles() {
		// TODO Auto-generated constructor stub
	}

}
