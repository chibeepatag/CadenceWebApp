package au.edu.cmu.dao;

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
	public void testSaveCoach() {
		Coach coach = new Coach();
		coach.setUsername("Celine");
		coach.setPassword("Patag");
		coach.setPhone("+61490144588");
		coachDao.saveCoach(coach);
	}

}
