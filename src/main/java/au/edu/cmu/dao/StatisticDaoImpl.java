/**
 * 
 */
package au.edu.cmu.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import au.edu.cmu.model.User;
import au.edu.cmu.model.Message;
import au.edu.cmu.model.Rider;
import au.edu.cmu.model.Statistic;
import au.edu.cmu.model.Statistic_;

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
//		this.entityManager.flush();
//		this.entityManager.refresh(entity);
		return entity;
	}

	@Override
	public Statistic edit(Statistic entity) {
		return this.entityManager.merge(entity);

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
	
	@Override
	public Statistic getRiderLatestStatistic(Rider rider) {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Statistic> cq = cb.createQuery(Statistic.class);
		Root<Statistic> statisticRoot = cq.from(Statistic.class);
	
		Subquery<Date> latestTSSubQuery = cq.subquery(Date.class);		
		Root<Statistic> statisticSubRoot = latestTSSubQuery.from(Statistic.class);
		Expression<Date> maxTS = cb.greatest(statisticSubRoot.get(Statistic_.stat_ts));
		Predicate riderPred = cb.equal(statisticSubRoot.get(Statistic_.rider), rider);		
		latestTSSubQuery.select(maxTS).where(riderPred).groupBy(statisticSubRoot.get(Statistic_.rider));
		
		Predicate whereMaxTS = cb.equal(statisticRoot.get(Statistic_.stat_ts), latestTSSubQuery);
		Predicate riderPred2 = cb.equal(statisticRoot.get(Statistic_.rider), rider);
		Predicate and = cb.and(whereMaxTS, riderPred2);
		cq.select(statisticRoot).where(and);
		
		Statistic statistic = null;
		try{
			statistic = this.entityManager.createQuery(cq).getSingleResult();
		}catch(NoResultException nre){
			System.out.println("No result for " + rider.getRider_id());
		}
		return statistic;
	}

}
