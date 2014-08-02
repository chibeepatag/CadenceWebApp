/**
 * 
 */
package au.edu.cmu.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * @author ChibeePatag
 *
 */
@Entity
@Table(name="Message_Recipient")
public class MessageRecipient {
	
	@EmbeddedId
	MessageRecipientId messageRecipientId;
	
	@Type(type="yes_no")
	private boolean sent;			

	public MessageRecipientId getMessageRecipientId() {
		return messageRecipientId;
	}

	public void setMessageRecipientId(MessageRecipientId messageRecipientId) {
		this.messageRecipientId = messageRecipientId;
	}

	public boolean isSent() {
		return sent;
	}

	public void setSent(boolean sent) {
		this.sent = sent;
	}		

}

