package de.thaso.orwo.db.store.note;

import de.thaso.orwo.db.api.note.NoteDS;
import de.thaso.orwo.db.api.note.NoteEntity;
import de.thaso.orwo.db.common.DatabaseError;
import de.thaso.orwo.db.common.DatabaseException;
import de.thaso.orwo.db.store.utils.DatabaseExceptionCodeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * NoteDAOTest
 *
 * @author thaler
 * @since 13.09.16
 */
public class NoteDAOTest {

    public static final DatabaseExceptionCodeMatcher EXCEPTION_MATCHER_ENTITY_NOT_FOUND
            = new DatabaseExceptionCodeMatcher(DatabaseError.ENTITY_NOT_FOUND);
    private static final int COUNT_RESULTS = 3;

    @InjectMocks
    private NoteDS underTest = new NoteDAO();

    @Mock
    private EntityManager entityManager;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Long primaryKey;
    private NoteEntity noteEntity;

    @Before
    public void setUp() {
        initMocks(this);

        primaryKey = 1L;
        noteEntity = new NoteEntity();
        when(entityManager.find(NoteEntity.class, primaryKey)).thenReturn(noteEntity);
    }

    @Test
    public void storeNote() {
        // when
        final NoteEntity result = underTest.storeNote(noteEntity);
        // then
        verify(entityManager).persist(noteEntity);
        assertThat(result, is(noteEntity));
    }

    @Test
    public void findNote() {
        // when
        final NoteEntity result = underTest.findNoteById(primaryKey);
        // then
        assertThat(result, is(noteEntity));
    }

    @Test
    public void findNote_whenPrimaryKeyNotFound() {
        // given
        when(entityManager.find(NoteEntity.class, primaryKey)).thenReturn(null);
        // when
        final NoteEntity result = underTest.findNoteById(primaryKey);
        // then
        assertThat(result, is(nullValue()));
    }

    @Test
    public void loadNote() {
        // when
        final NoteEntity result = underTest.loadNoteById(primaryKey);
        // then
        assertThat(result, is(noteEntity));
    }

    @Test
    public void loadNote_whenPrimaryKeyNotFound() {
        // given
        when(entityManager.find(NoteEntity.class, primaryKey)).thenReturn(null);
        exception.expect(DatabaseException.class);
        exception.expectMessage(containsString(" " + primaryKey.toString() + " "));
        exception.expect(EXCEPTION_MATCHER_ENTITY_NOT_FOUND);
        // when
        final NoteEntity result = underTest.loadNoteById(primaryKey);
    }

    @Test
    public void findLastNotes() {
        // given
        final TypedQuery query = mock(TypedQuery.class);
        when(entityManager.createNamedQuery(NoteEntity.FIND_LAST_NOTES,NoteEntity.class)).thenReturn(query);
        final List<NoteEntity> messageEntityList = new ArrayList<>();
        when(query.getResultList()).thenReturn(messageEntityList);
        // when
        final List<NoteEntity> result = underTest.findLastNotes(COUNT_RESULTS);
        // then
        assertThat(result,is(messageEntityList));
        verify(query).setMaxResults(COUNT_RESULTS);
    }

    @Test
    public void findNotesBefore() {
        // given
        final TypedQuery query = mock(TypedQuery.class);
        when(entityManager.createNamedQuery(NoteEntity.FIND_COUNT_NOTES_BEFORE,NoteEntity.class)).thenReturn(query);
        final List<NoteEntity> messageEntityList = new ArrayList<>();
        when(query.getResultList()).thenReturn(messageEntityList);
        final Date timestamp = new Date();
        // when
        final List<NoteEntity> result = underTest.findNotesBefore(COUNT_RESULTS, timestamp);
        // then
        assertThat(result, is(messageEntityList));
        verify(query).setParameter("timestamp",timestamp);
        verify(query).setMaxResults(COUNT_RESULTS);
    }
}