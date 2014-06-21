/**
 * 
 */
package au.edu.cmu.service;

import java.util.List;

import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;

/**
 * @author ChibeePatag
 *
 */
public interface ConfigurationService {

	Rider addRider(Rider rider);
	
	void deleteRiders(List<Long> id);
	
	Race createRace(String raceName, List<Long> ids);
}
