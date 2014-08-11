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

import au.edu.cmu.exceptions.CadencePersistenceException;
import au.edu.cmu.exceptions.NoStartedRaceException;
import au.edu.cmu.exceptions.RiderNotInRaceException;
import au.edu.cmu.model.Message;
import au.edu.cmu.model.Race;
import au.edu.cmu.model.Rider;
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
	
	public static final String MESSAGE_SUCCESS= "200";
	
	public static final String NO_STARTED_RACE = "No started race.";
	
	public static final String MESSAGE_FAIL = "You are not part of the race.";
	
	@Autowired
	StatisticService statisticService;
	
	@Autowired
	MessageService messageService;
	
	@RequestMapping(value="/riderData", method=RequestMethod.GET)
	@ResponseBody
	public String receiveData(String nickname, int heart_rate, double speed, double latitude, double longitude, double distance, double cadence, double power, String message){
		Date stat_ts = Calendar.getInstance().getTime();
		Statistic statistic = new Statistic(heart_rate, speed, latitude, longitude, distance, cadence, power, stat_ts);				
		logStatisticReceived(nickname, statistic);
		
		
			try {
				if(null != message && message.length() > 0){
					messageService.saveMessageFromRider(nickname, message);
				}
				Rider rider = statisticService.saveStatistic(statistic, nickname);
				Message messageForRider = messageService.getMessageForRider(nickname);
				
				if(null != messageForRider){
					messageService.setMessageAsSent(messageForRider, rider);
					return messageForRider.getMessage();
				}
			} catch (RiderNotInRaceException e) {				
				e.printStackTrace();				
				return MESSAGE_FAIL;
			}catch(NoStartedRaceException e){
				e.printStackTrace();				
				return NO_STARTED_RACE;
			}catch (CadencePersistenceException cpe){
				return MESSAGE_FAIL;
			}			
			
		
		return MESSAGE_SUCCESS;
	}
	
	private void logStatisticReceived(String nickname, Statistic statistic){
		StringBuffer buffer = new StringBuffer();
		buffer.append("Statistic received: Jersey: ");
		buffer.append(nickname);
		buffer.append(statistic.toString());	
		logger.info(buffer.toString());		
	}

}
