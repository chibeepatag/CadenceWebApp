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

/**
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
	
	@RequestMapping(value="/shared/dashboard", method=RequestMethod.GET)
	public String goToDashboard(Model model){
		try{
			Race currentRace = dashboardService.getCurrentRace();
			List<Statistic> statistics = dashboardService.buildStatisticTable(currentRace);
			model.addAttribute("statistics", statistics);
			
						
			model.addAttribute("currentRace", currentRace);
			return "shared/dashboard";
		}catch(CadencePersistenceException cpe){
			return "shared/NoOnGoingRace";
		}
		
	}
	
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
	
	@RequestMapping(value="/admin/endRace", method=RequestMethod.GET)
	public String endRace(Model model, @ModelAttribute("currentRace") Race currentRace){
		Race raceEnded = dashboardService.endRace(currentRace);
		model.addAttribute("raceEnded", raceEnded);
		return "shared/endedRace";
	}
	
	@RequestMapping(value="/admin/saveMsg", method=RequestMethod.POST)
	@ResponseBody
	public void saveMessage(@RequestParam("message")String message, @RequestParam("recipientIds")List<Long> ids){					
		dashboardService.saveMessage(message, ids);	
	}
	
	@RequestMapping(value="/admin/saveNote", method=RequestMethod.POST)
	@ResponseBody
	public void saveNote(@RequestParam("note")String note){
		dashboardService.saveNote(note);
	}
	
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
