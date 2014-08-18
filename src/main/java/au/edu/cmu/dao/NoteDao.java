/**
 * 
 */
package au.edu.cmu.dao;

import java.util.List;

import au.edu.cmu.model.Note;
import au.edu.cmu.model.Race;

/**
 * DAO layer for notes
 * @author ChibeePatag
 *
 */
public interface NoteDao extends BaseDao<Note> {
	
	List<Note> getNotesForThisRace(Race race);
}
