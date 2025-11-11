package com.closedsource.psymed.platform.appointmentandadministration.application.internal.queryservices;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates.Session;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.entities.Note;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.entities.Task;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries.*;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.PatientId;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.ProfessionalId;
import com.closedsource.psymed.platform.appointmentandadministration.domain.services.SessionQueryService;
import com.closedsource.psymed.platform.appointmentandadministration.infrastructure.persistence.jpa.repositories.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionQueryServiceImpl implements SessionQueryService {

    private final SessionRepository sessionRepository;

    public SessionQueryServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public List<Session> handle(GetAllSessionsByPatientIdQuery query) {

        Long patientIdAsLong = Long.parseLong(query.patientId()); // Convert String to Long

        return sessionRepository.findAllByPatientId(new PatientId(patientIdAsLong));
    }

    @Override
    public List<Session> handle(GetAllSessionsByProfessionalIdQuery query) {

        Long professionalIdAsLong = Long.parseLong(query.professionalId()); // Convert String to Long
        return sessionRepository.findAllByProfessionalId(new ProfessionalId(professionalIdAsLong));
    }

    @Override
    public Optional<Session> handle(GetSessionByPatientIdAndSessionIdQuery query) {
        Long patientIdAsLong = Long.parseLong(query.patientId()); // Convert String to Long
        return sessionRepository.findByPatientIdAndId(new PatientId(patientIdAsLong), query.id());
    }

    @Override
    public Optional<Session> handle(GetSessionByIdQuery query) {
        return sessionRepository.findById(query.id());
    }

    @Override
    public List<Session> handle() {
        return sessionRepository.findAll();
    }

    @Override
    public Optional<Note> handle(GetNoteBySessionIdQuery query) {
        if(!sessionRepository.existsById(query.sessionId())) {
            throw new IllegalArgumentException("Session with id " + query.sessionId() + " does not exist.");
        }
        return sessionRepository.findById(query.sessionId())
                .map(Session::getNote);
    }

    @Override
    public List<Task> handle(GetAllTasksBySessionIdQuery query) {
        if(!sessionRepository.existsById(query.sessionId())) {
            throw new IllegalArgumentException("Session with id " + query.sessionId() + " does not exist.");
        }
        return sessionRepository.findById(query.sessionId()).stream()
                .map(Session::getTasks)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Session does not have any tasks."));
    }
}
