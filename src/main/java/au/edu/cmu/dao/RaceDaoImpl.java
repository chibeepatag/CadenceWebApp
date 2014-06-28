/**
 * 
 */
package au.edu.cmu.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import au.edu.cmu.model.Coach;
import au.edu.cmu.model.Race;
import au.edu.cmu.model.Race_;

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
	
	@Override//Select race from race where race.date = (select max(date) from race)
	public Race getCurrentRace() {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Race> cq = cb.createQuery(Race.class);
		Root<Race> raceRoot = cq.from(Race.class);
		Expression<Boolean> ongoingCriteria = cb.equal(raceRoot.get(Race_.isOngoing), true);
		
		cq.select(raceRoot).where(ongoingCriteria);
		Race race = this.entityManager.createQuery(cq).getSingleResult();
		return race;
	}

}
