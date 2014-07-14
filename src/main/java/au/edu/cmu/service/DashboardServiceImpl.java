/**
 * 
 */
package au.edu.cmu.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import au.edu.cmu.dao.MessageDao;
import au.edu.cmu.dao.NoteDao;
import au.edu.cmu.dao.RaceDao;
import au.edu.cmu.dao.RiderDao;
import au.edu.cmu.dao.StatisticsDao;
import au.edu.cmu.dao.UserDao;
import au.edu.cmu.exceptions.CadencePersistenceException;
import au.edu.cmu.model.Message;
import au.edu.cmu.model.Note;
import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;
import au.edu.cmu.model.Statistic;
import au.edu.cmu.model.User;

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
		List<Rider> riderRecipients = new ArrayList<Rider>();
		for(Long id : recipientIds){
			Rider rider = riderDao.findById(id);
			riderRecipients.add(rider);
		}
		User coach = getCoach();
		
		Race race = raceDao.getLatestRace();
		
		Message message = new Message();
		message.setMessage(msgContent);
		message.setRecipients(riderRecipients);
		message.setCoach(coach);
		message.setMessage_ts(Calendar.getInstance().getTime());
		message.setRace(race);
		messageDao.create(message);					
	}	
	
	@Override
	public void saveNote(String noteTxt) {		
		Note note = new Note();
		note.setNote(noteTxt);
		note.setCoach(getCoach());
		note.setNote_ts(Calendar.getInstance().getTime());
		note.setRace(raceDao.getLatestRace());
		noteDao.create(note);
	}

	private User getCoach() {
		UserDetails principal = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = principal.getUsername();		
		
		User coach = userDao.findByUsername(username);
		return coach;
	}

}
