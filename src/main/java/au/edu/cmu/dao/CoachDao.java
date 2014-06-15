/**
 * 
 */
package au.edu.cmu.dao;

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
	
}
