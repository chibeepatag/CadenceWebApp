/**
 * 
 */
package au.edu.cmu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.edu.cmu.model.User;
import au.edu.cmu.model.Rider;
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
	public User getCoach(){
		return new User();
	}
	
	@ModelAttribute("rider")
	public Rider getRider(){
		return new Rider();
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(){		
		return "login";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(){		
		return "logout";
	}
	
	@RequestMapping(value="/configuration", method=RequestMethod.POST)
	public String configuration(User coach, Model model, BindingResult bindingResult){
		boolean result = loginService.login(coach);
		if(result){
			
			boolean isRaceOngoing = loginService.isThereAnOngoingRace();
			if(isRaceOngoing){
				return "redirect:dashboard";
			}						
			
			List<Rider> allRiders = loginService.getAllRiders();
			model.addAttribute("riders", allRiders);
			return "configuration";			
		}else{		
			bindingResult.addError(new ObjectError("invalid login", "Invalid username and password"));
			return "login";
		}
	}
}
