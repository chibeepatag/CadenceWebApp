/**
 * 
 */
package au.edu.cmu.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.edu.cmu.model.Coach;

/**
 * @author ChibeePatag
 *
 */
@Repository
public class CoachDaoImpl implements CoachDao {

	@Autowired
	private SessionFactory sessionFactory;
	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.CoachDao#getCoachByUsername(java.lang.String)
	 */
	@Override
	public Coach getCoachByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.CoachDao#saveCoach(au.edu.cmu.model.Coach)
	 */
	@Override
	public void saveCoach(Coach coach) {
		this.sessionFactory.getCurrentSession().save(coach);
	}

	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.CoachDao#updateCoach(au.edu.cmu.model.Coach)
	 */
	@Override
	public void updateCoach(Coach coach) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.CoachDao#deleteCoach(au.edu.cmu.model.Coach)
	 */
	@Override
	public void deleteCoach(Coach coach) {		 

	}

}
