package de.thaso.orwo.be.note.business;

import de.thaso.orwo.be.note.business.mapper.NoteMapper;
import de.thaso.orwo.be.note.service.NoteData;
import de.thaso.orwo.db.api.note.NoteDS;
import de.thaso.orwo.db.api.note.NoteEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * NoteServiceImplTest
 *
 * @author thaler
 * @since 2016-09-22
 */
public class NoteServiceImplTest {

    @InjectMocks
    private NoteServiceImpl underTest;

    @Mock
    private NoteDS noteDS;

    @Mock
    private NoteMapper noteMapper;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testName() {
        // given
        final NoteData noteData = new NoteData();
        final NoteEntity noteEntity = new NoteEntity();
        when(noteMapper.noteToEntity(noteData)).thenReturn(noteEntity);
        // when
        underTest.createNote(noteData);
        // then
        verify(noteDS).storeNote(noteEntity);
    }

    @Test
    public void testFindLastNotes() {
        // given
        final String name = "Hugo";
        final List<NoteEntity> noteList = new ArrayList<>();
        when(noteDS.findLastNotes(anyInt())).thenReturn(noteList);
        final List<NoteData> noteDataList = new ArrayList<>();
        when(noteMapper.noteListToDOList(noteList)).thenReturn(noteDataList);
        // when
        final List<NoteData> result = underTest.findNotes();
        // then
        assertThat(result, is(noteDataList));
    }
}
