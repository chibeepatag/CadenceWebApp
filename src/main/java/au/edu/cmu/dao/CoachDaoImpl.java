/**
 * 
 */
package au.edu.cmu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import au.edu.cmu.model.Coach;

/**
 * @author ChibeePatag
 * 
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class CoachDaoImpl implements CoachDao {

	@PersistenceContext(unitName = "entityManager")
	EntityManager entityManager;

	@Override
	public Coach findByUsername(String username) throws NoResultException {
		TypedQuery<Coach> query = this.entityManager.createNamedQuery(
				"findCoachByUsername", Coach.class);
		query.setParameter("username", username);

		Coach coach = query.getSingleResult();
		return coach;
	}

	@Override
	public Coach create(Coach coach) {
		this.entityManager.persist(coach);
        this.entityManager.flush();
        this.entityManager.refresh(coach);
        return coach;
	}

	@Override
	public void edit(Coach coach) {
		 this.entityManager.merge(coach);
	}

	@Override
	public void remove(Coach coach) {
		this.entityManager.remove(this.entityManager.merge(coach));

	}

	@Override
	public Coach findById(Long id) {
		return this.entityManager.find(Coach.class, id);
	}

	@Override
	public List<Coach> findAll() {
		CriteriaQuery<Coach> cq = this.entityManager.getCriteriaBuilder().createQuery(Coach.class);
        cq.select(cq.from(Coach.class));
        return this.entityManager.createQuery(cq).getResultList();
	}
}
