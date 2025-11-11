package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources;

import java.util.Date;

public record NoteResource(Long noteId, String title, String description, Date createdAt, Date updatedAt) {
}
