package com.closedsource.psymed.platform.clinicalhistory.interfaces.rest.transform;

import com.closedsource.psymed.platform.clinicalhistory.domain.model.aggregates.ClinicalHistory;
import com.closedsource.psymed.platform.clinicalhistory.interfaces.rest.resources.ClinicalHistoryResource;

public class ClinicalHistoryResourceFromEntityAssembler {
    public static ClinicalHistoryResource toResourceFromEntity(ClinicalHistory entity) {
        return new ClinicalHistoryResource(
                entity.getId(),
                entity.getBackground(),
                entity.getConsultationReason(),
                entity.getConsultationDate()
        );
    }

}
