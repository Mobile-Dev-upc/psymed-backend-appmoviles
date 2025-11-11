package com.closedsource.psymed.platform.clinicalhistory.interfaces.acl;

import java.time.LocalDate;

public interface ClinicalHistoryContextFacade {
    public long createClinicalHistory(String background, String consultationReason, LocalDate consultationDate);
}
