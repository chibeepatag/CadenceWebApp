/**
 * 
 */
package au.edu.cmu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import au.edu.cmu.model.MessageFromRider;
import au.edu.cmu.model.MessageFromRider_;
import au.edu.cmu.model.Message_;
import au.edu.cmu.model.Note;
import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;
import au.edu.cmu.model.User;
import au.edu.cmu.model.Message;

/**
 * @author ChibeePatag
 *
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class MessageDaoImpl implements MessageDao {

	@PersistenceContext(unitName = "entityManager")
	EntityManager entityManager;
	
	@Override
	public Message create(Message entity) {
		this.entityManager.persist(entity);
        this.entityManager.flush();
        this.entityManager.refresh(entity);
        return entity;
	}

	@Override
	public Message edit(Message entity) {
		return this.entityManager.merge(entity);		
	}

	@Override
	public void remove(Message entity) {
		this.entityManager.remove(this.entityManager.merge(entity));
		
	}

	@Override
	public Message findById(Long id) {
		return this.entityManager.find(Message.class, id);
	}

	@Override
	public List<Message> findAll() {
		CriteriaQuery<Message> cq = this.entityManager.getCriteriaBuilder().createQuery(Message.class);
        cq.select(cq.from(Message.class));
        return this.entityManager.createQuery(cq).getResultList();
	}

	@Override //select message where (rider in message.recipients) and sent = n;
	public Message getLatestMessageForRider(Rider rider) {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Message> cq = cb.createQuery(Message.class);
				
		Root<Message> messageRoot = cq.from(Message.class);
		Join<Message, Rider> riderRoot = messageRoot.join(Message_.recipients);
		
//		Parameter<Rider> riderRecipientParam = cb.parameter(Rider.class, "rider");			
		Predicate notSentPred = cb.equal(messageRoot.get(Message_.sent), Boolean.FALSE);
		Predicate equalRider = cb.equal(riderRoot, rider);
		Order messageTsOrder = cb.desc(messageRoot.get(Message_.message_ts));
		cq.select(messageRoot).where(notSentPred, equalRider).orderBy(messageTsOrder);
		TypedQuery<Message> messageQuery = this.entityManager.createQuery(cq).setMaxResults(1);
//		messageQuery.setParameter("rider", rider);
		return messageQuery.getSingleResult();
	}
	
	@Override
	public List<Message> getMessageForThisRace(Race race) {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Message> cq = cb.createQuery(Message.class);
		Root<Message> root = cq.from(Message.class);
		Predicate racePred = cb.equal(root.get(Message_.race), race);
		
		cq.select(root).where(racePred);
		TypedQuery<Message> messageQuery = this.entityManager.createQuery(cq);
		
		return messageQuery.getResultList();
	}
	
}
