package cz.cvut.fel.aos.airline.persistence;

import cz.cvut.fel.aos.airline.entity.AbstractEntity;
import java.util.List;

/**
 * Definuje metody, ktere by melo kazde dao implementovat.
 *
 * @author Pavel Micka (mickapa1@fel.cvut.cz)
 * @param <ENTITY> entita, ktere se dane dao tyka
 */
public interface GenericDao {

    /**
     * Vrati vsechny entity serazene sestupne dle id
     * @return vsechny entity pozadovaneho typu
     * (dle implementace potomka tohoto interfacu)
     */
    public <ENTITY> List<ENTITY> getAll(Class<ENTITY> clazz);

    /**
     * Vrati stranku entit zacinajici from o velikosti maxResults
     * @param <ENTITY> typovy parametr
     * @param base index uvodni entity
     * @param offset pocet entit na strance
     * @param clazz trida korespondujici s typem
     * @return maxResults entit
     */
    public <ENTITY> List<ENTITY> getPage(String sort, String filter, int base, int offset, Class<ENTITY> clazz);

    /**
     * Delete all entities by property
     * @param property the property to select
     * @param value value of the property
     * @return
     */
    public <ENTITY extends AbstractEntity> void removeByProperty(String property, Object value, Class<ENTITY> clazz);
    /**
     * Delete entity identified by id
     * @param id entity to remove
     */
    public <ENTITY extends AbstractEntity> void removeById(long id, Class<ENTITY> clazz);

    /**
     * Vrati pocet entit dane tridy
     * @param clazz trida
     * @return pocet
     */
    public Long getCount(Class clazz);

    /**
     * Odstrani dany objekt
     * @param o objekt k odstraneni
     */
    public <ENTITY extends AbstractEntity> void remove(ENTITY o);

    /**
     * Ulozi nebo zaktualizuje danou entitu (at jiz je attached nebo detached),
     * entita bude po navratu teto funkce vzdy attached
     * @param o
     * @return attached ulozeny (zaktualizovany objekt)
     */
    public <ENTITY extends AbstractEntity> ENTITY saveOrUpdate(ENTITY o);

    /**
     * Vrati objekt (pomoci get) dane tridy dle ID
     * @param id id objektu k vraceni
     * @return objekt identifikovany id, @null pokud neexistuje
     */
    public <ENTITY> ENTITY getById(Long id, Class<ENTITY> clazz);

    /**
     * Vrati vsechny instance razene sestupne dle property
     * @param property
     * @return
     */
    public <ENTITY> List<ENTITY> getAllOrderedDesc(String property, Class<ENTITY> clazz);

    /**
     * Vrati vsechny instance razene vzestupne dle property
     * @param property
     * @return
     */
    public <ENTITY> List<ENTITY> getAllOrderedAsc(String property, Class<ENTITY> clazz);

    /**
     * Get all entities by property
     * @param property the property to select
     * @param value value of the property
     * @return list of entities
     */
    public <ENTITY> List<ENTITY> getByProperty(String property, Object value, Class<ENTITY> clazz);
    public <ENTITY> List<ENTITY> getByProperty(String property, Object value, Class<ENTITY> clazz, String order);

    /**
     * Get all entities by property
     * @param property the property to select
     * @param value value of the property
     * @return entity
     */
    public <ENTITY> ENTITY getByPropertyUnique(String property, Object value, Class<ENTITY> clazz);



}