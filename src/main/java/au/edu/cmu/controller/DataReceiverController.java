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

import au.edu.cmu.model.Statistic;
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
	
	@RequestMapping(value="/dataSender", method=RequestMethod.GET)
	public String dataSender(){
		return "dataSender";
	}
	
	@RequestMapping(value="/riderData", method=RequestMethod.POST)
	@ResponseBody
	public String receiveData(String nickname, int heart_rate, double speed, double latitude, double longitude, double elevation, double distance, double cadence, double power){
		Date stat_ts = Calendar.getInstance().getTime();
		Statistic statistic = new Statistic(heart_rate, speed, latitude, longitude, elevation, distance, cadence, power, stat_ts);				
		logStatisticReceived(nickname, statistic);
		
		statisticService.saveStatistic(statistic, nickname);
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
