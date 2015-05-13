package cz.cvut.fel.aos.airline.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * AbstractEntity represents Database entity. Contains common method and properties for database entities.
 *
 * @author Michal Vlček<mychalvlcek@gmail.com>
 * @date 20.10.14
 */

@MappedSuperclass
public class AbstractEntity implements Serializable {
    protected static final long serialVersionUID = 7711505597348200997L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

	/**
	 * Return Column identifier (id)
	 * @return Long
	 */
    public Long getId() {
        return id;
    }

	/**
	 * Set column identifier (id)
	 * @param id column identifier
	 */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractEntity other = (AbstractEntity) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

}
