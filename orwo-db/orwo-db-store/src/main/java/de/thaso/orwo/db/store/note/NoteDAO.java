package de.thaso.orwo.db.store.note;

import de.thaso.orwo.db.api.note.NoteDS;
import de.thaso.orwo.db.api.note.NoteEntity;
import de.thaso.orwo.db.common.DatabaseError;
import de.thaso.orwo.db.common.DatabaseException;
import de.thaso.orwo.db.store.base.AbstractDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * NoteDAO
 *
 * @author thaler
 * @since 2017-07-15
 */
@Stateless
@Local(NoteDS.class)
public class NoteDAO extends AbstractDAO<NoteEntity> implements NoteDS {

    private final static Logger LOG = LoggerFactory.getLogger(NoteDAO.class);

    @Inject
    private EntityManager entityManager;

    @Override
    public NoteEntity storeNote(final NoteEntity noteEntity) {
        LOG.info("storeNote with id {}", noteEntity.getId());

        entityManager.persist(noteEntity);
        return noteEntity;
    }

    public NoteEntity updateNote(final NoteEntity noteEntity) {
        LOG.info("updateNote with id {}", noteEntity.getId());

        return entityManager.merge(noteEntity);
    }

    public NoteEntity findNoteById(final Long id) {
        LOG.info("findNoteById {}", id);

        final NoteEntity noteEntity = entityManager.find(NoteEntity.class, id);
        return noteEntity;
    }

    public NoteEntity loadNoteById(final Long id) throws DatabaseException {
        LOG.info("loadNoteById {}", id);
        
        final NoteEntity noteEntity = entityManager.find(NoteEntity.class, id);
        if(noteEntity == null) {
            throw new DatabaseException(DatabaseError.ENTITY_NOT_FOUND, "Note with id " + id + " not found!");
        }
        return noteEntity;
    }

    @Override
    public List<NoteEntity> findLastNotes(final int count) {
        LOG.info("findLastNotes {}", count);

        final TypedQuery<NoteEntity> query
                = entityManager.createNamedQuery(NoteEntity.FIND_LAST_NOTES, NoteEntity.class);
        query.setMaxResults(count);
        return query.getResultList();
    }

    @Override
    public List<NoteEntity> findNotesBefore(final int count, final Date timestamp) {
        LOG.info("findNotesBefore {} {}", count, timestamp);

        final TypedQuery<NoteEntity> query
                = entityManager.createNamedQuery(NoteEntity.FIND_COUNT_NOTES_BEFORE, NoteEntity.class);
        query.setParameter("timestamp", timestamp);
        query.setMaxResults(count);
        return query.getResultList();
    }
}
