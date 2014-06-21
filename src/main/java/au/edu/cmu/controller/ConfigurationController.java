/**
 * 
 */
package au.edu.cmu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String addRider(Rider rider){
		Rider riderManaged = configService.addRider(rider);		
		return "rider managed";
	}
}
