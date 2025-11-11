package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.entities.Note;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.entities.Task;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries.GetAllTasksBySessionIdQuery;
import com.closedsource.psymed.platform.appointmentandadministration.domain.services.SessionCommandService;
import com.closedsource.psymed.platform.appointmentandadministration.domain.services.SessionQueryService;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.AddNoteToSessionResource;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.AddTaskToSessionResource;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.NoteResource;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.TaskResource;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1/sessions/{sessionId}")
@Tag(name = "Session Tools", description = "Controller for manage notes and tasks of a session")
public class SessionToolsController {
    private final SessionCommandService sessionCommandService;
    private final SessionQueryService sessionQueryService;


    public SessionToolsController(SessionCommandService sessionCommandService, SessionQueryService sessionQueryService) {
        this.sessionCommandService = sessionCommandService;
        this.sessionQueryService = sessionQueryService;
    }

    @Operation(summary = "Add note to session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note added to session"),
            @ApiResponse(responseCode = "404", description = "Session not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/notes")
    public ResponseEntity<NoteResource> addNoteToSession(@PathVariable Long sessionId, @RequestBody AddNoteToSessionResource resource) {
        Optional<Note> note = sessionCommandService
                .handle(AddNoteToSessionCommandFromResourceAssembler.toCommandFromResource(sessionId, resource));
        return note.map(n ->
                new ResponseEntity<>(NoteResourceFromEntityAssembler
                        .toResourceFromEntity(n), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @Operation(summary="Add task to a session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task added to session"),
            @ApiResponse(responseCode = "404", description = "Session not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })

    @PostMapping("/tasks")
    public ResponseEntity<TaskResource> addTaskToSession(@PathVariable Long sessionId, @RequestBody AddTaskToSessionResource resource) {
        Optional<Task> task = sessionCommandService
                .handle(AddTaskToSessionCommandFromResourceAssembler.toCommandFromResource(sessionId, resource));
        return task.map(t ->
                new ResponseEntity<>(TaskResourceFromEntityAssembler.toResourceFromEntity(t), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary="Check task as complete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task added to session"),
            @ApiResponse(responseCode = "404", description = "Session not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })

    @PostMapping("/tasks/{taskId}/complete")
    public ResponseEntity<String> updateTaskStatusToComplete(@PathVariable Long sessionId, @PathVariable  Long taskId) {
        var command = UpdateTaskStatusToCompleteCommandFromResourceAssembler.toCommandFromResource(sessionId, taskId);
        sessionCommandService.handle(command);
        return ResponseEntity.ok("Task status updated to complete");
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResource>> getAllTasksBySessionId(@PathVariable Long sessionId) {
        var getAllTasksBySessionIdQuery = new GetAllTasksBySessionIdQuery(sessionId);
        var tasks = sessionQueryService.handle(getAllTasksBySessionIdQuery);
        var taskResources = tasks.stream().map(TaskResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(taskResources);
    }

}
