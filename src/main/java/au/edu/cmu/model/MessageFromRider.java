/**
 * 
 */
package au.edu.cmu.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author ChibeePatag
 *
 */
@Entity
public class MessageFromRider extends Log{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long msg_rider_id;
	
	@ManyToOne
	@JoinColumn(name="FromRider")
	private Rider from;
	
	private String message;
	
	@ManyToOne
	@JoinColumn(name="race_id")
	private Race race;	
	
	public long getMsg_rider_id() {
		return msg_rider_id;
	}

	public void setMsg_rider_id(long msg_rider_id) {
		this.msg_rider_id = msg_rider_id;
	}

	public Rider getFrom() {
		return from;
	}

	public void setFrom(Rider from) {
		this.from = from;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}			
	
}
