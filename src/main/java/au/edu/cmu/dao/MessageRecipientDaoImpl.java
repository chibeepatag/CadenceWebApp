/**
 * 
 */
package au.edu.cmu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import au.edu.cmu.model.MessageRecipient;

/**
 * @author ChibeePatag
 *
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class MessageRecipientDaoImpl implements MessageRecipientDao {
	
	static Logger logger = Logger.getLogger(MessageDaoImpl.class);

	@PersistenceContext(unitName = "entityManager")
	EntityManager entityManager;

	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.BaseDao#create(java.lang.Object)
	 */
	@Override
	public MessageRecipient create(MessageRecipient entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.BaseDao#edit(java.lang.Object)
	 */
	@Override
	public MessageRecipient edit(MessageRecipient entity) {
		return this.entityManager.merge(entity);
	}

	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.BaseDao#remove(java.lang.Object)
	 */
	@Override
	public void remove(MessageRecipient entity) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.BaseDao#findById(java.lang.Long)
	 */
	@Override
	public MessageRecipient findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.BaseDao#findAll()
	 */
	@Override
	public List<MessageRecipient> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
