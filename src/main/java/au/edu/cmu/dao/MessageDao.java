/**
 * 
 */
package au.edu.cmu.dao;

import org.springframework.stereotype.Repository;

import au.edu.cmu.model.Message;

/**
 * @author ChibeePatag
 *
 */
@Repository
public class MessageDao extends BaseDao<Message> {

	public MessageDao(){
		super(Message.class);
	}
}
