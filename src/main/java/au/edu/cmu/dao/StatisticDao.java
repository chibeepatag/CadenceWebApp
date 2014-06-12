/**
 * 
 */
package au.edu.cmu.dao;

import java.util.Date;
import java.util.List;

import au.edu.cmu.model.Statistic;

/**
 * @author ChibeePatag
 *
 */
public interface StatisticDao {

	Statistic getLatestRiderStatistic(int rider_id, Date timestamp);
	
	List<Statistic> getLatestStatistics(Date timestamp);
}
