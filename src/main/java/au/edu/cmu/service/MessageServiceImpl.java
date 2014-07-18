/**
 * 
 */
package au.edu.cmu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.cmu.dao.MessageDao;
import au.edu.cmu.dao.RaceDao;
import au.edu.cmu.model.Message;
import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;

/**
 * @author ChibeePatag
 *
 */
@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	RaceDao raceDao;
		
	@Autowired
	MessageDao messageDao;
	/* (non-Javadoc)
	 * @see au.edu.cmu.service.MessageService#getMessageForRider(java.lang.String)
	 */
	@Override
	public Message getMessageForRider(String nickname) {
		Race currentRace = raceDao.getCurrentRace();
		Rider rider = currentRace.getRiders().get(nickname);
		Message message = messageDao.getLatestMessageForRider(rider);
		return message;
	}
	
	@Override
	public void setMessageAsSent(Message message) {
		message.setSent(true);
		messageDao.edit(message);		
	}

}
