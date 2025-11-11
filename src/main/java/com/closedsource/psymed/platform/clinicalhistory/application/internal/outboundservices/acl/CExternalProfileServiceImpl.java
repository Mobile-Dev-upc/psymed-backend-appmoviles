package com.closedsource.psymed.platform.clinicalhistory.application.internal.outboundservices.acl;

import com.closedsource.psymed.platform.clinicalhistory.application.internal.outboundservices.CExternalProfileService;
import com.closedsource.psymed.platform.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

@Service
public class CExternalProfileServiceImpl implements CExternalProfileService {
    private final ProfilesContextFacade profilesContextFacade;

    public CExternalProfileServiceImpl(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    @Override
    public Long getClinicalHistoryIdByPatientId(long patientId) {
        return profilesContextFacade.fetchClinicalHistoryIdByPatientId(patientId);
    }
}
