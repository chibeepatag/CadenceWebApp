/**
 * 
 */
package au.edu.cmu.controller;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import au.edu.cmu.model.Message;
import au.edu.cmu.model.Statistic;
import au.edu.cmu.service.MessageService;
import au.edu.cmu.service.StatisticService;

/**
 * @author ChibeePatag
 *
 */
@Controller
public class DataReceiverController {
	
	static Logger logger = Logger.getLogger(DataReceiverController.class);
	
	@Autowired
	StatisticService statisticService;
	
	@Autowired
	MessageService messageService;
	
	@RequestMapping(value="/riderData", method=RequestMethod.GET)
	@ResponseBody
	public String receiveData(String nickname, int heart_rate, double speed, double latitude, double longitude, double elevation, double distance, double cadence, double power, String message){
		Date stat_ts = Calendar.getInstance().getTime();
		Statistic statistic = new Statistic(heart_rate, speed, latitude, longitude, elevation, distance, cadence, power, stat_ts);				
		logStatisticReceived(nickname, statistic);
		//persist message from Rider
		statisticService.saveStatistic(statistic, nickname);
		Message messageForRider = messageService.getMessageForRider(nickname);
		messageService.setMessageAsSent(messageForRider);
		if(null != messageForRider){
			return messageForRider.getMessage();
		}
		return "received";
	}
	
	private void logStatisticReceived(String nickname, Statistic statistic){
		StringBuffer buffer = new StringBuffer();
		buffer.append("Statistic received: Jersey: ");
		buffer.append(nickname);
		buffer.append(statistic.toString());	
		logger.info(buffer.toString());		
	}

}
