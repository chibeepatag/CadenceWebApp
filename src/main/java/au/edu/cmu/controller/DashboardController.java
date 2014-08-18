/**
 * 
 */
package au.edu.cmu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import au.edu.cmu.exceptions.CadencePersistenceException;
import au.edu.cmu.model.MessageFromRider;
import au.edu.cmu.model.Race;
import au.edu.cmu.model.Statistic;
import au.edu.cmu.service.DashboardService;
import au.edu.cmu.service.MessageService;
import au.edu.cmu.service.StatisticServiceImpl;

/**
 * Controller class for the dashboard
 * @author ChibeePatag
 *
 */
@Controller
@SessionAttributes("currentRace")
public class DashboardController {
	Logger logger = Logger.getLogger(DashboardController.class);
	
	@Autowired
	DashboardService dashboardService;
	
	@Autowired
	MessageService messageService;
	
	/**
	 * Controller method that opens the dashboard.
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/shared/dashboard", method=RequestMethod.GET)
	public String goToDashboard(Model model){
		try{
			Race currentRace = dashboardService.getCurrentRace();
			List<Statistic> statistics = dashboardService.createStatisticTable(currentRace);
			model.addAttribute("statistics", statistics);			
						
			model.addAttribute("currentRace", currentRace);
			return "shared/dashboard";
		}catch(CadencePersistenceException cpe){
			return "shared/NoOnGoingRace";
		}		
	}
	
	/**
	 * Controller method that starts the race.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/startRace", method=RequestMethod.GET)
	@ResponseBody
	public Race startRace(Model model){
		try{
			Race currentRace = dashboardService.startRace();			
			model.addAttribute("currentRace", currentRace);
			return currentRace;
		}catch (Exception e){
			logger.error(e.getMessage());
		}
		return null;
		
	}
	
	/**
	 * Controller method that refreshes the statistics 
	 * table in the dashboard.
	 * @param currentRace
	 * @return
	 */
	@RequestMapping(value="/shared/refreshStat", method=RequestMethod.GET)
	@ResponseBody
	public List<RefreshStatResponse> refreshDashboard(@ModelAttribute("currentRace") Race currentRace){
		List<Statistic> statistics = dashboardService.buildStatisticTable(currentRace);
		List<RefreshStatResponse> responseList = new ArrayList<RefreshStatResponse>();
		for(Statistic statistic : statistics){
			MessageFromRider message = messageService.getMessageFromRider(statistic.getRider(), currentRace);
			RefreshStatResponse resp;
			if(null != message && message.getMessage().length() > 0){
				resp = new RefreshStatResponse(statistic, message.getMessage(), message.getMsg_rider_id());				
			}else{
				resp = new RefreshStatResponse(statistic, "", 0);			
			}
			responseList.add(resp);
		}
		return responseList;
	}
	
	/**
	 * Controller method that ends a race.
	 * @param model
	 * @param currentRace
	 * @return
	 */
	@RequestMapping(value="/admin/endRace", method=RequestMethod.GET)
	public String endRace(Model model, @ModelAttribute("currentRace") Race currentRace){
		Race raceEnded = dashboardService.endRace(currentRace);
		StatisticServiceImpl.currentRace = null;
		model.addAttribute("raceEnded", raceEnded);
		return "shared/endedRace";
	}
	
	/**
	 * Controller method that saves messages sent to riders.
	 * @param currentRace
	 * @param message
	 * @param ids
	 */
	@RequestMapping(value="/admin/saveMsg", method=RequestMethod.POST)
	@ResponseBody
	public void saveMessage(@ModelAttribute("currentRace") Race currentRace, @RequestParam("message")String message, @RequestParam("recipientIds")List<Long> ids){					
		dashboardService.saveMessage(currentRace, message, ids);	
	}
	
	/**
	 * Controller method that saves notes entered by the coach
	 * @param currentRace
	 * @param note
	 */
	@RequestMapping(value="/admin/saveNote", method=RequestMethod.POST)
	@ResponseBody
	public void saveNote(@ModelAttribute("currentRace") Race currentRace, @RequestParam("note")String note){
		dashboardService.saveNote(currentRace, note);
	}
	
	/**
	 * This is the method that accepts log requests.
	 * @param currentRace
	 * @param response
	 */
	@RequestMapping(value="/shared/downloadLogs", method=RequestMethod.GET)
	public void downloadLogs(@ModelAttribute("currentRace") Race currentRace, HttpServletResponse response){
		try {
			response.setContentType("application/pdf");
			dashboardService.createLogFile(currentRace, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class RefreshStatResponse{
	private Statistic statistic;
	private String message;
	private long messageId;	
	
	public RefreshStatResponse(Statistic statistic,
			String message, long messageId) {
		super();
		this.statistic = statistic;
		this.message = message;
		this.messageId = messageId;
	}

	public Statistic getStatistic() {
		return statistic;
	}

	public String getMessage() {
		return message;
	}
	
	public long getMessageId() {
		return messageId;
	}
	
}
