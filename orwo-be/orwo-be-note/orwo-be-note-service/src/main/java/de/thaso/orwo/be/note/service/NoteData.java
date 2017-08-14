package de.thaso.orwo.be.note.service;

import java.io.Serializable;
import java.util.Date;

/**
 * NickNameData
 *
 * @author thaler
 * @since 21.09.16
 */
public class NoteData implements Serializable {

    private static final long serialVersionUID = -7049489388682590929L;

    private Long id;
    private Date timestamp;
    private String content;

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
