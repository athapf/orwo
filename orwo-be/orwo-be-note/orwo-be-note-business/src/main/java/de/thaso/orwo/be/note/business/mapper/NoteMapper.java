package de.thaso.orwo.be.note.business.mapper;

import de.thaso.orwo.be.note.service.NoteData;
import de.thaso.orwo.db.api.note.NoteEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * NoteMapper
 *
 * @author thaler
 * @since 2016-09-22
 */
@Mapper
public interface NoteMapper {

    NoteData noteToDO(NoteEntity noteEntity);

    NoteEntity noteToEntity(NoteData noteData);

    List<NoteData> noteListToDOList(List<NoteEntity> noteEntityList);
}
