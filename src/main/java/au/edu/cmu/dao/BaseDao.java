/**
 * 
 */
package au.edu.cmu.dao;

import java.util.List;

/**
 * @author ChibeePatag
 *
 */
public interface BaseDao<T> {
	T create(T entity);
	
	void edit(T entity);
	
	void remove(T entity);
	
	T findById(Long id);

	List<T> findAll();
}
