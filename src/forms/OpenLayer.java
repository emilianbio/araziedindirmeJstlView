/**
 * 
 */
package forms;

import java.awt.Point;
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
@Table(name = "openlayer", schema = "public")
public class OpenLayer implements Serializable {

	private static final long serialVersionUID = 4418029727139184238L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "openlayer_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	private long id;

	@Column(name = "coordinate")
	private Point[] coord;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Point[] getCoord() {
		return coord;
	}

	public void setCoord(Point[] coord) {
		this.coord = coord;
	}

}
