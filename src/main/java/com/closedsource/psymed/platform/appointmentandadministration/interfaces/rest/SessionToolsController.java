package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.entities.Note;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.entities.Task;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries.GetAllTasksBySessionIdQuery;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries.GetNoteBySessionIdQuery;
import com.closedsource.psymed.platform.appointmentandadministration.domain.services.SessionCommandService;
import com.closedsource.psymed.platform.appointmentandadministration.domain.services.SessionQueryService;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.AddNoteToSessionResource;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.AddTaskToSessionResource;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.NoteResource;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.TaskResource;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.UpdateTaskResource;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.UpdateNoteResource;
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

    @Operation(summary = "Get note from a session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note found"),
            @ApiResponse(responseCode = "404", description = "Session not found or session has no note"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @GetMapping("/notes")
    public ResponseEntity<NoteResource> getNoteBySessionId(@PathVariable Long sessionId) {
        var getNoteBySessionIdQuery = new GetNoteBySessionIdQuery(sessionId);
        var note = sessionQueryService.handle(getNoteBySessionIdQuery);
        return note.map(n -> ResponseEntity.ok(NoteResourceFromEntityAssembler.toResourceFromEntity(n)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update note from a session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note updated successfully"),
            @ApiResponse(responseCode = "404", description = "Session not found or session has no note"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/notes")
    public ResponseEntity<NoteResource> updateNoteFromSession(@PathVariable Long sessionId, @RequestBody UpdateNoteResource resource) {
        var command = UpdateNoteCommandFromResourceAssembler.toCommandFromResource(sessionId, resource);
        var note = sessionCommandService.handle(command);
        return note.map(n -> ResponseEntity.ok(NoteResourceFromEntityAssembler.toResourceFromEntity(n)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Delete note from a session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Note deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Session not found or session has no note"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @DeleteMapping("/notes")
    public ResponseEntity<Void> deleteNoteFromSession(@PathVariable Long sessionId) {
        var command = new com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.DeleteNoteFromSessionCommand(sessionId);
        sessionCommandService.handle(command);
        return ResponseEntity.noContent().build();
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

    @Operation(summary="Mark task as incomplete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task marked as incomplete"),
            @ApiResponse(responseCode = "404", description = "Session not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/tasks/{taskId}/incomplete")
    public ResponseEntity<String> updateTaskStatusToIncomplete(@PathVariable Long sessionId, @PathVariable  Long taskId) {
        var command = UpdateTaskStatusToIncompleteCommandFromResourceAssembler.toCommandFromResource(sessionId, taskId);
        sessionCommandService.handle(command);
        return ResponseEntity.ok("Task status updated to incomplete");
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResource>> getAllTasksBySessionId(@PathVariable Long sessionId) {
        var getAllTasksBySessionIdQuery = new GetAllTasksBySessionIdQuery(sessionId);
        var tasks = sessionQueryService.handle(getAllTasksBySessionIdQuery);
        var taskResources = tasks.stream().map(TaskResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(taskResources);
    }

    @Operation(summary="Update a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully"),
            @ApiResponse(responseCode = "404", description = "Session or task not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<TaskResource> updateTask(@PathVariable Long sessionId, @PathVariable Long taskId, @RequestBody UpdateTaskResource resource) {
        var command = UpdateTaskCommandFromResourceAssembler.toCommandFromResource(sessionId, taskId, resource);
        var task = sessionCommandService.handle(command);
        return task.map(t -> ResponseEntity.ok(TaskResourceFromEntityAssembler.toResourceFromEntity(t)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary="Delete a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Session or task not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long sessionId, @PathVariable Long taskId) {
        var command = DeleteTaskCommandFromResourceAssembler.toCommandFromResource(sessionId, taskId);
        sessionCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }

}
