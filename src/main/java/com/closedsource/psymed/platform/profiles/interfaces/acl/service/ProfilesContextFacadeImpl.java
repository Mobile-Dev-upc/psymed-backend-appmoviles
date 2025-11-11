package com.closedsource.psymed.platform.profiles.interfaces.acl.service;

import com.closedsource.psymed.platform.profiles.domain.model.commands.CheckPatientProfileByIdCommand;
import com.closedsource.psymed.platform.profiles.domain.model.commands.CheckProfessionalProfileByIdCommand;
import com.closedsource.psymed.platform.profiles.domain.model.queries.GetClinicalHistoryIdByPatientIdQuery;
import com.closedsource.psymed.platform.profiles.domain.services.PatientProfileCommandService;
import com.closedsource.psymed.platform.profiles.domain.services.PatientProfileQueryService;
import com.closedsource.psymed.platform.profiles.domain.services.ProfessionalProfileCommandService;
import com.closedsource.psymed.platform.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ProfilesContextFacadeImpl implements ProfilesContextFacade {
    //#region DI
    private final PatientProfileCommandService patientProfileCommandService;
    private final ProfessionalProfileCommandService professionalProfileCommandService;
    private final PatientProfileQueryService patientProfileQueryService;

    public ProfilesContextFacadeImpl(PatientProfileCommandService patientProfileCommandService,
                                     ProfessionalProfileCommandService professionalProfileCommandService, PatientProfileQueryService patientProfileQueryService) {
        this.patientProfileCommandService = patientProfileCommandService;
        this.professionalProfileCommandService = professionalProfileCommandService;
        this.patientProfileQueryService = patientProfileQueryService;
    }

    //#endregion

    public boolean verifyPatientProfile(Long patientId) {
        var verifyPatientCommand = new CheckPatientProfileByIdCommand(patientId);
        var exists = patientProfileCommandService.handle(verifyPatientCommand);
        //To improve
        if(!exists) {
            throw new RuntimeException("Patient profile does not exist");
        }
        return exists;
    }

    public boolean verifyProfessionalProfile(Long professionalId) {
        var verifyProfessionalCommand = new CheckProfessionalProfileByIdCommand(professionalId);
        var exists = professionalProfileCommandService.handle(verifyProfessionalCommand);
        //To improve
        if(!exists) {
            throw new RuntimeException("Professional profile does not exist");
        }
        return exists;
    }

    @Override
    public Long fetchClinicalHistoryIdByPatientId(Long patientId) {
        if(this.verifyPatientProfile(patientId)){
            var getClinicalHistoryIdByPatientIdQuery = new GetClinicalHistoryIdByPatientIdQuery(patientId);
            return patientProfileQueryService.handle(getClinicalHistoryIdByPatientIdQuery);
        }
        return null;
    }
}
