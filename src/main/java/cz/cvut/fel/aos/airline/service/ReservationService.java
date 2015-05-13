package cz.cvut.fel.aos.airline.service;

import cz.cvut.fel.aos.airline.api.data.Reservation;

import java.util.List;

/**
 * Reservation service
 *
 * @author Michal Vlček<mychalvlcek@gmail.com>
 * @date 26.10.14
 */
public interface ReservationService {

    /**
     * Gets all records
     * @return list of records
     */
    public List<Reservation> getAll();

    /**
     * Get entities by specific filters
     * @param sort colun name (default by id)
     * @param filter column name to filter
     * @param base limit from
     * @param offset count of records
     * @return list of records
     */
    public List<Reservation> find(String sort, String filter, int base, int offset);

    /**
     * Retrieves entity by id
     * @param id identifier of entity
     * @return entity
     */
    public Reservation get(Long id);

    /**
     * Delete entity specified by id
     * @param id of entity
     */
    public void delete(Long id);

    /**
     * Save or update entity
     * @param entity entity
     */
    public void save(Reservation entity);

    /**
     * Perform cancelation of reservation
     * @param id of reservation
     * @throws Exception
     */
    public void cancel(Long id) throws Exception;

    /**
     * Pays the reservation
     * @param id of reservation
     * @throws Exception
     */
    public void pay(Long id) throws Exception;

    /**
     * Get count of all records
     * @return Long number of records
     */
    public Long getCount();
}
