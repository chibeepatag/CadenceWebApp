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
import au.edu.cmu.model.Rider;

/**
 * @author ChibeePatag
 * 
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class RiderDaoImpl implements RiderDao {

	@PersistenceContext(unitName = "entityManager")
	EntityManager entityManager;
	
	@Override
	public Rider create(Rider entity) {
		this.entityManager.persist(entity);
        this.entityManager.flush();
        this.entityManager.refresh(entity);
        return entity;
	}

	@Override
	public void edit(Rider entity) {
		this.entityManager.merge(entity);
	}

	@Override
	public void remove(Rider entity) {
		this.entityManager.remove(this.entityManager.merge(entity));

	}

	@Override
	public Rider findById(Long id) {
		return this.entityManager.find(Rider.class, id);
	}

	@Override
	public List<Rider> findAll() {
		CriteriaQuery<Rider> cq = this.entityManager.getCriteriaBuilder().createQuery(Rider.class);
        cq.select(cq.from(Rider.class));
        return this.entityManager.createQuery(cq).getResultList();
	}

}
