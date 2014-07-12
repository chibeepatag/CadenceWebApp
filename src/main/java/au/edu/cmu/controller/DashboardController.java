/**
 * 
 */
package au.edu.cmu.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;
import au.edu.cmu.model.Statistic;
import au.edu.cmu.service.DashboardService;

/**
 * @author ChibeePatag
 *
 */
@Controller
public class DashboardController {
	Logger logger = Logger.getLogger(DashboardController.class);
	
	@Autowired
	DashboardService dashboardService;
	
	@RequestMapping(value="/shared/dashboard", method=RequestMethod.GET)
	public String goToDashboard(Model model){
		List<Statistic> statistics = dashboardService.buildStatisticTable();
		model.addAttribute("statistics", statistics);
		
		Race currentRace = dashboardService.getCurrentRace();
		model.addAttribute("currentRace", currentRace);
		return "shared/dashboard";
	}
	
	@RequestMapping(value="/shared/refreshStat", method=RequestMethod.GET)
	public String refreshDashboard(Model model){
		List<Statistic> statistics = dashboardService.buildStatisticTable();
		model.addAttribute("statistics", statistics);
		return "shared/statisticTable";
	}
	
	@RequestMapping(value="/shared/endRace", method=RequestMethod.GET)
	public String endRace(Model model){
		Race raceEnded = dashboardService.endRace();
		model.addAttribute("raceEnded", raceEnded);
		return "shared/endedRace";
	}

}
