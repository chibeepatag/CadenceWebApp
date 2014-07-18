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

import au.edu.cmu.model.MessageFromRider;
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
        this.entityManager.flush();
        this.entityManager.refresh(entity);
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

}
