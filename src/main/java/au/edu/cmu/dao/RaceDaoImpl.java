/**
 * 
 */
package au.edu.cmu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import au.edu.cmu.model.Race;
import au.edu.cmu.model.Race_;

/**
 * @author ChibeePatag
 *
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class RaceDaoImpl implements RaceDao {
	
	Logger logger = Logger.getLogger(RaceDaoImpl.class);
	
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
	public Race edit(Race entity) {
		return this.entityManager.merge(entity);
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
	
	@Override
	public Race getCurrentRace() {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Race> cq = cb.createQuery(Race.class);
		Root<Race> raceRoot = cq.from(Race.class);
		Expression<Boolean> ongoingCriteria = cb.equal(raceRoot.get(Race_.isOngoing), true);
		
		cq.select(raceRoot).where(ongoingCriteria);
		Race race = this.entityManager.createQuery(cq).getSingleResult();
		return race;
	}
	
	@Override
	public boolean isRaceOngoing() {
		boolean result = false;
		
		try {
			getCurrentRace();
			result = true;
		} catch (NoResultException e) {
			logger.info("No race is on going.");
		}
		return result;
	}

}
