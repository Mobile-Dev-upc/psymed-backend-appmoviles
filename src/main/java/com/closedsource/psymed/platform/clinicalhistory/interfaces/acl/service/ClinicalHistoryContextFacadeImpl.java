package com.closedsource.psymed.platform.clinicalhistory.interfaces.acl.service;

import com.closedsource.psymed.platform.clinicalhistory.domain.model.commands.CreateClinicalHistoryCommand;
import com.closedsource.psymed.platform.clinicalhistory.domain.service.ClinicalHistoryCommandService;
import com.closedsource.psymed.platform.clinicalhistory.interfaces.acl.ClinicalHistoryContextFacade;
import com.closedsource.psymed.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ClinicalHistoryContextFacadeImpl implements ClinicalHistoryContextFacade {
    private final ClinicalHistoryCommandService clinicalHistoryCommandService;

    public ClinicalHistoryContextFacadeImpl(ClinicalHistoryCommandService clinicalHistoryCommandService) {
        this.clinicalHistoryCommandService = clinicalHistoryCommandService;
    }

    @Override
    public long createClinicalHistory(String background, String consultationReason, LocalDate consultationDate) {
        var createClinicalHistoryCommand = new CreateClinicalHistoryCommand(background, consultationReason, consultationDate);
        //I'm getting the id
        return clinicalHistoryCommandService.handle(createClinicalHistoryCommand)
                .map(AuditableAbstractAggregateRoot::getId).orElseThrow();
    }
}
