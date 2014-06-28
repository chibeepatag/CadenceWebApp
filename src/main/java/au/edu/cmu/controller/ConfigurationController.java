/**
 * 
 */
package au.edu.cmu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import au.edu.cmu.exceptions.OnGoingRaceException;
import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;
import au.edu.cmu.service.ConfigurationService;

/**
 * @author ChibeePatag
 *
 */
@Controller
public class ConfigurationController {

	@Autowired
	ConfigurationService configService;
	
	@RequestMapping(value="/addRider", method=RequestMethod.POST)
	@ResponseBody
	public Rider addRider(Rider rider){
		Rider riderManaged = configService.addRider(rider);		
		return riderManaged;
	}
	
	@RequestMapping(value="/deleteRiders", method=RequestMethod.POST)
	@ResponseBody
	public void deleteRiders(@RequestParam("ids")List<Long> ids){
		configService.deleteRiders(ids);
	}
	
	@RequestMapping(value="/createRace", method=RequestMethod.POST)
	@ResponseBody
	public Race createRace(@RequestParam("ids")List<Long> ids, @RequestParam("raceName")String raceName){
		Race race = null;
		try{
			race = configService.createRace(raceName, ids);
			
		}catch (OnGoingRaceException onGoingRaceException){
			//bindingResult.addError(new ObjectError("Race is on going", onGoingRaceException.getMessage()));
			System.out.println(onGoingRaceException.getMessage());
		}catch(Exception e){
			e.printStackTrace();
		} 
		return race;
	}
	
	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public String goToDashboard(){
		System.out.println("go to dashboard");
		return "dashboard";
	}
	
	@RequestMapping(value="/TestServlet", method=RequestMethod.GET)
	@ResponseBody
	public String testServlet(@RequestParam("longitude")String longitude, @RequestParam("latitude")String latitude, @RequestParam("speed")String speed, @RequestParam("rider")String rider){
		System.out.println(longitude);
		System.out.println(latitude);
		System.out.println(speed);
		System.out.println(rider);
		return "Success";
	}
}
