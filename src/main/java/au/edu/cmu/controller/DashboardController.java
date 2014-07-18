/**
 * 
 */
package au.edu.cmu.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class DashboardController {
	Logger logger = Logger.getLogger(DashboardController.class);
	
	@Autowired
	DashboardService dashboardService;
	
	@Autowired
	MessageService messageService;
	
	@RequestMapping(value="/shared/dashboard", method=RequestMethod.GET)
	public String goToDashboard(Model model){
		try{
			List<Statistic> statistics = dashboardService.buildStatisticTable();
			model.addAttribute("statistics", statistics);
			
			Race currentRace = dashboardService.getCurrentRace();
			model.addAttribute("currentRace", currentRace);
			return "shared/dashboard";
		}catch(CadencePersistenceException cpe){
			return "shared/NoOnGoingRace";
		}
		
	}
	
	@RequestMapping(value="/shared/refreshStat", method=RequestMethod.GET)
	@ResponseBody
	public List<RefreshStatResponse> refreshDashboard(){
		List<Statistic> statistics = dashboardService.buildStatisticTable();
		List<RefreshStatResponse> responseList = new ArrayList<RefreshStatResponse>();
		for(Statistic statistic : statistics){
			MessageFromRider message = messageService.getMessageFromRider(statistic.getRider());
			RefreshStatResponse resp;
			if(null != message){
				resp = new RefreshStatResponse(statistic, message.getMessage(), message.getMsg_rider_id());				
			}else{
				resp = new RefreshStatResponse(statistic, "", 0);			
			}
			responseList.add(resp);
		}
		return responseList;
	}
	
	@RequestMapping(value="/shared/endRace", method=RequestMethod.GET)
	public String endRace(Model model){
		Race raceEnded = dashboardService.endRace();
		model.addAttribute("raceEnded", raceEnded);
		return "shared/endedRace";
	}
	
	@RequestMapping(value="/shared/saveMsg", method=RequestMethod.POST)
	@ResponseBody
	public void saveMessage(@RequestParam("message")String message, @RequestParam("recipientIds")List<Long> ids){					
		dashboardService.saveMessage(message, ids);	
	}
	
	@RequestMapping(value="/shared/saveNote", method=RequestMethod.POST)
	@ResponseBody
	public void saveNote(@RequestParam("note")String note){
		dashboardService.saveNote(note);
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
