package com.closedsource.psymed.platform.clinicalhistory.interfaces;

import com.closedsource.psymed.platform.clinicalhistory.domain.model.queries.GetClinicalHistoryByPatientIdQuery;
import com.closedsource.psymed.platform.clinicalhistory.domain.service.ClinicalHistoryQueryService;
import com.closedsource.psymed.platform.clinicalhistory.interfaces.rest.resources.ClinicalHistoryResource;
import com.closedsource.psymed.platform.clinicalhistory.interfaces.rest.transform.ClinicalHistoryResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="api/v1")
@Tag(name = "Patients Clinical History")
public class ClinicalHistoryController {

    private final ClinicalHistoryQueryService clinicalHistoryQueryService;

    public ClinicalHistoryController(ClinicalHistoryQueryService clinicalHistoryQueryService) {
        this.clinicalHistoryQueryService = clinicalHistoryQueryService;
    }


    @GetMapping("/patients/{patientId}/clinical-histories")
    public ResponseEntity<ClinicalHistoryResource> getClinicalHistoryByPatientId(@PathVariable Long patientId) {
        var getClinicalHistoryByPatientIdQuery = new GetClinicalHistoryByPatientIdQuery(patientId);
        var clinicalHistory = clinicalHistoryQueryService.handle(getClinicalHistoryByPatientIdQuery);
        if(clinicalHistory.isEmpty()) return ResponseEntity.notFound().build();
        var clinicalHistoryResource = ClinicalHistoryResourceFromEntityAssembler.toResourceFromEntity(clinicalHistory.get());
        return ResponseEntity.ok(clinicalHistoryResource);
    }

}
