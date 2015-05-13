package cz.cvut.fel.aos.airline.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Reservation entity
 *
 * @author Michal Vlček<mychalvlcek@gmail.com>
 * @date 19.10.14
 */

@Entity
@Table(name = "reservations")
public class ReservationEntity extends AbstractEntity {

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "seats", nullable = false)
    private int seats;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private StateChoices state;

    @Transient
    private String url;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="flight")
    private FlightEntity flight;

    /**
     * Empty constructor for proper functionality of JPA
     */
    public ReservationEntity() {
    }

    /**
     * Constructs new instance of reservation
     * @param created date of creation
     * @param seats number of seats
     * @param password password
     * @param state state of reservation
     * @param flight flight of reservation
     */
    public ReservationEntity(Date created, int seats, String password, StateChoices state, FlightEntity flight) {
        this.created = created;
        this.seats = seats;
        this.password = password;
        this.state = state;
        this.flight = flight;
    }

    /**
     * Returns id of reservation
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id of reservation
     * @param id Long
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets date of creation
     * @return Date
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Sets date of creation
     * @param created Date
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * Gets number of seats
     * @return int
     */
    public int getSeats() {
        return seats;
    }

    /**
     * Sets new number of seats
     * @param seats number of seats
     */
    public void setSeats(int seats) {
        this.seats = seats;
    }

    /**
     * Gets password of reservation
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password of reservation
     * @param password password string
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets actual state of reservation
     * @return StateChoices
     */
    public StateChoices getState() {
        return state;
    }

    /**
     * Sets new state of reservation
     * @param state StateChoices
     */
    public void setState(StateChoices state) {
        this.state = state;
    }

    /**
     * Gets url of reservation
     * @return url of reserarvation
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url of reservation
     * @param url url of reservation
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets id of flight of reservation
     * @return flight entity
     */
    public FlightEntity getFlight() {
        return flight;
    }

    /**
     * Sets id of flight
     * @param flight flight entity
     */
    public void setFlight(FlightEntity flight) {
        this.flight = flight;
    }
}
