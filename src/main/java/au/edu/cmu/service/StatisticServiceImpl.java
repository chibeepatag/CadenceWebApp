/**
 * 
 */
package au.edu.cmu.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.cmu.dao.RaceDao;
import au.edu.cmu.dao.RiderDao;
import au.edu.cmu.dao.StatisticsDao;
import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;
import au.edu.cmu.model.Statistic;

/**
 * @author ChibeePatag
 *
 */
@Service
public class StatisticServiceImpl implements StatisticService{

	@Autowired
	StatisticsDao statisticsDao;
	
	@Autowired
	RiderDao riderDao;
	
	@Autowired
	RaceDao raceDao;
	
	@Override
	public void saveStatistic(Statistic statistic, String nickname) {
		Race race = raceDao.getCurrentRace();		
		Map<String, Rider> riders = race.getRiders();
		Rider rider = riders.get(nickname);		
		statistic.setRider(rider);
		statisticsDao.create(statistic);				
	}

}
