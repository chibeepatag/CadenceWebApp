/**
 * 
 */
package au.edu.cmu.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author ChibeePatag
 *
 */
//@Entity
public class Message {
	
	@Id
	private int message_id;
	
	private String message;
	
	private int coach_id;
	
	private Date message_ts;
	
	private List<Rider> recipients;

	public int getMessage_id() {
		return message_id;
	}

	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCoach_id() {
		return coach_id;
	}

	public void setCoach_id(int coach_id) {
		this.coach_id = coach_id;
	}

	public Date getMessage_ts() {
		return message_ts;
	}

	public void setMessage_ts(Date message_ts) {
		this.message_ts = message_ts;
	}

	public List<Rider> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<Rider> recipients) {
		this.recipients = recipients;
	}		

}
