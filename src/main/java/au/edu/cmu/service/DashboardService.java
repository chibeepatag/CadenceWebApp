/**
 * 
 */
package au.edu.cmu.service;

import java.security.Principal;
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

	List<Statistic> buildStatisticTable() throws CadencePersistenceException;
	
	Statistic getLatestStatistic(Rider rider);
	
	Race getCurrentRace();
	
	Race endRace();
	
	void saveMessage(String msgContent, List<Long> recipientIds, Principal user);
}
