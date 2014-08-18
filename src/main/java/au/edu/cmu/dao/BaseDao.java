/**
 * 
 */
package au.edu.cmu.dao;

import java.util.List;

/**
 * Base interface for all data access classes
 * @author ChibeePatag
 *
 */
public interface BaseDao<T> {
	T create(T entity);
	
	T edit(T entity);
	
	void remove(T entity);
	
	T findById(Long id);

	List<T> findAll();
}
