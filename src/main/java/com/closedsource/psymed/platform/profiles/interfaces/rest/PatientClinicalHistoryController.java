package com.closedsource.psymed.platform.profiles.interfaces.rest;

import com.closedsource.psymed.platform.clinicalhistory.domain.model.aggregates.ClinicalHistory;
import com.closedsource.psymed.platform.clinicalhistory.interfaces.rest.resources.ClinicalHistoryResource;
import com.closedsource.psymed.platform.clinicalhistory.interfaces.rest.resources.CreateClinicalHistoryResource;
import com.closedsource.psymed.platform.clinicalhistory.interfaces.rest.transform.ClinicalHistoryResourceFromEntityAssembler;
import com.closedsource.psymed.platform.clinicalhistory.interfaces.rest.transform.CreateClinicalHistoryCommandFromResourceAssembler;
import com.closedsource.psymed.platform.profiles.domain.services.PatientProfileCommandService;
import com.closedsource.psymed.platform.profiles.domain.services.PatientProfileQueryService;
import com.closedsource.psymed.platform.profiles.interfaces.rest.resources.AddClinicalHistoryToPatientResource;
import com.closedsource.psymed.platform.profiles.interfaces.rest.transform.AddClinicalHistoryToPatientCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1/patients/{patientId}/clinical-histories", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Patients Clinical History")
public class PatientClinicalHistoryController {
    private final PatientProfileCommandService patientProfileCommandService;
    private final PatientProfileQueryService patientProfileQueryService;

    public PatientClinicalHistoryController(PatientProfileCommandService patientProfileCommandService, PatientProfileQueryService patientProfileQueryService) {
        this.patientProfileCommandService = patientProfileCommandService;
        this.patientProfileQueryService = patientProfileQueryService;
    }

    @Operation(summary = "Create a new clinical history for a patient", description = "Create a new clinical history with the given data")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "The clinical history was created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PostMapping
    public ResponseEntity<String> createClinicalHistory(@PathVariable Long patientId, @RequestBody AddClinicalHistoryToPatientResource resource) {
        var command = AddClinicalHistoryToPatientCommandFromResourceAssembler.toCommandFromResource(patientId, resource);
        patientProfileCommandService.handle(command);
        return ResponseEntity.ok("Clinical history added successfully");

    }
}
