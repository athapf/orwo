package de.thaso.orwo.be.note.business;

import de.thaso.orwo.be.note.business.mapper.NoteMapper;
import de.thaso.orwo.be.note.service.NoteData;
import de.thaso.orwo.be.note.service.NoteService;
import de.thaso.orwo.db.api.note.NoteDS;
import de.thaso.orwo.db.api.note.NoteEntity;
import de.thaso.orwo.db.common.DatabaseException;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * NoteServiceImpl
 *
 * @author thaler
 * @since 2016-09-21
 */
@Stateless
@Remote(NoteService.class)
public class NoteServiceImpl implements NoteService {

    public static final Logger LOG = LoggerFactory.getLogger(NoteServiceImpl.class);
    private static final int COUNT_NOTES = 10;

    @EJB
    private NoteDS noteDS;

    @Inject
    private NoteMapper noteMapper;

    @Override
    public void createNote(final NoteData noteData) throws DatabaseException {
        final NoteEntity noteEntity = noteMapper.noteToEntity(noteData);
        noteEntity.setTimestamp(new Date());

        noteDS.storeNote(noteEntity);
    }

    @Override
    public void updateNote(final NoteData noteData) throws DatabaseException {
        final NoteEntity originalNote = noteDS.loadNoteById(noteData.getId());

        final NoteEntity noteEntity = noteMapper.noteToEntity(noteData);
        noteEntity.setTimestamp(originalNote.getTimestamp());

        noteDS.updateNote(noteEntity);
    }

    @Override
    public NoteData findNote(final long id) {
        final NoteEntity noteEntity = noteDS.findNoteById(id);
        return noteMapper.noteToDO(noteEntity);
    }

    @Override
    public List<NoteData> findNotes() {
        LOG.debug("findNotes ...");
        final List<NoteEntity> noteList = noteDS.findLastNotes(COUNT_NOTES);
        final List<NoteData> noteDataList = noteMapper.noteListToDOList(noteList);
        LOG.debug(" ... got {} notes", noteDataList.size());
        return noteDataList;
    }

    @Override
    public List<NoteData> findNotesBefore(final Date timestamp) {
        LOG.debug("findNotesBefore {} ...", DateFormatUtils.format(timestamp, "yyyy-MM-dd HH:mm:ss.SSS"));
        final List<NoteEntity> noteList = noteDS.findNotesBefore(COUNT_NOTES, timestamp);
        final List<NoteData> noteDataList = noteMapper.noteListToDOList(noteList);
        LOG.debug(" ... got {} notes", noteDataList.size());
        return noteDataList;
    }
}
