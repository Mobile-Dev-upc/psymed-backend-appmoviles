package com.closedsource.psymed.platform.profiles.interfaces.rest;

import com.closedsource.psymed.platform.profiles.domain.model.queries.GetProfessionalProfileByAccountIdQuery;
import com.closedsource.psymed.platform.profiles.domain.model.queries.GetProfessionalProfileByIdQuery;
import com.closedsource.psymed.platform.profiles.domain.model.valueobjects.AccountId;
import com.closedsource.psymed.platform.profiles.domain.services.ProfessionalProfileCommandService;
import com.closedsource.psymed.platform.profiles.domain.services.ProfessionalProfileQueryService;
import com.closedsource.psymed.platform.profiles.interfaces.rest.resources.CreateProfessionalProfileResource;
import com.closedsource.psymed.platform.profiles.interfaces.rest.resources.ProfileResource;
import com.closedsource.psymed.platform.profiles.interfaces.rest.transform.CreateProfessionalProfileCommandFromResourceAssembler;
import com.closedsource.psymed.platform.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/professional-profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Professional Profile", description = "Professional Profiles Management Endpoints")
public class ProfessionalProfileController {
    private final ProfessionalProfileCommandService professionalProfileCommandService;
    private final ProfessionalProfileQueryService professionalProfileQueryService;

    public ProfessionalProfileController(ProfessionalProfileCommandService professionalProfileCommandService,
                                         ProfessionalProfileQueryService professionalProfileQueryService) {
        this.professionalProfileCommandService = professionalProfileCommandService;
        this.professionalProfileQueryService = professionalProfileQueryService;
    }

    @PostMapping
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfessionalProfileResource resource) {
        var createProfessionalProfileCommand = CreateProfessionalProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var profile = professionalProfileCommandService.handle(createProfessionalProfileCommand);

        if(profile.isEmpty()) return ResponseEntity.badRequest().build();

        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId) {
        var getProfileByIdQuery = new GetProfessionalProfileByIdQuery(profileId);
        var profile = professionalProfileQueryService.handle(getProfileByIdQuery);
        if(profile.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }




    @Operation(summary = "Get Professional Profile by Account ID")
    @GetMapping("/account/{accountId}")
    public ResponseEntity<ProfileResource> getProfileByAccountId(@PathVariable Long accountId) {
        var getProfessionalProfileByAccountIdQuery = new GetProfessionalProfileByAccountIdQuery(new AccountId(accountId));
        var profile = professionalProfileQueryService.handle(getProfessionalProfileByAccountIdQuery);
        if(profile.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

}
