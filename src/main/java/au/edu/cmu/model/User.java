/**
 * 
 */
package au.edu.cmu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * @author Celine Patag
 * 
 */
@Entity
@NamedQuery(name="findCoachByUsername", query="Select c from User c where c.username = :username")
@Table(name="Cadence_User")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long user_id;
		
	private String username;
		
	private String password;
	
	private String phone;			
	
	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
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
