/**
 * 
 */
package au.edu.cmu.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

/**
 * @author ChibeePatag
 * 
 */
@Entity
public class Message extends Log{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long message_id;

	private String message;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User coach;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "message_recipient", joinColumns = @JoinColumn(name = "Message_ID"))
	private List<MessageRecipient> recipients; 			
	
	@ManyToOne
	@JoinColumn(name="race_id")
	private Race race;

	public long getMessage_id() {
		return message_id;
	}

	public void setMessage_id(long message_id) {
		this.message_id = message_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getCoach() {
		return coach;
	}

	public void setCoach(User coach) {
		this.coach = coach;
	}

	public List<MessageRecipient> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<MessageRecipient> recipients) {
		this.recipients = recipients;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}		
		
}
