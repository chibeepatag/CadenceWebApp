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

	
}
