package com.closedsource.psymed.platform.appointmentandadministration.domain.services;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates.Session;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.entities.Note;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.entities.Task;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

/**
 * Service to handle queries related to sessions.
 * This service handles fetching session data based on various criteria.
 */
public interface SessionQueryService {

    /**
     * Handle the query to get all sessions for a specific patient.
     *
     * @param query the query object containing the patient ID
     * @return a list of sessions for the patient
     */
    List<Session> handle(GetAllSessionsByPatientIdQuery query);

    /**
     * Handle the query to get all sessions for a specific professional.
     *
     * @param query the query object containing the professional ID
     * @return a list of sessions for the professional
     */
    List<Session> handle(GetAllSessionsByProfessionalIdQuery query);

    /**
     * Handle the query to get a specific session for a patient by session ID.
     *
     * @param query the query object containing the patient ID and session ID
     * @return an optional session, if found
     */
    Optional<Session> handle(GetSessionByPatientIdAndSessionIdQuery query);

    /**
     * Handle the query to get a specific session by its ID.
     *
     * @param query the query object containing the session ID
     * @return an optional session, if found
     */
    Optional<Session> handle(GetSessionByIdQuery query);


    /**
     * Handle the query to get all sessions.
     *
     * @return a list of all sessions
     */
    List<Session> handle();

    Optional<Note> handle(GetNoteBySessionIdQuery query);

    List<Task> handle(GetAllTasksBySessionIdQuery query);
}
