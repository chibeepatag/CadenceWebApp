/**
 * 
 */
package au.edu.cmu.service;

import java.io.OutputStream;
import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;

import au.edu.cmu.exceptions.CadencePersistenceException;
import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;
import au.edu.cmu.model.Statistic;

/**
 * Service layer for the Dashboard page
 * @author ChibeePatag
 *
 */
public interface DashboardService {

	List<Statistic> createStatisticTable(Race currentRace) throws CadencePersistenceException;
	
	List<Statistic> buildStatisticTable(Race currentRace) throws CadencePersistenceException;
	
	Statistic getLatestStatistic(Rider rider);
	
	Race getCurrentRace();
	
	Race startRace() throws Exception;
	
	Race endRace(Race currentRace);
	
	/**
	 * Saves the message sent to the riders.
	 * Sets the race to be the latest race 
	 * so that users can send messages even
	 * if the race has ended.
	 * @ModelAttribute("currentRace") Race currentRace
	 * @param recipientIds - the ids of the recipients
	 */
	void saveMessage(Race currentRace, String msgContent, List<Long> recipientIds);
	
	/**
	 * Saves the note to the database.
	 * Sets the race to be the latest race
	 * so notes can still be made after ending the race.
	 * @param noteTxt
	 */
	void saveNote(Race currentRace, String noteTxt);
	
	OutputStream createLogFile(Race currentRace, OutputStream out);
}
