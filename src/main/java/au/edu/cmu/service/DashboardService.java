/**
 * 
 */
package au.edu.cmu.service;

import java.util.List;

import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;
import au.edu.cmu.model.Statistic;

/**
 * @author ChibeePatag
 *
 */
public interface DashboardService {

	List<Statistic> buildStatisticTable();
	
	Statistic getLatestStatistic(Rider rider);
	
	Race endRace();
}
