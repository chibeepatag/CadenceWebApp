package au.edu.cmu.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import au.edu.cmu.model.MessageFromRider;
import au.edu.cmu.model.Rider;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dao-config.xml")
public class MessageFromRiderDaoTest {
	
	@Autowired
	MessageFromRiderDao msgFromRiderDao;
	
	@Autowired
	RiderDao riderDao;
	
	@Test
	public void testGetLatestMessageFromRider() {
		Rider rider = riderDao.findById(2L);
//		MessageFromRider msgFromRider = msgFromRiderDao.getLatestMessageFromRider(rider);
//		System.out.println(msgFromRider.getMessage());
//		assertNotNull(msgFromRider);		
	}

}
