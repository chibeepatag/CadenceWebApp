/**
 * 
 */
package au.edu.cmu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Celine Patag
 * 
 */
@Entity
public class Coach {

	@Id
	@GeneratedValue
	private int coach_id;
	
	private String username;
	
	private String password;
	
	private String phone;
		
	public int getCoach_id() {
		return coach_id;
	}
	
	public void setCoach_id(int coach_id) {
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
