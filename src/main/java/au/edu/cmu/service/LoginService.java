/**
 * 
 */
package au.edu.cmu.service;

import java.util.List;

import au.edu.cmu.model.User;
import au.edu.cmu.model.Rider;

/**
 * @author ChibeePatag
 *
 */
public interface LoginService {

	boolean login(User user);
	
	List<Rider> getAllRiders();
	
	boolean isThereAnOngoingRace();
}
