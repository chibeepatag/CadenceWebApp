package au.edu.cmu.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * Composite id of a message
 * @author ChibeePatag
 *
 */
@Embeddable
public class MessageRecipientId implements Serializable{
	
	private static final long serialVersionUID = -1842225143112613535L;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((rider == null) ? 0 : rider.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageRecipientId other = (MessageRecipientId) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (rider == null) {
			if (other.rider != null)
				return false;
		} else if (!rider.equals(other.rider))
			return false;
		return true;
	}		
	
	
	
}