package au.edu.cmu.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import au.edu.cmu.model.Coach;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dao-config.xml")
public class LoginServiceTest {
	
	@Autowired
	LoginService loginService;

	@Test
	public void testLogin() {
		String username = "Christopher";
		String password = "Chris";
		Coach coach = new Coach();
		coach.setUsername(username);
		coach.setPassword(password);
		boolean result = loginService.login(coach);
		
		assertTrue(result);				
	}

}
