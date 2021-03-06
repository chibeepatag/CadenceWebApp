/**
 * 
 */
package au.edu.cmu.service;

import au.edu.cmu.exceptions.RiderNotInRaceException;
import au.edu.cmu.model.Message;
import au.edu.cmu.model.MessageFromRider;
import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;

/**
 * Service layer messages
 * @author ChibeePatag
 *
 */
public interface MessageService {

	Message getMessageForRider(String nickname);

	void setMessageAsSent(Message message, Rider rider);
	
	void saveMessageFromRider(String nickname, String message) throws RiderNotInRaceException;
	
	MessageFromRider getMessageFromRider(Rider rider, Race currentRace);
}
