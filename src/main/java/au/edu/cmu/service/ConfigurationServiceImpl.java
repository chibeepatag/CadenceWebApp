/**
 * 
 */
package au.edu.cmu.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.cmu.dao.RaceDao;
import au.edu.cmu.dao.RiderDao;
import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;

/**
 * @author ChibeePatag
 *
 */
@Service
public class ConfigurationServiceImpl implements ConfigurationService {

	@Autowired
	RiderDao riderDao;
	
	@Autowired
	RaceDao raceDao;
	/* (non-Javadoc)
	 * @see au.edu.cmu.service.ConfigurationService#addRider(au.edu.cmu.model.Rider)
	 */
	@Override
	public Rider addRider(Rider rider) {		
		return riderDao.create(rider);
	}
	
	@Override
	public void deleteRiders(List<Long> ids) {
		for(Long id: ids){
			Rider rider = riderDao.findById(id);
			riderDao.remove(rider);
		}		
	}
	
	@Override
	public Race createRace(String raceName, List<Long> ids) {
		List<Rider> riders = new ArrayList<Rider>();
		for(Long id : ids){
			Rider rider = riderDao.findById(id);
			riders.add(rider);
		}
		Race race = new Race();
		race.setRace_name(raceName);
		race.setRace_date(Calendar.getInstance().getTime());
		race.setRiders(riders);
		
		return raceDao.create(race);
	}

}
