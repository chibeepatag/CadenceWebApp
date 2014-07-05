/**
 * 
 */
package au.edu.cmu.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.cmu.dao.RaceDao;
import au.edu.cmu.dao.RiderDao;
import au.edu.cmu.exceptions.CadencePersistenceException;
import au.edu.cmu.exceptions.OnGoingRaceException;
import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;

/**
 * @author ChibeePatag
 *
 */
@Service
public class ConfigurationServiceImpl implements ConfigurationService {
	
	static Logger logger = Logger.getLogger(ConfigurationService.class);
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
	public Rider editRider(Rider rider) {		
		return riderDao.edit(rider);
	}
	
	@Override
	public void deleteRiders(List<Long> ids) {
		for(Long id: ids){
			Rider rider = riderDao.findById(id);
			riderDao.remove(rider);
		}		
	}
	
	@Override
	public Race createRace(String raceName, List<Long> ids) throws OnGoingRaceException, PersistenceException {
		Race currentRace;
		try{
			currentRace = raceDao.getCurrentRace();			
			throw new OnGoingRaceException();
		}catch(NoResultException nre){
			logger.info("No race is in session.");
		}				
		
		Map<String, Rider> riders = new HashMap<String, Rider>();
		for(Long id : ids){
			Rider rider = riderDao.findById(id);
			riders.put(rider.getJersey_no(), rider);
		}
		Race race = new Race();
		race.setRace_name(raceName);
		race.setRace_start(Calendar.getInstance().getTime());
		race.setRiders(riders);
		race.setIsOngoing(true);		
		
		Race createdRace = null;
		try{
			createdRace = raceDao.create(race);
		}catch(PersistenceException pe){			
			if (pe.getCause() instanceof ConstraintViolationException){
				throw new CadencePersistenceException(pe, "Race name exists.");				
			}else{
				throw pe;
			}
		}
		
		
		return createdRace;
	}

}
