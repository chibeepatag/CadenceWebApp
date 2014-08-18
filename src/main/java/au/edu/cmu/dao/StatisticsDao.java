/**
 * 
 */
package au.edu.cmu.dao;

import au.edu.cmu.model.Rider;
import au.edu.cmu.model.Statistic;

/**
 * DAO layer for rider statistics
 * @author ChibeePatag
 *
 */
public interface StatisticsDao extends BaseDao<Statistic> {

	Statistic getRiderLatestStatistic(Rider rider);
}
