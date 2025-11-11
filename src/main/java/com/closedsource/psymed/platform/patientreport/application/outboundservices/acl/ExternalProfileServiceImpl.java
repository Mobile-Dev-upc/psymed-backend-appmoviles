package com.closedsource.psymed.platform.patientreport.application.outboundservices.acl;

import com.closedsource.psymed.platform.patientreport.application.outboundservices.ExternalProfileService;
import com.closedsource.psymed.platform.profiles.interfaces.acl.ProfilesContextFacade;
import com.closedsource.psymed.platform.profiles.interfaces.acl.service.ProfilesContextFacadeImpl;
import org.springframework.stereotype.Service;

@Service
public class ExternalProfileServiceImpl implements ExternalProfileService {
    private final ProfilesContextFacade profilesContextFacade;

    public ExternalProfileServiceImpl(ProfilesContextFacadeImpl profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    public boolean existsPatientById(Long patientId) {
        return  profilesContextFacade.verifyPatientProfile(patientId);
    }
}
