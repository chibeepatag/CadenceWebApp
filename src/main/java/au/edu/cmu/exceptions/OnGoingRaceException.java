/**
 * 
 */
package au.edu.cmu.exceptions;

/**
 * Exception thrown if a new race is created while a race is on going.
 * @author ChibeePatag
 *
 */
public class OnGoingRaceException extends Exception {

	public OnGoingRaceException() {
		super("A race is on going. End current race before creating a new one.");
	}
}
