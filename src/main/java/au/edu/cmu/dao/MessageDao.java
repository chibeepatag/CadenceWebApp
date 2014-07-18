/**
 * 
 */
package au.edu.cmu.dao;

import au.edu.cmu.model.Message;
import au.edu.cmu.model.Rider;

/**
 * @author ChibeePatag
 *
 */
public interface MessageDao extends BaseDao<Message> {

	Message getLatestMessageForRider(Rider rider);
}
