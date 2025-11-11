package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries.GetAllSessionsByProfessionalIdQuery;
import com.closedsource.psymed.platform.appointmentandadministration.domain.services.SessionQueryService;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.SessionResource;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform.SessionResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/professionals/{professionalId}/sessions", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Professional Sessions",  description = "Controller for manage sessions for a professional")
public class ProfessionalSessionController {
    private final SessionQueryService sessionQueryService;

    public ProfessionalSessionController(SessionQueryService sessionQueryService) {
        this.sessionQueryService = sessionQueryService;
    }

    @Operation(summary = "Get all sessions for a professional",
            description = "Retrieve all sessions for a given professional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessions found"),
            @ApiResponse(responseCode = "404", description = "No sessions found for the professional")
    })
    @GetMapping
    public ResponseEntity<List<SessionResource>> getAllSessionsByProfessionalId(@PathVariable String professionalId) {
        var query = new GetAllSessionsByProfessionalIdQuery(professionalId);
        var sessions = sessionQueryService.handle(query);
        if (sessions.isEmpty()) return ResponseEntity.notFound().build();
        var sessionResources = sessions.stream()
                .map(SessionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(sessionResources);
    }
}
