package de.thaso.orwo.be.note.business.mapper;

import de.thaso.orwo.be.note.service.NoteData;
import de.thaso.orwo.db.api.note.NoteEntity;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * NoteMapperTest
 *
 * @author thaler
 * @since 2016-09-22
 */
public class NoteMapperTest {

    @InjectMocks
    private NoteMapper underTest = Mappers.getMapper(NoteMapper.class);

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void convertToEntity_WhenDOIsNull() {
        // when
        final NoteEntity result = underTest.noteToEntity(null);
        // then
        assertThat(result,is(nullValue()));
    }

    @Test
    public void convertListToDO() {
        // given
        final ArrayList<NoteEntity> noteEntityList = new ArrayList<>();
        final NoteEntity firstNoteEntity = new NoteEntity();
        firstNoteEntity.setId(743L);
        firstNoteEntity.setContent("Hello");
        noteEntityList.add(firstNoteEntity);
        final NoteEntity secondNoteEntity = new NoteEntity();
        secondNoteEntity.setId(234L);
        secondNoteEntity.setContent("World");
        noteEntityList.add(secondNoteEntity);
        // when
        final List<NoteData> noteDataList = underTest.noteListToDOList(noteEntityList);
        // then
        assertThat(noteDataList.size(),is(noteEntityList.size()));

        NoteData firstNoteData = noteDataList.get(0);
        assertThat(firstNoteData.getId(),is(firstNoteEntity.getId()));
        assertThat(firstNoteData.getContent(),is(firstNoteEntity.getContent()));
        NoteData secondNoteData = noteDataList.get(1);
        assertThat(secondNoteData.getId(),is(secondNoteEntity.getId()));
        assertThat(secondNoteData.getContent(),is(secondNoteEntity.getContent()));
    }
}
