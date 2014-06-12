/**
 * 
 */
package au.edu.cmu.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author ChibeePatag
 *
 */
@Entity
public class Statistic {

	@Id
	private int stat_id;
	
	private int rider_id;
	
	private int heart_rate;
	
	private double speed;
	
	private double latitude;
	
	private double longitude;
	
	private double elevation;
	
	private double distance;
	
	private double cadence;
	
	private double power;
	
	private Date stat_ts;

	public int getStat_id() {
		return stat_id;
	}

	public void setStat_id(int stat_id) {
		this.stat_id = stat_id;
	}

	public int getRider_id() {
		return rider_id;
	}

	public void setRider_id(int rider_id) {
		this.rider_id = rider_id;
	}

	public int getHeart_rate() {
		return heart_rate;
	}

	public void setHeart_rate(int heart_rate) {
		this.heart_rate = heart_rate;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getElevation() {
		return elevation;
	}

	public void setElevation(double elevation) {
		this.elevation = elevation;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getCadence() {
		return cadence;
	}

	public void setCadence(double cadence) {
		this.cadence = cadence;
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public Date getStat_ts() {
		return stat_ts;
	}

	public void setStat_ts(Date stat_ts) {
		this.stat_ts = stat_ts;
	}
		
}
