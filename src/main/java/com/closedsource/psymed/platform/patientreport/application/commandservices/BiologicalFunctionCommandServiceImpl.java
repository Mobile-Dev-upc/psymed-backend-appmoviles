package com.closedsource.psymed.platform.patientreport.application.commandservices;

import com.closedsource.psymed.platform.patientreport.application.outboundservices.ExternalProfileService;
import com.closedsource.psymed.platform.patientreport.application.outboundservices.acl.ExternalProfileServiceImpl;
import com.closedsource.psymed.platform.patientreport.domain.exceptions.PatientNotFoundException;
import com.closedsource.psymed.platform.patientreport.domain.model.aggregates.BiologicalFunction;
import com.closedsource.psymed.platform.patientreport.domain.model.commands.CreateBiologicalFunctionRecordCommand;
import com.closedsource.psymed.platform.patientreport.domain.services.BiologicalFunctionCommandService;
import com.closedsource.psymed.platform.patientreport.infrastructure.persistence.jpa.repositories.BiologicalFunctionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BiologicalFunctionCommandServiceImpl implements BiologicalFunctionCommandService {
    private final BiologicalFunctionRepository biologicalFunctionRepository;
    private final ExternalProfileService externalProfileService;

    public BiologicalFunctionCommandServiceImpl(BiologicalFunctionRepository biologicalFunctionRepository,
                                                ExternalProfileServiceImpl externalProfileService){
        this.biologicalFunctionRepository = biologicalFunctionRepository;
        this.externalProfileService = externalProfileService;

    }

    @Override
    public Optional<BiologicalFunction> handle(CreateBiologicalFunctionRecordCommand command) {
        var patientIdExists = externalProfileService.existsPatientById(command.patientId());

        if(patientIdExists) {
            var biologicalFunctionRecord = new BiologicalFunction(command);
            var lastBiologicalFunctionRecord = biologicalFunctionRepository
                    .findAllByPatientId(biologicalFunctionRecord.getPatientId())
                    .stream().reduce((first, second) -> second).orElse(null);

            if(lastBiologicalFunctionRecord != null)
                biologicalFunctionRecord.validateRecordAvailability(lastBiologicalFunctionRecord);

            try {
                var createdBiologicalFunctionRecord = biologicalFunctionRepository.save(biologicalFunctionRecord);
                return Optional.of(createdBiologicalFunctionRecord);
            }catch(Exception e){
                throw new IllegalArgumentException("Error creating the biological function record: %s"
                        .formatted(e.getMessage()));
            }
        }
        throw new PatientNotFoundException(command.patientId());
    }
}
