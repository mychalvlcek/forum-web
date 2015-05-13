package cz.cvut.fel.aos.airline.api.data;

import cz.cvut.fel.aos.airline.entity.StateChoices;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Reservation DTO represents database object with specified values.
 *
 * @author Michal Vlček<mychalvlcek@gmail.com>
 * @date 26.10.14
 */
@XmlRootElement
public class Reservation extends AbstractDto {

    private Date created;
    private int seats;
    private String password;
    private StateChoices state;
    private String url;
    private Long flight;

	/**
	 * Return sdate when the reservation has been created.
	 * @return Date
	 */
    public Date getCreated() {
        return created;
    }

	/**
	 * Sets the date when the reservation hass been created.
	 * @param created date when the reservation has been created
	 */
    public void setCreated(Date created) {
        this.created = created;
    }

	/**
	 * Retrurns number of reserved seats.
	 * @return int
	 */
    public int getSeats() {
        return seats;
    }

	/**
	 * Set nuber of seats for reservation.
	 * @param seats number of seats
	 */
    public void setSeats(int seats) {
        this.seats = seats;
    }

	/**
	 * Returns a password for the particular reservation.
	 * @return String
	 */
    public String getPassword() {
        return password;
    }

	/**
	 * Set password for the particular reservation.
	 * @param password
	 */
    public void setPassword(String password) {
        this.password = password;
    }

	/**
	 * Returns a state choice for the reservation
	 * @see StateChoices
	 * @return StateChoices
	 */
    public StateChoices getState() {
        return state;
    }

	/**
	 * Set the state choice for reservation
	 * @see StateChoices
	 * @param state state of the reservation
	 */
    public void setState(StateChoices state) {
        this.state = state;
    }

	/**
	 * Returns URL for the reservation
	 * @return String
	 */
    public String getUrl() {
        return url;
    }

	/**
	 * Set the URL for the reservation
	 * @param url url of the reservation
	 */
    public void setUrl(String url) {
        this.url = url;
    }

	/**
	 * Returns id of the Flight for the reservation
	 * @return Long
	 */
    public Long getFlight() {
        return flight;
    }

	/**
	 * Set the flight id for the reservation
	 * @param flight id of the flight
	 */
    public void setFlight(Long flight) {
        this.flight = flight;
    }
}
