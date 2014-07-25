/**
 * 
 */
package au.edu.cmu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import au.edu.cmu.exceptions.CadencePersistenceException;
import au.edu.cmu.model.MessageFromRider;
import au.edu.cmu.model.MessageFromRider_;
import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;

/**
 * @author ChibeePatag
 *
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class MessageFromRiderDaoImpl implements MessageFromRiderDao {
	
	@PersistenceContext(unitName = "entityManager")
	EntityManager entityManager;
	
	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.BaseDao#create(java.lang.Object)
	 */
	@Override
	public MessageFromRider create(MessageFromRider entity) {
		this.entityManager.persist(entity);
//        this.entityManager.flush();
//        this.entityManager.refresh(entity);
        return entity;
	}

	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.BaseDao#edit(java.lang.Object)
	 */
	@Override
	public MessageFromRider edit(MessageFromRider entity) {
		return this.entityManager.merge(entity);
	}

	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.BaseDao#remove(java.lang.Object)
	 */
	@Override
	public void remove(MessageFromRider entity) {
		this.entityManager.remove(this.entityManager.merge(entity));
	}

	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.BaseDao#findById(java.lang.Long)
	 */
	@Override
	public MessageFromRider findById(Long id) {
		return this.entityManager.find(MessageFromRider.class, id);
	}

	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.BaseDao#findAll()
	 */
	@Override
	public List<MessageFromRider> findAll() {
		CriteriaQuery<MessageFromRider> cq = this.entityManager.getCriteriaBuilder().createQuery(MessageFromRider.class);
        cq.select(cq.from(MessageFromRider.class));
        return this.entityManager.createQuery(cq).getResultList();
	}

	@Override
	public MessageFromRider getLatestMessageFromRider(Rider rider, Race race) {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<MessageFromRider> cq = cb.createQuery(MessageFromRider.class);
		Root<MessageFromRider> root = cq.from(MessageFromRider.class);
		Predicate riderPred = cb.equal(root.get(MessageFromRider_.from), rider);
		Predicate racePred = cb.equal(root.get(MessageFromRider_.race), race);
		Predicate riderAndRacePred = cb.and(riderPred, racePred);
		Order order = cb.desc(root.get(MessageFromRider_.message_ts));
		cq.select(root).where(riderAndRacePred).orderBy(order);
		TypedQuery<MessageFromRider> msgFromRiderQry = this.entityManager.createQuery(cq).setMaxResults(1);
		try{
			MessageFromRider msg = msgFromRiderQry.getSingleResult();
			return msg;
		}catch(NoResultException nre){
			CadencePersistenceException cpe = new CadencePersistenceException(nre, "No message for this rider");
		}
		return null;
	}
	
	@Override
	public List<MessageFromRider> getMessagesFromRiderForThisRace(Race race) {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<MessageFromRider> cq = cb.createQuery(MessageFromRider.class);
		Root<MessageFromRider> root = cq.from(MessageFromRider.class);
		Predicate racePred = cb.equal(root.get(MessageFromRider_.race), race);
		
		cq.select(root).where(racePred);
		TypedQuery<MessageFromRider> messageQuery = this.entityManager.createQuery(cq);
		return messageQuery.getResultList();
	}
}
