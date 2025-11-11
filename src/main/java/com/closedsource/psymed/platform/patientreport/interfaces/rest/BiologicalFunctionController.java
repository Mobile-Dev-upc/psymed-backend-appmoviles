package com.closedsource.psymed.platform.patientreport.interfaces.rest;

import com.closedsource.psymed.platform.patientreport.domain.model.aggregates.BiologicalFunction;
import com.closedsource.psymed.platform.patientreport.domain.model.queries.GetAllBiologicalFunctionsByPatientIdQuery;
import com.closedsource.psymed.platform.patientreport.domain.model.valueobjects.PatientId;
import com.closedsource.psymed.platform.patientreport.domain.services.BiologicalFunctionCommandService;
import com.closedsource.psymed.platform.patientreport.domain.services.BiologicalFunctionQueryService;
import com.closedsource.psymed.platform.patientreport.interfaces.rest.resources.BiologicalFunctionResource;
import com.closedsource.psymed.platform.patientreport.interfaces.rest.resources.CreateBiologicalFunctionRecordResource;
import com.closedsource.psymed.platform.patientreport.interfaces.rest.transform.BiologicalFunctionResourceFromEntityAssembler;
import com.closedsource.psymed.platform.patientreport.interfaces.rest.transform.CreateBiologicalFunctionRecordCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Patient Reports", description = "Controllers for managing patient reports")
public class BiologicalFunctionController {
    private final BiologicalFunctionCommandService biologicalFunctionCommandService;
    private final BiologicalFunctionQueryService biologicalFunctionQueryService;

    public BiologicalFunctionController(BiologicalFunctionCommandService biologicalFunctionCommandService,
                                        BiologicalFunctionQueryService biologicalFunctionQueryService){
        this.biologicalFunctionCommandService = biologicalFunctionCommandService;
        this.biologicalFunctionQueryService = biologicalFunctionQueryService;
    }

    @PostMapping("/patients/{patientId}/biological-functions")
    public ResponseEntity<BiologicalFunctionResource> createBiologicalFunction
            (@PathVariable Long patientId,  @RequestBody CreateBiologicalFunctionRecordResource resource) {
        Optional<BiologicalFunction> biologicalFunction = biologicalFunctionCommandService
                .handle(CreateBiologicalFunctionRecordCommandFromResourceAssembler.toCommandFromResource(resource, patientId));
        return biologicalFunction.map(biological ->
                new ResponseEntity<>(BiologicalFunctionResourceFromEntityAssembler
                        .toResourceFromEntity(biological), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }
//IN THE FUTURE THIS HAS TO WORK WITH PATIENT ID AND PROFESSIONAL ID
    @GetMapping("/patients/{patientId}/biological-functions")
    public ResponseEntity<List<BiologicalFunctionResource>> getAllBiologicalFunctionsByPatientId(@PathVariable Long patientId) {
        var patientIdConstructed = new PatientId(patientId);
        var getAllBiologicalFunctionsByPatientIdQuery = new GetAllBiologicalFunctionsByPatientIdQuery(patientIdConstructed);
        var biologicalFunctions = biologicalFunctionQueryService.handle(getAllBiologicalFunctionsByPatientIdQuery);
        var biologicalFunctionsResources = biologicalFunctions.stream()
                .map(BiologicalFunctionResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(biologicalFunctionsResources);
    }

}
