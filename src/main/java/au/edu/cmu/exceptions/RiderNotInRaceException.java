/**
 * 
 */
package au.edu.cmu.exceptions;

/**
 * @author ChibeePatag
 *
 */
public class RiderNotInRaceException extends Exception {
	
	private String message;
	
	public RiderNotInRaceException(String nickname) {
		StringBuffer buffer = new StringBuffer(nickname);
		buffer.append(" is not part of this race.");
		message = buffer.toString();
	}
	
	@Override
	public String getMessage() {		
		return message;
	}
}
