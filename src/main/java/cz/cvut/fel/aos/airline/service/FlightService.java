package cz.cvut.fel.aos.airline.service;

import cz.cvut.fel.aos.airline.api.data.Flight;

import java.util.List;

/**
 * Flight service
 *
 * @author Michal Vlček<mychalvlcek@gmail.com>
 * @date 24.10.14
 */
public interface FlightService {

    /**
     * Gets all records
     * @return list of records
     */
    public List<Flight> getAll();

    /**
     * Get entities by specific filters
     * @param sort colun name (default by id)
     * @param filter column name to filter
     * @param base limit from
     * @param offset count of records
     * @return list of records
     */
    public List<Flight> find(String sort, String filter, int base, int offset, String currency);

    /**
     * Retrieves entity by id
     * @param id identifier of entity
     * @return entity
     */
    public Flight get(Long id);

    /**
     * Delete entity specified by id
     * @param id of entity
     */
    public void delete(Long id);

    /**
     * Save or update entity
     * @param entity entity
     */
    public void save(Flight entity);

    /**
     * Get count of all records
     * @return Long number of records
     */
    public Long getCount();
}
