package cz.cvut.fel.aos.airline.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * DestinationEntity
 *
 * @author Michal Vlček<mychalvlcek@gmail.com>
 * @date 19.10.14
 */

@Entity
@Table(name = "destinations")
public class DestinationEntity extends AbstractEntity {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "lat", nullable = false)
    private double lat;

    @Column(name = "lon", nullable = false)
    private double lon;

    /**
     * Empty constructor for proper functionality of JPA
     */
    public DestinationEntity() {

    }

    /**
     * Gets name of destination
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of destination
     * @param name name of destination
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets latitude of destination
     * @return double
     */
    public double getLat() {
        return lat;
    }

    /**
     * Sets latitude of destination
     * @param lat latitude
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * Gets longitude of destination
     * @return double
     */
    public double getLon() {
        return lon;
    }

    /**
     * Sets longitude of destination
     * @param lon longitude
     */
    public void setLon(double lon) {
        this.lon = lon;
    }
}
