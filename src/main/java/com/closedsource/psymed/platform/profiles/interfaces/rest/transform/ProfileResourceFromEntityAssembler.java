package com.closedsource.psymed.platform.profiles.interfaces.rest.transform;

import com.closedsource.psymed.platform.profiles.domain.model.aggregates.PatientProfile;
import com.closedsource.psymed.platform.profiles.domain.model.aggregates.ProfessionalProfile;
import com.closedsource.psymed.platform.profiles.domain.model.valueobjects.AccountId;
import com.closedsource.psymed.platform.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(PatientProfile entity) {
        return new ProfileResource(entity.getId(), entity.getFullName(), entity.getEmail(), entity.getStreetAddress(),entity.getAccountId(), entity.getProfessionalId());
    }


    public static ProfileResource toResourceFromEntity(ProfessionalProfile entity){
        return new ProfileResource(entity.getId(), entity.getFullName(), entity.getEmail(), entity.getStreetAddress(), entity.getAccountId(), entity.getId());
    }
}

