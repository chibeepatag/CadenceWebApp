/**
 * 
 */
package au.edu.cmu.service;

import java.util.Map;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.cmu.dao.RaceDao;
import au.edu.cmu.dao.RiderDao;
import au.edu.cmu.dao.StatisticsDao;
import au.edu.cmu.exceptions.NoStartedRaceException;
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
	public Rider saveStatistic(Statistic statistic, String nickname) throws RiderNotInRaceException, NoStartedRaceException{
		Race race = getStartedRace();		
		Map<String, Rider> riders = race.getRiders();		
		Rider rider = riders.get(nickname);		
		if(null == rider){
			logger.info(String.format("%s who is not part of the race is sending data.", nickname));
			throw new RiderNotInRaceException(nickname);
		}
		statistic.setRider(rider);
		if(null != race.getRace_start()){
			statisticsDao.create(statistic);			
		}else{
			throw new NoStartedRaceException();
		}
		return rider;
	}
	
	@Override
	public Race getStartedRace(){
		if(null == currentRace){
			try{
				currentRace = raceDao.getStartedRace();							
			}catch (NoResultException nre){
				currentRace = null;
			}
		}
		return currentRace;
	}

}
