/**
 * 
 */
package au.edu.cmu.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;

import org.hibernate.annotations.Type;

/**
 * @author ChibeePatag
 *
 */
@Entity
public class Race {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long race_id;
	
	private String race_name;
	
	private Date race_start;
	
	private Date race_end;
	
	@Type(type="yes_no")
	private Boolean isOngoing;
	
	@ManyToOne
	@JoinColumn(name="Coach_ID")
	private User coach;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "race_team", joinColumns = @JoinColumn(name = "RACE_ID"), inverseJoinColumns = @JoinColumn(name = "Rider_ID"))
	@MapKey(name = "nickname")
	private Map<String, Rider> riders;

	public long getRace_id() {
		return race_id;
	}

	public void setRace_id(long race_id) {
		this.race_id = race_id;
	}

	public String getRace_name() {
		return race_name;
	}

	public void setRace_name(String race_name) {
		this.race_name = race_name;
	}	

	public Date getRace_start() {
		return race_start;
	}

	public void setRace_start(Date race_start) {
		this.race_start = race_start;
	}

	public Map<String, Rider> getRiders() {
		return riders;
	}

	public void setRiders(Map<String, Rider> riders) {
		this.riders = riders;
	}

	public Date getRace_end() {
		return race_end;
	}

	public void setRace_end(Date race_end) {
		this.race_end = race_end;
	}

	public Boolean getIsOngoing() {
		return isOngoing;
	}

	public void setIsOngoing(Boolean isOngoing) {
		this.isOngoing = isOngoing;
	}

	public User getCoach() {
		return coach;
	}

	public void setCoach(User coach) {
		this.coach = coach;
	}	
	
}
