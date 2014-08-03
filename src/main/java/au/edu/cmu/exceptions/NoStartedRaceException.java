/**
 * 
 */
package au.edu.cmu.exceptions;

/**
 * @author ChibeePatag
 * 
 */
public class NoStartedRaceException extends Exception {

	public NoStartedRaceException() {
		super("A race has not yet been started.");
	}
}
