/**
 * 
 */
package au.edu.cmu.dao;

import au.edu.cmu.exceptions.CadencePersistenceException;
import au.edu.cmu.model.Race;

/**
 * DAO layer for Race entity
 * @author ChibeePatag
 *
 */
public interface RaceDao extends BaseDao<Race> {

	Race getCurrentRace() throws CadencePersistenceException;
	
	/**
	 * Gets the current race that has been started by the coach
	 * by pressing the start button.
	 * @return
	 * @throws CadencePersistenceException
	 */
	Race getStartedRace() throws CadencePersistenceException;
	
	boolean isRaceOngoing();
	
	Race getLatestRace() throws CadencePersistenceException;
	
}
