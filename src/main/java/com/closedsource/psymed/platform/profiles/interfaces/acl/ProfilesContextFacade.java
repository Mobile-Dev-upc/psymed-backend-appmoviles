package com.closedsource.psymed.platform.profiles.interfaces.acl;

public interface ProfilesContextFacade {
    public boolean verifyPatientProfile(Long patientId);
    public boolean verifyProfessionalProfile(Long professionalId);
    public Long fetchClinicalHistoryIdByPatientId(Long patientId);
}
