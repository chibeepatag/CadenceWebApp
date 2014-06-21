/**
 * 
 */
package au.edu.cmu.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
	
	private Date race_date;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "race_team", joinColumns = @JoinColumn(name = "RACE_ID"), inverseJoinColumns = @JoinColumn(name = "Rider_ID"))
	private List<Rider> riders;

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

	public Date getRace_date() {
		return race_date;
	}

	public void setRace_date(Date race_date) {
		this.race_date = race_date;
	}

	public List<Rider> getRiders() {
		return riders;
	}

	public void setRiders(List<Rider> riders) {
		this.riders = riders;
	}
	
}
