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

import au.edu.cmu.model.User;

/**
 * @author ChibeePatag
 * 
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class UserDaoImpl implements UserDao {

	@PersistenceContext(unitName = "entityManager")
	EntityManager entityManager;

	@Override
	public User findByUsername(String username) throws NoResultException {
		TypedQuery<User> query = this.entityManager.createNamedQuery(
				"findCoachByUsername", User.class);
		query.setParameter("username", username);

		User coach = query.getSingleResult();
		return coach;
	}

	@Override
	public User create(User coach) {
		this.entityManager.persist(coach);
        this.entityManager.flush();
        this.entityManager.refresh(coach);
        return coach;
	}

	@Override
	public User edit(User coach) {
		 return this.entityManager.merge(coach);
	}

	@Override
	public void remove(User coach) {
		this.entityManager.remove(this.entityManager.merge(coach));

	}

	@Override
	public User findById(Long id) {
		return this.entityManager.find(User.class, id);
	}

	@Override
	public List<User> findAll() {
		CriteriaQuery<User> cq = this.entityManager.getCriteriaBuilder().createQuery(User.class);
        cq.select(cq.from(User.class));
        return this.entityManager.createQuery(cq).getResultList();
	}
}
