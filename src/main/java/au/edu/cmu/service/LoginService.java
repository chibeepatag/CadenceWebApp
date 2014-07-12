/**
 * 
 */
package au.edu.cmu.service;

import java.util.List;

import au.edu.cmu.model.Coach;
import au.edu.cmu.model.Rider;

/**
 * @author ChibeePatag
 *
 */
public interface LoginService {

	boolean login(Coach coach);
	
	List<Rider> getAllRiders();
	
	boolean isThereAnOngoingRace();
}
