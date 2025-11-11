package com.closedsource.psymed.platform.profiles.application.outboundservices.acl;

import com.closedsource.psymed.platform.clinicalhistory.interfaces.acl.ClinicalHistoryContextFacade;
import com.closedsource.psymed.platform.profiles.application.outboundservices.ExternalClinicalHistoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ExternalClinicalHistoryServiceImpl implements ExternalClinicalHistoryService {
    private final ClinicalHistoryContextFacade clinicalHistoryContextFacade;

    public ExternalClinicalHistoryServiceImpl(ClinicalHistoryContextFacade clinicalHistoryContextFacade) {
        this.clinicalHistoryContextFacade = clinicalHistoryContextFacade;
    }

    @Override
    public long createClinicalHistory(String background, String consultationReason, LocalDate consultationDate) {
        return clinicalHistoryContextFacade.createClinicalHistory(background, consultationReason, consultationDate);
    }
}
