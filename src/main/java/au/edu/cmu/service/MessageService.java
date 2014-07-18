/**
 * 
 */
package au.edu.cmu.service;

import au.edu.cmu.model.Message;

/**
 * @author ChibeePatag
 *
 */
public interface MessageService {

	Message getMessageForRider(String nickname);

	void setMessageAsSent(Message message);
}
