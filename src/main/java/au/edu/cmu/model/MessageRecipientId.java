package au.edu.cmu.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class MessageRecipientId{
	
	@ManyToOne
	@JoinColumn(name="message_id")
	private Message message;
	
	@ManyToOne
	@JoinColumn(name="rider_id")
	private Rider rider;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Rider getRider() {
		return rider;
	}

	public void setRider(Rider rider) {
		this.rider = rider;
	}			
	
}