/**
 * 
 */
package au.edu.cmu.dao;

import au.edu.cmu.model.Coach;

/**
 * @author ChibeePatag
 *
 */
public interface CoachDao {

	Coach getCoachByUsername(String username);
	
	void saveCoach(Coach coach);
	
	void updateCoach(Coach coach);
	
	void deleteCoach(Coach coach);
}
