/**
 * 
 */
package au.edu.cmu.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.edu.cmu.model.Coach;

/**
 * @author ChibeePatag
 *
 */
@Repository
public class CoachDao extends BaseDao<Coach> {
		
	public CoachDao(){
		super(Coach.class);
	}
	
	public Coach findByUsername(String username) throws NoResultException{
		TypedQuery<Coach> query = this.entityManager.createNamedQuery("findCoachByUsername", Coach.class);
		query.setParameter("username", username);
		
		Coach coach = query.getSingleResult();
		return coach;	
	}
}
