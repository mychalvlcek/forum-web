package cz.cvut.fel.aos.airline.helpers;

/**
 * GPS helper class for GPS coordinates representation.
 *
 * @author Michal Vlček<mychalvlcek@gmail.com>
 * @date 28.11.14
 */
public class GPS {
    public double lat;
    public double lng;

    /**
     * Creates new instance of GPS.
     * @param lat
     * @param lng
     */
    public GPS(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
}