/**
 * 
 */
package au.edu.cmu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * @author Celine Patag
 * 
 */
@Entity
@NamedQuery(name="findCoachByUsername", query="Select c from Coach c where c.username = :username")
public class Coach {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long coach_id;
		
	private String username;
		
	private String password;
	
	private String phone;
		
	public Long getCoach_id() {
		return coach_id;
	}
	
	public void setCoach_id(Long coach_id) {
		this.coach_id = coach_id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
		
}
