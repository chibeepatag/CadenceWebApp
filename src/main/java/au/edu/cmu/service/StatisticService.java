/**
 * 
 */
package au.edu.cmu.service;

import au.edu.cmu.exceptions.NoStartedRaceException;
import au.edu.cmu.exceptions.RiderNotInRaceException;
import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;
import au.edu.cmu.model.Statistic;

/**
 * Service layer for statistics from the rider
 * @author ChibeePatag
 *
 */
public interface StatisticService {
	
	Rider saveStatistic(Statistic statistic, String nickname) throws RiderNotInRaceException, NoStartedRaceException;
	
	Race getStartedRace();

}
