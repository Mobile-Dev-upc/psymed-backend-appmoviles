package com.closedsource.psymed.platform.appointmentandadministration.domain.services;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates.Session;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.AddNoteToSessionCommand;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.AddTaskToSessionCommand;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.CreateSessionCommand;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.UpdateTaskStatusToCompleteCommand;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.UpdateTaskCommand;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.DeleteTaskCommand;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.entities.Note;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.entities.Task;

import java.util.Optional;

/**
 * Service to handle commands related to appointments.
 * @summary
 * This service is responsible for handling commands related to creating, updating, and deleting appointments.
 */
public interface SessionCommandService {

    /**
     * Handle the command to create an appointment.
     *
     * @param command the command to create an appointment
     * @return the created appointment
     * @throws IllegalArgumentException if the command is invalid
     * @see CreateSessionCommand
     */
    Optional<Session> handle(CreateSessionCommand command);


    Optional<Note> handle(AddNoteToSessionCommand command);

    Optional<Task> handle(AddTaskToSessionCommand command);

    void handle(UpdateTaskStatusToCompleteCommand command);

    Optional<Task> handle(UpdateTaskCommand command);

    void handle(DeleteTaskCommand command);
}
