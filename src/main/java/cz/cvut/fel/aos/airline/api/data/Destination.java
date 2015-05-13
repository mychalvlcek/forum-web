package cz.cvut.fel.aos.airline.api.data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Destination DTO represents database object with specified values.
 *
 * @author Michal Vlček<mychalvlcek@gmail.com>
 * @date 21.10.14
 */
@XmlRootElement
public class Destination extends AbstractDto {

    private String name;
    private String url;
    private double lat;
    private double lon;

	/**
	 * Return name of the Destination in format City/ICAO.
	 * @return String
	 */
    public String getName() {
        return name;
    }

	/**
	 * Set the name of the Destination
	 * @param name name of the Destination in format City/ICAO
	 */
    public void setName(String name) {
        this.name = name;
    }

	/**
	 * Return the url of Destination
	 * @return String
	 */
    public String getUrl() {
        return url;
    }

	/**
	 * Set the url of Destination
	 * @param url url of the Destination
	 */
    public void setUrl(String url) {
        this.url = url;
    }

	/**
	 * Returns latitude of the Destination
	 * @return double
	 */
    public double getLat() {
        return lat;
    }

	/**
	 * Set latitude of the Destination
	 * @param lat latitude of the destination
	 */
    public void setLat(double lat) {
        this.lat = lat;
    }

	/**
	 * Returns longitude of the Destination
	 * @return double
	 */
    public double getLon() {
        return lon;
    }

	/**
	 * Set longitude of the Destination
	 * @param lon latitude of the destination
	 */
    public void setLon(double lon) {
        this.lon = lon;
    }
}
