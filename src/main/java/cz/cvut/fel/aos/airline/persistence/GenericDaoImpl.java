package cz.cvut.fel.aos.airline.persistence;

import cz.cvut.fel.aos.airline.entity.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * GenericDao implementation
 *
 * @author Michal Vlček<mychalvlcek@gmail.com>
 * @date 20.10.14
 */
public class GenericDaoImpl implements GenericDao {

    protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("persist-unit");

    /**
     * Get entity manager for the current transaction
     * @return EntityManager
     */
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @SuppressWarnings("unchecked")
    public <ENTITY> List<ENTITY> getAll(Class<ENTITY> clazz) {
        return getEntityManager().createQuery("SELECT e FROM " + clazz.getSimpleName() + " e").getResultList();
    }

    @SuppressWarnings("unchecked")
    public <ENTITY> List<ENTITY> getAllOrderedDesc(String property, Class<ENTITY> clazz) {
        return getEntityManager().createQuery("SELECT e FROM " + clazz.getSimpleName() + " e ORDER BY e.updated DESC").getResultList();
    }

    @SuppressWarnings("unchecked")
    public <ENTITY> List<ENTITY> getAllOrderedAsc(String property, Class<ENTITY> clazz) {
        throw new IllegalStateException("Not implemented yet");
    }

    @SuppressWarnings("unchecked")
    public <ENTITY> List<ENTITY> getByProperty(String property, Object value, Class<ENTITY> clazz) {
        String queryString = "SELECT e FROM " + clazz.getSimpleName() + " e WHERE e." + property + " = :value";
        return getEntityManager().createQuery(queryString).setParameter("value", value).getResultList();
    }

    @SuppressWarnings("unchecked")
    public <ENTITY> List<ENTITY> getByProperty(String property, Object value, Class<ENTITY> clazz, String order) {
        String queryString = "SELECT e FROM " + clazz.getSimpleName() + " e WHERE e." + property + " = :value ORDER BY e.id " + order;
        return getEntityManager().createQuery(queryString).setParameter("value", value).getResultList();
    }

    public <ENTITY extends AbstractEntity> void removeByProperty(String property, Object value, Class<ENTITY> clazz) {
        String queryString = "DELETE FROM " + clazz.getSimpleName() + " e WHERE e." + property + " = :value";
        getEntityManager().createQuery(queryString).setParameter("value", value);
    }

    @Override
    public <ENTITY extends AbstractEntity> void removeById(long id, Class<ENTITY> clazz) {

        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            ENTITY e = em.find(clazz, id);

            if(e == null) {
                System.out.println("Error Deleting Customer: Customer not found");
            } else {
                em.remove(e);
            }

            transaction.commit();
        } catch (Exception ex) {
            System.out.println("Error Deleting Customer: " + ex.getMessage());

            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public <ENTITY extends AbstractEntity> void remove(ENTITY o) {
        getEntityManager().remove(o);
    }

    @SuppressWarnings("unchecked")
    public <ENTITY> ENTITY getById(Long id, Class<ENTITY> clazz) {
        return getEntityManager().find(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public <ENTITY> List<ENTITY> getByIds(String property, List<Object> value, Class<ENTITY> clazz) {
        String queryString = "SELECT e FROM " + clazz.getSimpleName() + " e WHERE e." + property + " IN (:value)";
        return getEntityManager().createQuery(queryString).setParameter("value", value).getResultList();
    }

    public <ENTITY extends AbstractEntity> ENTITY saveOrUpdate(ENTITY o) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            if (o.getId() == null) {
                em.persist(o);
            } else {
                em.merge(o);
            }

            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error Saving Customer: " + e.getMessage());

            transaction.rollback();
        } finally {
            em.close();
        }

        return o;
    }

    public <ENTITY> ENTITY getByPropertyUnique(String property, Object value, Class<ENTITY> clazz) {
        ENTITY e;
        if (value == null) {
            e = clazz.cast(getEntityManager().createQuery("FROM " + clazz.getSimpleName() + " WHERE " + property + " IS NULL" ).getSingleResult());
        } else {
            e = clazz.cast(getEntityManager().createQuery("FROM " + clazz.getSimpleName() + " WHERE " + property + " = :value" ).setParameter("value", value).getSingleResult());
        }
        return e;
    }

    public Long getCount(Class clazz) {
        return (Long) getEntityManager().createQuery("SELECT COUNT(e) FROM " + clazz.getSimpleName() + " e").getSingleResult();
    }

    public <ENTITY> List<ENTITY> getPage(String sort, String filter, int base, int offset, Class<ENTITY> clazz) {
        return getEntityManager().createQuery("SELECT e FROM " + clazz.getSimpleName() + " e ORDER BY " + sort)
                .setFirstResult(offset)
                .setMaxResults(base)
                .getResultList();
    }

    public void merge(Object o) {
        throw new IllegalStateException("Not implemented yet");
    }
}

