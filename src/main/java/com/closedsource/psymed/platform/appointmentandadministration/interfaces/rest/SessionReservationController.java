package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates.Session;
import com.closedsource.psymed.platform.appointmentandadministration.domain.services.SessionCommandService;
import com.closedsource.psymed.platform.appointmentandadministration.domain.services.SessionQueryService;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.CreateSessionResource;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.SessionResource;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform.CreateSessionCommandFromResourceAssembler;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform.SessionResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/professionals/{professionalId}/patients/{patientId}/sessions", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Professional Sessions")
public class SessionReservationController {
    private final SessionCommandService sessionCommandService;
    private final SessionQueryService sessionQueryService;

    public SessionReservationController(SessionCommandService sessionCommandService, SessionQueryService sessionQueryService) {
        this.sessionCommandService = sessionCommandService;
        this.sessionQueryService = sessionQueryService;
    }

    @Operation(summary = "Make a reservation for patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation created successfully"),
            @ApiResponse(responseCode = "404", description = "Patient or professional not found")})
    @PostMapping
    public ResponseEntity<SessionResource>
    createSession(@PathVariable Long professionalId, @PathVariable Long patientId,  @RequestBody CreateSessionResource resource) {
        Optional<Session> session = sessionCommandService.handle(
                CreateSessionCommandFromResourceAssembler.toCommandFromResource(patientId, professionalId, resource));
        return session
                .map(s -> new ResponseEntity<>(SessionResourceFromEntityAssembler.toResourceFromEntity(s), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
