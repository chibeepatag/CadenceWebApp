package au.edu.cmu.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import au.edu.cmu.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dao-config.xml")
public class UserDaoImplTest {
	
	@Autowired
	UserDao userDao;

	@Test
	@Rollback(true)
	public void testCreateCoach() {
		User coach = new User();
		coach.setUsername("Chibee");
		coach.setPassword("Patag");
		coach.setPhone("+61490144588");
		userDao.create(coach);
	}
	
	@Test
	public void testFindAllCoach(){
		List<User> coaches = userDao.findAll();
		for(User coach : coaches){
			System.out.println(coach.getUsername());
		}
		assertEquals(2, coaches.size());
	}
	
	@Test
	@Rollback(true)
	public void testRemoveCoach(){
		User coach = new User();
		coach.setUsername("Celine");
		coach.setPassword("Patag");
		coach.setPhone("+61490144588");
		coach = userDao.create(coach);
		assertNotNull(coach);
		
		userDao.remove(coach);
		
		User coach2 = userDao.findById(coach.getUser_id());
		assertNull(coach2);
	}
	
	@Test
	public void testFindByUsername(){
		String username = "Chris";
		try{
			User coach = userDao.findByUsername(username);
			assertEquals("Chris", coach.getUsername());
			assertEquals("+61490114575", coach.getPhone());
		}catch (NoResultException nre){
			fail();
		}
	}
	
	@Test 
	public void testFind(){
		User coach = userDao.findById(1L);
		assertNotNull(coach);
	}

}
