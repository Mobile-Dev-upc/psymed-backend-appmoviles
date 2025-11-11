package com.closedsource.psymed.platform.appointmentandadministration.application.internal.outboundservices.acl;

import com.closedsource.psymed.platform.appointmentandadministration.application.internal.outboundservices.AppointmentVersionOfExternalProfileService;
import com.closedsource.psymed.platform.profiles.interfaces.acl.ProfilesContextFacade;
import com.closedsource.psymed.platform.profiles.interfaces.acl.service.ProfilesContextFacadeImpl;
import org.springframework.stereotype.Service;

@Service
public class AppointmentVersionOfExternalProfileServiceImpl implements AppointmentVersionOfExternalProfileService {
    private final ProfilesContextFacade profilesContextFacade;

    public AppointmentVersionOfExternalProfileServiceImpl(ProfilesContextFacadeImpl profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    public boolean existsPatientById(Long patientId) {
        return profilesContextFacade.verifyPatientProfile(patientId);
    }

    public boolean existsProfessionalById(Long professionalId) {
        return profilesContextFacade.verifyProfessionalProfile(professionalId); // Assuming this method exists in ProfilesContextFacade
    }
}
