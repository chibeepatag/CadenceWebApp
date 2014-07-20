/**
 * 
 */
package au.edu.cmu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import au.edu.cmu.model.Note;
import au.edu.cmu.model.Note_;
import au.edu.cmu.model.Race;

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
	
	@Override
	public List<Note> getNotesForThisRider(Race race){
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Note> cq = cb.createQuery(Note.class);
		Root<Note> root = cq.from(Note.class);
		Predicate racePred = cb.equal(root.get(Note_.race), race);
		cq.select(root).where(racePred);
		TypedQuery<Note> query = this.entityManager.createQuery(cq);
		return query.getResultList();		
	}

}
