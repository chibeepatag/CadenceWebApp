package au.edu.cmu.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import au.edu.cmu.model.Coach;

@ContextConfiguration("")
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
