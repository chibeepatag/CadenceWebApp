/**
 * 
 */
package au.edu.cmu.dao;

import java.util.List;

import au.edu.cmu.model.Rider;

/**
 * @author ChibeePatag
 *
 */
public interface RiderDao {

	List<Rider> getAllRiders();
	
	void saveRider(Rider rider);
	
	void updateRider(Rider rider);
}
