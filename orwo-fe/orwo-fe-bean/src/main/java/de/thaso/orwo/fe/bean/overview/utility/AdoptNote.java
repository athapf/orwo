package de.thaso.orwo.fe.bean.overview.utility;

import de.thaso.orwo.be.note.service.NoteData;
import de.thaso.orwo.fe.bean.overview.OverviewRowModel;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;

/**
 * AdoptNote
 *
 * @author thaler
 * @since 2017-08-07
 */
@ApplicationScoped
public class AdoptNote {

    public OverviewRowModel adoptNote(final NoteData noteData) {
        final OverviewRowModel rowData = new OverviewRowModel();

        rowData.setTimestamp(noteData.getTimestamp());
        rowData.setTitle(splitToTitle(noteData.getContent()));

        return rowData;
    }

    private String splitToTitle(final String content) {
        if(StringUtils.isNoneEmpty(content)) {
            return content.split("(\\r\\n|\\n|\\r)")[0];
        }
        return null;
    }
}
