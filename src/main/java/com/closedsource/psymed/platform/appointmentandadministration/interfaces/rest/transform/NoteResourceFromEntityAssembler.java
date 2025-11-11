package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.entities.Note;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.NoteResource;

public class NoteResourceFromEntityAssembler {
    public static NoteResource toResourceFromEntity (Note entity) {
        return new NoteResource(entity.getId(), entity.getTitle(), entity.getDescription());
    }
}
