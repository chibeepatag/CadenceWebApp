/**
 * 
 */
package au.edu.cmu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import au.edu.cmu.model.Note;

/**
 * @author ChibeePatag
 *
 */
@Repository
@Transactional(propagation=Propagation.REQUIRED)
public class NoteDaoImpl implements NoteDao {

	@PersistenceContext(unitName = "entityManager")
	EntityManager entityManager;
	
	@Override
	public Note create(Note entity) {
		this.entityManager.persist(entity);
        this.entityManager.flush();
        this.entityManager.refresh(entity);
        return entity;
	}

	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.BaseDao#edit(java.lang.Object)
	 */
	@Override
	public Note edit(Note entity) {
		return this.entityManager.merge(entity);
	}

	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.BaseDao#remove(java.lang.Object)
	 */
	@Override
	public void remove(Note entity) {
		this.entityManager.remove(this.entityManager.merge(entity));

	}

	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.BaseDao#findById(java.lang.Long)
	 */
	@Override
	public Note findById(Long id) {
		return this.entityManager.find(Note.class, id);
	}

	/* (non-Javadoc)
	 * @see au.edu.cmu.dao.BaseDao#findAll()
	 */
	@Override
	public List<Note> findAll() {
		CriteriaQuery<Note> cq = this.entityManager.getCriteriaBuilder().createQuery(Note.class);
        cq.select(cq.from(Note.class));
        return this.entityManager.createQuery(cq).getResultList();
	}

}
