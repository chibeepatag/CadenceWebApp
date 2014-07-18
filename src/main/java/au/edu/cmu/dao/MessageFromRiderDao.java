/**
 * 
 */
package au.edu.cmu.dao;

import au.edu.cmu.model.MessageFromRider;
import au.edu.cmu.model.Rider;

/**
 * @author ChibeePatag
 *
 */
public interface MessageFromRiderDao extends BaseDao<MessageFromRider> {

	MessageFromRider getLatestMessageFromRider(Rider rider);
}
