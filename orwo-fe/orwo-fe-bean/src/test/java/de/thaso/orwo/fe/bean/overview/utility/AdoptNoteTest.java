package de.thaso.orwo.fe.bean.overview.utility;

import de.thaso.orwo.be.note.service.NoteData;
import de.thaso.orwo.fe.bean.overview.OverviewRowModel;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


/**
 * AdoptNoteTest
 *
 * @author thaler
 * @since 2017-08-07
 */
public class AdoptNoteTest {

    private AdoptNote underTest;

    @Before
    public void setUp() {
        underTest = new AdoptNote();
    }

    @Test
    public void testAdoptNote_ContentWithLF() {
        final NoteData noteData = new NoteData();
        noteData.setContent("The Title\nThe main content ...");
        final OverviewRowModel result = underTest.adoptNote(noteData);
        assertThat(result.getTitle(), is("The Title"));
    }

    @Test
    public void testAdoptNote_ContentWithCR() {
        final NoteData noteData = new NoteData();
        noteData.setContent("The Title\rThe main content ...");
        final OverviewRowModel result = underTest.adoptNote(noteData);
        assertThat(result.getTitle(), is("The Title"));
    }

    @Test
    public void testAdoptNote_ContentWithCRLF() {
        final NoteData noteData = new NoteData();
        noteData.setContent("The Title\r\nThe main content ...");
        final OverviewRowModel result = underTest.adoptNote(noteData);
        assertThat(result.getTitle(), is("The Title"));
    }

    @Test
    public void testAdoptNote_WhenContentIsNull() {
        final NoteData noteData = new NoteData();
        final OverviewRowModel result = underTest.adoptNote(noteData);
        assertThat(result.getTitle(), is(nullValue()));
    }

    @Test
    public void testAdoptNote_WhenContentIsEmpty() {
        final NoteData noteData = new NoteData();
        noteData.setContent(StringUtils.EMPTY);
        final OverviewRowModel result = underTest.adoptNote(noteData);
        assertThat(result.getTitle(), is(nullValue()));
    }

    @Test
    public void testAdoptNote_Timestamp() {
        final Date timestamp = new Date();
        final NoteData noteData = new NoteData();
        noteData.setTimestamp(timestamp);
        final OverviewRowModel result = underTest.adoptNote(noteData);
        assertThat(result.getTimestamp(), is(timestamp));
    }
}