/**
 * 
 */
package au.edu.cmu.service;

import au.edu.cmu.model.Message;
import au.edu.cmu.model.MessageFromRider;
import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;

/**
 * @author ChibeePatag
 *
 */
public interface MessageService {

	Message getMessageForRider(String nickname);

	void setMessageAsSent(Message message);
	
	void saveMessageFromRider(String nickname, String message);
	
	MessageFromRider getMessageFromRider(Rider rider, Race currentRace);
}
