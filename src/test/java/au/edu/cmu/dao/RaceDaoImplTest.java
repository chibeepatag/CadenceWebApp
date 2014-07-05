package au.edu.cmu.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dao-config.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class RaceDaoImplTest {

	@Autowired
	RaceDao raceDao;
	
	@Test
	@Rollback(true)
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
	public void testFindById() {
		Race race = raceDao.findById(1L);
		assertNotNull(race);
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetCurrentRace(){
		Race race = raceDao.getCurrentRace();
		Map<String, Rider> riders = race.getRiders();
		assertNotNull(riders.get("Nelson"));
		assertNotNull(race);
	}
	
	@Test
	@Rollback(true)
	public void testEndRace(){
		Race race = raceDao.getCurrentRace();
		assertTrue(race.isOngoing());
		
		race = raceDao.endRace();
		assertFalse(race.isOngoing());
	}

}
