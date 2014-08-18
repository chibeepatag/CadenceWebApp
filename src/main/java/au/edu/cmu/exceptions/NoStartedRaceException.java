/**
 * 
 */
package au.edu.cmu.exceptions;

/**
 * Exception thrown if there is no race started.
 * @author ChibeePatag
 * 
 */
public class NoStartedRaceException extends Exception {

	public NoStartedRaceException() {
		super("A race has not yet been started.");
	}
}
