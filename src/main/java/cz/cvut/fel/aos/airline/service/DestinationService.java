package cz.cvut.fel.aos.airline.service;

import cz.cvut.fel.aos.airline.api.data.Destination;

import java.util.List;

/**
 * Destination service
 *
 * @author Michal Vlček<mychalvlcek@gmail.com>
 * @date 21.10.14
 */
public interface DestinationService {

    /**
     * Gets all records
     * @return list of records
     */
    public List<Destination> getAll();

    /**
     * Get entities by specific filters
     * @param sort colun name (default by id)
     * @param filter column name to filter
     * @param base limit from
     * @param offset count of records
     * @return list of records
     */
    public List<Destination> find(String sort, String filter, int base, int offset);

    /**
     * Retrieves entity by id
     * @param id identifier of entity
     * @return entity
     */
    public Destination get(Long id);

    /**
     * Delete entity specified by id
     * @param id of entity
     */
    public void delete(Long id);

    /**
     * Get count of all records
     * @return Long number of records
     */
    public Long getCount();

    /**
     * Save or update entity
     * @param destination entity
     */
    public void save(Destination destination);
}
