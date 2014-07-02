/**
 * 
 */
package au.edu.cmu.dao;

import java.util.Date;

import au.edu.cmu.model.Rider;
import au.edu.cmu.model.Statistic;

/**
 * @author ChibeePatag
 *
 */
public interface StatisticsDao extends BaseDao<Statistic> {

	Statistic getRiderLatestStatistic(Rider rider);
}
