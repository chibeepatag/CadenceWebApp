/**
 * 
 */
package au.edu.cmu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import au.edu.cmu.model.Coach;
import au.edu.cmu.model.Race;

/**
 * @author ChibeePatag
 *
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class RaceDaoImpl implements RaceDao {
	
	@PersistenceContext(unitName = "entityManager")
	EntityManager entityManager;

	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.BaseDao#create(java.lang.Object)
	 */
	@Override
	public Race create(Race entity) {
		this.entityManager.persist(entity);
        this.entityManager.flush();
        this.entityManager.refresh(entity);
        return entity;
	}

	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.BaseDao#edit(java.lang.Object)
	 */
	@Override
	public void edit(Race entity) {
		this.entityManager.merge(entity);

	}

	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.BaseDao#remove(java.lang.Object)
	 */
	@Override
	public void remove(Race entity) {
		this.entityManager.remove(this.entityManager.merge(entity));
	}

	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.BaseDao#findById(java.lang.Long)
	 */
	@Override
	public Race findById(Long id) {
		return this.entityManager.find(Race.class, id);
	}

	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.BaseDao#findAll()
	 */
	@Override
	public List<Race> findAll() {
		CriteriaQuery<Race> cq = this.entityManager.getCriteriaBuilder().createQuery(Race.class);
        cq.select(cq.from(Race.class));
        return this.entityManager.createQuery(cq).getResultList();
	}

}
