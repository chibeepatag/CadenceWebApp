package au.edu.cmu.dao;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import au.edu.cmu.model.Message;
import au.edu.cmu.model.Rider;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dao-config.xml")
public class MessageDaoTest {

	@Autowired
	MessageDao messageDao;
	
	@Autowired
	RiderDao riderDao;
	
	@Test
	public void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	public void testEdit() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testFind() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		List<Message> messages = messageDao.findAll();
		for(Message message : messages){
			System.out.println(message.getMessage());
		}
	}
	
	@Test
	public void testGetLatestMessageForRider(){
		Rider rider = riderDao.findById(2L);
		Message message = messageDao.getLatestMessageForRider(rider);
		System.out.println(message.getMessage());
		assertNotNull(message);
	}

}
