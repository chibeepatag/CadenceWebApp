/**
 * 
 */
package au.edu.cmu.service;

import au.edu.cmu.model.Rider;
import au.edu.cmu.model.Statistic;

/**
 * @author ChibeePatag
 *
 */
public interface StatisticService {
	
	Rider saveStatistic(Statistic statistic, String nickname);

}
