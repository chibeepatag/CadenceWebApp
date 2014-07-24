/**
 * 
 */
package au.edu.cmu.service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.cmu.dao.MessageDao;
import au.edu.cmu.dao.MessageFromRiderDao;
import au.edu.cmu.dao.RaceDao;
import au.edu.cmu.model.Message;
import au.edu.cmu.model.MessageFromRider;
import au.edu.cmu.model.MessageRecipient;
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
	
	@Autowired
	MessageFromRiderDao messageFromRiderDao;
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
		List<MessageRecipient> recipients = message.getRecipients();
		for(MessageRecipient msgRec : recipients){
			msgRec.setSent(true);
		}
		messageDao.edit(message);		
	}
	
	@Override
	public void saveMessageFromRider(String nickname, String message) {
		Race race = raceDao.getCurrentRace();		
		Map<String, Rider> riders = race.getRiders();
		Rider rider = riders.get(nickname);	
		
		MessageFromRider msgFromRider = new MessageFromRider();
		msgFromRider.setRace(race);
		msgFromRider.setMessage(message);
		msgFromRider.setFrom(rider);
		msgFromRider.setMessage_ts(Calendar.getInstance().getTime());		
		
		messageFromRiderDao.create(msgFromRider);
	}
	
	@Override
	public MessageFromRider getMessageFromRider(Rider rider, Race currentRace) {
		return messageFromRiderDao.getLatestMessageFromRider(rider, currentRace);
	}
}
