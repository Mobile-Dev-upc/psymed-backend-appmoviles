package com.closedsource.psymed.platform.appointmentandadministration.application.internal.commandservices;

import com.closedsource.psymed.platform.appointmentandadministration.application.internal.outboundservices.AppointmentVersionOfExternalProfileService;
import com.closedsource.psymed.platform.appointmentandadministration.application.internal.outboundservices.acl.AppointmentVersionOfExternalProfileServiceImpl;
import com.closedsource.psymed.platform.appointmentandadministration.domain.exceptions.PatientNotFoundException;
import com.closedsource.psymed.platform.appointmentandadministration.domain.exceptions.ProfessionalNotFoundException;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates.Session;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.AddNoteToSessionCommand;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.AddTaskToSessionCommand;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.CreateSessionCommand;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.UpdateTaskStatusToCompleteCommand;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.entities.Note;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.entities.Task;
import com.closedsource.psymed.platform.appointmentandadministration.domain.services.SessionCommandService;
import com.closedsource.psymed.platform.appointmentandadministration.infrastructure.persistence.jpa.repositories.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SessionCommandServiceImpl implements SessionCommandService {
    private final SessionRepository sessionRepository;
    private final AppointmentVersionOfExternalProfileService externalProfileService; // Service to check patient and professional existence

    public SessionCommandServiceImpl(SessionRepository sessionRepository, AppointmentVersionOfExternalProfileServiceImpl externalProfileService) {
        this.sessionRepository = sessionRepository;
        this.externalProfileService = externalProfileService;
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public Optional<Session> handle(CreateSessionCommand command) {
        // Check if the patient and professional exist
        boolean patientExists = externalProfileService.existsPatientById(command.patientId());
        boolean professionalExists = externalProfileService.existsProfessionalById(command.professionalId());

        if (!patientExists) {
            throw new PatientNotFoundException(command.patientId()); // Custom exception for patient not found
        }
        if (!professionalExists) {
            throw new ProfessionalNotFoundException(command.professionalId()); // Custom exception for professional not found
        }

        // Create a new session based on the command
        var session = new Session(command);
        var createdSession = sessionRepository.save(session);
        return Optional.of(createdSession);
    }

    @Override
    public Optional<Note> handle(AddNoteToSessionCommand command) {

        var session = sessionRepository.findById(command.sessionId());
        if(session.isEmpty()) throw new IllegalStateException("Session does not exist");
        var sessionInstance = session.get();

        if(sessionInstance.getNote() != null) throw new IllegalStateException("Session already has a note");

        sessionInstance.addNoteToSession(command.title(), command.description());

        var note = sessionInstance.getNote();
        try {
            sessionRepository.save(sessionInstance);
            return Optional.of(note);
        } catch(Exception e) {
            throw new IllegalArgumentException("Error adding a session: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public Optional<Task> handle(AddTaskToSessionCommand command) {
        if(!sessionRepository.existsById(command.sessionId())) throw new IllegalArgumentException("Session does not exist");
        try {
            var session = sessionRepository.findById(command.sessionId()).get();
            session.addTaskToSession(command.title(), command.description());
            sessionRepository.save(session);

            var ActualTask = session.getLastTask();

            return Optional.of(ActualTask);
        } catch(Exception e) {
            throw new IllegalArgumentException("Error adding a task: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public void handle(UpdateTaskStatusToCompleteCommand command) {
        if(!sessionRepository.existsById(command.sessionId())) throw new IllegalArgumentException("Session does not exist");
        try{
            var session = sessionRepository.findById(command.sessionId()).get();
            var task = session.getTaskById(command.taskId());
            task.completeTask();
            sessionRepository.save(session);
        } catch(Exception e) {
            throw new IllegalArgumentException("Error updating task status: %s".formatted(e.getMessage()));
        }
    }

}
