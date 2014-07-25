/**
 * 
 */
package au.edu.cmu.service;

import java.io.OutputStream;
import java.util.List;

import au.edu.cmu.exceptions.CadencePersistenceException;
import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;
import au.edu.cmu.model.Statistic;

/**
 * @author ChibeePatag
 *
 */
public interface DashboardService {

	List<Statistic> buildStatisticTable(Race currentRace) throws CadencePersistenceException;
	
	Statistic getLatestStatistic(Rider rider);
	
	Race getCurrentRace();
	
	Race endRace(Race currentRace);
	
	/**
	 * Saves the message sent to the riders.
	 * Sets the race to be the latest race 
	 * so that users can send messages even
	 * if the race has ended.
	 * @param msgContent - the message
	 * @param recipientIds - the ids of the recipients
	 */
	void saveMessage(String msgContent, List<Long> recipientIds);
	
	/**
	 * Saves the note to the database.
	 * Sets the race to be the latest race
	 * so notes can still be made after ending the race.
	 * @param noteTxt
	 */
	void saveNote(String noteTxt);
	
	OutputStream createLogFile(Race currentRace, OutputStream out);
}
