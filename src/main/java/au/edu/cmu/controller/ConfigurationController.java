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
 * Controller class for the configuration page.
 * @author ChibeePatag
 *
 */
@Controller
public class ConfigurationController {

	static Logger logger = Logger.getLogger(ConfigurationController.class);
	
	@Autowired
	ConfigurationService configService;
	
	/**
	 * Method that accepts add rider requests.
	 * @param rider
	 * @return
	 */
	@RequestMapping(value="/admin/addRider", method=RequestMethod.POST)
	@ResponseBody
	public Rider addRider(Rider rider){
		Rider riderManaged = configService.addRider(rider);		
		return riderManaged;
	}
	
	/**
	 * Method that accepts edit rider requests.
	 * @param rider
	 * @return
	 */
	@RequestMapping(value="/admin/editRider", method=RequestMethod.POST)
	@ResponseBody
	public Rider editRider(Rider rider){
		Rider riderManaged = configService.editRider(rider);		
		return riderManaged;
	}
	
	/**
	 * Method that accepts delete rider requests.
	 * @param ids - Id of riders to delete
	 */
	@RequestMapping(value="/admin/deleteRiders", method=RequestMethod.POST)
	@ResponseBody
	public void deleteRiders(@RequestParam("ids")List<Long> ids){
		configService.deleteRiders(ids);
	}
	
	/**
	 * Method that accepts create new race requests.
	 * @param ids - Ids of the riders of the race
	 * @param raceName - Name of the event
	 * @param response
	 * @return
	 */
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
		} 
		return race;
	}
	
	/**
	 * Controller method that opens the configuration page.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/configuration", method = RequestMethod.GET)
	public String configuration(Model model) {
			logger.info("Configuration page");
			boolean isRaceOngoing = configService.isThereAnOngoingRace();
			if (isRaceOngoing) {
				return "redirect:../shared/dashboard";
			}

			return buildConfigPage(model);		
	}
	
	/**
	 * Controller method that opens the configuration page from the dashboard.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/reconfigure", method = RequestMethod.GET)
	public String reconfiguration(Model model) {			
			return buildConfigPage(model);
	}
	
	private String buildConfigPage(Model model) {
		List<Rider> allRiders = configService.getAllRiders();
		model.addAttribute("riders", allRiders);
		List<Rider> latestTeam = configService.getLastestTeam();
		model.addAttribute("latestTeam", latestTeam);
		return "admin/configuration";
	}
	
	@ModelAttribute("rider")
	public Rider getRider() {
		return new Rider();
	}
	
	
	
}
