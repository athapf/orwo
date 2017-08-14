package de.thaso.orwo.fe.bean.overview;

import java.io.Serializable;
import java.util.Date;

/**
 * OverviewRowModel
 *
 * @author thaler
 * @since 2017-08-01
 */
public class OverviewRowModel implements Serializable {

    private static final long serialVersionUID = -6458924245986614746L;

    private Long id;
    private Date timestamp;
    private String title;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }
}
