package au.edu.cmu.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import au.edu.cmu.model.Coach;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dao-config.xml")
public class CoachDaoImplTest {
	
	@Autowired
	CoachDao coachDao;

	@Test
	@Rollback(true)
	public void testCreateCoach() {
		Coach coach = new Coach();
		coach.setUsername("Celine");
		coach.setPassword("Patag");
		coach.setPhone("+61490144588");
		coachDao.create(coach);
	}
	
	@Test
	@Rollback(true)
	public void testEditCoach(){
		Coach coach = coachDao.find(1L);
		assertEquals("Chris", coach.getUsername());
		coach.setUsername("Christopher");
		coachDao.edit(coach);
		
		Coach coach2 = coachDao.find(1L);
		assertEquals("Christopher", coach2.getUsername());
	}
	
	@Test
	public void testFindAllCoach(){
		List<Coach> coaches = coachDao.findAll();
		for(Coach coach : coaches){
			System.out.println(coach.getUsername());
		}
		assertEquals(2, coaches.size());
	}
	
	@Test
	@Rollback(true)
	public void testRemoveCoach(){
		Coach coach = new Coach();
		coach.setUsername("Celine");
		coach.setPassword("Patag");
		coach.setPhone("+61490144588");
		coach = coachDao.create(coach);
		assertNotNull(coach);
		
		coachDao.remove(coach);
		
		Coach coach2 = coachDao.find(coach.getCoach_id());
		assertNull(coach2);
	}
	
	@Test
	public void testFindByUsername(){
		String username = "Chris";
		try{
			Coach coach = coachDao.findByUsername(username);
			assertEquals("Chris", coach.getUsername());
			assertEquals("+61490114575", coach.getPhone());
		}catch (NoResultException nre){
			fail();
		}
	}
	
	@Test 
	public void testFind(){
		Coach coach = coachDao.find(1L);
		assertNotNull(coach);
	}

}
