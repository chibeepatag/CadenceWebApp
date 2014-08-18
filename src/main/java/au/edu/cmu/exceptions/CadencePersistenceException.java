/**
 * 
 */
package au.edu.cmu.exceptions;

import javax.persistence.PersistenceException;

/**
 * Wrapper class for persistence exceptions
 * @author ChibeePatag
 *
 */
public class CadencePersistenceException extends PersistenceException {
	
	PersistenceException persistenceException;
	
	public CadencePersistenceException(PersistenceException pe, String message) {
		super(message);
		this.persistenceException = pe;
	}

}
