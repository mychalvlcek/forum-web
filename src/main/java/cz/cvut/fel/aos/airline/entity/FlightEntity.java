package cz.cvut.fel.aos.airline.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Flight Entity
 *
 * @author Michal Vlček<mychalvlcek@gmail.com>
 * @date 19.10.14
 */

@Entity
@Table(name = "flights")
public class FlightEntity extends AbstractEntity {

    @Column(name = "date_of_departure")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfDeparture;

    @Column(name = "distance", nullable = false)
    private double distance;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "seats", nullable = false)
    private int seats;

    @Column(name = "name", nullable = false)
    private String name;

    @Transient
    private String url;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="dest_from")
    private DestinationEntity from;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="dest_to")
    private DestinationEntity to;

    /**
     * Empty constructor for proper functionality of JPA
     */
    public FlightEntity() {
    }

    /**
     * Constructs new instance of flight
     * @param dateOfDeparture Date of departure
     * @param distance distance of flight
     * @param price price of flight
     * @param seats number of seats in airplane of flight
     * @param name name of flight
     * @param from Destination from
     * @param to Destination to
     */
    public FlightEntity(Date dateOfDeparture, double distance, double price, int seats, String name, DestinationEntity from, DestinationEntity to) {
        this.dateOfDeparture = dateOfDeparture;
        this.distance = distance;
        this.price = price;
        this.seats = seats;
        this.name = name;
        this.from = from;
        this.to = to;
    }

    /**
     * Gets date of departure
     * @return Date
     */
    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }

    /**
     * Sets date of departure
     * @param dateOfDeparture Date
     */
    public void setDateOfDeparture(Date dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    /**
     * Gets distance of flight
     * @return double
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Sets distance of flight
     * @param distance double
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Gets price of flight
     * @return  double
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price of flight
     * @param price double
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets no. of seats in flight
     * @return integer
     */
    public int getSeats() {
        return seats;
    }

    /**
     * Sets no. of seats in flight
     * @param seats integer
     */
    public void setSeats(int seats) {
        this.seats = seats;
    }

    /**
     * Gets name of flight
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of flight
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets url of flight
     * @return String
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url of flight
     * @param url String
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets entity of destination "from"
     * @return DestinationEntity
     */
    public DestinationEntity getFrom() {
        return from;
    }

    /**
     * Sets "from" entity
     * @param from DestinationEntity
     */
    public void setFrom(DestinationEntity from) {
        this.from = from;
    }

    /**
     * Gets entity of destination "to"
     * @return
     */
    public DestinationEntity getTo() {
        return to;
    }

    /**
     * Sets "to" entity
     * @param to DestinationEntity
     */
    public void setTo(DestinationEntity to) {
        this.to = to;
    }
}
