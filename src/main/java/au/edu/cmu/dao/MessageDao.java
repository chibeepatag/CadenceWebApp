/**
 * 
 */
package au.edu.cmu.dao;

import java.util.Date;
import java.util.List;

import au.edu.cmu.model.Message;

/**
 * @author ChibeePatag
 *
 */
public interface MessageDao {

	void SaveMessage(Message message);
	
	List<Message> getMessagesToRider(int rider_id, Date start, Date end);
}
