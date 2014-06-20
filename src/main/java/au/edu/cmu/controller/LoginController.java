/**
 * 
 */
package au.edu.cmu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import au.edu.cmu.model.Coach;
import au.edu.cmu.service.LoginService;

/**
 * @author ChibeePatag
 *
 */
@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@ModelAttribute("coach")
	public Coach getCoach(){
		return new Coach();
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(){		
		return "login";
	}
	
	@RequestMapping(value="/dashboard", method=RequestMethod.POST)
	public String dashboard(Coach coach){
		boolean result = loginService.login(coach);
		if(result){
			return "dashboard";			
		}else{
			return "login";
		}
	}
}
