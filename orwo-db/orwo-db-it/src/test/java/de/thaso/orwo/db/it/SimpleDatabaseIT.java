package de.thaso.orwo.db.it;

import de.thaso.orwo.db.api.note.NoteEntity;
import de.thaso.orwo.db.it.base.DbTestBase;
import de.thaso.orwo.db.it.utils.SecondCauseMatcher;
import de.thaso.orwo.db.store.note.NoteDAO;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.postgresql.util.PSQLException;

import javax.persistence.Query;
import javax.persistence.RollbackException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * SimpleIT
 *
 * @author thaler
 * @since 13.09.16
 */
public class SimpleDatabaseIT extends DbTestBase {

    private static final int COUNT_RESULTS = 3;
    @InjectMocks
    private NoteDAO underTest;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testStoreNote() throws SQLException {
        // given
        final NoteEntity noteEntity = new NoteEntity();
        noteEntity.setTimestamp(new Date());
        noteEntity.setContent("test note");
        // when
        entityManager.persist(noteEntity);
        // then
        assertThat(noteEntity.getId(), is(greaterThan(1000000L)));

        entityManager.flush();
        final ResultSet resultSet = getConnection().prepareStatement("SELECT COUNT(*) FROM T_NOTE").executeQuery();
        resultSet.next();
        assertThat(resultSet.getInt(1),is(15));
    }

    @Test
    public void testPrimaryKeyViolation() throws SQLException {
        // given
        Query nativeQuery = entityManager.createNativeQuery("INSERT INTO T_NOTE (ID, TIMESTAMP, CONTENT) VALUES(74, '2014-02-15', 'foo bar')");
        nativeQuery.executeUpdate();

        final NoteEntity noteEntity = new NoteEntity();
        noteEntity.setId(74L);
        noteEntity.setTimestamp(new Date());
        noteEntity.setContent("double note");

        exception.expect(RollbackException.class);
        exception.expectCause(new SecondCauseMatcher(PSQLException.class, "duplicate key value violates unique constraint"));
        // when
        entityManager.persist(noteEntity);
    }

    @Test
    public void testFindBy() throws SQLException {
        // when
        final List<NoteEntity> result = underTest.findNotesBefore(COUNT_RESULTS, new Date());
        // then
        assertThat(result.size(), is(COUNT_RESULTS));
        Long previousTimestamp = null;
        for (NoteEntity noteEntity : result) {
            if(previousTimestamp != null) {
                assertThat(noteEntity.getTimestamp().getTime(),is(lessThan(previousTimestamp)));
            }
            previousTimestamp = noteEntity.getTimestamp().getTime();
        }
    }
}
