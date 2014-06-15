/**
 * 
 */
package au.edu.cmu.dao;

import org.springframework.stereotype.Repository;

import au.edu.cmu.model.Rider;

/**
 * @author ChibeePatag
 *
 */
@Repository
public class RiderDao extends BaseDao<Rider> {

	public RiderDao() {
		super(Rider.class);
	}
}
