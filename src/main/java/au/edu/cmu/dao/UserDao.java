/**
 * 
 */
package au.edu.cmu.dao;

import javax.persistence.NoResultException;

import au.edu.cmu.model.User;

/**
 * @author ChibeePatag
 *
 */
public interface UserDao extends BaseDao<User>{	
	
	User findByUsername(String username) throws NoResultException;	
	
}
