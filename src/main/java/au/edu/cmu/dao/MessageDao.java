/**
 * 
 */
package au.edu.cmu.dao;

import au.edu.cmu.model.Message;

/**
 * @author ChibeePatag
 *
 */
public class MessageDao extends BaseDao<Message> {

	public MessageDao(){
		super(Message.class);
	}
}
