/**
 * 
 */
package au.edu.cmu.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.cmu.dao.RaceDao;
import au.edu.cmu.dao.StatisticsDao;
import au.edu.cmu.exceptions.CadencePersistenceException;
import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;
import au.edu.cmu.model.Statistic;

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

}
