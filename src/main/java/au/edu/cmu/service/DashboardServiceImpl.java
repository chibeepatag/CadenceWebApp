/**
 * 
 */
package au.edu.cmu.service;

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

	@Override
	public List<Statistic> createStatisticTable(Race currentRace)
			throws CadencePersistenceException {
		try {
			Map<String, Rider> riderMap = currentRace.getRiders();
			List<Rider> riders = new ArrayList<Rider>(riderMap.values());
			List<Statistic> startStatistics = new ArrayList<Statistic>();
			for (Rider rider : riders) {							
				Statistic statistic = new Statistic();
				statistic.setRider(rider);				
				startStatistics.add(statistic);	
			}

			return startStatistics;

		} catch (CadencePersistenceException cpe) {
			throw cpe;
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see au.edu.cmu.service.DashboardService#getCurrentRiders()
	 */
	@Override
	public List<Statistic> buildStatisticTable(Race currentRace)
			throws CadencePersistenceException {
		try {
			Map<String, Rider> riderMap = currentRace.getRiders();
			List<Rider> riders = new ArrayList<Rider>(riderMap.values());
			List<Statistic> latestStatistics = new ArrayList<Statistic>();
			for (Rider rider : riders) {
				Statistic statistic = getLatestStatistic(rider);
				if(null==statistic){
					statistic = new Statistic();
					statistic.setRider(rider);
				}
				latestStatistics.add(statistic);	
			}

			return latestStatistics;

		} catch (CadencePersistenceException cpe) {
			throw cpe;
		}
	}

	@Override
	public Statistic getLatestStatistic(Rider rider) {
		return statisticsDao.getRiderLatestStatistic(rider);
	}
	
	@Override
	public Race startRace() throws Exception{
		Race currentRace = getCurrentRace();
		if(null == currentRace.getRace_start()){
			currentRace.setRace_start(Calendar.getInstance().getTime());			
			raceDao.edit(currentRace);
		}else{
			throw new Exception("Race has already started.");
		}
		return currentRace;
	}

	@Override
	public Race endRace(Race currentRace) {
		currentRace.setIsOngoing(false);
		currentRace.setRace_end(Calendar.getInstance().getTime());
		raceDao.edit(currentRace);
		return currentRace;
	}

	@Override
	public void saveMessage(Race currentRace, String msgContent, List<Long> recipientIds) {
		Message message = new Message();
		message.setMessage(msgContent);
		User coach = getCoach();
		message.setCoach(coach);
		message.setMessage_ts(Calendar.getInstance().getTime());
		
		message.setRace(currentRace);

		List<MessageRecipient> riderRecipients = new ArrayList<MessageRecipient>();
		for (Long id : recipientIds) {
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
	public void saveNote(Race currentRace, String noteTxt) {
		Note note = new Note();
		note.setNote(noteTxt);
		note.setCoach(getCoach());
		note.setMessage_ts(Calendar.getInstance().getTime());
		note.setRace(currentRace);
		noteDao.create(note);
	}

	private User getCoach() {
		UserDetails principal = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String username = principal.getUsername();

		User coach = userDao.findByUsername(username);
		return coach;
	}

	@Override
	public OutputStream createLogFile(Race currentRace, OutputStream out) {
		List<Log> logContent = getLogContent(currentRace);
		out = createLogPdf(currentRace, logContent, out);
		return out;
	}

	private List<Log> getLogContent(Race currentRace) {
		List<Log> logs = new ArrayList<Log>();
		logs.addAll(messageDao.getMessageForThisRace(currentRace));
		logs.addAll(messageFromRiderDao
				.getMessagesFromRiderForThisRace(currentRace));
		logs.addAll(noteDao.getNotesForThisRace(currentRace));
		Collections.sort(logs);

		return logs;
	}

	OutputStream createLogPdf(Race currentRace, List<Log> logContent, OutputStream outputStream) {
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		try {
			PdfWriter writer = PdfWriter.getInstance(document, outputStream);
			document.open();
			if(!logContent.isEmpty()){
				Paragraph raceTitle = new Paragraph();
				raceTitle.add("Event name: ");
				raceTitle.add(currentRace.getRace_name());
				document.add(raceTitle);
				for (Log log : logContent) {
					Paragraph paragraph = new Paragraph();					
					if (log instanceof Note) {
						paragraph.add("Note: ");
						paragraph.add(log.getMessage_ts().toString());
						paragraph.add(" ");
						paragraph.add(((Note) log).getNote());
					} else if (log instanceof Message) {
						paragraph.add("Message From coach: ");
						paragraph.add("Sent to ");
						
						for(MessageRecipient recipient : ((Message) log).getRecipients()){
							paragraph.add(recipient.getMessageRecipientId().getRider().getNickname());
							paragraph.add(" ");
						}			
						
						paragraph.add(log.getMessage_ts().toString());
						paragraph.add(" ");
						paragraph.add(((Message) log).getMessage());
					} else if (log instanceof MessageFromRider) {
						paragraph.add("Message from ");
						paragraph.add(((MessageFromRider) log).getFrom().getNickname());
						paragraph.add(": ");
						paragraph.add(log.getMessage_ts().toString());
						paragraph.add(" ");
						paragraph.add(((MessageFromRider) log).getMessage());
					}
					document.add(paragraph);
				}
			}else{
				Paragraph paragraph = new Paragraph();
				paragraph.add("No messages have been sent.");
				paragraph.add("No notes have been saved.");			
				document.add(paragraph);
			}
			document.close();
			outputStream.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return outputStream;
	}

}
