/**
 * 
 */
package au.edu.cmu.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import au.edu.cmu.dao.MessageDao;
import au.edu.cmu.dao.MessageFromRiderDao;
import au.edu.cmu.dao.NoteDao;
import au.edu.cmu.dao.RaceDao;
import au.edu.cmu.dao.RiderDao;
import au.edu.cmu.dao.StatisticsDao;
import au.edu.cmu.dao.UserDao;
import au.edu.cmu.exceptions.CadencePersistenceException;
import au.edu.cmu.model.Log;
import au.edu.cmu.model.Message;
import au.edu.cmu.model.MessageFromRider;
import au.edu.cmu.model.MessageRecipient;
import au.edu.cmu.model.MessageRecipientId;
import au.edu.cmu.model.Note;
import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;
import au.edu.cmu.model.Statistic;
import au.edu.cmu.model.User;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author ChibeePatag
 *
 */
@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	RaceDao raceDao;
	
	@Autowired
	StatisticsDao statisticsDao;
	
	@Autowired
	MessageDao messageDao;
	
	@Autowired
	MessageFromRiderDao messageFromRiderDao;
	
	@Autowired
	RiderDao riderDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	NoteDao noteDao;
	
	@Override
	public Race getCurrentRace() {		
		return raceDao.getCurrentRace();
	}
	/* (non-Javadoc)
	 * @see au.edu.cmu.service.DashboardService#getCurrentRiders()
	 */
	@Override
	public List<Statistic> buildStatisticTable() throws CadencePersistenceException {
		try{
			Map<String, Rider> riderMap = raceDao.getCurrentRace().getRiders();
			List<Rider> riders = new ArrayList<Rider>(riderMap.values());
			List<Statistic> latestStatistics = new ArrayList<Statistic>();
			for(Rider rider : riders){
				Statistic statistic = getLatestStatistic(rider);
				latestStatistics.add(statistic);
			}
							
			return latestStatistics;
			
		}catch(CadencePersistenceException cpe){
			throw cpe;
		}
		
	}
	
	@Override
	public Statistic getLatestStatistic(Rider rider){		
		return statisticsDao.getRiderLatestStatistic(rider);
	}
	
	@Override
	public Race endRace() {	
		Race race = getCurrentRace();
		race.setIsOngoing(false);
		race.setRace_end(Calendar.getInstance().getTime());
		raceDao.edit(race);
		return race;
	}
	
	
	@Override
	public void saveMessage(String msgContent, List<Long> recipientIds) {
		Message message = new Message();
		message.setMessage(msgContent);
		User coach = getCoach();
		message.setCoach(coach);
		message.setMessage_ts(Calendar.getInstance().getTime());
		Race race = raceDao.getLatestRace();
		message.setRace(race);
		
		List<MessageRecipient> riderRecipients = new ArrayList<MessageRecipient>();
		for(Long id : recipientIds){
			Rider rider = riderDao.findById(id);
			MessageRecipient recipient = new MessageRecipient();
			MessageRecipientId messageRecipientId = new MessageRecipientId();
			messageRecipientId.setRider(rider);
			messageRecipientId.setMessage(message);
			recipient.setSent(false);
			recipient.setMessageRecipientId(messageRecipientId);
			riderRecipients.add(recipient);
		}
		message.setRecipients(riderRecipients);
		messageDao.create(message);					
	}	
	
	@Override
	public void saveNote(String noteTxt) {		
		Note note = new Note();
		note.setNote(noteTxt);
		note.setCoach(getCoach());
		note.setMessage_ts(Calendar.getInstance().getTime());
		note.setRace(raceDao.getLatestRace());
		noteDao.create(note);
	}

	private User getCoach() {
		UserDetails principal = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = principal.getUsername();		
		
		User coach = userDao.findByUsername(username);
		return coach;
	}
	
	@Override
	public OutputStream createLogFile(Race currentRace, OutputStream out) {
		List<Log> logContent = getLogContent(currentRace);
		out  = createLogPdf(logContent, out);
		return out;
	}
	private List<Log> getLogContent(Race currentRace) {
		List<Log> logs = new ArrayList<Log>();
		logs.addAll(messageDao.getMessageForThisRace(currentRace));
		logs.addAll(messageFromRiderDao.getMessagesFromRiderForThisRace(currentRace));
		logs.addAll(noteDao.getNotesForThisRider(currentRace));
		Collections.sort(logs);

		return logs;
	}
	
	OutputStream createLogPdf(List<Log> logContent, OutputStream outputStream){
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		try {
			PdfWriter writer = PdfWriter.getInstance(document, outputStream);
			document.open();
			for(Log log : logContent){
				Paragraph paragraph = new Paragraph();
				if(log instanceof Note){
					paragraph.add("Note: ");
					paragraph.add(log.getMessage_ts());
					paragraph.add(((Note) log).getNote());
				}else if(log instanceof Message){
					paragraph.add("Message From coach: ");
					paragraph.add(log.getMessage_ts());
					paragraph.add(((Message) log).getMessage());
				}else if (log instanceof MessageFromRider){
					paragraph.add("Message From coach: ");
					paragraph.add(log.getMessage_ts());
					paragraph.add(((MessageFromRider) log).getMessage());
				}
				document.add(paragraph);
				document.close();
				outputStream.close();
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}

		return outputStream;
	}

}
