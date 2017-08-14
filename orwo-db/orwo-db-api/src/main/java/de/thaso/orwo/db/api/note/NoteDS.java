package de.thaso.orwo.db.api.note;

import java.util.Date;
import java.util.List;

/**
 * NickNameDAO
 *
 * @author thaler
 * @since 13.09.16
 */
public interface NoteDS {

    NoteEntity storeNote(NoteEntity noteEntity);

    NoteEntity updateNote(NoteEntity noteEntity);

    NoteEntity findNoteById(Long id);

    NoteEntity loadNoteById(Long id);

    List<NoteEntity> findLastNotes(int count);

    List<NoteEntity> findNotesBefore(int count, Date timestamp);
}
