/**
 * 
 */
package au.edu.cmu.exceptions;

import javax.persistence.PersistenceException;

/**
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
