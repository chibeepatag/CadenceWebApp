/**
 * 
 */
package au.edu.cmu.service;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.cmu.dao.RaceDao;
import au.edu.cmu.dao.RiderDao;
import au.edu.cmu.dao.StatisticsDao;
import au.edu.cmu.exceptions.RiderNotInRaceException;
import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;
import au.edu.cmu.model.Statistic;

/**
 * @author ChibeePatag
 *
 */
@Service
public class StatisticServiceImpl implements StatisticService{

	Logger logger = Logger.getLogger(StatisticServiceImpl.class);
	
	@Autowired
	StatisticsDao statisticsDao;
	
	@Autowired
	RiderDao riderDao;
	
	@Autowired
	RaceDao raceDao;
	
	public static Race currentRace;
	@Override
	public Rider saveStatistic(Statistic statistic, String nickname) throws RiderNotInRaceException{
		Race race = getCurrentRace();		
		Map<String, Rider> riders = race.getRiders();		
		Rider rider = riders.get(nickname);		
		if(null == rider){
			logger.info(String.format("%s who is not part of the race is sending data.", nickname));
			throw new RiderNotInRaceException(nickname);
		}
		statistic.setRider(rider);
		statisticsDao.create(statistic);
		return rider;
	}
	
	@Override
	public Race getCurrentRace(){
		if(null == currentRace){
			currentRace = raceDao.getCurrentRace();			
		}
		return currentRace;
	}

}
