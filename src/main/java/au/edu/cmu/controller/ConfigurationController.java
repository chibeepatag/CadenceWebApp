/**
 * 
 */
package au.edu.cmu.controller;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import au.edu.cmu.exceptions.OnGoingRaceException;
import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;
import au.edu.cmu.model.User;
import au.edu.cmu.service.ConfigurationService;

/**
 * @author ChibeePatag
 *
 */
@Controller
public class ConfigurationController {

	static Logger logger = Logger.getLogger(ConfigurationController.class);
	
	@Autowired
	ConfigurationService configService;
	
	@RequestMapping(value="/admin/addRider", method=RequestMethod.POST)
	@ResponseBody
	public Rider addRider(Rider rider){
		Rider riderManaged = configService.addRider(rider);		
		return riderManaged;
	}
	
	@RequestMapping(value="/admin/editRider", method=RequestMethod.POST)
	@ResponseBody
	public Rider editRider(Rider rider){
		Rider riderManaged = configService.editRider(rider);		
		return riderManaged;
	}
	
	@RequestMapping(value="/admin/deleteRiders", method=RequestMethod.POST)
	@ResponseBody
	public void deleteRiders(@RequestParam("ids")List<Long> ids){
		configService.deleteRiders(ids);
	}
	
	@RequestMapping(value="/admin/createRace", method=RequestMethod.POST)
	@ResponseBody
	public Race createRace(@RequestParam("ids")List<Long> ids, @RequestParam("raceName")String raceName, HttpServletResponse response){
		Race race = null;
		try{
			race = configService.createRace(raceName, ids);
			
		}catch (OnGoingRaceException onGoingRaceException){
			logger.info(onGoingRaceException.getMessage());
		}catch(PersistenceException e){
			logger.info(e.getMessage());
			e.printStackTrace();
			System.out.println("Errrrrroooorrrr");
//			response.sendError(500, e.getMessage());
		} 
		return race;
	}
	
	@RequestMapping(value = "/admin/configuration", method = RequestMethod.GET)
	public String configuration(Model model) {

			boolean isRaceOngoing = configService.isThereAnOngoingRace();
			if (isRaceOngoing) {
				return "redirect:../shared/dashboard";
			}

			List<Rider> allRiders = configService.getAllRiders();
			model.addAttribute("riders", allRiders);
			return "admin/configuration";		
	}
	
	@ModelAttribute("rider")
	public Rider getRider() {
		return new Rider();
	}
	
}
