package de.thaso.orwo.db.api.note;

import de.thaso.orwo.db.api.base.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * NoteEntity
 *
 * @author thaler
 * @since 13.09.16
 */
@Entity
@Table(name = "T_NOTE")
@NamedQueries({
    @NamedQuery(name = NoteEntity.FIND_LAST_NOTES, query = "select n from NoteEntity n order by n.timestamp desc"),
    @NamedQuery(name = NoteEntity.FIND_COUNT_NOTES_BEFORE, query = "select n from NoteEntity n where n.timestamp < :timestamp order by n.timestamp desc"),
})
public class NoteEntity extends EntityBase {

    private static final long serialVersionUID = -4319045348350461073L;

    public static final String FIND_LAST_NOTES = "FIND_LAST_NOTES";
    public static final String FIND_COUNT_NOTES_BEFORE = "FIND_COUNT_NOTES_BEFORE";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NoteSequence")
    @SequenceGenerator(name = "NoteSequence", sequenceName = "SEQ_ID_NOTE", allocationSize = 10)
    @Column(name = "ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIMESTAMP")
    @NotNull
    private Date timestamp;

    @Column(name = "CONTENT")
    @Size(max = 5000)
    private String content;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }
}
