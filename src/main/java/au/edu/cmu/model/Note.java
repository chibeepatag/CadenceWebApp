/**
 * 
 */
package au.edu.cmu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity representing a note made 
 * by the coach on the dashboard.
 * @author ChibeePatag
 *
 */
@Entity
public class Note extends Log{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long note_id;

	private String note;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User coach;	
	
	@ManyToOne
	@JoinColumn(name="race_id", nullable = false)
	private Race race;

	public long getNote_id() {
		return note_id;
	}

	public void setNote_id(long note_id) {
		this.note_id = note_id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public User getCoach() {
		return coach;
	}

	public void setCoach(User coach) {
		this.coach = coach;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}		
	
}
