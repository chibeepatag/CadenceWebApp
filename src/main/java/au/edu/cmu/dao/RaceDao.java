/**
 * 
 */
package au.edu.cmu.dao;

import au.edu.cmu.exceptions.CadencePersistenceException;
import au.edu.cmu.model.Race;

/**
 * @author ChibeePatag
 *
 */
public interface RaceDao extends BaseDao<Race> {

	Race getCurrentRace() throws CadencePersistenceException;
	
	boolean isRaceOngoing();
	
}
