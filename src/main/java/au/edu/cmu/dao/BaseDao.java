/**
 * 
 */
package au.edu.cmu.dao;

/**
 * @author ChibeePatag
 *
 */

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
public abstract class BaseDao<T> {

    @PersistenceContext(unitName = "entityManager")    
    EntityManager entityManager;

    private Class<T> entityClass;

    public BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public BaseDao() {
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    public T create(T entity) {
        this.entityManager.persist(entity);
        this.entityManager.flush();
        this.entityManager.refresh(entity);
        return entity;
    }

    public void edit(T entity) {
        this.entityManager.merge(entity);
    }

    public void remove(T entity) {
        this.entityManager.remove(this.entityManager.merge(entity));
    }

    public T find(Long primaryKey) {
        return this.entityManager.find(entityClass, primaryKey);
    }

    public List<T> findAll() {
        CriteriaQuery<T> cq = this.entityManager.getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        return this.entityManager.createQuery(cq).getResultList();
    }

}