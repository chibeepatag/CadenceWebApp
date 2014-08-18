/**
 * 
 */
package au.edu.cmu.dao;

import java.util.List;

import au.edu.cmu.model.MessageFromRider;
import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;

/**
 * DAO for MessagesFromRider entity
 * @author ChibeePatag
 *
 */
public interface MessageFromRiderDao extends BaseDao<MessageFromRider> {

	MessageFromRider getLatestMessageFromRider(Rider rider, Race race);
	
	List<MessageFromRider> getMessagesFromRiderForThisRace(Race race);
}
