/**
 * 
 */
package au.edu.cmu.model;

import java.util.Date;

import javax.persistence.MappedSuperclass;

/**
 * @author ChibeePatag
 * 
 */
@MappedSuperclass
public abstract class Log implements Comparable<Log> {
	Date message_ts;

	public Date getMessage_ts() {
		return message_ts;
	}

	public void setMessage_ts(Date message_ts) {
		this.message_ts = message_ts;
	}

	@Override
	public int compareTo(Log o) {
		return this.message_ts.compareTo(o.getMessage_ts());		
	}
}
