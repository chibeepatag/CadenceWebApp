/**
 * 
 */
package au.edu.cmu.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.cmu.dao.CoachDao;
import au.edu.cmu.dao.RiderDao;
import au.edu.cmu.model.Coach;
import au.edu.cmu.model.Rider;

/**
 * @author ChibeePatag
 *
 */
@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	CoachDao coachDao;
	
	@Autowired
	RiderDao riderDao;
	
	@Override
	public boolean login(Coach coach) {
		boolean result = false;
		try{
			Coach coach2 = coachDao.findByUsername(coach.getUsername());
			if(coach2.getPassword().equals(coach.getPassword())){
				result = true;
			}			
		}catch (NoResultException nre){
			System.out.println("Coach does not exist");			
		}
		return result;		
	}

	@Override
	public List<Rider> getAllRiders() {
		return riderDao.findAll();		
	}
}
