package au.edu.cmu.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import au.edu.cmu.model.Coach;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dao-config.xml")
public class CoachDaoImplTest {
	
	@Autowired
	CoachDao coachDao;

	@Test
	public void testCreateCoach() {
		Coach coach = new Coach();
		coach.setUsername("Celine");
		coach.setPassword("Patag");
		coach.setPhone("+61490144588");
		coachDao.create(coach);
	}
	
	@Test
	public void testEditCoach(){
		
	}
	
	@Test
	public void testFindAllCoach(){
		List<Coach> coaches = coachDao.findAll();
		for(Coach coach : coaches){
			System.out.println(coach.getUsername());
		}
		assertEquals(5, coaches.size());
	}

}
