/**
 * 
 */
package au.edu.cmu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.cmu.dao.RiderDao;
import au.edu.cmu.model.Rider;

/**
 * @author ChibeePatag
 *
 */
@Service
public class ConfigurationServiceImpl implements ConfigurationService {

	@Autowired
	RiderDao riderDao;
	/* (non-Javadoc)
	 * @see au.edu.cmu.service.ConfigurationService#addRider(au.edu.cmu.model.Rider)
	 */
	@Override
	public Rider addRider(Rider rider) {		
		return riderDao.create(rider);
	}

}
