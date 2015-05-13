package cz.cvut.fel.aos.airline.api.data;

import java.io.Serializable;

/**
 * AbstractDto which represents common methods and properties of objects.
 *
 * @author Michal Vlček<mychalvlcek@gmail.com>
 * @date 20.10.14
 */
public class AbstractDto implements Serializable {
    protected Long id;

	/**
	 * Return the id of entity.
	 * @return Long
	 */
    public Long getId() {
        return id;
    }

	/**
	 * Set the id of entity
	 * @param id id of entity
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
        final AbstractDto other = (AbstractDto) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

}
