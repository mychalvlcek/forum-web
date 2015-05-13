package cz.cvut.fel.aos.airline.api.data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Flight DTO represents database object with specified values.
 *
 * @author Michal Vlček<mychalvlcek@gmail.com>
 * @date 24.10.14
 */
@XmlRootElement
public class Flight extends AbstractDto {
    private String url;
    private String name;
    private Date dateOfDeparture;
    private double distance;
    private double price;
    private int seats;
    private Long from;
    private Long to;

	/**
	 * Returns name of the flight.
	 * @return String
	 */
    public String getName() {
        return name;
    }

	/**
	 * Set the name of flight
	 * @param name name of the flight
	 */
    public void setName(String name) {
        this.name = name;
    }

	/**
	 * Return Departure date of the flight
	 * @return Date
	 */
    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }

	/**
	 * Set the departure date.
	 * @param dateOfDeparture departure date of the flight
	 */
    public void setDateOfDeparture(Date dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

	/**
	 * Return total distance of the flight
	 * @return double
	 */
    public double getDistance() {
        return distance;
    }

	/**
	 * Set the distance of the flight
	 * @param distance total distance of the flight
	 */
    public void setDistance(double distance) {
        this.distance = distance;
    }

	/**
	 * Return price of the flight
	 * @return double
	 */
    public double getPrice() {
        return price;
    }

	/**
	 * Set the price
	 * @param price price of the flight
	 */
    public void setPrice(double price) {
        this.price = price;
    }

	/**
	 * Returns the number of the seats in flight
	 * @return int
	 */
    public int getSeats() {
        return seats;
    }

	/**
	 * Set the number of the seats in flight
	 * @param seats number of the seats in flight
	 */
    public void setSeats(int seats) {
        this.seats = seats;
    }

	/**
	 * Retruns id of Departure airport.
	 * @see Destination
	 * @return Long
	 */
    public Long getFrom() {
        return from;
    }

	/**
	 * Sets id of the Departure airport.
	 * @see Destination
	 * @param from id of the Departure airport
	 */
    public void setFrom(Long from) {
        this.from = from;
    }

	/**
	 * Retruns id of Destination airport.
	 * @see Destination
	 * @return Long
	 */
    public Long getTo() {
        return to;
    }

	/**
	 * Set id of Destination airport
	 * @param to Long
	 */
	public void setTo(Long to) {
        this.to = to;
    }

	/**
	 * Retruns url of the Flight
	 * @return String
	 */
    public String getUrl() {
        return url;
    }

	/**
	 * Set url of the Flight
	 * @param url url for the flight
	 */
    public void setUrl(String url) {
        this.url = url;
    }

}
