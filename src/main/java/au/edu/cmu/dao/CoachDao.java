/**
 * 
 */
package au.edu.cmu.dao;

import javax.persistence.NoResultException;

import au.edu.cmu.model.Coach;

/**
 * @author ChibeePatag
 *
 */
public interface CoachDao extends BaseDao<Coach>{	
	
	Coach findByUsername(String username) throws NoResultException;	
	
}
