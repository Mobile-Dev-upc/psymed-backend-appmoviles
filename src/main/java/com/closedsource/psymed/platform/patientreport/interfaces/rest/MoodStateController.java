package com.closedsource.psymed.platform.patientreport.interfaces.rest;

import com.closedsource.psymed.platform.patientreport.domain.model.aggregates.MoodState;
import com.closedsource.psymed.platform.patientreport.domain.model.queries.GetAllMoodStatesByPatientIdQuery;
import com.closedsource.psymed.platform.patientreport.domain.model.valueobjects.PatientId;
import com.closedsource.psymed.platform.patientreport.domain.services.MoodStateCommandService;
import com.closedsource.psymed.platform.patientreport.domain.services.MoodStateQueryService;
import com.closedsource.psymed.platform.patientreport.interfaces.rest.resources.CreateMoodStateRecordResource;
import com.closedsource.psymed.platform.patientreport.interfaces.rest.resources.MoodStateResource;
import com.closedsource.psymed.platform.patientreport.interfaces.rest.transform.CreateMoodStateRecordCommandFromResourceAssembler;
import com.closedsource.psymed.platform.patientreport.interfaces.rest.transform.MoodStateResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value="api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Patient Reports")
public class MoodStateController {
    private final MoodStateCommandService moodStateCommandService;
    private final MoodStateQueryService moodStateQueryService;

    public MoodStateController (MoodStateCommandService moodStateCommandService,
                                MoodStateQueryService moodStateQueryService) {
        this.moodStateCommandService = moodStateCommandService;
        this.moodStateQueryService = moodStateQueryService;
    }

    @PostMapping("/patients/{patientId}/mood-states")
        public ResponseEntity<MoodStateResource> createMoodState(@PathVariable Long patientId,  @RequestBody CreateMoodStateRecordResource resource) {
        Optional<MoodState> moodState = moodStateCommandService
                .handle(CreateMoodStateRecordCommandFromResourceAssembler.toCommandFromResource(resource, patientId));
        return moodState.map(mood -> new ResponseEntity<>(MoodStateResourceFromEntityAssembler
                        .toResourceFromEntity(mood), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //IN THE FUTURE THIS HAS TO WORK WITH PATIENT ID AND PROFESSIONAL ID

    @GetMapping("/patients/{patientId}/mood-states")
    public ResponseEntity<List<MoodStateResource>> getAllMoodStatesByPatientId(@PathVariable Long patientId) {
        var patientIdConstructed = new PatientId(patientId);
        var getAllMoodStatesByPatientIdQuery = new GetAllMoodStatesByPatientIdQuery(patientIdConstructed);
        var moodStates = moodStateQueryService.handle(getAllMoodStatesByPatientIdQuery);
        var moodStatesResources = moodStates.stream()
                .map(MoodStateResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(moodStatesResources);
    }
}
