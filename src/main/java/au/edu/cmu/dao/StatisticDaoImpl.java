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
import au.edu.cmu.model.Message;
import au.edu.cmu.model.Statistic;

/**
 * @author ChibeePatag
 * 
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class StatisticDaoImpl implements StatisticsDao {

	@PersistenceContext(unitName = "entityManager")
	EntityManager entityManager;

	@Override
	public Statistic create(Statistic entity) {
		this.entityManager.persist(entity);
		this.entityManager.flush();
		this.entityManager.refresh(entity);
		return entity;
	}

	@Override
	public void edit(Statistic entity) {
		this.entityManager.merge(entity);

	}

	@Override
	public void remove(Statistic entity) {
		this.entityManager.remove(this.entityManager.merge(entity));

	}

	@Override
	public Statistic findById(Long id) {
		return this.entityManager.find(Statistic.class, id);
	}

	@Override
	public List<Statistic> findAll() {
		CriteriaQuery<Statistic> cq = this.entityManager.getCriteriaBuilder().createQuery(Statistic.class);
        cq.select(cq.from(Statistic.class));
        return this.entityManager.createQuery(cq).getResultList();
	}

}
