package com.closedsource.psymed.platform.appointmentandadministration.infrastructure.persistence.jpa.repositories;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates.Session;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.entities.Note;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.PatientId;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.ProfessionalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    /**
     * Find all sessions by patient ID.
     *
     * @param patientId The value object of PatientId
     * @return a list of sessions for the specified patient
     */
    List<Session> findAllByPatientId(PatientId patientId);

    /**
     * Find all sessions by professional ID.
     *
     * @param professionalId The value object of ProfessionalId
     * @return a list of sessions for the specified professional
     */
    List<Session> findAllByProfessionalId(ProfessionalId professionalId);

    /**
     * Find a specific session by patient ID and session ID.
     *
     * @param patientId The value object of PatientId
     * @param sessionId The unique ID of the session (Long)
     * @return an optional session if found
     */
    Optional<Session> findByPatientIdAndId(PatientId patientId, Long sessionId);

    boolean existsByNote(Note note);

}
