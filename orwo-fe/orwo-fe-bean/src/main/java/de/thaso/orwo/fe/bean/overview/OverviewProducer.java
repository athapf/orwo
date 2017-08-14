package de.thaso.orwo.fe.bean.overview;

import de.thaso.orwo.be.note.service.NoteData;
import de.thaso.orwo.be.note.service.NoteService;
import de.thaso.orwo.fe.bean.overview.utility.AdoptNote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

/**
 * OverviewProducer
 *
 * @author thaler
 * @since 2017-06-06
 */
@ApplicationScoped
public class OverviewProducer {
    private static final Logger LOG = LoggerFactory.getLogger(OverviewProducer.class);

    @EJB
    private NoteService noteService;

    @Inject
    private AdoptNote adoptNote;

    @Produces
    @RequestScoped
    @Named("overviewTableModel")
    public OverviewTableModel produceOverviewModel(@New OverviewTableModel overviewTableModel) {
        LOG.info("produce overview table model ...");

        final List<NoteData> noteDataList = noteService.findNotesBefore(new Date());
        for (NoteData noteData : noteDataList) {
            overviewTableModel.getOverviewRowModelList().add(createRowModel(noteData));
        }

        return overviewTableModel;
    }

    private OverviewRowModel createRowModel(final NoteData noteData) {
        return adoptNote.adoptNote(noteData);
    }
}
