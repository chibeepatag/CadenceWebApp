/**
 * 
 */
package au.edu.cmu.dao;

import org.springframework.stereotype.Repository;

import au.edu.cmu.model.Statistic;

/**
 * @author ChibeePatag
 *
 */
@Repository
public class StatisticDao extends BaseDao<Statistic> {

	public StatisticDao(){
		super(Statistic.class);
	}
}
