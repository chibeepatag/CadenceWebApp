/**
 * 
 */
package au.edu.cmu.exceptions;

/**
 * @author ChibeePatag
 *
 */
public class OnGoingRaceException extends Exception {

	public OnGoingRaceException() {
		super("A race is on going. End current race before creating a new one.");
	}
}
