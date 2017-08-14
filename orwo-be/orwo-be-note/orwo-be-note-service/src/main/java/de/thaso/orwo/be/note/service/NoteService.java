package de.thaso.orwo.be.note.service;

import java.util.Date;
import java.util.List;

/**
 * NickNameService
 *
 * @author thaler
 * @since 21.09.16
 */
public interface NoteService {

    void createNote(NoteData noteData);

    void updateNote(NoteData noteData);

    NoteData findNote(long id);

    List<NoteData> findNotes();

    List<NoteData> findNotesBefore(Date timestamp);
}
