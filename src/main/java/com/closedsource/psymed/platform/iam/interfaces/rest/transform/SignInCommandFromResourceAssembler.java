package com.closedsource.psymed.platform.iam.interfaces.rest.transform;

import com.closedsource.psymed.platform.iam.domain.model.commands.SignInCommand;
import com.closedsource.psymed.platform.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource resource) {
        return new SignInCommand(resource.username(), resource.password());
    }
}
