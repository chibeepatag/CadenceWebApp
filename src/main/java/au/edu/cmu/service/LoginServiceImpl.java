/**
 * 
 */
package au.edu.cmu.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.cmu.dao.UserDao;
import au.edu.cmu.dao.RaceDao;
import au.edu.cmu.dao.RiderDao;
import au.edu.cmu.model.User;
import au.edu.cmu.model.Rider;

/**
 * @author ChibeePatag
 *
 */
@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	UserDao coachDao;
	
	@Autowired
	RiderDao riderDao;
	
	@Override
	public boolean login(User user) {
		boolean result = false;
		try{
			User user2 = coachDao.findByUsername(user.getUsername());
			if(user2.getPassword().equals(user.getPassword())){
				result = true;
			}			
		}catch (NoResultException nre){
			System.out.println("Coach does not exist");			
		}
		return result;		
	}
	
}
