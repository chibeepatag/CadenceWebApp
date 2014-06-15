/**
 * 
 */
package au.edu.cmu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Celine Patag
 *
 */
@Entity
public class Rider {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int rider_id;
	
	private String first_Name;
	
	private String last_name;
	
	private String nickname;
	
	private String jersey_no;
	
	private String phone;

	public int getRider_id() {
		return rider_id;
	}

	public void setRider_id(int rider_id) {
		this.rider_id = rider_id;
	}

	public String getFirst_Name() {
		return first_Name;
	}

	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getJersey_no() {
		return jersey_no;
	}

	public void setJersey_no(String jersey_no) {
		this.jersey_no = jersey_no;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}	
	
}
