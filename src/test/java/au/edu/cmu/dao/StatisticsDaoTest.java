package au.edu.cmu.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import au.edu.cmu.model.Rider;
import au.edu.cmu.model.Statistic;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dao-config.xml")
public class StatisticsDaoTest {
	
	@Autowired
	StatisticsDao statisticDao;
	
	@Autowired
	RiderDao riderDao;

	@Test
	public void testCreate() {
		Statistic statistic = new Statistic(12, 12, 12, 12, 12, 12, 12, 12, Calendar.getInstance().getTime());
		Rider rider = riderDao.findById(1L);
		statistic.setRider(rider);
		
		Statistic stat = statisticDao.create(statistic);
		assertNotNull(stat);
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
		fail("Not yet implemented");
	}

}
